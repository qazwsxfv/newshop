package filter;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;import com.sun.accessibility.internal.resources.accessibility;
import com.sun.corba.se.spi.orbutil.fsm.Action;

import entity.User;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {
	private String[] ignorePath;
	private String[] ignoreAction;

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request2=(HttpServletRequest) request;
		HttpServletResponse response2=(HttpServletResponse) response;
		User user=(User) request2.getSession().getAttribute("user");
		String action=request2.getParameter("action");
		String path=request2.getServletPath();
		if(user!=null||isIgnoreAction(action)||isIgnorePath(path)) {
			chain.doFilter(request, response);
		}else {
			response.getWriter().write("<script>alert('你没登录，请先登录！');location.href='../backLogin.jsp';</script>");
		}

		// pass the request along the filter chain
		
	}
	
	private boolean isIgnoreAction(String action) {
		for(int i=0;i<ignoreAction.length;i++) {
			if(ignoreAction[i].equals(action)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isIgnorePath(String path) {
		for(int i=0;i<ignorePath.length;i++) {
			if(ignorePath[i].equals(path)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		String fileName=fConfig.getInitParameter("ignorePath");
		Properties properties=new 	Properties();
		try {
			properties.load(LoginFilter.class.getClassLoader().getResourceAsStream(fileName));
			ignorePath=properties.getProperty("path").split(",");
			ignoreAction=properties.getProperty("action").split(",");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
