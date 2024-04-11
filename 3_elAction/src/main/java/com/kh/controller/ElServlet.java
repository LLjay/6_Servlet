package com.kh.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.model.vo.Person;

/**
 * Servlet implementation class ElServlet
 */
@WebServlet("/el.do")
public class ElServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ElServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 데이터를 담을 수 있는 JSP 내장 객체의 종류
			1. ServletContext(application scope)
				한 애플리케이션 당 단 1개 존재하는 객체
				이 영역에 데이터를 담으면 애플리케이션? 전역에서 사용 가능
				공유 범위가 가장 큼
			
			2. Http Session(session scope)
				한 브라우저 당 1개 존재하는 객체
				이 영역에 데이터를 담으면 jsp/servlet 단에서 사용 가능
			
			3. HttpServletRequest
				요청 때마다 매번 생성되는 객체
				이 영역에 데이터를 담으면 해당 request 객체를 포워딩 받는 응답 jsp에서만 사용 가능
			
			4. PageContext(page scope)
				jsp마다 존재하는 객체
				공유 범위가 가장 작음(해당 페이지에서만 공유)
			
			위 4개의 객체들에
				데이터를 담을 때는 .setAttribute('키', 데이터)
				데이터를 꺼낼 때 .getAttribute('키')
				데이터를 삭제할 때 .removeAtrribute('키')
		 * 
		 *  // 밑으로 갈수록 범위가 작아짐
		 *  // 브라우저도 하나의 소프트웨어, 쿠키를 담을 수 있는
		 * 
		 */
		
		// request scope에 담기
		request.setAttribute("classRoom", "I강의장");
		request.setAttribute("student", new Person("전은지", 20, "여자"));
		
		// session scope에 담기
		HttpSession session = request.getSession();
		session.setAttribute("academy", "kh");
		session.setAttribute("teacher", new Person("전은지", 27, "여자"));
		
		// 각 scope에 동일한 키로 데이터 담기
		request.setAttribute("scope", "request");
		session.setAttribute("scope", "session");
		
		// application scope에 담기
		ServletContext application = request.getServletContext();
		application.setAttribute("scope", "application");
		
		// page context는 페이지에 존재하는 객체이므로 뭔가 담아서 보낼 수가 없음
	
		// 응답 페이지를 지정하여 해당 페이지가 포워딩 되도록 설정
		request.getRequestDispatcher("views/1_El/01_el.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
