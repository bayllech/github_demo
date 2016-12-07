package com.kaishengit.dao;

import com.kaishengit.entity.Admin;
import com.kaishengit.entity.Message;
import com.kaishengit.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

/**
 * Created by 帅比 on 2016/11/23.
 */
public class MessageDao {

    public void addBook(Message message) {
        String sql = "insert into t_guest(message,name,mid) values(?,?,?)";
        DbHelp.update(sql,message.getMessage(),message.getName(),message.getMid());
    }

    public void delBook(String id) {
        String sql = "delete from t_guest where id = ?";
        DbHelp.update(sql,id);
    }

    public Message findById(String id) {
        String sql = "select * from t_guest where id = ?";
        return DbHelp.query(sql,new BeanHandler<Message>(Message.class), id);
    }

    public void updateBook(Message book) {
        String sql = "update t_guest set message=?,name=? where id=?";
        DbHelp.update(sql,book.getMessage(),book.getName(),book.getId());
    }

    public List<Message> findByPageNo(Admin admin, int start, int pageSize) {
        String sql = "select * from t_guest where mid = ? limit ?,?  ";
        return DbHelp.query(sql,new BeanListHandler<>(Message.class),admin.getId() ,start,pageSize);
    }

    public Long count(Admin admin) {
        String sql = "select count(*) from t_guest where mid = ?";
        return DbHelp.query(sql, new ScalarHandler<Long>(),admin.getId());
    }

    public Long count() {
        String sql = "select count(*) from t_guest";
        return DbHelp.query(sql, new ScalarHandler<Long>());
    }

    public List<Message> findAll(int start, int pageSize) {
        String sql = "select * from t_guest limit ?,?  ";
        return DbHelp.query(sql,new BeanListHandler<>(Message.class),start,pageSize);
    }
}
