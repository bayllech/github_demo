package com.kaishengit.dao;

import com.kaishengit.entity.Node;
import com.kaishengit.entity.Reply;
import com.kaishengit.entity.Topic;
import com.kaishengit.entity.User;
import com.kaishengit.util.Config;
import com.kaishengit.util.DbHelp;
import com.kaishengit.util.StringUtils;
import jdk.nashorn.internal.objects.annotations.Where;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.handlers.AbstractListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
    public Topic findTopicById(Integer topicid) {
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


    /**
     * 分页查找主题
     * @param map
     * @return
     */
    public List<Topic> findAll(HashMap<String, Object> map) {
        String sql = "SELECT tp.*,tu.username,tu.avatar FROM t_topic tp,t_user tu WHERE tp.userid=tu.id";
        String nodeId = map.get("nodeId") == null ? null : String.valueOf(map.get("nodeId"));
        String where = "";
        List<Object> array = new ArrayList<>();
        if (StringUtils.isNotEmpty(nodeId)) {
            where += " AND tp.nodeid=?";
            array.add(nodeId);
        }
        where += " ORDER BY tp.lastreplytime DESC LIMIT ?,? ";
        array.add(map.get("start"));
        array.add(map.get("pageSize"));
        sql += where;

        return DbHelp.query(sql, new AbstractListHandler<Topic>() {
            @Override
            protected Topic handleRow(ResultSet rs) throws SQLException {
                Topic topic = new BasicRowProcessor().toBean(rs, Topic.class);
                User user = new User();
                user.setId(rs.getInt("userid"));
                user.setUsername(rs.getString("username"));
                user.setAvatar(Config.get("domain") + rs.getString("avatar"));
                topic.setUser(user);
                return topic;
            }
        },array.toArray());

    }
}
