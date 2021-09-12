package com.example.tomcattest.util.Hibernate.DataAccessObject;

import com.example.tomcattest.model.Group;
import com.example.tomcattest.util.Hibernate.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateGroupRepo {

    public void add(Group group) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(group);
        transaction.commit();
        session.close();
    }

    public Group getById(long id) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Group group = session.get(Group.class, id);
        transaction.commit();
        session.close();
        return group;
    }

    public void deleteById(long id) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Group group = session.get(Group.class, id);
        session.remove(group);
        transaction.commit();
        session.close();
    }

    public List<Group> getAll() {
        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Group> allGroupsItems = session.createQuery("SELECT g  FROM Group g", Group.class).getResultList();
        transaction.commit();
        return allGroupsItems;
    }
}