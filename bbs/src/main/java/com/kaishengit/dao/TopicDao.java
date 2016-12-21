package com.kaishengit.dao;

import com.kaishengit.entity.Node;
import com.kaishengit.entity.Topic;
import com.kaishengit.entity.User;
import com.kaishengit.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

/**
 * Created by bayllech on 2016/12/21.
 */
public class TopicDao {

    /**
     **从数据库中查找所有发帖类型
     * @return 所有发帖类型集合
     */
    public List<Node> findAllNode() {
        String sql = "select * from t_node";
        return DbHelp.query(sql, new BeanListHandler<Node>(Node.class));
    }

    /**
     *保存新帖
     * @return 新发帖的id
     */
    public Integer addTopic(Topic topic) {
        String sql = "insert into t_topic (title,content,nodeid,userid) values(?,?,?,?)";
        return DbHelp.insert(sql,topic.getTitle(),topic.getContent(),topic.getNodeid(),topic.getUserid());
    }

    /**
     * 根据帖子id查找帖子
     * @param topicid
     * @return
     */
    public Topic findTopicById(String topicid) {
        String sql = "select * from t_topic where id = ?";
        return DbHelp.query(sql, new BeanHandler<Topic>(Topic.class), topicid);
    }
}