package service;

import entity.Admin;

import java.util.List;

/* 管理员表admin对应的服务类
 * 创建者：FHN		日期：2017.10.20
 * 修改者：		日期：
 */

public interface AdminService {
    // 增加或修改admin账号
    public void saveorupdate(Admin admin) throws Exception;

    // 删除admin账户
    public void delete(int id) throws Exception;

    // 查询admin账户
    public Admin querybyid(int id) throws Exception;

    //通过用户名查询admin账户
    public boolean querybyname(String adminName) throws Exception;

    // 按关键字查询
    public List querrybykey(int privilege, String key, int page) throws Exception;

    // 页码显示
    public int showSumPages(int privilege, String key, int page) throws Exception;

    // 登陆验证
    public Admin logincheck(Admin admin) throws Exception;

}

