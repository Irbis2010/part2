package application;

import application.services.FileService;
import application.services.PostgresqlDBManager;
import application.servlets.ServletContextListenerDB;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URISyntaxException;


import application.model.Staff.*;
import application.services.DocService;
import application.model.document.*;


import java.io.FileWriter;
import java.io.IOException;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, URISyntaxException {

        //1. сохраняем данные из файлов(пока только персонал )

        //Создаем hashmap для хранения классов и названий файлов
        Map<String, Class> staffMap = new HashMap<>();
        staffMap.put("persons.xml", Persons.class);
        staffMap.put("departments.xml", Departments.class);
        staffMap.put("organizations.xml", Organizations.class);
        FileService fileService = new FileService();
        Persons persons = fileService.getPersons();
        //создаем экземпляр DocService
        DocService docService = new DocService();
        //сохраняем person в docFieldStorage
        docService.savePersons(persons);
        //доп масссив для случайной генерации одного из документов
        Class[] classDoc = new Class[3];
        classDoc[0] = Task.class;
        classDoc[1] = Outgoing.class;
        classDoc[2] = Incoming.class;
        //создаем TreeSet для хранения документов
        TreeSet<Document> allDoc = new TreeSet<Document>();
        int p;//переменная для хранения случайного значения
        //создаем документы
        for (int i = 0; i < 30; i++) {
            p = (int) (Math.random() * 3);
            Document doc = docService.createDoc(classDoc[p]);
            if (doc != null) {
                allDoc.add(doc);
            }
        }
        Map<Person, TreeSet<Document>> docsByPersonMap = new TreeMap<Person, TreeSet<Document>>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        //сортируем документы по авторам
        for (Document d : allDoc) {
            if (!docsByPersonMap.containsKey(d.getAuthor())) {
                docsByPersonMap.put(d.getAuthor(), new TreeSet<Document>());
            }
            docsByPersonMap.get(d.getAuthor()).add(d);
        }
        //генерируем отчеты по каждому автору в json файлы
        GsonBuilder builder = new GsonBuilder();
        Gson gson = new Gson();
        String authorStr;
        String stringInJSON;
        String dirName = "F:\\Java\\practice\\";
        String extension = ".json";
        // сортировка документов по автору
        for (Person author : docsByPersonMap.keySet()) {
            //очищаем строку
            stringInJSON = "";
            //сохраняем имя автора в переменную
            authorStr = author.getSurname() + " " + author.getName() + " " + author.getSecondName();
            //добавляем документы в формате json в строку
            for (Document d : docsByPersonMap.get(author)) {
                stringInJSON = stringInJSON + gson.toJson(d);
            }
            try (FileWriter fileWriter = new FileWriter(dirName + authorStr + extension)) {
                fileWriter.write(stringInJSON);
            } catch (IOException ex) {
                Logger.getLogger(Test.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
        //очищаем строку
        stringInJSON = "";
        for (Person pers : docService.getDocFieldsStorage().getPersonDocStorage().values()) {
            //добавляем сотрудников в формате json в строку
            stringInJSON = stringInJSON + gson.toJson(pers);
        }
        try (FileWriter fileWriter = new FileWriter(dirName + "Persons" + extension)) {
            fileWriter.write(stringInJSON);
        } catch (IOException ex) {
            Logger.getLogger(Test.class.getName())
                    .log(Level.SEVERE, null, ex);
        }


        PostgresqlDBManager postgresqlDBManager = new PostgresqlDBManager("console_application");


        //  fileService.readFiles();
        try {
            fileService = new FileService();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            try {
                // запись данных в таблицу
                postgresqlDBManager.executeUpdatePersons(fileService.getPersons());
            } catch (SQLException e) {
                // если БД не существовала, то создаем таблицу и записываем данные
                postgresqlDBManager.createPersonTable();
                postgresqlDBManager.executeUpdatePersons(fileService.getPersons());
            }

            try {
                // запись данных в таблицу
                postgresqlDBManager.executeUpdateOrganizations(fileService.getOrganizations());
            } catch (SQLException e) {
                // если БД не существовала или нет таблицы, то создаем таблицу
                // и после этого заполняем её значениями
                postgresqlDBManager.createOrganizationTables();
                //запись данных из созданных таблиц
                postgresqlDBManager.executeUpdateOrganizations(fileService.getOrganizations());
            }

            try {
                // запись данных в таблицу
                postgresqlDBManager.executeUpdateDepartments(fileService.getDepartments());
            } catch (SQLException e) {
                // если БД не существовала, то создаем таблицу
                // и после этого заполняем её значениями
                postgresqlDBManager.createDepartmentTables();
                //запись данных из созданных таблиц
                postgresqlDBManager.executeUpdateDepartments(fileService.getDepartments());
            }

        } catch (SQLException e) {
            Logger.getLogger(FileService.class.getName()).log(Level.SEVERE, null, e);
        }

        postgresqlDBManager.getPersonsFromDB();
        for (Person text : postgresqlDBManager.getPersonsFromDB().person) {
            int id = text.getId();
            String name = text.getName();
            String secondName = text.getSecondName();
            String surName = text.getSurname();
            String position = text.getPosition();
            String dateOfBirth = text.getDateOfBirth();
            int telephone = text.getTelephone();

            System.out.println(id + " " + name + " " + secondName + " " + surName + " " + position + " " + dateOfBirth + " " + telephone );
        }


    }

}





