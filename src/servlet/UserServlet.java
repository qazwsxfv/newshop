package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Page;
import entity.User;
import service.IUserService;
import service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private IUserService userService = new UserServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		if("userList".equals(action)) {
			String current = request.getParameter("currentPage");
			Page<User> page=userService.getPage(current,"User");
			page.setUrl("UserServlet?action=userList");
			request.setAttribute("page", page);
			request.getRequestDispatcher("back/user/userinfo.jsp").forward(request, response);
		}else if("updateInit".equals(action)) {
			Integer id = Integer.parseInt(request.getParameter("id"));
			User user = userService.getById(id,"User");
			request.setAttribute("user", user);
			request.getRequestDispatcher("back/user/updateuser.jsp").forward(request, response);
		}else if("updateUser".equals(action)) {
			User user = getUserFromParam(request);  //有id
			int result = userService.updaeUser(user);
			response.sendRedirect("UserServlet?action=userList");
		}else if("deleteUser".equals(action)) {
			Integer id = Integer.parseInt(request.getParameter("id"));
			int result = userService.deleteById(id,"User");
			response.sendRedirect("UserServlet?action=userList");
		}else if("addUser".equals(action)){
			User user = getUserFromParam(request);
			int result = userService.add(user);
			response.sendRedirect("UserServlet?action=userList");
		}else if("backLogin".equals(action)) {
			String code = request.getParameter("code");
			String imgCode = (String) request.getSession().getAttribute("imgCode");	
			if(!code.equals(imgCode)){
				response.getWriter().write("<script>alert('验证码出错！');location.href='backLogin.jsp';</script>");
			}
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			User user = userService.getBybackLogin(name,password);
			if(user!=null) {
				String isAdmin=user.getIsAdmin();
				if("1".equals(isAdmin)) {
					//设置登录凭证
					request.getSession().setAttribute("user", user);
					response.sendRedirect("back/main.jsp");
				}else{
					response.getWriter().write("<script>alert('你不是管理员，无法进后台！');location.href='backLogin.jsp';</script>");
				}
				
			}else{
				response.getWriter().write("<script>alert('用户名或密码出错，请联系管理员！');location.href='backLogin.jsp';</script>");
			} 
		}else if("batchDelId".equals(action)){
			String ids[] = request.getParameterValues("id[]");
			int result = userService.batchDel(ids,"User");
		};
	}

	private User getUserFromParam(HttpServletRequest request) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String sex = request.getParameter("sex");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String isAdmin = request.getParameter("isAdmin");
		
		Integer nid = 0;
		if(id != null){
			nid = Integer.parseInt(id); //注意：id为null则不能转
		}
		
		return new User(nid, name, password, sex, phone, email, isAdmin);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
