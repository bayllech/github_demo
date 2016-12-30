package com.kaishengit.dao;

import com.kaishengit.entity.Fav;
import com.kaishengit.entity.Topic;
import com.kaishengit.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * Created by bayllech on 2016/12/24.
 */
public class FavDao {
    /**
     * 添加收藏
     * @param userid
     * @param topicid
     */
    public void fav(Integer userid, Integer topicid) {
        String sql = "insert into t_fav (userid,topicid) values (?,?)";
        DbHelp.update(sql,userid,topicid);
    }

    /**
     * 取消收藏
     * @param userid
     * @param topicid
     */
    public void unfav(Integer userid, Integer topicid) {
        String sql = "delete from t_fav where userid = ? and topicid = ?";
        DbHelp.update(sql,userid,topicid);
    }

    /**
     * 根据userid和topicid查找是否收藏
     * @param userid
     * @param topicid
     * @return
     */
    public Fav findFav(Integer userid, Integer topicid) {
        String sql = "select * from t_fav where userid=? and topicid=?";
        return DbHelp.query(sql, new BeanHandler<Fav>(Fav.class), userid, topicid);
    }

    /**
     * 删除主题时删除回复数
     * @param topic
     */
    public void delFav(Topic topic) {
        String sql = "delete from t_fav where topicid=?";
        DbHelp.update(sql,topic.getId());
    }
}
