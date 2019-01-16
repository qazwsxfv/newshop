package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.GoodsInfo;
import entity.GoodsType;
import entity.Page;
import entity.User;
import service.IGoodsTypeService;
import service.impl.GoodsTypeServiceImpl;

/**
 * Servlet implementation class GoodsTypeServlet
 */
public class GoodsTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private IGoodsTypeService goodsTypeService = new GoodsTypeServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		if("goodsTypeList".equals(action)) {
			list(request, response);
		}else if("updateInit".equals(action)) {
			Integer id = Integer.parseInt(request.getParameter("id"));
			GoodsType goodsType = goodsTypeService.getById(id,"GoodsType");
			List<GoodsType> bigList = goodsTypeService.getBigClass();
			request.setAttribute("bigList", bigList);
			request.setAttribute("goodsType", goodsType);
			request.getRequestDispatcher("back/goodstype/goodstypeupdate.jsp").forward(request, response);
		}else if("update".equals(action)) {
			GoodsType goodsInfo = getGoodsTypeFromParam(request);  //有id
			int result = goodsTypeService.updateGoodsType(goodsInfo);
			list(request, response);
		}else if("del".equals(action)) {
			Integer id = Integer.parseInt(request.getParameter("id"));
			int result = goodsTypeService.deleteById(id,"GoodsType");
			response.sendRedirect("GoodsTypeServlet?action=goodsTypeList");
		}else if("goodsTypeToAdd".equals(action)){
			//从后台拿到父类名称(其父id为0的)，回传到前端
			List<GoodsType> list = goodsTypeService.getGoodsTypeName();
			request.setAttribute("list", list);
			request.getRequestDispatcher("back/goodstype/goodsadd.jsp").forward(request, response);
		}else if("GoodsTypeAdd".equals(action)){
			String goodsTypeName = request.getParameter("name");
			Integer parentId = Integer.parseInt(request.getParameter("parentId"));
			int result = goodsTypeService.add(new GoodsType(0, goodsTypeName, parentId, "xxx"));
			list(request, response);

		}else if("batchDelId".equals(action)){
			String ids[] = request.getParameterValues("id[]");
			int result = goodsTypeService.batchDel(ids,"GoodsType");
		}
		
		
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String current = request.getParameter("currentPage");
		Page<GoodsType> page=goodsTypeService.getPage(current,"GoodsType");
		page.setUrl("GoodsTypeServlet?action=goodsTypeList");
		request.setAttribute("page", page);
		request.getRequestDispatcher("back/goodstype/goodstype.jsp").forward(request, response);
	}

	private GoodsType getGoodsTypeFromParam(HttpServletRequest request) {
		String id = request.getParameter("id");
		String goods_name = request.getParameter("gtype_name");
		Integer nid = 0;
		if(id != null){
			nid = Integer.parseInt(id); //注意：id为null则不能转
		}
		
		return new GoodsType(nid,0,goods_name);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
