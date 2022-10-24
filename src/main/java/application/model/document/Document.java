package application.model.document;

import application.model.Staff.Person;
import application.model.Storable;

import java.util.Date;

public abstract class Document implements Comparable, Storable {

    private int id;                         //	идентификатор документа;
    private String text;                    // текст документа;
    private String name;                    // название документа;
    private String registrationNumber;      // регистрационный номер документа;
    private Date dateOfRegistration;        // дата регистрации документа;
    private Person author;                  // автор документа.


    @Override
    public String getTable() {
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameDoc() {
        return name;
    }

    public void setNameDoc(String nameDoc) {
        this.name = nameDoc;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRegisterNumOfDoc() {
        return registrationNumber;
    }

    public void setRegisterNumOfDoc(String registerNumOfDoc) {
        this.registrationNumber = registerNumOfDoc;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    @Override
     public int compareTo(Object obj) {
       Document entry = (Document) obj;
       int result = author.getSurname().compareTo(entry.author.getSurname());
        if (result != 0) {
            return result;
      }
        result = author.getName().compareTo(entry.author.getName());
        if (result != 0) {
            return result;
        }
        result = author.getSecondName().compareTo(entry.author.getSecondName());
        if (result != 0) {
            return result;
        }

        result = dateOfRegistration.compareTo(entry.dateOfRegistration);
        if (result != 0) {
            return result;
        }

        result = registrationNumber.compareTo(entry.registrationNumber);
        if (result != 0) {
            return result;
        }

        return 0;

    }
}
