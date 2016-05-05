package test.dao;

import org.junit.Test;

import cn.nju.user.dao.UserDao;
import cn.nju.user.domain.User;
import cn.nju.user.service.DaoFactory;

public class UserDaoTest {
	@Test
	public void testFindByUsername(){
		UserDao userDao=DaoFactory.getUserDao();
		User user=userDao.findByUsername("王五");
		System.out.println(user);
	}
	
	@Test
	public void testAdd(){
		UserDao userDao=DaoFactory.getUserDao();
		User user=new User();
		user.setUsername("王五");
		user.setPassword("wangwu");
		user.setAge(25);
		user.setGender("男");
		userDao.add(user);
	}
}
