package model.Staff;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "id" }, name = "staff")
@XmlRootElement(name = "staff")
public abstract class Staff {


    private int id;

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id=id;
    }
}
