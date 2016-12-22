package com.kaishengit.dao;

import com.kaishengit.util.DbHelp;

/**
 * Created by bayllech on 2016/12/22.
 */
public class ReplyDao {

    /**
     * 添加新回复
     * @param content
     * @param topicid
     * @param userid
     */
    public void addReply(String content, String topicid, Integer userid) {
        String sql = "insert into t_reply (content,topicid,userid) values(?,?,?)";
        DbHelp.update(sql,content,topicid,userid);
    }
}
