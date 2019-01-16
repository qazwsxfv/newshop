package servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckServlet
 */
public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int width = 100;  //默认为像素
		int height = 50;  
		//参数3： 存储图片类型： RGB
		//1.创建缓存图片
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//2.创建画笔,绘制内容
		Graphics graphics = image.getGraphics();
		//设置颜色，并填充区域
		graphics.setColor(Color.BLUE);   
		graphics.fillRect(0, 0, width, height);
		
		//将随机的4个数字绘制 
		Random random = new Random();
		graphics.setColor(Color.YELLOW);
		graphics.setFont(new Font("宋体", Font.BOLD, 28));   //设置字体
		StringBuffer sb = new StringBuffer();  //用于存图片验证码
		for(int i=0;i<4;i++){  
			int code = random.nextInt(10);  //随机数
			graphics.drawString(code+"", i*22+20, 35);
			sb.append(code);  //拼图片验证码
		}
		
		//方式4：session对象接收--ok（原因：一个客户端为一个独立的session对象）
		request.getSession().setAttribute("imgCode", sb.toString());
		
		//----设置干扰线:绘制线条---
		for(int i=0;i<10;i++){
			graphics.drawLine(random.nextInt(width), random.nextInt(height),
					random.nextInt(width), random.nextInt(height));
		}
		
		
		//3.返回到前端的img中
		//参数1：缓存图片  参数2：输出格式  参数3：输出流 
		ImageIO.write(image, "jpg", response.getOutputStream());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
