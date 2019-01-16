package dao;

import java.util.List;

public interface CommonDao<T> {

	List<T> getList(int i, int pageSize,String a);
	 int getCount(String a);
	T getById(Integer id,String a);
	int update(T t);
	int delete(Integer id,String a);
	int add(T t);
	int batchDel(String[] ids,String a);
	List<T> getList(String a);
}
