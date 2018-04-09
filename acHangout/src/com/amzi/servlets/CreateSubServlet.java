package com.amzi.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amzi.dao.CreateSub;

public class CreateSubServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, IOException {  

		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  

		HttpSession session = request.getSession(false);

		String username = (String) session.getAttribute("name");
		String password = (String) session.getAttribute("pass");

		String title = request.getParameter("title");
		String description =  request.getParameter("description");
		String topic = request.getParameter("topic");
		System.out.println(topic);

		if(topic == null) {
			RequestDispatcher rd=request.getRequestDispatcher("newpost.jsp");  
			rd.forward(request,response);
		}

		String values[] = 
			{
					username,password,title,description,topic
			};

		if(CreateSub.insert(values)){  
			String pagename = "display?sub=" + title + "&page=1";  
			response.sendRedirect(pagename);
		}  
		else{  
			String message = "Error! Failed to submit post";
			request.setAttribute("message", message);
			request.getRequestDispatcher("newpost.jsp").forward(request, response);
		}  
		out.close();  
	}  
}
