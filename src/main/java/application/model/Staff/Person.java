package application.model.Staff;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "person")
public class Person extends Staff implements Comparable {

    private String surname;     //поле фамилия
    private String name;        //поле имя
    private String secondName;  //поле отчество
    private String position;    //поле должность
    private String dateOfBirth; //дата рождения
    private int telephone;   //номер телефона



    public String getSurname(){
        return this.surname;
    }
    @XmlElement
    public void setSurname(String surname){
        this.surname=surname;
    }
    public String getName(){
        return this.name;
    }
    @XmlElement
    public void setName(String name){
        this.name=name;
    }
    public String getSecondName(){
        return this.secondName;
    }
    @XmlElement
    public void setSecondName(String secondName){
        this.secondName=secondName;
    }
    public String getPosition(){
        return this.position;
    }
    @XmlElement
    public void setPosition(String position){
        this.position=position;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
    @XmlElement
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getTelephone() {
        return telephone;
    }
    @XmlElement
    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public int compareTo(Object obj) {
        Person entry = (Person) obj;
        int result = this.getSurname().compareTo(entry.getSurname());
        if (result != 0) {
            return result;
        }
        result = this.getName().compareTo(entry.getName());
        if (result != 0) {
            return result;
        }
        result = this.getSecondName().compareTo(entry.getSecondName());
        if (result != 0) {
            return result;
        }
        return 0;
    }
}
