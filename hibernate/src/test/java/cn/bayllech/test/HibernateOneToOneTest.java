package cn.bayllech.test;

import cn.bayllech.pojo.Card;
import cn.bayllech.pojo.Person;
import cn.bayllech.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

public class HibernateOneToOneTest {
    @Test
    public void save() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Person person = new Person();
        person.setName("赵");

        Card card = new Card();
        card.setNum("A004");
        card.setPerson(person);

        session.save(card);
        session.save(person);


        session.getTransaction().commit();
    }

    @Test
    public void find() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        /*Person person = (Person) session.get(Person.class, 4);
        System.out.println(person.getCard().getNum());*/

        Card card = (Card) session.get(Card.class, 3);
        System.out.println(card.getPerson().getName());

        session.getTransaction().commit();
    }

    @Test
    public void delete() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Person person = (Person) session.get(Person.class, 3);

        session.delete(person);

        session.getTransaction().commit();
    }
}
