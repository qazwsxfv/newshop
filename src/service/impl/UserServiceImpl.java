package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entity.Page;

import entity.User;
import service.IUserService;

public class UserServiceImpl extends CommonServiceImpl<User> implements IUserService {
	private UserDao user=new UserDaoImpl();


	@Override
	public int updaeUser(User u) {
		// TODO Auto-generated method stub
		return user.update(u);
	}

	@Override
	public User getBybackLogin(String name, String password) {
		// TODO Auto-generated method stub
		return user.getBybackLogin(name,password);
	}


	}
	
