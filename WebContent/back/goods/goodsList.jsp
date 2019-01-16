<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String path = request.getContextPath();
	String basePath = request.getScheme()+"://" +request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/backstyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.2.js"></script>

<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});
$(function(){
    //点击全选
	$("#allSel").click(function(){
		//prop比attr更为高级的属性设置
		$(".single").prop("checked",$(this).prop("checked"));
		//$(".single").attr("checked",$(this).attr("checked"));
	})
	
	//点击单选
	var array = $(".single:checkbox");
			array.click(function(){
				//当前选中的复选框个数是不是等于全部复选框个数
				var checkedCount = 0;
				array.each(function(){
					if($(this).attr("checked")=="checked"){
						checkedCount++;  //记录选中个数
					}
				})
				//如果是，就把全选的复选框选上
				if(checkedCount == array.length){
					$("#allSel").attr("checked",true);
				}else{
					//如果不是，就把全选的复选框取消选上
					$("#allSel").attr("checked",false);
				}
				
			})

    //批量删除
    $("#batchDel").click(function(){
		//将选中的个数拿出来,放入数组中
        var arr = new Array();
        $(".single:checked").each(function(){
			arr.push($(this).val());
		})
			
		//将数组变为对象形式传到后台
        var param = new Object();
        param.id = arr;   //{id=数组值}
        //使用异步方式，传到后台
        $.get("GoodsInfoServlet?action=batchDelId",param,function(data){
			location.reload();  //刷新页面
		})
		 $(".single:checked").each(function(){
			$(this).attr("checked",false);
		})
	})
})
</script>

</head>
<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">数据表</a></li>
    <li><a href="#">基本内容</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
        <li class="click"><a href="GoodsInfoServlet?action=goodsInfoToAdd"><img src="images/t01.png" align="middle" />添加</a></li>
        <li class="click"><span><img src="images/t02.png" /></span>修改</li>
        <li id="batchDel"><span><img src="images/t03.png" /></span>删除</li>
        <li><span><img src="images/t04.png" /></span>统计</li>
        </ul>
        
        
        <ul class="toolbar1">
        <li><span><img src="images/t05.png" /></span>设置</li>
        </ul>
    
    </div>
    <table class="tablelist" >
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" id="allSel"/></th>
        <th>商品编号</th>
        <th>商品名称</th>
        <th>商品描述</th>
        <th>商品图片</th>
        <th>单价</th>
        <th>操作</th>
       
        </tr>
        </thead>
        <tbody>
        	<c:forEach items="${page.list}" var="goods">
		        <tr>
		        <td><input name="" type="checkbox" value="${goods.id}" class="single"  /></td>
		        <td>${goods.id}</td>
		        <td>${goods.goods_name}</td>
		        <td>${goods.goods_description}</td>
		        <td align="center"><img src="images/${goods.goods_pic}" onerror="this.src='images/wenhao.jpg'" style="width: 120px;height: 90px;" align="middle"/></td>
		        <td>${goods.goods_price}</td>
		        <td>
		        <a href="GoodsInfoServlet?action=updateInit&&id=${goods.id}" class="tablelink">编辑</a>     
				<a href="GoodsInfoServlet?action=del&&id=${goods.id}" class="tablelink"> 删除</a>
				</td>
		        </tr>	        
        	</c:forEach>
        </tbody>
    </table>
    <%@include file="/common/page.jsp" %>
    
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    
    </div>
    
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
	
	
	
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
