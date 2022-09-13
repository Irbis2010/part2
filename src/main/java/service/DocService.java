package service;

import model.Staff.Person;
import model.Staff.Persons;
import model.document.Document;
import model.document.DocumentExistsException;
import service.factory.DocumentFactory;

import java.util.HashSet;

public class DocService {

    DocumentFactory documentFactory;
    DocFieldsStorage docFieldsStorage;
    HashSet<String> regNumbers;

    public DocService(){
        regNumbers = new HashSet<String>();
        documentFactory = new DocumentFactory();
        docFieldsStorage = new DocFieldsStorage();
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

    public void savePersons(Persons persons){
        int i=0;
        for (Person person: persons.persons) {
            docFieldsStorage.getPerson(i, person);
            i++;
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
}


