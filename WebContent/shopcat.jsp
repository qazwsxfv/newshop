<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

<title>购物车页面</title>

<link href="AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet"
	type="text/css" />
<link href="basic/css/demo.css" rel="stylesheet" type="text/css" />
<link href="css/cartstyle.css" rel="stylesheet" type="text/css" />
<link href="css/optstyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
<script type="text/javascript">

	function sub(id){
		debugger
		var value = parseInt($("#count_"+id).val());
		if(value>1){
			var count = value-1;
			$("#count_"+id).val(count);
			
			var param = new Object();
			param.id=id;
			param.count=count;
			
			//将id,和count的变动信息传到后台更新购物车  ，  同时异步刷新小计和总价
			$.get("ShoppingServlet?action=update",param,function(){
				location.reload();  //刷新
			});
		}
	}
	
	function add(id){
		debugger
		var stock = parseInt($("#stock_"+id).val());
		var value = parseInt($("#count_"+id).val());
		if(value<stock){  //如果小于库存，则+1，否则不变
			var count = value+1;
			$("#count_"+id).val(count);
			
			var param = new Object();
			param.id=id;
			param.count=count;
		
			//将id,和count的变动信息传到后台更新购物车  ，  同时异步刷新小计和总价
			$.get("ShoppingServlet?action=update",param,function(){
				location.reload();  //刷新
			});
		}
	}


/* 	$(function(){
		
		$("#min").click(function(){
			debugger
			var value = parseInt($("#count_id").val());
			if(value>1){
				$("#count_id").val(value-1);
			}
		})
		
		$("#add").click(function(){
			debugger
			var stock = parseInt($("#stock").val());
			var value = parseInt($("#count_id").val());
			if(value<stock){  //如果小于库存，则+1，否则不变
				$("#count_id").val(value+1);
			}
			
		})
	}) */
</script>
</head>

