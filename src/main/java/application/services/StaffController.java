package application.services;

import application.model.Staff.Person;
import application.model.Staff.Persons;
import application.model.document.Document;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.TreeSet;


@Path("/employees")
public class StaffController {
    TreeSet<Document> document;
    FileService fileService;
    PostgresqlDBManager postgresqlDBManager;
    Collection<Person> personCollection;

    public StaffController() {
        try {
            fileService = new FileService();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        postgresqlDBManager = new PostgresqlDBManager("console_application");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/get")
    public Persons getJSONPersons() throws SQLException {
        Persons persons = postgresqlDBManager.getPersonsFromDB();
        return persons;
    }

    @GET
    @Produces(MediaType.APPLICATION_XML + ";charset=UTF-8")
    @Path("/get/{id}")
    public TreeSet<Document> getXMLDocuments(@PathParam("id") int id) {
        document = fileService.findDocId(fileService.allDoc, id);
        return document;
    }


}
