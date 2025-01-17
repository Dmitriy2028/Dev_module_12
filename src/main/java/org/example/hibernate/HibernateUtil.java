package org.example.hibernate;

import lombok.Getter;
import org.example.entities.Client;
import org.example.entities.Planet;
import org.example.propertyReader.PropertyReader;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    @Getter
    private static final HibernateUtil INSTANCE = new HibernateUtil();

    @Getter
    private SessionFactory sessionFactory;

//    static {
//        INSTANCE = new HibernateUtil();
//    }

    private HibernateUtil() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Planet.class)
                .buildSessionFactory();
        flywayMigration(PropertyReader.getConnectionUrlForPostgres(),
                PropertyReader.getUserForPostgres(),
                PropertyReader.getPasswordForPostgres());
    }

    public void close() {
        sessionFactory.close();
    }

    private void flywayMigration(String connectionUrl, String username, String password) {
        Flyway flyway = Flyway.configure().dataSource(connectionUrl, username, password).locations("classpath:/db/migration").load();
        flyway.migrate();
    }

    public static HibernateUtil getINSTANCE() {
        return INSTANCE;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
