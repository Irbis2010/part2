package model.document;

import model.Storable;

import java.util.Date;

public abstract class Document implements Comparable, Storable {

    private int id;                         //	идентификатор документа;
    private String text;                    // текст документа;
    private String name;                    // название документа;
    private String registrationNumber;      //регистрационный номер документа;
    private Date dateOfRegistration;        //дата регистрации документа;
    private String author;                  //автор документа.


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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public int compareTo(Object obj) {
        Document incoming = (Document) obj;
        int result = author.compareTo(incoming.author);
        if (result != 0) {
            return result;
        }

        result = dateOfRegistration.compareTo(incoming.dateOfRegistration);
        if (result != 0) {
            return result;
        }

        result = registrationNumber.compareTo(incoming.registrationNumber);
        if (result != 0) {
            return result;
        }

        return 0;
    }
}
