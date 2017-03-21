package cn.bayllech.action;

import cn.bayllech.pojo.User;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import java.util.Arrays;
import java.util.List;

@Namespace("/user")
public class UserAction extends BaseAction {

    private String code;
    private User user;
    private List<String> nameList;
    private String name;

    @Action("list")
    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    @Action("save")
    public String save() throws Exception {
        code = "1002";

        user = new User();
        user.setPassword("123");
        user.setUsername("中国");

        nameList = Arrays.asList("aa", "bb", "cc");

        name = "tom";

        return SUCCESS;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getNameList() {
        return nameList;
    }

    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
