package entity;

public class GoodsTypeDomain {
	private Integer id;  //类别编号
	private String gtype_name;  //商品类别名
	private Integer gtype_parentid;   //
	private String gtype_pic;
	private String  parentName;  //父类名称 --新增
	@Override
	public String toString() {
		return "GoodsTypeDomain [id=" + id + ", gtype_name=" + gtype_name + ", gtype_parentid=" + gtype_parentid
				+ ", gtype_pic=" + gtype_pic + ", parentName=" + parentName + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGtype_name() {
		return gtype_name;
	}
	public void setGtype_name(String gtype_name) {
		this.gtype_name = gtype_name;
	}
	public Integer getGtype_parentid() {
		return gtype_parentid;
	}
	public void setGtype_parentid(Integer gtype_parentid) {
		this.gtype_parentid = gtype_parentid;
	}
	public String getGtype_pic() {
		return gtype_pic;
	}
	public void setGtype_pic(String gtype_pic) {
		this.gtype_pic = gtype_pic;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public GoodsTypeDomain(Integer id, String gtype_name, Integer gtype_parentid, String gtype_pic, String parentName) {
		super();
		this.id = id;
		this.gtype_name = gtype_name;
		this.gtype_parentid = gtype_parentid;
		this.gtype_pic = gtype_pic;
		this.parentName = parentName;
	}
	public GoodsTypeDomain() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
