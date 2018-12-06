package dao;

import entity.Admin;
import entity.NewsInfo;

import java.util.List;

/* 新闻信息表NewsInfo对应的接口
 * 创建者：FHN		日期：2017.10.19
 * 修改者：		日期：
 */
public interface NewsDAO {
    // 增加或修改admin账号
    public void saveorupdate(NewsInfo news) throws Exception;

    //删除新闻
    public void delete(int id) throws Exception;

    //查询一条新闻
    public NewsInfo querybyid(int id) throws Exception;

    // 按关键字查询
    public List querrybykey(int countdown, String key, int page) throws Exception;

    // 页码显示
    public int showSumPages(int countdown, String key, int page) throws Exception;

    //查询最新的六条新闻
    public List querySexNews() throws Exception;

    //查询最新的四条倒计时新闻
    public NewsInfo queryOneCountdownNews() throws Exception;

}
