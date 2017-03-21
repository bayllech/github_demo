package cn.bayllech.actionOld;

import cn.bayllech.pojo.User;

import java.util.Arrays;
import java.util.List;

public class UserAction {

    /*private String username;
    private String password;*/
    private User user;
    private List<String> names;


    public String newUser() {
        names = Arrays.asList("aa", "bb", "cc");
        /*names.add("aa");
        names.add("bb");
        names.add("cc");*/
        return "success";
    }

    public String save() throws Exception {
        System.out.println("username: " + user.getUsername());
        return "success";
    }



    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    /* public String getUsername() {
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
    }*/
}
