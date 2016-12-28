package com.kaishengit.dao;

import com.kaishengit.entity.Notify;
import com.kaishengit.entity.User;
import com.kaishengit.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

/**
 * Created by bayllech on 2016/12/27.
 */
public class NotifyDao  {
    /**
     * 新增回复通知
     * @param notify
     */
    public void save(Notify notify) {
        String sql = "insert into t_notify (userid,content,state) values(?,?,?)";
        DbHelp.update(sql,notify.getUserid(),notify.getContent(),notify.getState());
    }

    /**
     * 根据userid查找notify列表
     * @param user
     * @return
     */
    public List<Notify> findNotifyByUser(User user) {
        String sql = "select * from t_notify where userid = ? ORDER BY createtime DESC,readtime";
        return DbHelp.query(sql, new BeanListHandler<Notify>(Notify.class), user.getId());
    }

    /**
     * 查找notify通过id
     * @param id
     * @return
     */
    public Notify findNotifyById(String id) {
        String sql = "select * from t_notify where id = ?";
        return DbHelp.query(sql, new BeanHandler<Notify>(Notify.class), Integer.valueOf(id));
    }

    /**
     * 更新notify
     * @param notify
     */
    public void update(Notify notify) {
        String sql = "update t_notify set state=?,readtime=? where id = ?";
        DbHelp.update(sql,notify.getState(),notify.getReadtime(),notify.getId());
    }
}
