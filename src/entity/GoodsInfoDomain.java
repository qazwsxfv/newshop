package entity;

public class GoodsInfoDomain {
	private Integer id;
	private String goods_name;
	private String goods_description;   //商品描述
	private String goods_pic;     //商品图片
	private Double goods_price;   //原价
	private Integer goods_stock;  //库存
	private Double goods_price_off;  //促销价
	private Double goods_discount;  //折扣
	private Integer goods_parentid;  //大类id  
	private Integer goods_fatherid;  //小类id
	private Integer count;    //增加数量的属性与前端对应
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGoods_description() {
		return goods_description;
	}
	public void setGoods_description(String goods_description) {
		this.goods_description = goods_description;
	}
	public String getGoods_pic() {
		return goods_pic;
	}
	public void setGoods_pic(String goods_pic) {
		this.goods_pic = goods_pic;
	}
	public Double getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(Double goods_price) {
		this.goods_price = goods_price;
	}
	public Integer getGoods_stock() {
		return goods_stock;
	}
	public void setGoods_stock(Integer goods_stock) {
		this.goods_stock = goods_stock;
	}
	public Double getGoods_price_off() {
		return goods_price_off;
	}
	public void setGoods_price_off(Double goods_price_off) {
		this.goods_price_off = goods_price_off;
	}
	public Double getGoods_discount() {
		return goods_discount;
	}
	public void setGoods_discount(Double goods_discount) {
		this.goods_discount = goods_discount;
	}
	public Integer getGoods_parentid() {
		return goods_parentid;
	}
	public void setGoods_parentid(Integer goods_parentid) {
		this.goods_parentid = goods_parentid;
	}
	public Integer getGoods_fatherid() {
		return goods_fatherid;
	}
	public void setGoods_fatherid(Integer goods_fatherid) {
		this.goods_fatherid = goods_fatherid;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "GoodsInfoDomain [id=" + id + ", goods_name=" + goods_name + ", goods_description=" + goods_description
				+ ", goods_pic=" + goods_pic + ", goods_price=" + goods_price + ", goods_stock=" + goods_stock
				+ ", goods_price_off=" + goods_price_off + ", goods_discount=" + goods_discount + ", goods_parentid="
				+ goods_parentid + ", goods_fatherid=" + goods_fatherid + ", count=" + count + "]";
	}
	public GoodsInfoDomain(Integer id, String goods_name, String goods_description, String goods_pic,
			Double goods_price, Integer goods_stock, Double goods_price_off, Double goods_discount,
			Integer goods_parentid, Integer goods_fatherid, Integer count) {
		super();
		this.id = id;
		this.goods_name = goods_name;
		this.goods_description = goods_description;
		this.goods_pic = goods_pic;
		this.goods_price = goods_price;
		this.goods_stock = goods_stock;
		this.goods_price_off = goods_price_off;
		this.goods_discount = goods_discount;
		this.goods_parentid = goods_parentid;
		this.goods_fatherid = goods_fatherid;
		this.count = count;
	}
	public GoodsInfoDomain() {
		super();
		// TODO Auto-generated constructor stub
	}
}
