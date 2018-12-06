package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.NewsInfo;
import factory.DAOFactory;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Date;

public class NewsAction extends ActionSupport {
    private Session session = HibernateSessionFactory.getSession();
    private Transaction transaction = null;
    private NewsInfo news;
    private int id;
    /*模糊查询*/
    private int countdown=0;
    private String keywords="";
    private int page=1;
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
            String key = "%" + keywords.trim() + "%";
            request.setAttribute("all", DAOFactory.getNewsDAOInstance().querrybykey(countdown, key, page));
            request.setAttribute("count", DAOFactory.getNewsDAOInstance().showSumPages(countdown, key, page));
            //提交事务
            transaction.commit();
            return "selectAll";
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return "errors";
    }

    //insertInput加载新增页面
    public String insertInput() throws Exception {
        return "insertInput";
    }

    //添加新闻
    public String insert() throws Exception {
        //开启事务
        transaction = session.beginTransaction();
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
                picFileName = "null.jpg";
            } else {
                //剪切：把临时文件剪切指定的位置，并且给他重命名
                String fileExtName=picFileName.substring(picFileName.lastIndexOf(".")-1);
                picFileName = System.currentTimeMillis()+fileExtName;
                pic.renameTo(new File(file, picFileName));
            }

            HttpServletRequest request = ServletActionContext.getRequest();
            //设置图片名
            news.setPic(picFileName);
            //设置时间
            Date date = new Date(new java.util.Date().getTime()); //构建一个java.sql.Date对象
            news.setNewstime(date);
            // 3、调用DAO完成数据库的插入操作
            boolean flag = false;
            try {
                DAOFactory.getNewsDAOInstance().saveorupdate(news);
                flag = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
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
            this.news = DAOFactory.getNewsDAOInstance().querybyid(id);
            //提交事务
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return "queryById";
    }

    //按ID查询
    public String updateInput() throws Exception {
        //开启事务
        transaction = session.beginTransaction();
        try {
            this.news = DAOFactory.getNewsDAOInstance().querybyid(id);
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
            if (picFileName == null || picFileName.equals(""))
                picFileName = news.getPic();
            else {
                //剪切：把临时文件剪切指定的位置，并且给他重命名
                String fileExtName=picFileName.substring(picFileName.lastIndexOf(".")-1);
                picFileName = System.currentTimeMillis()+fileExtName;
                pic.renameTo(new File(file, picFileName));
            }

            HttpServletRequest request = ServletActionContext.getRequest();
            //修改图片
            news.setPic(picFileName);
            //更新时间
            Date date = new Date(new java.util.Date().getTime()); //构建一个java.sql.Date对象
            news.setNewstime(date);
            // 3、调用DAO完成数据库的插入操作
            boolean flag = false;
            try {
                DAOFactory.getNewsDAOInstance().saveorupdate(news);
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
            DAOFactory.getNewsDAOInstance().delete(id);
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
            String[] id = request.getParameterValues("newsId"); //存放被选中管理员的id号
            if (id != null) { //判断是否有管理员被选中
                for (int i = 0; i < id.length; i++) {
                    DAOFactory.getNewsDAOInstance().delete(Integer.parseInt(id[i]));
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


    public NewsInfo getNews() {
        return news;
    }

    public void setNews(NewsInfo news) {
        this.news = news;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*  public int getNewsid() {
        return newsid;
    }

    public void setNewsid(int newsid) {
        this.newsid = newsid;
    }*/

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

    public int getCountdown() {
        return countdown;
    }

    public void setCountdown(int countdown) {
        this.countdown = countdown;
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
