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
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/backstyle.css" rel="stylesheet" type="text/css" />
<script type="" src="js/jquery-1.8.2.js"></script>
<script type="">
	$(function(){
		$("#goods_parentid").change(function(){
			//获取大类的id，传到后台
			var id = $(this).val();
            var param = new Object();
            param.id = id;
			$.get("GoodsInfoServlet?action=getSmall",param,function(data){
				var arr = eval(data);
				var text = "";
				for(var i=0;i<arr.length;i++){
					text += "<option value='"+arr[i].id+"'>"+arr[i].gtype_name+"</option>";
				}
				$("#goods_fatherid").html(text);
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
    <li><a href="#">表单</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>添加商品信息</span></div>
    <form action="GoodsInfoServlet?action=goodsInfoAdd" method="post" enctype="multipart/form-data">
    	<ul class="forminfo">
	    <li><label>商品名称</label><input name="goods_name" type="text" class="dfinput" /><i>标题不能超过30个字符</i></li>
	    <li><label>所属大类</label>
	    		<select name="goods_parentid" id="goods_parentid">
					<c:forEach items="${bigList}" var="goodsType">
						<option value="${goodsType.id}">${goodsType.gtype_name}</option>
					</c:forEach>
	    		</select>
	    		
	    </li>
	    <li><label>所属小类</label>
	    		<select name="goods_fatherid" id="goods_fatherid">
	    		</select>
	    </li>
	     <li><label>商品图片</label><input name="goods_pic" type="file" /></li>
	    <li><label>商品价格</label><input name="goods_price" type="text" class="dfinput" /></li>
	    <li><label>商品描述</label><textarea rows="8" cols="40" name="goods_description" ></textarea></li>
	    <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="确认保存"/></li>
	    </ul>
    
    </form>
    </div>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>

