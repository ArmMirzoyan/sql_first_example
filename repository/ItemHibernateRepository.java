package com.example.tomcattest.repository;

import com.example.tomcattest.model.Item;
import com.example.tomcattest.repository.DataAccessObject.ItemHibernateRepo;
import com.example.tomcattest.repository.config.ApplicationContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemHibernateRepository {
    private static final ItemHibernateRepo hibernateItemRepo = new ItemHibernateRepo();

    private ItemHibernateRepository() {
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

    public static List<Item> getAll() {
        return hibernateItemRepo.getAllItems();
    }

    public static void clear( ) {
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        SessionFactory sessionFactory = ApplicationContext.context.getBean("getSessionFactory",SessionFactory.class);
        Session session =sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("delete  from Item").executeUpdate();
        session.close();
    }
}
