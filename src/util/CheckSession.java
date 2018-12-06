/**
 * @Title: LoginInterceptoe.java
 * @Description: 拦截非登录用户请求
 * @author ThinkPad
 * @version 1.0
 * @date 2018年3月29日
 */
package util;

import action.PostAction;
import action.UserAction;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CheckSession extends MethodFilterInterceptor {
    @Override
    //拦截Action处理的方法
    public String doIntercept(ActionInvocation invocation) throws Exception {
        // 对LoginAction不做该项拦截
        Object action = invocation.getAction();
    if (action instanceof UserAction) {
            System.out.println("exit check login, because this is login action.");
            return invocation.invoke();
        }
        if (action instanceof PostAction) {
            System.out.println("exit check login, because this is login action.");
            return invocation.invoke();
        }
        //先获取session
       /*
        ActionContext ctx = invocation.getInvocationContext();
        Map session = ctx.getSession();
        */
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        //session不为空
        /*||session.getAttribute("user") != null*/
        if (session.getAttribute("admin") != null)
        {
            return invocation.invoke();
        }
        //用户未登录，设置提示信息
        request.setAttribute("errInf", "未登录,自动跳转至登录界面");
        return "adminLogin";
    }

}