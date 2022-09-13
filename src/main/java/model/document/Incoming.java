package model.document;

import model.Staff.Person;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Incoming extends Document{

    private Person sender;                      //отправитель;
    private Person destination;                 //адресат;
    private int incomeNumber;                   //исходящий номер;
    private Date incomeRegistrationDate;        //исходящая дата регистрации.


    public Person getSender() {
        return sender;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public Person getDestination() {
        return destination;
    }

    public void setDestination(Person destination) {
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
        String str = "\n"+"идентификатор документа: "+this.getId()+"\nНазвание документа: "+this.getNameDoc()+
                     "\nТекст документа: "+ this.getText()+"\nРегистрационный номер документа: "+this.getRegisterNumOfDoc()+
                     "\nДата регистрации документа: "+ this.getDateOfRegistration()+"\nАвтор: "+this.getAuthor().getSurname()+" "+
                     this.getAuthor().getName()+" "+this.getAuthor().getSecondName()+
                     "\nОтправитель: "+sender.getSurname()+" "+ sender.getName()+" "+sender.getSecondName()+
                     "\nАдресат: "+destination.getSurname()+destination.getName() +
                     destination.getSecondName()+ "\nИсходящий номер: "+incomeNumber+
                     "\n" + "Исходящая дата регистрации: "+incomeRegistrationDate;
        return str;
    }



}
