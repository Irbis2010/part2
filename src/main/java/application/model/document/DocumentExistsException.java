package application.model.document;

public class DocumentExistsException extends Exception {


    public DocumentExistsException(String msgText){
        super(msgText);
    }
}
