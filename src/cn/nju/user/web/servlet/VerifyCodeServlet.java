package cn.nju.user.web.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.vcode.utils.VerifyCode;

/**
 * Servlet implementation class VerifyCodeServlet
 */
@WebServlet("/VerifyCodeServlet")
public class VerifyCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VerifyCode vc=new VerifyCode();
		BufferedImage image=vc.getImage();
		request.getSession().setAttribute("session_vcode", vc.getText());
		VerifyCode.output(image, response.getOutputStream());
	}


}
