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
		System.out.println();
		
		/**
		 * 응답 페이지에 전달할 값이 있을 경우 어딘가에는 담아야 함 (담을 수 있는 영역)
		 * 1) application : 여기에 담긴 데이터는 웹 애플리케이션 전역에서 다 꺼내서 쓸 수 있음
		 * 2) session : 여기에 담긴 데이터는 직접 지우기 전까지 또는 세션이 만료(서버가 멈추거나 브라우저가 종료) 되기 전까지
		 * 				어떤 jsp든, 어떤 servlet이든 꺼내서 사용할 수 있음
		 * 				// request, response가 있기 때문 // 서버마다 아이디를 만드므로 양이 많아지면 사용자 백만 명의 서버가 생김, 좋지 않음
		 * 3) request : 해당 영역에 담긴 데이터는 현재 이 request 객체를 "포워딩한 응답 jsp에서만" 꺼내 쓸 수 있음
		 * 4) page : 해당 jsp에서 담고 해당 jsp에서만 사용할 수 있음
		 * 
		 * 공통적으로 데이터를 담고자 한다면 특정객체.setAttribute("키", 밸류)
		 * 			데이터를 꺼내고자 한다면 특정객체.getAttribute("키")
		 * 			데이터를 지우고자 한다면 특정객체.removeAttribute("키")
		 */
		
		
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
			
			// 1. 포워딩 방식 => 해당 방식은 url이 변경 되지 않음 => 우리는 localhost:8001/kh라는 기존 메인 url이 있는데
			// 해당 방식으로 화면 응답 시 url은 http://localhost:8001/kh/login.me가 나타남
			// 실제 화면은 localhost:8001/kh/의 응답 화면이 나타남
				// jsp에게 응답을 던지는 방식
				// url의 응답 화면과 실제 가지고 있는 url은 다를 수 있음
//			원래 이후에 포워딩 해야함 하지만 하지 않을 것
//			response.sendRedirect("/kh"); 이런 식으로 직접 써주지 않음
			response.sendRedirect(request.getContextPath());
			// 기존에 해당 페이지를 응답 하는 url이 존재 한다면 사용 가능
			// 응답 페이지 => index.jsp 페이지(jsp url 요청
//			url 들어감
			
			// request는 jsp가 끝나면 소멸, 요청에 필요한 정보이므로
			// 쿠키는 서버에서 생성, 톰캣 사용하고 있으니 톰캣 서버에
			// 서버가 세션을 쿠키에 감싸서 브라우저에 보내줌
			// 쿠키는 세션 아이디, 즉 인증정보를 가지고 있다는 정보를
			// 여러 번 이 아이디를 가진 곳에 요청을 반복해서 보냄, 왔다갔다 하니까 유지가 되는 것
			// 브라우저에 종속된다...
			// request에는 세션 정보가 있음
			// 브라우저에 저장할 수 있는 저장 공간 쿠키
			// 세션 대신 토큰을 저장, 암호화된 알고리즘
			// json 형식은 키 값 형태
			// 세션마다 톰캣이 아이디를 부여해 쿠기로 감싸서 줌, 그걸 브라우저가 저장하고 있는 것
			// 쿠키는 url마다 저장, 요청을 다시 보내는 것
			// 8001 포트면 무조건 같이 정보를 보내줌
			
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
