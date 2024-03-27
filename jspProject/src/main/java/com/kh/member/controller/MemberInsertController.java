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
 * Servlet implementation class MemberInsertController
 */
@WebServlet("/insert.me")
public class MemberInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) 인코딩
		request.setCharacterEncoding("UTF-8");
//		지정해주는 것이므로 set
		
		// 내 예시
		// 2) 요청 시 전달값 추출해서 변수 및 객체 저장
//		String userId = request.getParameter("userId");
//		String userPwd = request.getParameter("userPwd");
//		String userName = request.getParameter("userName");
//		String phone = request.getParameter("phone"); // input 창이므로 null이 아닌 빈값이 올 수 있는 것 ""
//		String email = request.getParameter("email");
//		String address = request.getParameter("address");
//		String[] interest = request.getParameterValues("interest"); // null
//		String[] interestArr = request.getParameterValues("interest");
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone"); // input 창이므로 null이 아닌 빈값이 올 수 있는 것 ""
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String[] interestArr = request.getParameterValues("interest");
		
		// String[] ==> String
		String interest = "";
		if (interestArr != null) {
			interest = String.join(",", interestArr);
		}
		
		Member m = new Member(userId, userPwd, userName, phone, email, address, interest);
		
		// 3) sql 요청 => service => dao => sql 실행
//		int result = new MemberService().insertMember(userId, userPwd, userName, phone, email, address, interest);
		int result = new MemberService().insertMember(m);
		
		// 4) 결과값에 따른 페이지 반환
//		if (result == 0) {
//			request.setAttribute("errorMsg", "회원가입에 실패하였습니다.");
//			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
//			view.forward(request, response);
//		} else {
//			RequestDispatcher view = request.getRequestDispatcher("views/member.insertSuccess.jsp");
//			view.forward(request, response);
//		}
		
		if (result > 0) { // 회원가입 성공
			HttpSession session = request.getSession();
			session.setAttribute("alertMsg", "성공적으로 회원 가입이 되었습니다.");
			
			response.sendRedirect(request.getContextPath());
//			redirect는 브라우저에 다시 요청해달라고 보내는 것, 따라서 request에 기존에 있던 정보들은 날아감
//			이 정보들을 저장하려면 session이 가지고 있어야 함
		} else {
			request.setAttribute("errorMsg", "회원가입에 실패하였습니다.");
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
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
