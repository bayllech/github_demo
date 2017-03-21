package cn.bayllech.actionOld;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class HelloAction extends BaseAction {

    private String code;

    public String execute() throws Exception{
        System.out.println("Hello,word!");
        ActionContext actionContext = ActionContext.getContext();
        Map<String,Object> session = actionContext.getSession();

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        Map<String,Object> servletApplication = ActionContext.getContext().getApplication();
        ServletContext application = ServletActionContext.getServletContext();

        HttpSession httpSession = getHttpSession();
        httpSession.setAttribute("h1","v1");

        session.put("K1", "V1");

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
