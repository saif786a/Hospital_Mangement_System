package com.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Db.DBConnect;
import com.dao.AppointmentDao;
import com.entity.Appointment;

@WebServlet("/appAppointment")
public class AppointmentServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		int userId = Integer.parseInt(req.getParameter("userid"));
		String fullName = req.getParameter("fullname");
		String gender = req.getParameter("gender");
		String age = req.getParameter("age");
		String appoint_date = req.getParameter("appoint_date");
		String email = req.getParameter("email");
		String phno = req.getParameter("phno");
		String diseases = req.getParameter("diseases");
		int doctor_id = Integer.parseInt(req.getParameter("doct"));
		String address = req.getParameter("address");
		
		System.out.println(userId+" "+fullName+" "+gender+" "+age+" "+appoint_date+" "+email+" "+phno+" "+diseases+" "+doctor_id+" "+address);
	
		Appointment ap=new Appointment(userId, fullName, gender, age, appoint_date, email, phno, diseases, doctor_id, address, "pending");
		
		AppointmentDao dao=new AppointmentDao(DBConnect.getconn());
		
		HttpSession session=req.getSession();  
		
		if(dao.addAppointment(ap))
		{
			session.setAttribute("succMsg", "Appointment Successfully");
			resp.sendRedirect("user_apppointment.jsp");
		}else 
		{
			session.setAttribute("errorMsg", "Appointment Unsuccessfully");
			resp.sendRedirect("user_apppointment.jsp");
		}
		
		

	}

}
