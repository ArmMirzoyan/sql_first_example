package com.example.tomcattest.repository.DataAccessObject;

import com.example.tomcattest.model.Group;
import com.example.tomcattest.repository.config.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class GroupHibernateRepo {

    public void add(Group group) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(group);
            transaction.commit();
        }
    }

    public Group getById(long id) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Group group = session.get(Group.class, id);
            transaction.commit();
            return group;
        }
    }

    public void deleteById(long id) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Group group = session.get(Group.class, id);
            session.remove(group);
            transaction.commit();
        }
    }

    public List<Group> getAll() {
        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Group> allGroupsItems = session.createQuery("SELECT g  FROM Group g", Group.class)
                    .getResultList();
            transaction.commit();
            return allGroupsItems;
        }
    }
}