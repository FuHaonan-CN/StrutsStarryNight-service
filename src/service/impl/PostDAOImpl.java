package service.impl;

import dao.PostDAO;
import entity.PostInfo;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateSessionFactory;

import java.util.List;

/* Post对应的接口实现类
 * 创建者：FHN		日期：2017.10.20
 * 修改者：		日期：
 */
public class PostDAOImpl implements PostDAO {
    //获取session
    private Session session = HibernateSessionFactory.getSession();

    // 增加或修改admin账号
    public void saveorupdate(PostInfo post) throws Exception {
        try {
            session.saveOrUpdate(post);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除一条新闻
    @Override
    public void delete(int id) throws Exception {
        try {
            PostInfo post = (PostInfo) session.load(PostInfo.class, id);
            session.delete(post);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //查询一条新闻
    @Override
    public PostInfo querybyid(int id) throws Exception {
        try {
            PostInfo post = (PostInfo) session.load(PostInfo.class, id);
            return post;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List querrybykey(String rightname, String key, int page) throws Exception {
        // TODO Auto-generated method stub
        try {
            String hql = "FROM PostInfo p WHERE p.posttitle LIKE :title AND p.rightname LIKE :rightname ";

            Query query = session.createQuery(hql);
            query.setString("rightname", rightname);
            query.setString("title", key);

            int pageNo = page;
            int pageSize = 6;
            int start = (pageNo - 1) * pageSize;

            List<PostInfo> postList = query.setFirstResult(start)
                    .setMaxResults(pageSize)
                    .list();
            return postList;

        } catch (RuntimeException re) {
            throw re;
        }
    }

         // 页码显示
         @Override
         public int showSumPages(String rightname, String key, int page) throws Exception {
             // TODO Auto-generated method stub
             int Rowcount = 0;
             try {
                 String hql = "FROM PostInfo p WHERE p.posttitle LIKE :title AND p.rightname LIKE :rightname ";

                 Query query = session.createQuery(hql);
                 query.setString("rightname", rightname);
                 query.setString("title", key);

                 Rowcount = query.list().size();
                 return Rowcount;

             } catch (RuntimeException re) {
                 throw re;
             }
         }
    //查询最新的六条新闻
    @Override
    public List querySexPosts() throws Exception {
        try {
            //创建Query 对象
            String hql = "FROM PostInfo p order by p.postid desc";
            Query query = session.createQuery(hql);

            int pageSize = 6;
            int start = 0;

            //执行查询
            List<PostInfo> postList = query.setFirstResult(start)
                    .setMaxResults(pageSize)
                    .list();
            return postList;
        } catch (RuntimeException re) {
            throw re;
        }


    }
}
