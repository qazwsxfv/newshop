package service;

import java.util.List;

import entity.Page;
import entity.User;

public interface CommonService<T> {
	Page<T> getPage(String current,String a);
	
	T getById(Integer id,String a);

	int update(T t);

	int deleteById(Integer id,String a);

	int add(T t);

	int batchDel(String[] ids,String a);
	List<T>getList(String a);
}
