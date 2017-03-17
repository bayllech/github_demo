package cn.bayllech.action;

public class UserAction {

    private String username;
    private String password;


    public String newUser() {
        return "success";
    }

    public String save() throws Exception {
        System.out.println("username: " + username + ", password: " + password);
        return "success";
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
