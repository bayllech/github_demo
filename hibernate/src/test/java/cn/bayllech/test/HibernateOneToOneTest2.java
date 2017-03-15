package cn.bayllech.test;

import cn.bayllech.pojo.TitleContent;
import cn.bayllech.pojo.Topic;
import cn.bayllech.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

public class HibernateOneToOneTest2 {
    @Test
    public void save() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Topic topic = new Topic();
        topic.setTitle("T3");

        TitleContent titleContent = new TitleContent();
        titleContent.setContent("33333");

        titleContent.setTopic(topic);
        topic.setTitleContent(titleContent);

        session.save(topic);
        session.save(titleContent);

        session.getTransaction().commit();
    }
}
