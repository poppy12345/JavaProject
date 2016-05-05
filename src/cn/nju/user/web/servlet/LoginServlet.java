package cn.nju.user.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.nju.user.domain.User;
import cn.nju.user.service.UserException;
import cn.nju.user.service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		User user=new User();
		user.setUsername((String)request.getParameter("username"));
		user.setPassword((String)request.getParameter("password"));
		
		UserService userService=new UserService();
		try {
			User selectUser=userService.login(user);
			request.getSession().setAttribute("sessionUser",selectUser);
			response.sendRedirect(request.getContextPath()+"/user/welcome.jsp");
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", user);
			request.getRequestDispatcher("/user/login.jsp").forward(request, response);
		}
		
	}

}
