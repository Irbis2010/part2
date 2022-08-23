package service;

import model.document.Document;
import model.document.DocumentExistsException;
import service.factory.DocumentFactory;

import java.util.HashSet;

public class DocService {
    DocumentFactory documentFactory;
    DocFieldsStorage docFieldsStorage;
    HashSet<String> regNumbers;

    public DocService() {
        regNumbers = new HashSet<String>();
        documentFactory = new DocumentFactory();
        docFieldsStorage = new DocFieldsStorage();
    }


    public void regDoc(Document doc) throws DocumentExistsException {

        String regNom = docFieldsStorage.getRegisterNumOfDoc();
        if (regNumbers.contains(regNom)) {
            throw new DocumentExistsException("Exception! Document with this number already exists!");
        } else {
            doc.setRegistrationNumber(regNom);//добавляем документу регистрационный номер
            regNumbers.add(regNom);// добавляем регистрационный номер в коллекцию уже существующих регистрационных номеров
            doc.setDateOfRegistration(docFieldsStorage.getDate());//задаем дату
        }
    }

    public Document createDoc(String type) {
        Document doc = documentFactory.createDocument(type);
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


