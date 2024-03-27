package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutController
 */
@WebServlet("/logout.me")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그아웃 요청 처리 => session 만료 시키기 == session을 무효화 시키기
			// removeAttribute로 처리해도 됨
		HttpSession session = request.getSession();
//		이미 브라우저에 쿠키에 담긴 세션 정보가 있으므로 request 요청 정보에 담아서 줄 것
		session.invalidate();
//		세션에 있는 모든 정보를 다 날리고 싶다면 만료, invalidate고 세션 중 로그인 정보 속성만 지우고 싶다면 remove
		
		response.sendRedirect(request.getContextPath()); // /kh
//		그냥 포워딩과 다르게 보내는 방식이 sendRedirect인 것, 어디로 보낼지 url을 적어줘야 함
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
