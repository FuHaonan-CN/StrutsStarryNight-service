package dao;

import entity.PostInfo;

import java.util.List;

public interface PostDAO {
	// 增加或修改一个帖子
	public void saveorupdate(PostInfo post) throws Exception;

	//删除帖子
	public void delete(int id) throws Exception;

	//查询一个帖子
	public PostInfo querybyid(int id) throws Exception;

	// 按关键字查询
	public List querrybykey(String rightname, String key, int page) throws Exception;

	// 页码显示
	public int showSumPages(String rightname, String key, int page) throws Exception;

	//查询最新的六条新闻
	public List querySexPosts() throws Exception;

}
