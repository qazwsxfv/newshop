package entity;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {
	private int currentPage;
	private int pageSize;
	private int pageCount;
	private String url;
	private List<T>list=new ArrayList<>();
	public Page(int currentPage, int pageSize) {
		this.currentPage=currentPage;
		this.pageSize=pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
