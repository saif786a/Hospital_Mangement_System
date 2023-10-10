package com.user.servlet;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Db.DBConnect;
import com.dao.UserDao;
import com.entity.User;

@WebServlet("/user_register")
public class UserRegister extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String fullName=req.getParameter("fullname");
			String email=req.getParameter("email");
			String password=req.getParameter("password");
		
		User u=new User();
		u.setFullName(fullName);
		u.setEmail(email);
		u.setPassword(password);
		
		UserDao dao=new UserDao(DBConnect.getconn());
		 
		HttpSession session=req.getSession();
		
		boolean f=dao.register(u);
		
		if(f) {
			
			session.setAttribute("sucMsg", "Register Successfull");
			resp.sendRedirect("signup.jsp");
			   
		}else {
			session.setAttribute("errorMsg", "Register Unsuccessfull");
			resp.sendRedirect("signup.jsp");
			
		}
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
