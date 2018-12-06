package service.impl;

import dao.AdminDAO;
import entity.Admin;
import factory.DAOFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.AdminService;
import util.HibernateSessionFactory;

import java.util.Iterator;
import java.util.List;

/* 管理员表admin对应的接口实现类
 * 创建者：FHN		日期：2017.10.20
 * 修改者：		日期：
 */
public class AdminServiceImpl implements AdminService {
    //获取session
    private Session session = HibernateSessionFactory.getSession();
    private Transaction transaction = null;
    // 增加或修改admin账号
    public void saveorupdate(Admin admin) throws Exception {
       try {
        //开启事务
        transaction = session.beginTransaction();

        DAOFactory.getAdminDAOInstance().saveorupdate(admin);

        //提交事务
        transaction.commit();
    } catch (Exception e) {
        e.printStackTrace();
        transaction.rollback();
    } finally {
        session.close();
    }
    }

    // 删除admin账户
    @Override
    public void delete(int id) throws Exception {
        try {
            //开启事务
            transaction = session.beginTransaction();

            DAOFactory.getAdminDAOInstance().delete(id);

            //提交事务
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    // 查询admin账户
    @Override
    public Admin querybyid(int id) throws Exception {
        try {
            //开启事务
            transaction = session.beginTransaction();

            Admin admin = DAOFactory.getAdminDAOInstance().querybyid(id);

            //提交事务
            transaction.commit();
            return admin;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return null;
    }

    //通过用户名查找admin
    @Override
    public boolean querybyname(String adminName) throws Exception {
        boolean flag = true;
        try {
            //开启事务
            transaction = session.beginTransaction();

            flag = DAOFactory.getAdminDAOInstance().querybyname(adminName);

            //提交事务
            transaction.commit();
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return flag;
    }

    // 按关键字查询
    @Override
    public List querrybykey(int privilege, String key, int page) throws Exception {
        List<Admin> adminList = null;
        try {
            //开启事务
            transaction = session.beginTransaction();

            adminList= DAOFactory.getAdminDAOInstance().querrybykey(privilege,key,page);

            //提交事务
            transaction.commit();
            return adminList;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return adminList;
    }

    // 页码显示
    @Override
    public int showSumPages(int privilege, String key, int page) throws Exception {
        // TODO Auto-generated method stub
        int Rowcount = 0;
        try {
            //开启事务
            transaction = session.beginTransaction();

            Rowcount = DAOFactory.getAdminDAOInstance().showSumPages(privilege,key,page);

            //提交事务
            transaction.commit();
            return Rowcount;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    // 登陆验证
    @Override
    public Admin logincheck(Admin admin) throws Exception {
        try {
            //开启事务
            transaction = session.beginTransaction();

            Admin admin1 = DAOFactory.getAdminDAOInstance().logincheck(admin);

            //提交事务
            transaction.commit();
            return admin1;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return null;
    }
}
