package com.kaishengit.service;

import com.kaishengit.dao.NodeDao;
import com.kaishengit.entity.Node;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.StringUtils;

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

    /**
     * 判断nodeId是否存在
     * @param nodeId
     * @return
     */
    public String existNodeId(String nodeId) {
        //查找nodeId列表
        Boolean Flag = true;
        List<Integer> nodeIdList = nodeDao.nodeIdList();
         if ((StringUtils.isNotEmpty(nodeId) && !StringUtils.isNumeric(nodeId)) || nodeId==null ||nodeId=="") {
            nodeId = null;
        } else {
             for (int i = 0;i<nodeIdList.size();i++) {
                 if (nodeIdList.get(i) == Integer.valueOf(nodeId)) {
                     Flag = false;
                 }
             }
             if (Flag) {
                 nodeId = null;
             }
        }
        return nodeId;
    }


    public void delNode(String nodeid) {
        Node node = nodeDao.findNodeById(Integer.valueOf(nodeid));
        if (node.getTopicnum() > 0){
            throw  new ServiceException("该节点下已有主题,不可删除");
        }else{
            nodeDao.del(Integer.valueOf(nodeid));
        }
    }

    public String validateNodeName(String nodeId, String nodeName) {
        // 根据nodeid查询node,并判断nodeName是否等于node的nodename
        Node node = nodeDao.findNodeById(Integer.valueOf(nodeId));
        if (node.getNodename().equals(nodeName)) {
            return "true";
        } else {
            Node nodeIsIn = nodeDao.findNodeByName(nodeName);
            if (nodeIsIn == null) {
                return "true";
            }
        }
        return "false";
    }


    public String validateNodeName(String nodeName) {
        Node nodeIsIn = nodeDao.findNodeByName(nodeName);
        if (nodeIsIn == null) {
            return "true";
        } else {
            return "false";
        }
    }

    public void updateNode(String nodeId, String nodeName) {
        if (StringUtils.isNumeric(nodeId) && StringUtils.isNotEmpty(nodeName)) {
            Node node = nodeDao.findNodeById(Integer.valueOf(nodeId));
            node.setNodename(nodeName);
            nodeDao.updateNode(node);
        } else {
            throw new ServiceException("参数异常");
        }
    }

    public void addNode(String nodeName) {
        nodeDao.addNode(nodeName);
    }
}
