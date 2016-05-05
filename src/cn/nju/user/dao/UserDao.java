package cn.nju.user.dao;

import cn.nju.user.domain.User;

public interface UserDao {
	public User findByUsername(String username);
	public void add(User user);
}
