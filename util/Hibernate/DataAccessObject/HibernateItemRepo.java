package com.example.tomcattest.util.Hibernate.DataAccessObject;

import com.example.tomcattest.model.Item;
import com.example.tomcattest.util.Hibernate.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;


public class HibernateItemRepo {


    public void add(Item item) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(item);
        transaction.commit();
        session.close();
    }


    public Item getById(long id) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Item item = session.get(Item.class, id);
        transaction.commit();
        session.close();
        return item;
    }

    public void deleteById(long id) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Item item = session.get(Item.class, id);
        session.remove(item);
        transaction.commit();
        session.close();
    }

    public void updateById(Item item) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(item);
        transaction.commit();
        session.close();
    }

    public List<Item> getAllItems() {
        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Item> allItems = session.createQuery("SELECT a FROM Item a", Item.class).getResultList();
        transaction.commit();
        return allItems;

    }
}
