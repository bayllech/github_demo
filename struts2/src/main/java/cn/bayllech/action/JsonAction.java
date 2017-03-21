package cn.bayllech.action;

import cn.bayllech.pojo.User;

public class JsonAction  extends BaseAction{

    private Integer pid;
    private User user;

    @Override
    public String execute() throws Exception {
        user = new User();
        user.setPassword("123");
        user.setUsername("奥尔");

        return SUCCESS;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}
