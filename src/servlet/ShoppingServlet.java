package servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import entity.Address;
import entity.GoodsInfo;
import entity.GoodsInfoDomain;
import entity.GoodsType;
import entity.Order;
import entity.OrderDetail;
import entity.ShoppingCart;
import entity.User;
import service.IAddressService;
import service.IGoodsInfoService;
import service.IGoodsTypeService;
import service.OrderDetailService;
import service.OrderService;
import service.impl.AddressServiceImpl;
import service.impl.GoodsInfoServiceImpl;
import service.impl.GoodsTypeServiceImpl;
import service.impl.OrderDetailServiceImpl;
import service.impl.OrderServiceImpl;

/**
 * Servlet implementation class ShoppingServlet
 */
public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
  //商品信息的业务助理
  	private IGoodsInfoService goodsInfoService = new GoodsInfoServiceImpl();
  	//商品类别的业务助理
  	private IGoodsTypeService goodsTypeService = new GoodsTypeServiceImpl();
  	
  	//获取地址的业务助理
  	private IAddressService addressService = new AddressServiceImpl(); 
  	
  	//增加订单的业务助理
  	private OrderService orderService = new OrderServiceImpl();
  	//增加订单明细的业务助理
  	private OrderDetailService orerDetialService = new OrderDetailServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String action=request.getParameter("action");
		 if("findAll".equals(action)){
				List<GoodsInfo> giList = goodsInfoService.getList("GoodsInfo");
				
				List<GoodsType> gtList = goodsTypeService.getList("GoodsType");
				
				request.setAttribute("giList", giList);
				request.setAttribute("gtList", gtList);
				//将商品信息和商品类别的数据展示到前端首页
				request.getRequestDispatcher("index.jsp").forward(request, response);
		 }else if("getInfoById".equals(action)){
				Integer id = Integer.parseInt(request.getParameter("id"));
				//根据id取出单个商品对象
				GoodsInfo goodsInfo = goodsInfoService.getById(id, "GoodsInfo");
				//展示单个商品到前端
				request.setAttribute("goodsInfo", goodsInfo);
				request.getRequestDispatcher("introduction.jsp").forward(request, response);
			}else if("toShoppingCart".equals(action)){
				Integer id = Integer.parseInt(request.getParameter("id"));
				Integer count = Integer.parseInt(request.getParameter("count"));
				
				//根据id得到商品对象
				GoodsInfo goodsInfo = goodsInfoService.getById(id, "GoodsInfo");
				
				//将GoodsInfo的数据注入到Domain中，并加入count的值
				//根据id和count，将商品对象和数量存到购物车,如何存数量---购物车中加一个Domain
				GoodsInfoDomain domain = new GoodsInfoDomain();
				try {
					BeanUtils.copyProperties(domain, goodsInfo); //将一个对象的属性值注入到目标对象中
					domain.setCount(count);
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}  
				
				
				//从session中获取购物车
				ShoppingCart cart = getShoppingCartfromSession(request.getSession());
				cart.add(domain);  //添加购物车数据

			}else if("update".equals(action)){
				Integer id = Integer.parseInt(request.getParameter("id"));
				Integer count = Integer.parseInt(request.getParameter("count"));
				//更新购物车
				ShoppingCart cart = getShoppingCartfromSession(request.getSession());
				cart.update(id,count);
			}else if("pay".equals(action)){
				//到达支付页面前，先判断有没有前端用户凭证
				User user = (User) request.getSession().getAttribute("user");
				if(user == null){
					//跳到前端的登录页面
					response.sendRedirect("login.jsp");
				}else{
					//根据前端用户的id将对应的地址表中的匹配地址记录带出来
					List<Address> addrList = addressService.getAddrListByUserId(user.getId());
					request.setAttribute("addrList", addrList);
					request.getRequestDispatcher("pay.jsp").forward(request, response);
				}
			}else if("sumbitOrder".equals(action)){
				String shouhuoren = request.getParameter("shouhuoren");
				String phone = request.getParameter("phone");
				String address = request.getParameter("address");
				String express = request.getParameter("express");
				String bank = request.getParameter("bank");
				
				//1. 获取到收货信息后，将数据存储到订单表中
				//1.1 总价格从购物车中获取，userid从用户表中获取
				ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("shoppingCart");
				User user = (User) request.getSession().getAttribute("user");
				if(user != null){
					//int result = orderService.add(new Order(0, express, bank, cart.getPrice(), null, user.getId(), shouhuoren, phone, address));
					int orderid = orderService.addReturnPrimarykey(new Order(0, express, bank, cart.getPrice(), null, user.getId(), shouhuoren, phone, address));
					//2. 该订单明细（购物车中的数据）存储到订单明细表中
					//注意：订单表中的订单id，对应有多条订单明细
					//关键点： 获取订单id
					//方法1: 自定义订单id传入订单表，同时在订单明细中也传入该订单id
					//方法2： 添加订单时，主键回填----推荐，
					List<OrderDetail> orderDetails = new ArrayList<>();
					for(GoodsInfoDomain domain : cart.getList()){
						//注意：尽量不要循环在数据库中操作数据
						//orerDetialService.add(new OrderDetail(0, orderid, domain.getId(), domain.getGoods_name(), domain.getGoods_price(),
						//		domain.getGoods_description(), domain.getCount(), domain.getGoods_pic(), domain.getCount()*domain.getGoods_price(), null));
						
						OrderDetail orderDetail = new OrderDetail(0, orderid, domain.getId(), domain.getGoods_name(), domain.getGoods_price(),
										domain.getGoods_description(), domain.getCount(), domain.getGoods_pic(), domain.getCount()*domain.getGoods_price(), null);
						
						orderDetails.add(orderDetail);
					}
					
					orerDetialService.batchAdd(orderDetails);
					
					//匹配插入数据的方式
					
					//带数据过去。。略
					request.getRequestDispatcher("success.jsp").forward(request, response);
					
				}else{
					response.sendRedirect("login.jsp");
				}

			}
		}
	


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private ShoppingCart getShoppingCartfromSession(HttpSession session) {
		//将购物车放入session中
		ShoppingCart cart = (ShoppingCart) session.getAttribute("shoppingCart");
		if(cart == null){
			cart = new ShoppingCart();
			//将购物车放入session
			session.setAttribute("shoppingCart", cart);
		}
		return cart;
	}

}
