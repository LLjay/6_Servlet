package com.jay.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jay.model.vo.Member;

/**
 * Servlet implementation class signUpServlet
 */
@WebServlet("/signUp.do")
public class signUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		String userName = request.getParameter("userName");
		String gender = request.getParameter("gender");
		String[] genre = request.getParameterValues("genre");
		
		Member mem = new Member(userId, userPassword, userName, gender, genre);
		
//		int result = new MemberService().insertMember(mem);
		int result = 1;
		
		if (result == 1) {
			request.setAttribute("userName", userName);
			request.setAttribute("genre", genre);
			
			for (String s : genre) {
				System.out.println(s);
			}
			RequestDispatcher view = request.getRequestDispatcher("views/successSignUp.jsp");
			
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
