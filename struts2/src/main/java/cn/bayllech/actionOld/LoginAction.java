package cn.bayllech.actionOld;

public class LoginAction extends BaseAction {

    private String userName;
    private String password;
    private String code;

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public String login() throws Exception {
        if ("tom".equals(userName) && password.equals("123")) {
            getSession().put("current_user", "tom");
            return SUCCESS;
        } else {
            code = "1002";
            return ERROR;
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
