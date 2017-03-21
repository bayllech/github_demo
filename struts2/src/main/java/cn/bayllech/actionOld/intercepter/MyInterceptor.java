package cn.bayllech.actionOld.intercepter;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MyInterceptor extends AbstractInterceptor {

    private String namespace;
    private String actionNames;

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {

        ActionProxy proxy = actionInvocation.getProxy();
        String namespace = proxy.getNamespace();
        String actionName = proxy.getActionName();

        List<String> actionNameList = Arrays.asList(actionNames.split(","));
        if (this.namespace.equals(namespace) && actionNameList.contains(actionName)) {
            return actionInvocation.invoke();
        } else {
            Map<String,Object> session = ActionContext.getContext().getSession();
            if(session.get("current_user") == null) {
                return Action.LOGIN;
            } else {
                return actionInvocation.invoke();
            }
        }

    }


    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getActionNames() {
        return actionNames;
    }

    public void setActionNames(String actionNames) {
        this.actionNames = actionNames;
    }
}
