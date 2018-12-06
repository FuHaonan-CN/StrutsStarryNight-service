package dao.impl;

import dao.UserDAO;
import db.DataBaseConnection;
import entity.Admin;
import entity.UserBasicInfo;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateSessionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/* 用户表user对应的接口实现类
     * 创建者：FHN		日期：2017.10.20
     * 修改者：		日期：
     */
public class UserDAOImpl implements UserDAO {
    //获取session
    private Session session = HibernateSessionFactory.getSession();

    // 增加或修改admin账号
    public void saveorupdate(UserBasicInfo user) throws Exception {
        try {
            session.saveOrUpdate(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 删除admin账户
    @Override
    public void delete(int id) throws Exception {
        try {
            UserBasicInfo user = (UserBasicInfo) session.load(UserBasicInfo.class, id);
            session.delete(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 查询用户账户
    @Override
    public UserBasicInfo querybyid(int id) throws Exception {
        try {
            UserBasicInfo user = (UserBasicInfo) session.get(UserBasicInfo.class, id);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    //通过用户名查找user
    @Override
    public boolean querybyname(String userName) throws Exception {
        // TODO Auto-generated method stub
        boolean flag = true;
        try {
            //创建Query 对象
            //基于命名参数
            String hql = "FROM UserBasicInfo u " +
                    "WHERE u.username = :username ";
            Query query = session.createQuery(hql);

            //绑定参数

            //执行查询
            query.setString("username", userName);
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
    public List querrybykey(String key, int page) throws Exception {
        // TODO Auto-generated method stub
        String key1 = "%" + key + "%";

        try {
            String hql = "FROM UserBasicInfo u WHERE u.username LIKE :username ";
            Query query = session.createQuery(hql);
            query.setString("username", key1);
            int pageNo = page;
            int pageSize = 6;
            int start = (pageNo - 1) * pageSize;

            List<UserBasicInfo> userList = query.setFirstResult(start)
                    .setMaxResults(pageSize)
                    .list();
            return userList;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    // 页码显示
    @Override
    public int showSumPages(String key, int page) throws Exception {
        // TODO Auto-generated method stub
        int Rowcount = 0;
        String key1 = "%" + key + "%";
        try {
            String hql = "FROM UserBasicInfo u WHERE u.username LIKE :username ";
            Query query = session.createQuery(hql);

            query.setString("username", key1);

            Rowcount = query.list().size();
            return Rowcount;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    // 登陆验证
    @Override
    public UserBasicInfo logincheck(UserBasicInfo user) throws Exception {
        // TODO Auto-generated method stub
        String username = user.getUsername();
        String userpwd = user.getUserpassword();
        try {
            //hql语句
            String hql = "FROM UserBasicInfo u WHERE u.username = :username and u.userpassword = :userpwd";
            //query对象
            Query query = session.createQuery(hql);
            //绑定参数
            query.setString("username", username).setString("userpwd", userpwd);

            Iterator<UserBasicInfo> result = query.iterate();
            if (result.hasNext()) {
                user = result.next();
            } else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
