package com.kaishengit.dao;

import com.kaishengit.entity.Message;
import com.kaishengit.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

/**
 * Created by 帅比 on 2016/12/9.
 */
public class MessageDao {

    public List<Message> findByMaxId(int id) {
        String sql = "select * from t_message where id = ? order by id dec";
        return DbHelp.query(sql, new BeanListHandler<>(Message.class),id);
    }
}
