package com.kaishengit.service;

import com.kaishengit.dao.NodeDao;
import com.kaishengit.dao.ReplyDao;
import com.kaishengit.dao.TopicDao;
import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.Node;
import com.kaishengit.entity.Topic;
import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Config;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by bayllech on 2016/12/21.
 */
public class TopicService {

    TopicDao topicDao = new TopicDao();
    UserDao userDao = new UserDao();
    NodeDao nodeDao = new NodeDao();
    ReplyDao replyDao = new ReplyDao();

    /**
     * 发布新帖
     * @param title 帖子主题
     * @param content 帖子内容
     * @param nodeid 发帖区
     * @param userid 发帖人id
     * @return 新发帖
     */
    public Topic addTopic(String title, String content, Integer nodeid, Integer userid) {
         Topic topic = new Topic();
         topic.setTitle(title);
         topic.setContent(content);
         topic.setNodeid(nodeid);
         topic.setUserid(userid);
         Integer tipicId = topicDao.addTopic(topic);
         topic.setId(tipicId);

         return topic;
    }

    /**
     * 根据帖子id查找帖子
     * @param topicid
     * @return
     */
    public Topic findTopicById(String topicid) {
        if (StringUtils.isNumeric(topicid)) {
            Topic topic = topicDao.findTopicById(topicid);
            if (topic != null) {
                User user = userDao.findUserById(topic.getUserid());
                Node node = nodeDao.findNodeById(topic.getNodeid());
                user.setAvatar(Config.get("domain")+user.getAvatar());
                topic.setUser(user);
                topic.setNode(node);

                //点击数加1
                topic.setClicknum(topic.getClicknum()+1);
                topicDao.update(topic);

                return topic;
            } else {
                throw new ServiceException("帖子已删除或不存在");
            }
        } else {
            throw new ServiceException("参数错误");
        }

    }

    /**
     * 添加新回复
     * @param content
     * @param topicid
     * @param user
     */
    public void addReply(String content, String topicid, User user) {
        if (new TopicService().findTopicById(topicid) != null) {
            replyDao.addReply(content,topicid,user.getId());
        } else {
            throw new ServiceException("帖子不存在或已被删除");
        };
    }
}