<body>

	<!--顶部导航条 -->
	<%@include file="head.jsp"%>

	<!--购物车 -->
	<div class="concent">
		<div id="cartTable">
			<div class="cart-table-th">
				<div class="wp">
					<div class="th th-chk">
						<div id="J_SelectAll1" class="select-all J_SelectAll"></div>
					</div>
					<div class="th th-item">
						<div class="td-inner">商品信息</div>
					</div>
					<div class="th th-price">
						<div class="td-inner">单价</div>
					</div>
					<div class="th th-amount">
						<div class="td-inner">数量</div>
					</div>
					<div class="th th-sum">
						<div class="td-inner">金额</div>
					</div>
					<div class="th th-op">
						<div class="td-inner">操作</div>
					</div>
				</div>
			</div>
			<div class="clear"></div>

			<tr class="item-list">
				<div class="bundle  bundle-last ">
					<div class="bundle-hd">
						<div class="bd-promos"></div>
					</div>
					<div class="clear"></div>
					<div class="bundle-main"></div>
				</div>
			</tr>
			<div class="clear"></div>
				<c:forEach items="${shoppingCart.list}" var="domain">
				
				
				<tr class="item-list">
					<div class="bundle  bundle-last ">

						<div class="clear"></div>
						<div class="bundle-main">
						<!-- 购物车 -->
							<ul class="item-content clearfix">
								
								<li class="td td-item">
									<div class="item-pic">
										<a href="#" target="_blank" class="J_MakePoint"
											data-point="tbcart.8.12"> <img
											src="images/${domain.goods_pic}"
											style="width: 80px; height: 80px" class="itempic J_ItemImg"></a>
									</div>
									<div class="item-info">
										<div class="item-basic-info">
											<a href="#" target="_blank" title=""
												class="item-title J_MakePoint" data-point="tbcart.8.11">${domain.goods_description}</a>
										</div>
									</div>
								</li>
								<li class="td td-info">
									<div class="item-props item-props-can">

										<i class="theme-login am-icon-sort-desc"></i>
									</div>
								</li>
								<li class="td td-price">
									<div class="item-price price-promo-promo">
										<div class="price-content">
													<div class="price-line">
														<em class="price-original">${domain.goods_price_off}</em>
													</div>
														
													<div class="price-line">
														<em class="J_Price price-now" tabindex="0">${domain.goods_price}</em>
													</div>
										</div>
									</div>
								</li>
								<li class="td td-amount">
									<div class="amount-wrapper ">
										<div class="item-amount ">
											<div class="sl">
												<input id="min" class="min am-btn" name="" type="button" value="-" onclick="sub(${domain.id})" />
											 <input class="text_box" id="count_${domain.id}" type="text" value="${domain.count}" style="width: 30px; text-align: center;" /> 
												<input id="add" class="add am-btn" name="" type="button" value="+" onclick="add(${domain.id})" />
												<input type="hidden" name="stock_${domain.id}" value="${domain.goods_stock}" id="stock_${domain.id}" />
											</div>
										</div>
									</div>
								</li>
								<li class="td td-sum">
									<div class="td-inner">
										<em tabindex="0" class="J_ItemSum number" ><fmt:formatNumber type="number" value="${domain.count*domain.goods_price}" maxFractionDigits="2"/></em>
									</div>
								</li>
								<li class="td td-op">
									<div class="td-inner">
									<a href="" data-point-url="#" class="delete"> 删除</a>
									</div>
								</li>
							
							</ul>
						</div>
					</div>
				</tr>
				</c:forEach>
		</div>
		<div class="clear"></div>

		<div class="float-bar-wrapper">
			<div class="float-bar-right">
				<div class="amount-sum">
					<span class="txt">已选商品</span> <em id="getnum"></em><span
						class="txt">件</span>
					<div class="arrow-box">
						<span class="selected-items-arrow"></span> <span class="arrow"></span>
					</div>
				</div>
				<div class="price-sum">
					<span class="txt">合计:</span> 
					<strong class="price">
					¥<em id="J_Total"></em>
					<fmt:formatNumber type="number" value="${shoppingCart.price}" maxFractionDigits="2"/>
					
					</strong>
				</div>
				<div class="btn-area">
					<a href="ShoppingServlet?action=pay" id="J_Go" class="submit-btn submit-btn-disabled"
						aria-label="请注意如果没有选择宝贝，将无法结算"> <span>结&nbsp;算</span></a>
				</div>
			</div>

		</div>

		<div class="footer">
			<div class="footer-hd">
				<p>
					<a href="#">恒望科技</a> <b>|</b> <a href="#">商城首页</a> <b>|</b> <a
						href="#">支付宝</a> <b>|</b> <a href="#">物流</a>
				</p>
			</div>
			<div class="footer-bd">
				<p>
					<a href="#">关于恒望</a> <a href="#">合作伙伴</a> <a href="#">联系我们</a> <a
						href="#">网站地图</a> <em>© 2015-2025 Hengwang.com 版权所有</em>
				</p>
			</div>
		</div>

	</div>

	<!--操作页面-->

	<div class="theme-popover-mask"></div>

	<div class="theme-popover">
		<div class="theme-span"></div>
		<div class="theme-poptit h-title">
			<a href="javascript:;" title="关闭" class="close">×</a>
		</div>
		<div class="theme-popbod dform">
			<form class="theme-signin" name="loginform" action="" method="post">

				<div class="theme-signin-left">

					<li class="theme-options">
						<div class="cart-title">颜色：</div>
						<ul>
							<li class="sku-line selected">12#川南玛瑙<i></i></li>
							<li class="sku-line">10#蜜橘色+17#樱花粉<i></i></li>
						</ul>
					</li>
					<li class="theme-options">
						<div class="cart-title">包装：</div>
						<ul>
							<li class="sku-line selected">包装：裸装<i></i></li>
							<li class="sku-line">两支手袋装（送彩带）<i></i></li>
						</ul>
					</li>
					<div class="theme-options">
						<div class="cart-title number">数量</div>
						<dd>
							<input class="min am-btn am-btn-default" name="" type="button" value="-" />
							 <input class="text_box" name="" type="text" value="1" style="width: 30px;" />
							  <input class="add am-btn am-btn-default" name="" type="button" value="+" /> 
							  <span class="tb-hidden">库存<span class="stock">1000</span>件
							</span>
						</dd>

					</div>
					<div class="clear"></div>
					<div class="btn-op">
						<div class="btn am-btn am-btn-warning">确认</div>
						<div class="btn close am-btn am-btn-warning">取消</div>
					</div>

				</div>
				<div class="theme-signin-right">
					<div class="img-info">
						<img src="images/kouhong.jpg_80x80.jpg" />
					</div>
					<div class="text-info">
						<span class="J_Price price-now">¥39.00</span> <span id="Stock"
							class="tb-hidden">库存<span class="stock">1000</span>件
						</span>
					</div>
				</div>

			</form>
		</div>
	</div>
	<!--引导 -->
	<div class="navCir">
		<li><a href="home.html"><i class="am-icon-home "></i>首页</a></li>
		<li><a href="sort.html"><i class="am-icon-list"></i>分类</a></li>
		<li class="active"><a href="shopcart.html"><i
				class="am-icon-shopping-basket"></i>购物车</a></li>
		<li><a href="person/index.html"><i class="am-icon-user"></i>我的</a></li>
	</div>
</body>

</html>