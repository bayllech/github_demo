package java.cn.bayllech.test;

import cn.bayllech.pojo.User;
import cn.bayllech.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.junit.Test;

import java.util.List;

public class HibernateCriteriaTest {
    @Test
    public void findAll() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        List<User> userList = criteria.list();
        for (User user : userList) {
            System.out.println(user);
        }

        session.getTransaction().commit();
    }

    @Test
    public void where() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("username", "李四"));
        List<User> userList = criteria.list();
        for (User user : userList) {
            System.out.println(user);
        }

        session.getTransaction().commit();
    }

    @Test
    public void like() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.like("username", "i", MatchMode.ANYWHERE));
        List<User> userList = criteria.list();
        for (User user : userList) {
            System.out.println(user);
        }

        session.getTransaction().commit();
    }

    @Test
    public void count() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        criteria.setProjection(Projections.rowCount());
        Object object = criteria.uniqueResult();
        System.out.println(object);
        session.getTransaction().commit();
    }

    @Test
    public void moreCount() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.rowCount());
        projectionList.add(Projections.max("id"));

        criteria.setProjection(projectionList);
        Object[] array = (Object[]) criteria.uniqueResult();
        System.out.println(array[0] +"  "+ array[1]);

        session.getTransaction().commit();
    }

    @Test
    public void desc() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        criteria.addOrder(Order.desc("id"));
        List<User> userList = criteria.list();
        for (User user : userList) {
            System.out.println(user);
        }

        session.getTransaction().commit();
    }
}
