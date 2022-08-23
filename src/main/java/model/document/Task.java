package model.document;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task extends Document {
    private Date dateOfIssueOrder;        //дата выдачи поручения;
    private Date  termOfExecutionOrder;   //срок исполнения поручения; - до такой то даты
    private String executorName;          // ответственный исполнитель;
    private boolean control;              // признак контрольности;
    private String controllerName;        // контролер поручения.



    @Override
    public String getTable() {
        return null;
    }

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

    public String getExecutorName() {
        return executorName;
    }

    public void setExecutorName(String executorName) {
        this.executorName = executorName;
    }

    public boolean isControl() {
        return control;
    }

    public void setControl(boolean control) {
        this.control = control;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    @Override
    public String toString() {


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        String str =getAuthor() + " Id:" +  getId() + " Task" + " "  + dateFormat.format(getDateOfRegistration())
                    + " " + getRegistrationNumber() +" \"" + getText() +"\"";
        return str;
    }
}
