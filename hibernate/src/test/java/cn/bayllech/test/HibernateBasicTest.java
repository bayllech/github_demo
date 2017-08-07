package cn.bayllech.test;


import cn.bayllech.pojo.User;
import cn.bayllech.util.HibernateUtil;
import org.hibernate.Cache;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

import java.util.List;

public class HibernateBasicTest {
    @Test
    public void hibernateBuilder() {
        Configuration configuration = new Configuration().configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        Session session = sessionFactory.getCurrentSession();
        session.getTransaction().begin();

        User user = new User();
        user.setUsername("hbm");
        user.setPassword("777");

        session.save(user);

        session.getTransaction().commit();
    }

    @Test
    public void findById() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        User user = (User) session.get(User.class, 1025);
        System.out.println(user);
        session.getTransaction().commit();

        Cache cache = HibernateUtil.getSessionFactory().getCache();
        System.out.println(cache.containsEntity(User.class, 1025));
//        cache.evictEntityRegion(User.class);

        Session session2 = HibernateUtil.getSession();
        session2.beginTransaction();

        User user2 = (User) session2.get(User.class, 1025);
        System.out.println(user2);

        session2.getTransaction().commit();
    }

    @Test
    public void updateUser() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        User user = (User) session.get(User.class, 1025);
        user.setMobile("123123231");

        session.getTransaction().commit();
    }

    @Test
    public void findAll() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Query query = session.createQuery("from User");
        List<User> userList = query.list();
        for (User user : userList) {
            System.out.println(user);
        }

        session.getTransaction().commit();
    }

    @Test
    public void getAndLoad() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
//        User user = (User) session.get(User.class, 1333);
        User user = (User) session.load(User.class, 1034);
//        System.out.println(user);

        session.getTransaction().commit();
    }

    @Test
    public void saveAndUpdate() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        User user = (User) session.get(User.class, 1025);
        session.getTransaction().commit();

        Session session2 = HibernateUtil.getSession();
        session2.beginTransaction();
        user.setMobile("999");
        session2.update(user);

        session2.getTransaction().commit();
    }

    @Test
    public void saveAndUpdateAndMerge() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        User user = new User();
        user.setId(1010);
        user.setUsername("李四");

        User user1 = (User) session.get(User.class, 1010);
        System.out.println(user1);
//        session.saveOrUpdate(user);
        session.merge(user);
        session.getTransaction().commit();
    }
}
