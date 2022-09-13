package model.Staff;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlType(name = "Departments")
@XmlRootElement
public class Departments {
    @XmlElementWrapper(name="departments", nillable = true)
    public List<Department> department = new ArrayList<Department>();
}
