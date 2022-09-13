package model.document;

import model.Staff.Person;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task extends Document {
    private Date dateOfIssueOrder;        //дата выдачи поручения;
    private Date termOfExecutionOrder;   //срок исполнения поручения; - до такой то даты
    private Person executorName;          // ответственный исполнитель;
    private boolean control;              // признак контрольности;
    private Person controllerName;        // контролер поручения.


    public Date getDateOfIssueOrder() {
        return dateOfIssueOrder;
    }

    public void setDateOfIssueOrder(Date dateOfIssueOrder) {
        this.dateOfIssueOrder = dateOfIssueOrder;
    }

    public Date getTermOfExecutionOrder() {
        return termOfExecutionOrder;
    }

    public void setTermOfExecutionOrder(Date termOfExecutionOrder) {
        this.termOfExecutionOrder = termOfExecutionOrder;
    }

    public Person getExecutorName() {
        return executorName;
    }

    public void setExecutorName(Person executorName) {
        this.executorName = executorName;
    }

    public boolean isControl() {
        return control;
    }

    public void setControl(boolean control) {
        this.control = control;
    }

    public Person getControllerName() {
        return controllerName;
    }

    public void setControllerName(Person controllerName) {
        this.controllerName = controllerName;
    }

    @Override
    public String toString() {
        String cont;
        if (control) {
            cont = "Контрольный";
        } else {
            cont = "Неконтрольный";
        }
        String str = "\n" + "идентификатор документа:" + this.getId() + "\nНазвание документа:" + this.getNameDoc() + "\nТекст документа:" +
                     this.getText() + "\nРегистрационный номер документа:" + this.getRegisterNumOfDoc() + "\nДата регистрации документа:" +
                     this.getDateOfRegistration() + "\nАвтор:" + this.getAuthor().getSurname() + " " + this.getAuthor().getName() + " " +
                     this.getAuthor().getSecondName() + "\nДата выдачи поручения:" + dateOfIssueOrder + "\nСрок исполнения получения:" + termOfExecutionOrder +
                     "\nОтветственный исполнитель:" + executorName.getSurname() + " " + executorName.getName() +
                     " " + executorName.getSecondName() + "\nПризнак контрольности:" + cont +
                     "\nКонтроллер поручения:" + controllerName.getSurname() + " " + controllerName.getName() + " " + controllerName.getSurname();
        return str;
    }
}