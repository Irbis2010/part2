package model.document;

import model.Staff.Person;

import java.text.SimpleDateFormat;

public class Outgoing extends Document {

    private Person destination;     //адресат
    private String deliveryMethod;  //способ доставки


    public Person getDestination() {
        return destination;
    }

    public void setDestination(Person destination) {
        this.destination = destination;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    @Override
    public String toString() {

        String str ="\n"+ "Идентификатор документа: "+this.getId()+"\nНазвание документа:"+this.getNameDoc()+
                    "\nТекст документа: "+ this.getText()+"\nРегистрационный номер документа: "+this.getRegisterNumOfDoc()+
                    "\nДата регистрации документа: "+ this.getDateOfRegistration()+"\nАвтор: "+this.getAuthor().getSurname() +
                    " "+ this.getAuthor().getName()+" "+this.getAuthor().getSecondName()+
                    "\nАдресат: "+destination.getSurname() + " " + destination.getName() + " " +
                    destination.getSecondName()+ "\nСпособ доставки: "+deliveryMethod;

        return str;
    }

}
