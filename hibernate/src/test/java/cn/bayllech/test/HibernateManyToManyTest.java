package cn.bayllech.test;

import cn.bayllech.pojo.Student;
import cn.bayllech.pojo.Teacher;
import cn.bayllech.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HibernateManyToManyTest {
    @Test
    public void save() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Teacher t1 = new Teacher();
        t1.setTname("T3");

        Teacher t2 = new Teacher();
        t2.setTname("T4");

        Student s1 = new Student();
        s1.setSname("S3");

        Student s2 = new Student();
        s2.setSname("S4");

        Set<Teacher> teacherList = new HashSet<>();
        teacherList.add(t1);
        teacherList.add(t2);
        s1.setTeacherSet(teacherList);
        s2.setTeacherSet(teacherList);

        Set<Student> studentSet = new HashSet<>();
        studentSet.add(s1);
        studentSet.add(s2);
        t1.setStudentSet(studentSet);
        t2.setStudentSet(studentSet);

        session.save(s1);
        session.save(s2);
        session.save(t1);
        session.save(t2);

        session.getTransaction().commit();
    }

    @Test
    public void find() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Student student = (Student) session.get(Student.class, 3);
        System.out.println(student.getSname());

//        Hibernate.initialize(student.getTeacherSet());

//        OpenSessionInView

        Set<Teacher> teachers = student.getTeacherSet();
        for (Teacher teacher : teachers) {
            System.out.println(teacher.getTname());
        }

        session.getTransaction().commit();
    }
}
