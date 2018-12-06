package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.NewsInfo;
import factory.DAOFactory;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class HomepageAction extends ActionSupport {
    private Session session = HibernateSessionFactory.getSession();
    private Transaction transaction = null;
    private NewsInfo countdownNews;
    private List<NewsInfo> sixNews;

    public String execute() {
        HttpServletRequest request = ServletActionContext.getRequest();
        //开启事务
        transaction = session.beginTransaction();
        try {
            this.sixNews =DAOFactory.getNewsDAOInstance().querySexNews();
//            request.getSession().setAttribute("sixNews", DAOFactory.getNewsDAOInstance().querySexNews());
           this.countdownNews = DAOFactory.getNewsDAOInstance().queryOneCountdownNews();
            //提交事务
            transaction.commit();
            return "index";
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return "errors";
    }

    public String time() {
        return "time";
    }

   /* //查询首页六条新闻
    public String sixNews() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        //开启事务
        transaction = session.beginTransaction();
        try {
            request.getSession().setAttribute("sixNews", DAOFactory.getNewsDAOInstance().querySexNews());
            //提交事务
            transaction.commit();
            return "sixNews";
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return "errors";
    }*/

    public NewsInfo getCountdownNews() {
        return countdownNews;
    }

    public void setCountdownNews(NewsInfo countdownNews) {
        this.countdownNews = countdownNews;
    }

    public List<NewsInfo> getSixNews() {
        return sixNews;
    }

    public void setSixNews(List<NewsInfo> sixNews) {
        this.sixNews = sixNews;
    }
}
