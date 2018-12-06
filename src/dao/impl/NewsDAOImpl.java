package dao.impl;

import dao.NewsDAO;
import entity.NewsInfo;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateSessionFactory;

import java.util.List;

public class NewsDAOImpl implements NewsDAO {
    //获取session
    private Session session = HibernateSessionFactory.getSession();

    // 增加或修改admin账号
    public void saveorupdate(NewsInfo news) throws Exception {
        try {
            session.saveOrUpdate(news);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除一条新闻
    @Override
    public void delete(int id) throws Exception {
        try {
            NewsInfo news = (NewsInfo) session.load(NewsInfo.class, id);
            session.delete(news);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //查询一条新闻
    @Override
    public NewsInfo querybyid(int id) throws Exception {
        try {
            NewsInfo news = (NewsInfo) session.get(NewsInfo.class, id);
            return news;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List querrybykey(int countdown, String key, int page) throws Exception {
        // TODO Auto-generated method stub
        try {
            String hql = "FROM NewsInfo n WHERE n.title LIKE :title ";
            if (countdown != 0) {
                hql += "AND n.countdown = :countdown ";
            }
            Query query = session.createQuery(hql);
            if (countdown != 0)
                query.setInteger("countdown", countdown);

            query.setString("title", key);

            int pageNo = page;
            int pageSize = 6;
            int start = (pageNo - 1) * pageSize;

            List<NewsInfo> newsList = query.setFirstResult(start)
                    .setMaxResults(pageSize)
                    .list();
            return newsList;

        } catch (RuntimeException re) {
            throw re;
        }
    }

    // 页码显示
    @Override
    public int showSumPages(int countdown, String key, int page) throws Exception {
        // TODO Auto-generated method stub
        int Rowcount = 0;
        try {
            String hql = "FROM NewsInfo n WHERE n.title LIKE :title ";
            if (countdown != 0) {
                hql += "AND n.countdown = :countdown ";
            }
            Query query = session.createQuery(hql);
            if (countdown != 0)
                query.setInteger("countdown", countdown);
            query.setString("title", key);

            Rowcount = query.list().size();
            return Rowcount;

        } catch (RuntimeException re) {
            throw re;
        }
    }

    //查询最新的六条新闻
    @Override
    public List querySexNews() throws Exception {
        try {
            //创建Query 对象
            String hql = "FROM NewsInfo n WHERE n.countdown=2 order by n.newsid desc";
            Query query = session.createQuery(hql);

            int pageSize = 6;
            int start = 0;

            //执行查询
            List<NewsInfo> newsList = query.setFirstResult(start)
                    .setMaxResults(pageSize)
                    .list();
            return newsList;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    //查询最新的4倒计时新闻
    @Override
    public NewsInfo queryOneCountdownNews() throws Exception {
        try {
            //创建Query 对象
            String hql = "FROM NewsInfo n WHERE n.countdown=1 order by n.newstime";
            Query query = session.createQuery(hql);

            int pageSize = 4;
            int start = 0;

            //执行查询
            List<NewsInfo> newsList = query.setFirstResult(start)
                    .setMaxResults(pageSize)
                    .list();
            return newsList.get(0);
        } catch (RuntimeException re) {
            throw re;
        }
    }
}

