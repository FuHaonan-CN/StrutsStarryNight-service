package factory;

import dao.*;
import dao.impl.*;

//一个方法一个接口
public class DAOFactory
{
	//AdminDAO接口
	public static AdminDAO getAdminDAOInstance()
	{
		return new AdminDAOImpl();
	}
	//NewsDAO接口
	public static NewsDAO getNewsDAOInstance()
	{
		return new NewsDAOImpl();
	}

	//UserDAO接口
	public static UserDAO getUserDAOInstance()
	{
		return new UserDAOImpl();
	}

	//PostDAO接口
	public static PostDAO getPostDAOInstance()
	{
		return new PostDAOImpl();
	}
	
//	//HomepageDAO接口
//	public static HomepageDAO getHomepageDAOInstance()
//		{
//			return new HomepageDAOImpl();
//		}

}


/*
public class UserDaoFactory {

    private static UserDao userDao = null;// 注意此句必须放在实例化工厂类的语句之前，否者会在运行时被置为null
    private static UserDaoFactory userDaoFactory = new UserDaoFactory();

    private UserDaoFactory() {
        Properties properties = new Properties();
        InputStream inputStream = UserDaoFactory.class.getClassLoader()
                .getResourceAsStream("daoConfig.properties");
        try {
            properties.load(inputStream);
            String userDaoImpl = properties.getProperty("userDaoImpl");
            userDao = (UserDao) Class.forName(userDaoImpl).newInstance();
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);// 此问题非常严重
        }finally{
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static UserDaoFactory getInstance() {
        return userDaoFactory;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}
*/