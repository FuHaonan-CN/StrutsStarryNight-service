package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.UserBasicInfo;
import factory.DAOFactory;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class UserAction extends ActionSupport {
    private Session session = HibernateSessionFactory.getSession();
    private Transaction transaction = null;
    private UserBasicInfo user;
    private int id;
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
            String userName = "";
            userName = request.getParameter("keywords");
            if (userName == null)
                userName = "";
            String key = "%" + userName + "%";
            String page = request.getParameter("page");
            if (page == null) {
                page = "1";
            }
            request.setAttribute("currentKeywords", userName);
            request.setAttribute("currentPage", page);
            request.setAttribute("all", DAOFactory.getUserDAOInstance().querrybykey(key, Integer.parseInt(page)));
            request.setAttribute("page", DAOFactory.getUserDAOInstance().showSumPages(key, Integer.parseInt(page)));
            //提交事务
            transaction.commit();
            return "selectAll";
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return "errors";
    }

    //insertInput加载新增页面
    public String insertInput() throws Exception {
        return "insertInput";
    }

    //新增用户
    public String insert() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        //开启事务
        transaction = session.beginTransaction();
        try {
            String pic = "null.jpg";
            boolean b = false;
            b = DAOFactory.getUserDAOInstance().querybyname(user.getUsername());
            if (!b) {
                request.setAttribute("errInf", "该用户已注册！请重新输入！");
                return "userLogin";
            } else {
                // 插入默认头像
                user.setPic(pic);
                // 3、调用DAO完成数据库的插入操作
                boolean flag = false;
                try {
                    DAOFactory.getUserDAOInstance().saveorupdate(user);
                    //存入session，跳过登录
                    request.getSession().setAttribute("user", user);
                    flag = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                request.setAttribute("flag", new Boolean(flag));
            }
            //提交事务
            transaction.commit();
//            return "userLogin";
            return "register";
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return "errors";
    }

  /*  //按用户名查询
    public String querybyname() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        //开启事务
        transaction = session.beginTransaction();
        try {
            // 接收参数
            String userName = "";
            userName = request.getParameter("userName");
            boolean flag = false;
            boolean b = DAOFactory.getUserDAOInstance().querybyname(userName);
            if (!b) {
                return "userLogin";
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
    public String updateInput() throws Exception {
        //开启事务
        transaction = session.beginTransaction();
        try {
            this.user = DAOFactory.getUserDAOInstance().querybyid(id);
            //提交事务
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return "updateInput";
    }

    //更新
    public String update() throws Exception {
        //开启事务
        transaction = HibernateSessionFactory.getSession().beginTransaction();
        try {
            //1.拿到ServletContext
            ServletContext servletContext = ServletActionContext.getServletContext();
            //2.调用realPath方法，获取根据一个虚拟目录得到的真实目录
            String realPath = servletContext.getRealPath("/upload");
            //3.如果这个真实的目录不存在，需要创建
            File file = new File(realPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            //4.把文件存过去
            //拷贝：把文件的临时文件复制到指定的位置
            // FileUtils.copyFile(pic, new File(file, picFileName));

            if (picFileName == null || picFileName.equals("")) {
                picFileName = user.getPic();
            } else {
                //剪切：把临时文件剪切指定的位置，并且给他重命名
                String fileExtName=picFileName.substring(picFileName.lastIndexOf(".")-1);
                picFileName = System.currentTimeMillis()+fileExtName;
                pic.renameTo(new File(file, picFileName));
            }

            HttpServletRequest request = ServletActionContext.getRequest();

            user.setPic(picFileName);
            // 3、调用DAO完成数据库的插入操作
            boolean flag = false;
            try {
                DAOFactory.getUserDAOInstance().saveorupdate(user);
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

    //删除管理员信息
    public String delete() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        //开启事务
        transaction = session.beginTransaction();
        try {
            // 接收参数
            int id = 0;
            id = Integer.parseInt(request.getParameter("id"));
            //boolean flag = false;
            DAOFactory.getUserDAOInstance().delete(id);
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

    //删除多个管理员信息
    public String deleteMore() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        //开启事务
        transaction = session.beginTransaction();
        try {
            String[] id = request.getParameterValues("userId"); //存放被选中管理员的id号
            if (id != null) { //判断是否有管理员被选中
                for (int i = 0; i < id.length; i++) {
                    DAOFactory.getUserDAOInstance().delete(Integer.parseInt(id[i]));
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

    //登录验证
    public String login() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        //开启事务
        transaction = session.beginTransaction();
        boolean b = false;
        try {
            b = DAOFactory.getUserDAOInstance().querybyname(user.getUsername());
            if (b) {
                request.setAttribute("errInf", "该用户名尚未注册！请先注册或重新输入！");
                return "userLogin";
            } else {
                user = DAOFactory.getUserDAOInstance().logincheck(user);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                } else {
                    request.setAttribute("errInf", "用户名或密码错误, 请重新输入!");
                    return "userLogin";
                }
            }
            //提交事务
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return "userLoginSuccess";
    }

    // 账户注销
    public String logout() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        request.getSession().removeAttribute("user");
        return "logout";
    }

    public UserBasicInfo getUser() {
        return user;
    }

    public void setUser(UserBasicInfo user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public File getPic() {
        return pic;
    }

    public void setPic(File pic) {
        this.pic = pic;
    }

    public String getPicFileName() {
        return picFileName;
    }

    public void setPicFileName(String picFileName) {
        this.picFileName = picFileName;
    }

    public String getPicContentType() {
        return picContentType;
    }

    public void setPicContentType(String picContentType) {
        this.picContentType = picContentType;
    }
}
