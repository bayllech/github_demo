package com.kaishengit.dao;

import com.kaishengit.entity.Topic;
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

    /**
     * 删除主题时删除回复
     * @param topic
     */
    public void delReply(Topic topic) {
        String sql = "delete from t_reply where topicid =?";
        DbHelp.update(sql,topic.getId());
    }
}
