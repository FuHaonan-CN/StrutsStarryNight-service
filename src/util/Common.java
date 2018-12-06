package util;

public class Common {
    /*管理员转换*/
    public String privilegeToString(int i) {
        if (i == 1)
            return "超级管理员";
        else
            return "网页管理员";
    }
/*新闻类型转换*/
    public String countdownToString(int i) {
        if (i == 1)
            return "倒计时事件";
        else
            return "新闻事件";
    }
}
