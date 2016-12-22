package com.kaishengit.entity;

import java.sql.Timestamp;

public class Topic {
    private Integer id;
    private String title;
    private String content;
    private Timestamp createTime;
    private int clicknum;
    private int favnum;
    private int thankyounum;
    private int replynum;
    private Timestamp lastReplyTime;
    private Integer userid;
    private Integer nodeid;

    private User user;
    private Node node;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getClicknum() {
        return clicknum;
    }

    public void setClicknum(int clicknum) {
        this.clicknum = clicknum;
    }

    public int getFavnum() {
        return favnum;
    }

    public void setFavnum(int favnum) {
        this.favnum = favnum;
    }

    public int getThankyounum() {
        return thankyounum;
    }

    public void setThankyounum(int thankyounum) {
        this.thankyounum = thankyounum;
    }

    public int getReplynum() {
        return replynum;
    }

    public void setReplynum(int replynum) {
        this.replynum = replynum;
    }

    public Timestamp getLastReplyTime() {
        return lastReplyTime;
    }

    public void setLastReplyTime(Timestamp lastReplyTime) {
        this.lastReplyTime = lastReplyTime;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getNodeid() {
        return nodeid;
    }

    public void setNodeid(Integer nodeid) {
        this.nodeid = nodeid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
