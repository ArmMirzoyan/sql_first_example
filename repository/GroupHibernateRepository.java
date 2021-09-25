package com.example.tomcattest.repository;

import com.example.tomcattest.model.Group;
import com.example.tomcattest.repository.DataAccessObject.GroupHibernateRepo;
import com.example.tomcattest.repository.config.ApplicationContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GroupHibernateRepository {
    SessionFactory sessionFactory = ApplicationContext.context.getBean("getSessionFactory", SessionFactory.class);
    public static GroupHibernateRepo groupHibernateRepo = new GroupHibernateRepo();

    private GroupHibernateRepository() {
    }

    public static void add(Group group) {
        groupHibernateRepo.add(group);
    }

    public static void removeById(int id) {
        groupHibernateRepo.deleteById(id);
    }

    public static Group getById(int id) {
        return groupHibernateRepo.getById(id);
    }

    public static List<Group> getAll() {
        return groupHibernateRepo.getAll();
    }

    public static void clear() {
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        SessionFactory sessionFactory = ApplicationContext.context.getBean("getSessionFactory", SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("delete  from Group").executeUpdate();
        session.close();
    }
}
