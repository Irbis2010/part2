package application.model.Staff;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;
@XmlType(name = "Organizations")
@XmlRootElement
public class Organizations {
    @XmlElementWrapper(name="organizationList", nillable = true)
    public List<Organization> organization = new ArrayList<Organization>();
}
