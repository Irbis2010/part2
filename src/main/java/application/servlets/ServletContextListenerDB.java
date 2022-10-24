package application.servlets;

import application.services.FileService;
import application.services.PostgresqlDBManager;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebListener
public class ServletContextListenerDB implements ServletContextListener {
    ServletContext context;
    @Override
    public void contextInitialized(ServletContextEvent contextEvent) {

        FileService fileService = null;
        try {
            fileService = new FileService();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        PostgresqlDBManager postgresqlDBManager=new PostgresqlDBManager("console_application");

        context = contextEvent.getServletContext();

        fileService.readFiles();
        try {
            fileService = new FileService();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            try {
                // запись данных в таблицу
                postgresqlDBManager.executeUpdatePersons(fileService.getPersons());
            } catch (SQLException e) {
                // если БД не существовала, то создаем таблицу и записываем данные
                postgresqlDBManager.createPersonTable();
                postgresqlDBManager.executeUpdatePersons(fileService.getPersons());
            }

            try {
                // запись данных в таблицу
                postgresqlDBManager.executeUpdateOrganizations(fileService.getOrganizations());
            } catch (SQLException e) {
                // если БД не существовала или нет таблицы, то создаем таблицу
                // и после этого заполняем её значениями
                postgresqlDBManager.createOrganizationTables();
                //запись данных из созданных таблиц
                postgresqlDBManager.executeUpdateOrganizations(fileService.getOrganizations());
            }

            try {
                // запись данных в таблицу
                postgresqlDBManager.executeUpdateDepartments(fileService.getDepartments());
            } catch (SQLException e) {
                // если БД не существовала, то создаем таблицу
                // и после этого заполняем её значениями
                postgresqlDBManager.createDepartmentTables();
                //запись данных из созданных таблиц
                postgresqlDBManager.executeUpdateDepartments(fileService.getDepartments());
            }

        } catch (SQLException e) {
            Logger.getLogger(FileService.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent contextEvent) {
        context = contextEvent.getServletContext();

    }
}
