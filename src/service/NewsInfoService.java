/*
package service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DataBaseConnection;
import entity.NewsInfo;

*/
/* 新闻信息表NewsInfo对应的服务类
 * 创建者：FHN		日期：2017.10.19
 * 修改者：		日期：
 *//*

public class NewsInfoService {
    // 添加新闻
    public void insert(NewsInfo news) {
        // 连接数据库
        DataBaseConnection db = new DataBaseConnection();
        Connection con = db.getConnection();

        // 发送sql语句
        Date date = new Date(new java.util.Date().getTime()); //构建一个java.sql.Date对象
        String sql = "insert into newsinfo(title,newstime,editor,news,countdown,pic) values(?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, news.getTitle());
            pstmt.setDate(2,date);
            pstmt.setString(3, news.getEditor());
            pstmt.setString(4, news.getNews());
            pstmt.setInt(5, news.getCountdown());
            pstmt.setInt(6, news.getPic());
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    //删除新闻
    public void delete(int id) {

        // 连接数据库
        DataBaseConnection db = new DataBaseConnection();
        Connection con = db.getConnection();

        // 使用PreparedStatement对象发送sql语句
        String query = "delete FROM newsinfo where newsid=?";

        PreparedStatement pstmt = null; // 创建PreparedStatement对象
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();// 更新数据
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // 发送sql语句

    }

    // 批量删除

    // 查找新闻
    public NewsInfo querybyid(int id) {
        NewsInfo news = new NewsInfo();

        // 连接数据库
        DataBaseConnection db = new DataBaseConnection();
        Connection con = db.getConnection();

        // 发送sql语句
        String sql = "select * from newsinfo where id=?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            news.setNewsid(rs.getInt("id"));
            news.setTitle(rs.getString("title"));
            news.setNewstime(rs.getDate("newstime"));
            news.setEditor(rs.getString("editor"));
            news.setNews(rs.getString("news"));
            news.setPic(rs.getInt("pic"));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return news;
    }

    // 通过id修改新闻
    public void updatebyid(NewsInfo news) {
        // 连接数据库
        DataBaseConnection db = new DataBaseConnection();
        Connection con = db.getConnection();

        Date date = new Date(new java.util.Date().getTime()); //构建一个java.sql.Date对象
        String sql = "update newsinfo set title=?,newstime=?,editor=?,news=?,countdown=?,pic=? where id=?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, news.getTitle());
            pstmt.setDate(2,date);
            pstmt.setString(3, news.getEditor());
            pstmt.setString(4, news.getNews());
            pstmt.setInt(5, news.getCountdown());
            pstmt.setInt(6, news.getPic());
            pstmt.executeUpdate();// 更新数据
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    // 查询全部新闻
    public List queryall() {
        List newslist = new ArrayList();

        // 连接数据库
        DataBaseConnection db = new DataBaseConnection();
        Connection con = db.getConnection();

        // 发送sql语句
        String sql = "select * from newsinfo";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                NewsInfo news = new NewsInfo();
                news.setNewsid(rs.getInt("newsid"));
                news.setTitle(rs.getString("title"));
                news.setNewstime(rs.getDate("newstime"));
                news.setEditor(rs.getString("editor"));
                news.setNews(rs.getString("news"));
                news.setCountdown(rs.getInt("countdown"));
                news.setPic(rs.getInt("pic"));
                newslist.add(news);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return newslist;
    }
    // 按页码查询
}
*/
