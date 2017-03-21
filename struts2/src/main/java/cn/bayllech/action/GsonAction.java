package cn.bayllech.action;

import cn.bayllech.pojo.User;

public class GsonAction extends BaseAction{

    @Override
    public String execute() throws Exception {
        User user = new User();
        user.setPassword("1234134");
        user.setUsername("中国");
        rendJson(user);

        return SUCCESS;
    }

}
