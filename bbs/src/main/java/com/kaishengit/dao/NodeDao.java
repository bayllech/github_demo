package com.kaishengit.dao;

import com.kaishengit.entity.Node;
import com.kaishengit.exception.DataAccessException;
import com.kaishengit.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;

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
}
