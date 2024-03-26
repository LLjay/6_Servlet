package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.vo.Member;
import com.kh.member.service.MemberService;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login.me")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) 전달값에 한글이 있을 경우 인코딩 처리 (POST)
		request.setCharacterEncoding("UTF-8");
		
		// 2) 요청 시 전달값 추출해서 변수 또는 객체에 기록하기
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		// 3) 요청하기(db에 sql문 활용해서 조회)
		// service 클래스에 담당 메소드 호출
		Member loginUser = new MemberService().loginMember(userId, userPwd);
	
		// 4) 처리된 결과를 가지고 사용자가 보게 될 응답 뷰를 지정해서 포워딩 또는 url 재요청
		if (loginUser == null) {
			// 조회 결과 없음 => 로그인 실패 => 에러 문구가 보여지는 에러 페이지로 포워딩
			request.setAttribute("errorMsg", "로그인에 실패하였습니다.");
			
			// 응답 페이지에게 위임
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			view.forward(request, response);
		} else {
			// 조회 결과 있음 => 로그인 성공!
//			세션? 이라는 영역에 담아줘야 함
//			Attribute 영역은 request가 가는 영역까지만 전달될 수 있음
//			하지만 login 정보는 풀리면 안 됨, 페이지를 넘어갈 때마다 로그인이 된 상태인지 계속해서 체크 해줘야 함
//			따라서 세션 영역에 담음
			// 로그인 한 회원 정보(loginUser)를 session 영역에 담기(여기저기서 다 가져다 쓸 수 있도록)
			// Servlet에서는 session 영역에 접근하고자 한다면 우선 세션 객체를 얻어야 함
			HttpSession session = request.getSession();
//			request를 통해 세션이라는 것을 가져올 수 있음
			session.setAttribute("loginUser", loginUser);
//			attribute는 request 영역 내에서만, session 영역은 따로 있음
//			전역에서 쓸 거면 session, 한 번 쓰고 버릴 거면 Attribute
			
//			원래 이후에 포워딩 해야함 하지만 하지 않을 것
			response.sendRedirect("/kh");
//			url 들어감
			
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
