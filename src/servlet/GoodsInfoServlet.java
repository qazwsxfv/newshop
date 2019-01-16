package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;

import entity.GoodsInfo;
import entity.GoodsType;
import entity.Page;
import entity.User;
import service.IGoodsInfoService;
import service.IGoodsTypeService;
import service.impl.GoodsInfoServiceImpl;
import service.impl.GoodsTypeServiceImpl;

/**
 * Servlet implementation class GoodsInfoServlet
 */
public class GoodsInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private IGoodsInfoService goodsInfoService = new GoodsInfoServiceImpl();
    private IGoodsTypeService goodsTypeService = new GoodsTypeServiceImpl();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		if("goodsInfoList".equals(action)) {
			list(request, response);
		}else if("updateInit".equals(action)) {
			Integer id = Integer.parseInt(request.getParameter("id"));
			GoodsInfo goodsInfo = goodsInfoService.getById(id,"GoodsInfo");
			List<GoodsType> bigList = goodsTypeService.getBigClass();
			request.setAttribute("goodsInfo", goodsInfo);
			request.setAttribute("bigList", bigList);
			request.getRequestDispatcher("back/goods/goodupdate.jsp").forward(request, response);
		}else if("goodsInfoAdd".equals(action)) {
			//1.实例化工厂对象
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//2.通过工厂对象获取文件上传对象
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			
			//1. 实例化一个商品信息对象
			//2.实例化map集合，用于存前端添加的key-value，将map集合注入到商品对象中
			GoodsInfo goodsInfo = new GoodsInfo();
			Map<String, Object> map = new HashMap<>();
			try {
				//3.通过文件上传对象解析request
				//遍历集合，取出表单数据---判断文本表单和非文本表单
				List<FileItem> list = fileUpload.parseRequest(request);
				for(FileItem fi : list){
					if(fi.isFormField()){  //是文本表单内容
						//添加文本内容
						map.put(fi.getFieldName(), fi.getString("utf-8"));
					}else{ //取二进制数据
						String imagePath = getServletContext().getRealPath("images");
						File file = new File(imagePath);
						if(!file.exists()){
							file.mkdirs();   //不存在则创建目录
						}
						//将文件名拼uuid
						String fileName = UUID.randomUUID().toString()+"_"+fi.getName();
						File allFile = new File(file, fileName);
						//将解析的资源存储指定目标目录
						IOUtils.copy(fi.getInputStream(), new FileOutputStream(allFile));
						
						goodsInfo.setGoods_pic(fileName);  //存储图片到对象中
						
						//将图片拷贝到真实路径
						String realPath = "C:\\Users\\Administrator\\eclipse-workspace\\testq\\WebContent\\images\\"+fileName;
						IOUtils.copy(fi.getInputStream(), new FileOutputStream(realPath));
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			
			
			try {
				//将map集合的数据注入到商品实体类中
				BeanUtils.populate(goodsInfo, map);
				//添加到数据库中
				goodsInfoService.add(goodsInfo);
				
				//回到展示页面
				response.sendRedirect("GoodsInfoServlet?action=goodsInfoList");
				
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}else if("goodsInfoToAdd".equals(action)) {
			//获取大类
			List<GoodsType> bigList = goodsTypeService.getBigClass();
			
			//获取小类--在联动查询时带出来即可
			//List<GoodsType> smallList =  goodsTypeService.getSmallClass();
			
			request.setAttribute("bigList", bigList);
			request.getRequestDispatcher("back/goods/goodsadd.jsp").forward(request, response);
		}else if("getSmall".equals(action)){
			//根据传入的大类的id，拿到对应所有小类名
			Integer id = Integer.parseInt(request.getParameter("id"));
			List<GoodsType> list = goodsTypeService.getGoodsTypeListByFatherId(id);
			//转为json字符串方式，将数据传到前端去
			String json = JSON.toJSONString(list);
			response.getWriter().write(json);
		}else if("update".equals(action)) {
			GoodsInfo goodsInfo = getGoodInfoFromParam(request);  //有id
			int result = goodsInfoService.updaeUser(goodsInfo);
			response.sendRedirect("GoodsInfoServlet?action=goodsInfoList");
		}else if("del".equals(action)) {
			Integer id = Integer.parseInt(request.getParameter("id"));
			int result = goodsInfoService.deleteById(id,"GoodsInfo");
			list(request, response);
		}else if("batchDelId".equals(action)){
			String ids[] = request.getParameterValues("id[]");
			int result = goodsInfoService.batchDel(ids,"GoodsInfo");
		};
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String current = request.getParameter("currentPage");
		Page<GoodsInfo> page=goodsInfoService.getPage(current,"GoodsInfo");
		page.setUrl("GoodsInfoServlet?action=goodsInfoList");
		request.setAttribute("page", page);
		request.getRequestDispatcher("back/goods/goodsList.jsp").forward(request, response);
	}

	private GoodsInfo getGoodInfoFromParam(HttpServletRequest request) {
		String id = request.getParameter("id");
		String goods_name = request.getParameter("goods_name");
		Double goods_price = Double.parseDouble(request.getParameter("goods_price"));
		String goods_pic = request.getParameter("goods_pic");
		String goods_description = request.getParameter("goods_description");
		
		Integer nid = 0;
		if(id != null){
			nid = Integer.parseInt(id); //注意：id为null则不能转
		}
		
		return new GoodsInfo(nid,goods_name,0,0,goods_price,goods_description);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
