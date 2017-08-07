package cn.bayllech.test;

import cn.bayllech.pojo.User;
import cn.bayllech.util.HibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

public class HibernateSQLTest {
    @Test
    public void findAll() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String sql = "select id,username,password,mobile from user";
        /*List<Object[]> list = session.createSQLQuery(sql).list();
        for (Object[] obj : list) {
            System.out.println("username: " +obj[0]+ "  password" + obj[1]);
        }*/

        List<User> userList = session.createSQLQuery(sql).addEntity(User.class).list();
        for (User user : userList) {
            System.out.println(user);
        }

        session.getTransaction().commit();
    }

    @Test
    public void uniqueFind() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String sql = "select id,username,password,mobile from user where id = 1027";
        User user = (User) session.createSQLQuery(sql).addEntity(User.class).uniqueResult();
        user.setUsername("张三");
        System.out.println(user);

        session.getTransaction().commit();
    }

}
