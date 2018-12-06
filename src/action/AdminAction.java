package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Admin;
import factory.DAOFactory;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class AdminAction extends ActionSupport {
    private Session session = HibernateSessionFactory.getSession();
    private Transaction transaction = null;
    private Admin admin;
    private int id;
    /*模糊查询*/
    private int privilege=0;
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
//            String privilege = "";
//            privilege = sort;
//            if (privilege == null || privilege.equals(""))
//                privilege = "0";
//            if (keywords == null)
//                keywords = "";
            String key = "%" + keywords.trim() + "%";
//            if (page == null) {
//                page = "1";
//            }
//            request.setAttribute("currentPrivilege", privilege);
//            request.setAttribute("currentKeywords", keywords);
//            request.setAttribute("currentPage", page);
//            admins = DAOFactory.getAdminDAOInstance().querrybykey(privilege, key, Integer.parseInt(page));
            request.setAttribute("all", DAOFactory.getAdminDAOInstance().querrybykey(privilege, key, page));
            request.setAttribute("count", DAOFactory.getAdminDAOInstance().showSumPages(privilege, key, page));
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
                String name = admin.getAdminname();
                String fileExtName=picFileName.substring(picFileName.lastIndexOf(".")-1);
//                System.out.println("文件的后缀名是"+fileExtName);

                picFileName = name+System.currentTimeMillis()+fileExtName;
                pic.renameTo(new File(file, picFileName));
            }

            HttpServletRequest request = ServletActionContext.getRequest();

            boolean b = false;
            b = DAOFactory.getAdminDAOInstance().querybyname(admin.getAdminname());
            if (!b) {
                request.setAttribute("errInf", "该用户已注册！请重新输入！");
                return "insertInput";
            } else {
                //插入图片
                admin.setPic(picFileName);
                // 3、调用DAO完成数据库的插入操作
                boolean flag = false;
                try {
                    DAOFactory.getAdminDAOInstance().saveorupdate(admin);
                    flag = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                request.setAttribute("flag", new Boolean(flag));
            }
            //提交事务
            transaction.commit();
            return "insert";
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
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
        }finally {
            session.close();
        }
        return "insert";
    }*/

    //updateInput加载更新页面
    public String updateInput() throws Exception {
        //开启事务
        transaction = session.beginTransaction();
        try {
            this.admin = DAOFactory.getAdminDAOInstance().querybyid(id);
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
                picFileName = admin.getPic();
            } else {
                //剪切：把临时文件剪切指定的位置，并且给他重命名
                String name = admin.getAdminname();
                String fileExtName=picFileName.substring(picFileName.lastIndexOf(".")-1);

                picFileName = name+System.currentTimeMillis()+fileExtName;
                pic.renameTo(new File(file, picFileName));
            }

            HttpServletRequest request = ServletActionContext.getRequest();

            admin.setPic(picFileName);
            // 3、调用DAO完成数据库的插入操作
            boolean flag = false;
            try {
                DAOFactory.getAdminDAOInstance().saveorupdate(admin);
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
            DAOFactory.getAdminDAOInstance().delete(id);
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
            String[] id = request.getParameterValues("adminId"); //存放被选中管理员的id号
            if (id != null) { //判断是否有管理员被选中
                for (int i = 0; i < id.length; i++) {
                    DAOFactory.getAdminDAOInstance().delete(Integer.parseInt(id[i]));
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
            b = DAOFactory.getAdminDAOInstance().querybyname(admin.getAdminname());
            if (b) {
                request.setAttribute("errInf", "该用户名尚未注册！请先注册或重新输入！");
                return "adminLogin";
            } else {
                admin = DAOFactory.getAdminDAOInstance().logincheck(admin);
                if (admin != null) {
                    request.getSession().setAttribute("admin", admin);
                } else {
                    request.setAttribute("errInf", "用户名或密码错误, 请重新输入!");
                    return "adminLogin";
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
        return "adminLoginSuccess";
    }

    // 账户注销
    public String logout() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        request.getSession().removeAttribute("admin");
        return "adminLogin";
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
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

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
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
//validate验证
   /* public void validateLogin() throws Exception {
        if (DAOFactory.getAdminDAOInstance().querybyname(getAdmin().getAdminname().trim())){
            addFieldError("admin.adminname","用户名不存在");
        }
    }*/
   //添加admin账号的数据验证
   /* public void validateInsert() throws Exception {
        String name = getAdmin().getAdminname().trim();
        if (!DAOFactory.getAdminDAOInstance().querybyname(name)){
        addFieldError("admin.adminname","该admin账户已存在！请重新输入！");
    }
}*/
}
