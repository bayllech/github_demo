package com.kaishengit.dao;

import com.kaishengit.entity.Admin;
import com.kaishengit.util.DbHelp;
import com.kaishengit.vto.HomeView;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Date;
import java.util.List;

/**
 * Created by bayllech on 2016/12/29.
 */
public class AdminDao  {

    public Admin findAdminByName(String adminName) {
        String sql = "select * from t_admin where adminname = ?";
        return DbHelp.query(sql, new BeanHandler<>(Admin.class), adminName);
    }

    public List<HomeView> homeView(Integer start,Integer pageSize) {
        String sql = "SELECT COUNT(*) topicnum,DATE_FORMAT(createtime,'%Y-%m-%d') AS DATA ,\n" +
                "(SELECT COUNT(*) FROM t_reply WHERE DATE_FORMAT(createtime,'%Y-%m-%d') = (DATE_FORMAT(t_topic.createtime,'%Y-%m-%d'))) AS replynum\n" +
                "FROM t_topic  GROUP BY (DATE_FORMAT(createtime,'%Y-%m-%d')) \n" +
                "ORDER BY (DATE_FORMAT(createtime,'%Y-%m-%d')) DESC limit ?,?";
        return DbHelp.query(sql, new BeanListHandler<>(HomeView.class),start,pageSize);
    }

    /**
     * 统计DATE_RORMAT的数量
     * @return
     */
    public int countDay() {
        String sql = "select count(*) from (select count(*) from t_topic group by DATE_FORMAT(createtime,'%y-%m-%d')) AS topicCount";
        return DbHelp.query(sql, new ScalarHandler<Long>()).intValue();
    }
}
