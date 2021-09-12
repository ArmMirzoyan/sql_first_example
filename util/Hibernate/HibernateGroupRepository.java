package com.example.tomcattest.util.Hibernate;

import com.example.tomcattest.util.Hibernate.DataAccessObject.HibernateGroupRepo;
import com.example.tomcattest.model.Group;
import org.hibernate.Session;

public class HibernateGroupRepository {
    static HibernateGroupRepo hibernateGroupRepo = new HibernateGroupRepo();


    public static void add(Group group) {
        hibernateGroupRepo.add(group);
    }

    public static void removeById(int id) {
        hibernateGroupRepo.deleteById(id);
    }

    public static Group getById(int id) {
        return hibernateGroupRepo.getById(id);
    }


    public static void clear() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.createQuery("delete  from Group").executeUpdate();
        session.close();
    }

}
