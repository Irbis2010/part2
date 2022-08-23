package service.factory;

import model.document.Document;

public interface FoundationDocumentFactory {
    Document createDocument(String type);

}
