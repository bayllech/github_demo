package cn.bayllech.test;

import cn.bayllech.pojo.Dept;
import cn.bayllech.pojo.Employment;
import cn.bayllech.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class HibernateManyToOneTest {
    @Test
    public void manyToOneSave() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Dept dept = new Dept();
        dept.setDeptname("财务部");

        Employment employment = new Employment();
        employment.setName("王梦宇");
        employment.setDept(dept);

        Employment employment1 = new Employment();
        employment1.setName("王闯");
        employment1.setDept(dept);

        Set<Employment> employments = new HashSet<>();
        employments.add(employment);
        employments.add(employment1);

        dept.setEmploymentSet(employments);

        session.save(dept);
        session.save(employment);
        session.save(employment1);

        session.getTransaction().commit();
    }

    @Test
    public void findOneFromMany() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Employment employment = (Employment) session.get(Employment.class, 9);
//        System.out.println(employment);
        System.out.println(employment.getDept().getDeptname());

        session.getTransaction().commit();
    }

    @Test
    public void findManyFromOne() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Dept dept = (Dept) session.get(Dept.class, 5);
        Set<Employment> employments = dept.getEmploymentSet();
        for (Employment e : employments) {
            System.out.println(e);
        }

        session.getTransaction().commit();
    }
}
