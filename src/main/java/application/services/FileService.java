package application.services;


import application.model.Staff.Departments;
import application.model.Staff.Organizations;
import application.model.Staff.Persons;
import application.model.document.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileService {
    private static Connection con = null;

    Departments departments;      // переменная для хранения Departments
    Organizations organizations;  // переменная для хранения Organizations
    Object obj;                   //переменная для хранения объектов
    File file;                    // переменная для хранения файлов
    DocService docService;
    Persons persons;
    TreeSet<Document> allDoc;     //для хранения документов
    TreeSet<Document> idFindDoc;  //для хранения документов с искомым id

    public FileService() throws URISyntaxException {

        this.readFiles();

        //создаем экземпляр DocService
        docService = new DocService();
        //сохраняем person в docFieldStorage
        docService.savePersons(persons);
        //создаем класс PostgresqlDBManager и передаем ему имя бд
        //создаем документы
        allDoc = createDocuments();

    }

    // выгрузка данных оргштатных едениц из xml файла
    public void readFiles() {
        //считываем сохраняем данные из файлов
        //Создаем hashmap для хранения классов и названий файлов
        Map<String, Class> staffMap = new HashMap<>();
        staffMap.put("persons.xml", Persons.class);
        staffMap.put("departments.xml", Departments.class);
        staffMap.put("organizations.xml", Organizations.class);
        //читаем файлы
        String fileName;
        for (Map.Entry entry : staffMap.entrySet()) {
            try {
                fileName = entry.getKey().toString();
                //input.substring(1, input.length() - 1);
                ClassLoader classLoader = getClass().getClassLoader();

                URL url = classLoader.getResource(fileName);
                //записываем выбранный файл
                //  file = new File("C:\\Users\\Zver\\Documents\\practice\\practice\\practice1\\src\\main\\resources\\"+fileName);
                file = new File(url.getPath());
                // создаем образец контекста и передаем Class объекта с которым будем работать
                JAXBContext context = JAXBContext.newInstance((Class) entry.getValue());
                Unmarshaller unmarshaller = context.createUnmarshaller();
                // сохраняем данные в объект
                obj = (Object) unmarshaller.unmarshal(file);

            } catch (JAXBException ex) {
                Logger.getLogger(FileService.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
            //Сохраняем объект в зависимости от его класса
            if (obj instanceof Persons) {
                persons = (Persons) obj;
            } else if (obj instanceof Departments) {
                departments = (Departments) obj;
            } else if (obj instanceof Organizations) {
                organizations = (Organizations) obj;
            }

        }

    }

    //ищем документ по id
    public TreeSet<Document> findDocId(TreeSet<Document> allDoc, int findId) {
        Map<Integer, TreeSet<Document>> docsByPersonMap = new TreeMap<Integer, TreeSet<Document>>();

        //сортируем документ по Id автора
        for (Document d : allDoc) {
            if (!docsByPersonMap.containsKey(d.getAuthor().getId())) {
                docsByPersonMap.put(d.getAuthor().getId(), new TreeSet<Document>());
            }
            docsByPersonMap.get(d.getAuthor().getId()).add(d);
        }

        // Ищем документы по  искомому id и записываем их в treeSet idFindDoc
        for (Integer id : docsByPersonMap.keySet()) {
            if (id == findId) {
                idFindDoc = docsByPersonMap.get(id);
            }
        }
        //если документов в treSet не записалось,
        // т.е. не найдены документы с автром с искомым id тогда возвращаем null
        if (idFindDoc.isEmpty()) {
            return null;
            //если документы найдены , возвращаем коллекцию
        } else return idFindDoc;
    }

    //создание документов
    public TreeSet<Document> createDocuments() {
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
        //возвращаем TreeSet с документами
        return allDoc;
    }

    public void fillDB(PostgresqlDBManager db, Object obj) throws SQLException {

        if (obj instanceof Persons) db.executeUpdatePersons(persons);
        if (obj instanceof Departments) db.executeUpdateDepartments(departments);
        if (obj instanceof Organizations) db.executeUpdateOrganizations(organizations);
    }

    //геттеры используются для servletContextExample
    public Persons getPersons() {
        return this.persons;
    }

    public Departments getDepartments() {
        return this.departments;
    }

    public Organizations getOrganizations() {
        return this.organizations;
    }


}

