/*
* 验证码通过action转发
* */
package action;
import com.opensymphony.xwork2.ActionSupport;

public class VerificationAction extends ActionSupport {

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }
}
