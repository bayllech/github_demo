package com.kaishengit.service;

import com.kaishengit.dao.NodeDao;
import com.kaishengit.entity.Node;

import java.util.List;

/**
 * Created by bayllech on 2016/12/22.
 */
public class NodeService {
    NodeDao nodeDao = new NodeDao();
    /**
     * 修改版块主题数
     * @param node
     */
    public void updateNode(Node node) {
        node.setTopicnum(node.getTopicnum()+1);
        nodeDao.updateNode(node);
    }

    public Node findNodeById(Integer nodeid) {
        return nodeDao.findNodeById(nodeid);
    }

    /**
     * 查找所有发帖类型
     * @return 所有发帖类型
     */
    public List<Node> findAllNode() {
        return nodeDao.findAllNode();
    }
}
