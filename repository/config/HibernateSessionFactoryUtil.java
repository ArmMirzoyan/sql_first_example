package com.example.tomcattest.repository.config;

import com.example.tomcattest.model.Group;
import com.example.tomcattest.model.Item;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {

    }

    @Bean
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Item.class);
                configuration.addAnnotatedClass(Group.class);
                configuration.addAnnotatedClass(Configuration.class);
//                configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
//                configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
//                configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres");
//                configuration.setProperty("hibernate.connection.username", "postgres");
//                configuration.setProperty("hibernate.connection.password", "12345");
//                configuration.setProperty("hibernate.show_sql.password", "true");
//                configuration.setProperty("hibernate.format_sql", "true");
//                configuration.setProperty("hibernate.show_sql", "true");
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Attention, Exception!" + e);
            }
        }
        return sessionFactory;
    }
}
