package application.model.Staff;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;

@XmlType(name = "organization")
public class Organization extends Staff {
    private String orgName;                   //полное наименование;
    private String shortOrgName;              //краткое наименование;
    private String orgDirector;               //руководитель;
    private ArrayList<Integer> orgTelNumbers; //контактные телефоны;

    public String getOrgName() {
        return this.orgName;
    }

    @XmlElement
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getShortOrgName() {
        return this.shortOrgName;
    }

    @XmlElement
    public void setShortOrgName(String shortOrgName) {
        this.shortOrgName = shortOrgName;
    }

    public String getOrgDirector() {
        return this.orgDirector;
    }

    @XmlElement
    public void setOrgDirector(String orgDirector) {
        this.orgDirector = orgDirector;
    }

    public ArrayList<Integer> getOrgTelNumbers() {
        return this.orgTelNumbers;
    }

    @XmlElement(name = "orgTelNumbers")
    @XmlElementWrapper
    public void setOrgTelNumbers(ArrayList<Integer> orgTelNumbers) {
        this.orgTelNumbers = orgTelNumbers;
    }

    public Organization createDep(int id, String orgName, String shortOrgName, String orgDirector,
                                  ArrayList<Integer> orgTelNumbers) {
        Organization organization = new Organization();
        organization.setId(id);
        organization.setOrgName(orgName);
        organization.setShortOrgName(shortOrgName);
        organization.setOrgDirector(orgDirector);
        organization.setOrgTelNumbers(orgTelNumbers);
        return organization;
    }
}