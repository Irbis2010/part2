package application.services;

import application.DocFieldsStorage;
import application.model.Staff.Departments;
import application.model.Staff.Organizations;
import application.model.Staff.Person;
import application.model.Staff.Persons;
import application.model.document.Document;
import application.model.document.DocumentExistsException;
import application.services.factory.DocumentFactory;
import com.google.gson.Gson;

import java.io.File;
import java.util.HashSet;

public class DocService {

    DocumentFactory documentFactory;

    HashSet<String> regNumbers;
    // переменная для хранения Persons
    Persons persons;
    // переменная для хранения Departments
    Departments departments;
    // переменная для хранения Organizations
    Organizations organizations;
    //переменная для хранения объектов
    Object obj;
    // переменная для хранения файлов
    File file;
    DocFieldsStorage docFieldsStorage;
    Gson gson;
    String string;

    public DocService(){
        regNumbers = new HashSet<String>();
        documentFactory = new DocumentFactory();
        docFieldsStorage = new DocFieldsStorage();


    }

    public DocFieldsStorage getDocFieldsStorage() {
        return docFieldsStorage;
    }

    public void setDocFieldsStorage(DocFieldsStorage docFieldsStorage) {
        this.docFieldsStorage = docFieldsStorage;
    }

    public void regDoc(Document doc) throws DocumentExistsException {

        String regNom = docFieldsStorage.getRegisterNumOfDoc();
        if (regNumbers.contains(regNom)){
            throw new DocumentExistsException("Exception! Document with this number already exists!");
        } else{
            doc.setRegisterNumOfDoc(regNom);//добавляем документу рег номер
            regNumbers.add(regNom);// добавляем рег номер в коллекцию уже существующих рег номеров
            doc.setDateOfRegistration(docFieldsStorage.getDate());//задаем дату
        }
    }

    public Document createDoc(Class aClass) {
        Document doc = documentFactory.createDocument(aClass);
        docFieldsStorage.saveDocField(doc);
        try {
            regDoc(doc);
            return doc;
        } catch (DocumentExistsException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void savePersons(Persons persons){
        int i=0;
        for (Person person: persons.person) {
            docFieldsStorage.addPersonToDocStorage(i, person);
            i++;
        }
    }
}




