package dao;

import java.util.List;

import entity.Page;
import entity.User;

public interface UserDao extends CommonDao<User> {

	int update(User u);



	User getBybackLogin(String name, String password);


}
