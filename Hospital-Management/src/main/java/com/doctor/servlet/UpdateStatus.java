package com.doctor.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Db.DBConnect;
import com.dao.AppointmentDao;

@WebServlet("/updateStatus")
public class UpdateStatus extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id=Integer.parseInt(req.getParameter("id"));
		int did=Integer.parseInt(req.getParameter("did"));
		String comm=req.getParameter("comm");
		
		AppointmentDao dao=new AppointmentDao(DBConnect.getconn());
		
		HttpSession session=req.getSession();
		
		
		if(dao.updateCommentStatus(id, did, comm))
		{
			session.setAttribute("sucMsg", "Comment Update");
			resp.sendRedirect("doctor/patient.jsp");
			
		}else {
			session.setAttribute("errorMsg", "Sometehing Wrong");
			resp.sendRedirect("doctor/patient.jsp");
		}
		
	}
  
	
	
}
