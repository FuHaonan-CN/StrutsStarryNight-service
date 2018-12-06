package dao.impl;

import dao.AdminDAO;
import entity.Admin;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateSessionFactory;

import java.util.Iterator;
import java.util.List;

/* 管理员表admin对应的接口实现类
 * 创建者：FHN		日期：2017.10.20
 * 修改者：		日期：
 */
public class AdminDAOImpl implements AdminDAO {
    //获取session
    private Session session = HibernateSessionFactory.getSession();

    // 增加或修改admin账号
    public void saveorupdate(Admin admin) throws Exception {
        try {
            session.saveOrUpdate(admin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 删除admin账户
    @Override
    public void delete(int id) throws Exception {
        try {
            Admin admin = (Admin) session.load(Admin.class, id);
            session.delete(admin);
        } catch (Exception e) {
            e.printStackTrace();
        }
/*该语句执行2次才能真正删除*/
//        Admin admin1 = new Adin();
//        admin1.setId(id);
//        session.delete(admin1);
    }

    // 查询admin账户
    @Override
    public Admin querybyid(int id) throws Exception {
        // TODO Auto-generated method stub
        try {
            Admin admin = (Admin) session.get(Admin.class, id);
            return admin;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //通过用户名查找admin
    @Override
    public boolean querybyname(String adminName) throws Exception {
        // TODO Auto-generated method stub
        boolean flag = true;
        try {
            //创建Query 对象
            //基于命名参数
            String hql = "FROM Admin a " +
                    "WHERE a.adminname = :adminName ";
            Query query = session.createQuery(hql);

            //绑定参数

            //执行查询
            query.setString("adminName", adminName);
            int size = query.list().size();
            if (size > 0) {
                flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    // 按关键字查询
    @Override
    public List querrybykey(int privilege, String key, int page) throws Exception {
        // TODO Auto-generated method stub
//        int privilege1 = privilege;
//        String key1 = key ;

        try {
            //String queryString = "from StudentInfo s where s.name like '%"+sname+"%'";
//			String hql = "select * from admin a where a.privilege like'%"+privilege+"%' and a.adminname like'%"+key+"%' limit \"+(page - 1)*6+\",6";
            String hql = "FROM Admin a WHERE a.adminname LIKE :adminname ";
            if (privilege != 0) {
                hql += "AND a.privilege = :privilege ";
            }
            Query query = session.createQuery(hql);
            if (privilege != 0)
                query.setInteger("privilege", privilege);
            query.setString("adminname", key);

            int pageNo = page;
            int pageSize = 6;
            int start = (pageNo - 1) * pageSize;

            List<Admin> adminList = query.setFirstResult(start)
                    .setMaxResults(pageSize)
                    .list();
            return adminList;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    // 页码显示
    @Override
    public int showSumPages(int privilege, String key, int page) throws Exception {
        // TODO Auto-generated method stub
        int Rowcount = 0;

        int privilege1 = privilege;
        String key1 = "%" + key + "%";

        try {
            String hql = "FROM Admin a WHERE a.adminname LIKE :adminname ";
            if (privilege1 != 0) {
                hql += "AND a.privilege = :privilege ";
            }
            Query query = session.createQuery(hql);
            if (privilege1 != 0)
                query.setInteger("privilege", privilege1);
            query.setString("adminname", key1);

//            String hql = "SELECT count(*) FROM Admin a WHERE a.privilege = :privilege AND a.adminname LIKE :adminname ";
//            Query query = session.createQuery(hql);
//
//            query.setInteger("privilege",privilege1).setString("adminname",key1);

            Rowcount = query.list().size();
            return Rowcount;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    // 登陆验证
    @Override
    public Admin logincheck(Admin admin) throws Exception {
        // TODO Auto-generated method stub
        String adminname = admin.getAdminname();
        String adminpwd = admin.getAdminpassword();
        try {
            String hql = "FROM Admin a WHERE a.adminname = :adminname and a.adminpassword = :adminpwd";
            Query query = session.createQuery(hql);
            query.setString("adminname", adminname).setString("adminpwd", adminpwd);
            Iterator<Admin> result = query.iterate();
            if (result.hasNext()) {
                admin = result.next();
            } else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }

}
