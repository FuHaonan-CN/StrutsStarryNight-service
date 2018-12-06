package dao;

import entity.Admin;
import entity.UserBasicInfo;import java.util.List;

public interface UserDAO {
    // 增加或修改admin账号
    public void saveorupdate(UserBasicInfo user) throws Exception;

    // 删除user账户
    public void delete(int id) throws Exception;

    // 查询user账户
    public UserBasicInfo querybyid(int id) throws Exception;

    //通过用户名查询user账户
    public boolean querybyname(String userName) throws Exception;

    // 按关键字查询
    public List querrybykey(String key, int page) throws Exception;

    // 页码显示
    public int showSumPages(String key, int page) throws Exception;

    // 登陆验证
    public UserBasicInfo logincheck(UserBasicInfo user) throws Exception;
}
