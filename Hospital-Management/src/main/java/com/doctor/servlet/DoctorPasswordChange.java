package com.doctor.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Db.DBConnect;
import com.dao.DoctorDao;
;

@WebServlet("/doctChangePassword")
public class DoctorPasswordChange extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int uid=Integer.parseInt(req.getParameter("uid"));
		String oldPassword=req.getParameter("oldPassword");
		String newPassword=req.getParameter("newPassword");
		
		DoctorDao dao=new DoctorDao(DBConnect.getconn());
		HttpSession session=req.getSession();
		
		if(dao.checkOldPassword(uid, oldPassword))
		{
			if(dao.changePassword(uid, newPassword))
			{
				session.setAttribute("succMsg", "Password Change Successfully");
				resp.sendRedirect("doctor/edit_profile.jsp");
			}else 
			{
				session.setAttribute("errorMsg", "Something Wrong");
				resp.sendRedirect("doctor/edit_profile.jsp");
			}
		}else {
			session.setAttribute("errorMsg", "Old Password Incorrect");
			resp.sendRedirect("doctor/edit_profile.jsp");
		}
		
	}

	
}
