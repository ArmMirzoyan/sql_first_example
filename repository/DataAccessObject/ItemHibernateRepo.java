package com.example.tomcattest.repository.DataAccessObject;

import com.example.tomcattest.model.Item;
import com.example.tomcattest.repository.config.ApplicationContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;


public class ItemHibernateRepo {


    public void add(Item item) {
//        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        SessionFactory sessionFactory = ApplicationContext.context.getBean("getSessionFactory",SessionFactory.class);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(item);
            transaction.commit();
        }
    }

    public Item getById(long id) {
//        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        SessionFactory sessionFactory = ApplicationContext.context.getBean("getSessionFactory",SessionFactory.class);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Item item = session.get(Item.class, id);
            transaction.commit();
            return item;
        }

    }

    public void deleteById(long id) {
//        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        SessionFactory sessionFactory = ApplicationContext.context.getBean("getSessionfactory",SessionFactory.class);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Item item = session.get(Item.class, id);
            session.remove(item);
            transaction.commit();
        }
    }

    public void updateById(Item item) {
//        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        SessionFactory sessionFactory = ApplicationContext.context.getBean("getSessionFactory",SessionFactory.class);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(item);
            transaction.commit();
        }
    }

    public List<Item> getAllItems() {
//        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        SessionFactory sessionFactory = ApplicationContext.context.getBean("getSessionFactory",SessionFactory.class);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Item> allItems = session.createQuery("SELECT a FROM Item a", Item.class)
                    .getResultList();
            transaction.commit();
            return allItems;
        }
    }
}
