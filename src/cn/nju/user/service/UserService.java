package cn.nju.user.service;

import cn.nju.user.dao.UserDao;
import cn.nju.user.domain.User;
/*
 * 业务逻辑层
 */
public class UserService {

	private UserDao userDao=DaoFactory.getUserDao();
	
	public void regist(User user)throws UserException{
		User findUser=userDao.findByUsername(user.getUsername());
		if(findUser!=null){
			throw new UserException("用户名"+user.getUsername()+"已被注册！");
		}else{
			userDao.add(user);
		}
	}
	
	public User login(User form)throws UserException{
		User findUser=userDao.findByUsername(form.getUsername());
		if(findUser==null)throw new UserException("用户名不存在！");
			
		if(!findUser.getPassword().equals(form.getPassword()))throw new UserException("密码错误！");
		
		return findUser;
		
	}
	
}
