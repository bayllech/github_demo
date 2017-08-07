package cn.bayllech.test;

import cn.bayllech.pojo.User;
import cn.bayllech.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

public class HibernateHQLTest {
    @Test
    public void whereTest() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        String hql = "from User as u where u.username = '李四'";
        Query query = session.createQuery(hql);
        List<User> users = query.list();
        for (User user : users) {
            System.out.println(user);
        }

        session.getTransaction().commit();
    }

    @Test
    public void selectTest() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        String hql = "select username,password from User";
        Query query = session.createQuery(hql);
        List<Object[]> list = query.list();
        for (Object[] obj : list) {
            System.out.println(obj[0] + "-->" +obj[1]);
        }

        session.getTransaction().commit();
    }

    @Test
    public  void countAndMax() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        String hql = "select count(*),max(id) from User";
        Query query = session.createQuery(hql);
        List<Object[]> list = query.list();
        for (Object[] array:list){
            System.out.println("count: " + array[0] + " max: " + array[1]);
        }

        session.getTransaction().commit();
    }

    @Test
    public void placeholder() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
//        String hql = "from User where username = ?";
        String hql = "from User where username = :name";
        Query query = session.createQuery(hql);
//        query.setParameter(0, "hibernate");
        query.setParameter("name", "hibernate");
        List<User> userList = query.list();
        for (User user : userList) {
            System.out.println(user);
        }

        session.getTransaction().commit();
    }

    @Test
    public void limit() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        String hql = "from User";
        Query query = session.createQuery(hql);
        query.setFirstResult(0);
        query.setMaxResults(3);
        List<User> userList = query.list();
        for (User user : userList) {
            System.out.println(user);
        }

        session.getTransaction().commit();
    }
}
