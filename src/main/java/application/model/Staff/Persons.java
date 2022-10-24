package application.model.Staff;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlType(name = "Persons")
@XmlRootElement
public class Persons {
    @XmlElementWrapper(name = "personList", nillable = true)
    public List<Person> person = new ArrayList<Person>();

}
