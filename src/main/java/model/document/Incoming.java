package model.document;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Incoming extends Document{

    private String sender;                      //отправитель;
    private String destination;                 //адресат;
    private int incomeNumber;                   //исходящий номер;
    private Date incomeRegistrationDate;        //исходящая дата регистрации.



    @Override
    public String getTable() {
        return null;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getIncomeNumber() {
        return incomeNumber;
    }

    public void setIncomeNumber(int incomeNumber) {
        this.incomeNumber = incomeNumber;
    }

    public Date getIncomeRegistrationDate() {
        return incomeRegistrationDate;
    }

    public void setIncomeRegistrationDate(Date incomeRegistrationDate) {
        this.incomeRegistrationDate = incomeRegistrationDate;
    }

    @Override
    public String toString() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        String str =getAuthor() + " Id:"+  getId() + " Task" + " "  + dateFormat.format(getDateOfRegistration())
                    + " " + getRegistrationNumber() +" \"" + getText() +"\"";
        return str;
    }
}
