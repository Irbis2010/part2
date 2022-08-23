package model.document;

import java.text.SimpleDateFormat;

public class Outgoing extends Document {

    private String destination;     //адресат
    private String deliveryMethod;  //способ доставки


    @Override
    public String getTable() {
        return null;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
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

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        String str =getAuthor() + " Id:" +  getId() + " Task" + " "  + dateFormat.format(getDateOfRegistration())
                    + " " + getRegistrationNumber() +" \"" + getText() +"\"";
        return str;
    }
}
