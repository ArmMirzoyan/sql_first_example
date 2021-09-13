package com.example.tomcattest.repository;

import com.example.tomcattest.repository.DataAccessObject.GroupHibernateRepo;
import com.example.tomcattest.model.Group;
import com.example.tomcattest.repository.config.HibernateSessionFactoryUtil;
import org.hibernate.Session;

public class GroupHibernateRepository {
    static GroupHibernateRepo hibernateGroupRepo = new GroupHibernateRepo();


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
