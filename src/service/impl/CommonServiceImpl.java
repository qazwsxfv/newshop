package service.impl;

import java.util.List;

import dao.CommonDao;
import dao.impl.CommonDaoImpl;
import entity.Page;
import entity.User;
import service.CommonService;

public class CommonServiceImpl<T> implements CommonService<T>{
	private CommonDao<T> dao=new CommonDaoImpl<>();
	@Override
	public Page<T> getPage(String current,String a) {
			int indexPage=1;
			if(current!=null) {
				indexPage=Integer.parseInt(current);
			}
			int pageSize=5;
			int count=dao.getCount(a);
			int pageCount=count/5;
			pageCount=count/5==0?pageCount:pageCount+1;
			Page<T> page=new Page(indexPage,pageSize);
			page.setList(dao.getList((indexPage-1)*pageSize,pageSize,a));
			page.setPageCount(pageCount);
			// TODO Auto-generated method stub
			return page;
		}
	
	@Override
	public T getById(Integer id,String a) {
		// TODO Auto-generated method stub
		return dao.getById(id,a);
	}

	@Override
	public int update(T t) {
		// TODO Auto-generated method stub
		return dao.update(t);
	}

	@Override
	public int deleteById(Integer id,String a) {
		// TODO Auto-generated method stub
		return dao.delete(id,a);
	}

	@Override
	public int add(T t) {
		// TODO Auto-generated method stub
		return dao.add(t);
	}

	@Override
	public int batchDel(String[] ids,String a) {
		// TODO Auto-generated method stub
		return dao.batchDel(ids,a);
	}

	@Override
	public List<T> getList(String a) {
		// TODO Auto-generated method stub
		return dao.getList(a);
	}
	
}
