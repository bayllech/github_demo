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
        topic.setTitle("T8");

        TitleContent titleContent = new TitleContent();
        titleContent.setContent("88888");

        topic.setTitleContent(titleContent);
        titleContent.setTopic(topic);

        session.save(titleContent);
        session.save(topic);


        session.getTransaction().commit();
    }
}
