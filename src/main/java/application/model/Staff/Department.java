package application.model.Staff;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;

@XmlType(name = "department")
public class Department extends Staff {
    private String depName;                //полное наименование
    private String shortDepName;           //краткое наименование
    private String depDirector;            //руководитель
    private ArrayList<Integer> telNumbers; //контактные телефоны

    public String getDepName() {
        return depName;
    }
    @XmlElement
    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getShortDepName() {
        return shortDepName;
    }
    @XmlElement
    public void setShortDepName(String shortDepName) {
        this.shortDepName = shortDepName;
    }

    public String getDepDirector() {
        return depDirector;
    }
    @XmlElement
    public void setDepDirector(String depDirector) {
        this.depDirector = depDirector;
    }

    public ArrayList<Integer> getTelNumbers() {
        return telNumbers;
    }
    @XmlElement(name = "telNumbers")
    @XmlElementWrapper
    public void setTelNumbers(ArrayList<Integer> telNumbers) {
        this.telNumbers = telNumbers;
    }

    public Department createDep(int id, String depName, String shortDepName, String depDirector,
                                ArrayList<Integer> telNumbers ) {
        Department department = new Department();
        department.setId(id);
        department.setDepName(depName);
        department.setShortDepName(shortDepName);
        department.setDepDirector(depDirector);
        department.setTelNumbers(telNumbers);
        return department;

    }
}
