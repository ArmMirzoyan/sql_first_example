package com.example.tomcattest.util.Hibernate;

import com.example.tomcattest.util.Hibernate.DataAccessObject.HibernateItemRepo;
import com.example.tomcattest.model.Item;
import org.hibernate.Session;

public class HibernateItemRepository {
    private static final HibernateItemRepo hibernateItemRepo = new HibernateItemRepo();

    private HibernateItemRepository() {
    }

    public static void save(Item item) {
        hibernateItemRepo.add(item);
    }


    public static void deleteById(int id) {
        hibernateItemRepo.deleteById(id);
    }

    public static Item getById(int id) {
        return hibernateItemRepo.getById(id);
    }

    public static void updateById(Item item) {
        hibernateItemRepo.updateById(item);
    }

    public static void clear( ) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.createQuery("delete  from Item").executeUpdate();
        session.close();
    }
}
