package cn.bayllech.test;


import cn.bayllech.pojo.User;
import cn.bayllech.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

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
        user.setUsername("hibernate");
        user.setPassword("666");

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
    }
}
