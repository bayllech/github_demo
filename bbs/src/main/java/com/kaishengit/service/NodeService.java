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
     * 增加版块主题数
     * @param node
     */
    public void addTopicNum(Node node) {
        node.setTopicnum(node.getTopicnum()+1);
        nodeDao.updateNode(node);
    }

    /**
     * 减去版块主题数
     * @param oldNode
     */
    public void subTopicNum(Node oldNode) {
        oldNode.setTopicnum(oldNode.getTopicnum()-1);
        nodeDao.updateNode(oldNode);
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
