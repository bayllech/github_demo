package com.kaishengit.service;

import com.google.common.collect.Maps;
import com.kaishengit.dao.*;
import com.kaishengit.entity.*;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Config;
import com.kaishengit.util.Page;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by bayllech on 2016/12/21.
 */
public class TopicService {

    TopicDao topicDao = new TopicDao();
    UserDao userDao = new UserDao();
    NodeDao nodeDao = new NodeDao();
    ReplyDao replyDao = new ReplyDao();
    FavDao favDao = new FavDao();
    NotifyDao notifyDao = new NotifyDao();

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
         //设置最后回复时间为发帖时间
         topic.setLastReplyTime(new Timestamp(new Date().getTime()));
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
            Topic topic = topicDao.findTopicById(Integer.valueOf(topicid));
            if (topic != null) {
                User user = userDao.findUserById(topic.getUserid());
                Node node = nodeDao.findNodeById(topic.getNodeid());
                user.setAvatar(Config.get("domain")+user.getAvatar());
                topic.setUser(user);
                topic.setNode(node);

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
        Topic topic = findTopicById(topicid);
        if (topic != null) {
            //设置最后回复时间和回复数
            topic.setLastReplyTime(new Timestamp(new DateTime().getMillis()));
            topic.setReplynum(topic.getReplynum()+1);
            updateTopic(topic);
            replyDao.addReply(content,topicid,user.getId());
        } else {
            throw new ServiceException("帖子不存在或已被删除");
        }
        //添加回复通知
        if (!user.getId().equals(topic.getUserid())){
            Notify notify = new Notify();
            notify.setUserid(topic.getUserid());
            notify.setContent("您的主题 <a href=\"/topicDetail?topicid="+topic.getId()+"\">["+ topic.getTitle()+"] </a> 有了新的回复,请查看.");
            notify.setState(Notify.NOTIFY_STATE_UNREAD);
            notifyDao.save(notify);
        }
    }

    /**
     * 根据主题id查找回复列表
     * @return
     */
    public List<Reply> replyListByTopicId(Integer topicid) {
        return topicDao.replyListByTopicId(topicid);
    }

    public void updateTopic(Topic topic) {
        topicDao.update(topic);
    }

    /**
     * 更新帖子
     * @param title
     * @param content
     * @param nodeid
     * @return
     */
    public Topic updateTopic(String title, String content, Integer nodeid,String topicId) {
        Topic topic = findTopicById(topicId);
        if (topic.isEdit()) {
            topic.setTitle(title);
            topic.setContent(content);
            topic.setNodeid(nodeid);

            topicDao.update(topic);
            return topic;
        } else {
            throw new ServiceException("该帖子已不可被编辑");
        }
    }

    /**
     * 添加收藏
     * @param userid
     * @param topicid
     */
    public Topic fav(Integer userid, String topicid) {
        favDao.fav(userid, Integer.valueOf(topicid));
        Topic topic = findTopicById(topicid);
        topic.setFavnum(topic.getFavnum() + 1);
        updateTopic(topic);
        return topic;
    }

    /**
     * 取消收藏
     * @param userid
     * @param topicid
     */
    public Topic unfav(Integer userid, String  topicid) {
        favDao.unfav(userid, Integer.valueOf(topicid));
        Topic topic = findTopicById(topicid);
        topic.setFavnum(topic.getFavnum() - 1);
        updateTopic(topic);
        return topic;
    }

    public Fav findFav(Integer userid, String topicid) {
        return favDao.findFav(userid, Integer.valueOf(topicid));
    }

    /**
     * 按nodeId分页查找数据
     * @param pageNo
     * @param nodeId
     * @return
     */
    public Page<Topic> findAllTopics(Integer pageNo, String nodeId) {
        HashMap<String,Object> map = Maps.newHashMap();
        int count = nodeDao.findTopicnum(nodeId);

        Page<Topic> topicPage = new Page<>(count,pageNo);
        map.put("nodeId",nodeId);
        map.put("start",topicPage.getStart());
        map.put("pageSize",topicPage.getPageSize());

        List<Topic> topicList = topicDao.findAll(map);
        topicPage.setItems(topicList);
        return topicPage;
    }

    /**
     * 删除帖子
     * @param topicid
     */
    public void delTopic(String topicid) {
        Topic topic = findTopicById(topicid);
        //修改节点下主题数
        Node node = nodeDao.findNodeById(topic.getNodeid());
        node.setTopicnum(node.getTopicnum()-1);
        nodeDao.updateNode(node);
        //删除回复数
        replyDao.delReply(topic);
        //删除收藏
        favDao.delFav(topic);
        //删除主题
        topicDao.delTopic(topic);
    }

    /**
     * 修改主题节点
     * @param nodeid
     * @param topicid
     */
    public void updateTopicNode(String nodeid, String topicid) {
        if(StringUtils.isNumeric(topicid) && StringUtils.isNumeric(nodeid)){
            Topic topic = topicDao.findTopicById(Integer.valueOf(topicid));
            NodeService nodeService = new NodeService();
            //topic.nodeid->nodeid
            Node node = nodeService.findNodeById(topic.getNodeid());
            //topicid topic node->topicnum-1
            nodeService.subTopicNum(node);

            //nodeid node->topicnum+1
            Node newnode = nodeService.findNodeById(Integer.valueOf(nodeid));
            nodeService.addTopicNum(newnode);

            //更新topic的nodeid
            topic.setNodeid(Integer.valueOf(nodeid));
            topicDao.update(topic);
            /*//更新node表中的topicnum字段
            updatNode(topic.getNodeid(),nodeid);*/
        }else{
            throw new ServiceException("参数异常");
        }
    }

    public Node findNodeById(String nodeId) {
        if (StringUtils.isNumeric(nodeId)) {
            Node node = nodeDao.findNodeById(Integer.valueOf(nodeId));
            if (node == null) {
                throw new ServiceException("节点不存在");
            } else {
                return node;
            }
        } else {
            throw new ServiceException("参数错误");
        }
    }
}
