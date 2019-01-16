package service;

import entity.Page;
import entity.User;

public interface IUserService extends CommonService<User> {


	int updaeUser(User user);


	User getBybackLogin(String name, String password);



}
