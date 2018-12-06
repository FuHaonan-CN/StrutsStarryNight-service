package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.PostInfo;
import factory.DAOFactory;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Date;

public class PostAction extends ActionSupport {
    private Session session = HibernateSessionFactory.getSession();
    private Transaction transaction = null;
    private PostInfo post;
    private int id;
    /*模糊查询*/
    private String rightname = "";
    private String keywords = "";
    private int page = 1;
    //文件上传
    //表单上提供的字段
    private File pic;
    //struts2在文件上传时提供的属性
    private String picFileName;//上传的文件名。上传字段名称+FileName
    private String picContentType;//上传文件的MIME类型。上传字段名称+ContentType

    //查询所有
    public String selectAll() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        //开启事务
        transaction = session.beginTransaction();
        try {
            //接收参数或初始化
            String username = "%" + rightname.trim() + "%";
            String key = "%" + keywords.trim() + "%";
            request.setAttribute("all", DAOFactory.getPostDAOInstance().querrybykey(username, key, page));
            request.setAttribute("count", DAOFactory.getPostDAOInstance().showSumPages(username, key, page));
            //提交事务
            transaction.commit();
            return "postSelectAll";
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return "errors";
    }

    //insertInput加载新增页面
    public String insertInput() throws Exception {
        return "postInsertInput";
    }

    //添加新闻
    public String insert() throws Exception {
        //开启事务
        transaction = session.beginTransaction();
        try {
            //设置时间
            Date date = new Date(new java.util.Date().getTime()); //构建一个java.sql.Date对象
            post.setPosttime(date);
            // 3、调用DAO完成数据库的插入操作
            boolean flag = false;
            try {
                DAOFactory.getPostDAOInstance().saveorupdate(post);
                flag = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("flag", new Boolean(flag));
            //提交事务
            transaction.commit();
            return "insert";
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return "errors";
    }

   /* //按用户名查询
    public String querybyname() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        //开启事务
        transaction = session.beginTransaction();
        try {
            // 接收参数
            String adminName = "";
            adminName = request.getParameter("adminName");
            boolean flag = false;
            boolean b = DAOFactory.getAdminDAOInstance().querybyname(adminName);
            if (!b) {
                return "adminInsert";
            }
//            request.setAttribute("flag", new Boolean(flag));
            //提交事务
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return "insert";
    }*/

    //按ID查询
    public String queryById() throws Exception {
        //开启事务
        transaction = session.beginTransaction();
        try {
            this.post = DAOFactory.getPostDAOInstance().querybyid(id);
            //提交事务
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return "postQueryById";
    }

    //按ID查询
    public String updateInput() throws Exception {
        //开启事务
        transaction = session.beginTransaction();
        try {
            this.post = DAOFactory.getPostDAOInstance().querybyid(id);
            //提交事务
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return "postUpdateInput";
    }

    //更新
    public String update() throws Exception {
        //开启事务
        transaction = HibernateSessionFactory.getSession().beginTransaction();
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            //更新时间
            Date date = new Date(new java.util.Date().getTime()); //构建一个java.sql.Date对象
            post.setPosttime(date);
            // 3、调用DAO完成数据库的插入操作
            boolean flag = false;
            try {
                DAOFactory.getPostDAOInstance().saveorupdate(post);
                flag = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            request.setAttribute("flag", new Boolean(flag));
            //提交事务
            transaction.commit();
            return "update";
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return "errors";
    }

    //删除新闻
    public String delete() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        //开启事务
        transaction = session.beginTransaction();
        try {
            // 接收参数
            int id = 0;
            id = Integer.parseInt(request.getParameter("id"));
//            System.out.println(id);
            //boolean flag = false;
            DAOFactory.getPostDAOInstance().delete(id);
            /*flag = true;
            request.setAttribute("flag", new Boolean(flag));*/
            //提交事务
            transaction.commit();
            return "delete";
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return "errors";
    }


    //删除条新闻
    public String deleteMore() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        //开启事务
        transaction = session.beginTransaction();
        try {
            String[] id = request.getParameterValues("postId"); //存放被选中管理员的id号
            if (id != null) { //判断是否有管理员被选中
                for (int i = 0; i < id.length; i++) {
                    DAOFactory.getPostDAOInstance().delete(Integer.parseInt(id[i]));
                }
            }
            //提交事务
            transaction.commit();
            return "deleteMore";
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return "errors";
    }


    public PostInfo getPost() {
        return post;
    }

    public void setPost(PostInfo post) {
        this.post = post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRightname() {
        return rightname;
    }

    public void setRightname(String rightname) {
        this.rightname = rightname;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
