package cn.bayllech.action;

public class HelloAction {

    private String code;

    public String execute() throws Exception{
        System.out.println("Hello,word!");
        return "success";
    }

    public String add() throws Exception {
        System.out.println("add ");
        return "success";
    }

    public String list() throws Exception {
        code = "1009";
        return "success";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
