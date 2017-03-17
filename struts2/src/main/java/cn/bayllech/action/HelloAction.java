package cn.bayllech.action;

public class HelloAction {

    public String execute() throws Exception{
        System.out.println("Hello,word!");
        return "success";
    }

    public String add() throws Exception {
        System.out.println("add ");
        return "success";
    }

    public String list() throws Exception {
        return "success";
    }
}
