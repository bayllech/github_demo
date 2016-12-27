package com.kaishengit.dao;

import com.kaishengit.entity.Notify;
import com.kaishengit.util.DbHelp;

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
}
