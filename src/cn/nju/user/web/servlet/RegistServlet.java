package cn.nju.user.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.nju.user.domain.User;
import cn.nju.user.service.UserException;
import cn.nju.user.service.UserService;

@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		/*
		 * 获取表单数据使用getParameter()
		 */
		User user=new User();
		user.setUsername((String)request.getParameter("username"));
		user.setPassword((String)request.getParameter("password"));
		user.setVerifyCode((String)request.getParameter("verifyCode"));
		//User form=CommonUtils.toBean(request.getParameterMap(),User.class);
		
		Map<String,String> errors=new HashMap<String,String>();
		String uname=user.getUsername();
		String pswd=user.getPassword();
		String vcode=user.getVerifyCode();
		if(uname==null){
			errors.put("username", "用户名不能为空！");
		}else if(uname.length()<3||uname.length()>15){
			
			errors.put("username", "用户名长度必须为3-15！");
		}
		
		if(pswd==null){
			errors.put("password", "密码不能为空！");
		}else if(pswd.length()<3||pswd.length()>15){
			errors.put("password", "密码长度必须为3-15！");
		}
		
		String session_verifyCode=(String) request.getSession().getAttribute("session_vcode");
		if(vcode==null){
			errors.put("verifycode", "验证码不能为空！");
		}else if(vcode.length()!=4){
			errors.put("verifycode", "验证码长度必须为4位！");
		}else if(!session_verifyCode.equals(vcode)){
			errors.put("verifycode", "验证码错误！");
		}
		
		if(errors!=null&&errors.size()>0){
			request.setAttribute("errors", errors);
			request.setAttribute("form", user);
			request.getRequestDispatcher("/user/regist.jsp").forward(request, response);
			return;
		}
		
		
		
		UserService userService=new UserService();
		try {
			userService.regist(user);
			response.getWriter().print("<h1>注册成功！</h1>"
					+ "<a href='"+request.getContextPath()+"/user/login.jsp'>点击这里去登录</a>");
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", user);
			request.getRequestDispatcher("/user/regist.jsp").forward(request, response);
		}
	}

}
