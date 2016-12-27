package com.kaishengit.dao;

import com.kaishengit.entity.Node;
import com.kaishengit.exception.DataAccessException;
import com.kaishengit.util.DbHelp;
import com.kaishengit.util.StringUtils;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

/**
 * Created by bayllech on 2016/12/21.
 */
public class NodeDao {

    /**
     * 根据id查找发帖类型
     * @param nodeid
     * @return
     */
    public Node findNodeById(Integer nodeid) {
        String sql = "select * from t_node where id = ?";
        return DbHelp.query(sql, new BeanHandler<Node>(Node.class), nodeid);
    }

    /**
     * 修改版块下主题数
     * @param node
     */
    public void updateNode(Node node) {
        String sql = "update t_node set nodename =?, topicnum =?  where id = ?";
        DbHelp.update(sql,node.getNodename(),node.getTopicnum(),node.getId());
    }

    /**
     **从数据库中查找所有发帖类型
     * @return 所有发帖类型集合
     */
    public List<Node> findAllNode() {
        String sql = "select * from t_node";
        return DbHelp.query(sql, new BeanListHandler<Node>(Node.class));
    }

    public int findTopicnum(String nodeId) {
        if (StringUtils.isEmpty(nodeId)) {
            String sql = "SELECT SUM(topicnum) FROM t_node";
            Object obj = DbHelp.query(sql, new ScalarHandler<Long>());
            return Integer.valueOf(obj.toString());
        } else {
            String sql = "SELECT topicnum FROM t_node where id = ?";
            return DbHelp.query(sql, new ScalarHandler<Integer>(),Integer.valueOf(nodeId));
        }
    }

    /**
     * nodeId列表
     * @return
     */
    public List<Integer> nodeIdList() {
        String sql = "select id from t_node";
        return DbHelp.query(sql, new ColumnListHandler<Integer>());
    }
}
