package com.kaishengit.dao;

import com.kaishengit.entity.Node;
import com.kaishengit.entity.Reply;
import com.kaishengit.entity.Topic;
import com.kaishengit.entity.User;
import com.kaishengit.util.Config;
import com.kaishengit.util.DbHelp;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.handlers.AbstractListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by bayllech on 2016/12/21.
 */
public class TopicDao {

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

    public void update(Topic topic) {
        String sql = "update t_topic set title=?,content=?,clicknum=?,favnum=?,thankyounum=?,replynum=?,lastreplytime=? where id = ?";
        DbHelp.update(sql, topic.getTitle(), topic.getContent(), topic.getClicknum(), topic.getFavnum(), topic.getThankyounum(),topic.getReplynum(), topic.getLastReplyTime(), topic.getId());
    }

    /**
     * 根据topicid查找回复列表
     * @param topicid
     * @return
     */
    public List<Reply> replyListByTopicId(Integer topicid) {
        String sql = "SELECT tr.*,tu.id,tu.username,tu.avatar FROM t_user tu,t_reply tr WHERE tr.userid=tu.id AND topicid = ?";
        return DbHelp.query(sql, new AbstractListHandler<Reply>() {
            @Override
            protected Reply handleRow(ResultSet resultSet) throws SQLException {
                Reply reply = new BasicRowProcessor().toBean(resultSet, Reply.class);
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setAvatar(Config.get("domain")+resultSet.getString("avatar"));
                reply.setUser(user);
                return reply;
            }
        }, topicid);
    }
}
