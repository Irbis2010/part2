package application.services.factory;

import application.model.document.Document;

public interface FoundationDocumentFactory {
    Document createDocument(String type);

}
