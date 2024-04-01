package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.vo.Member;
import com.kh.member.service.MemberService;

/**
 * Servlet implementation class MemberDeleteController
 */
@WebServlet("/delete.me")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
//		넘겨야 할 정보가 1~2개면 그냥 넘기고, 3개 이상이면 객체에 담는 것 같다.
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		
		int result = new MemberService().deleteMember(m);
		
		/**
		 * 정보 변경, 비밀번호 변경 -> 데이터를 데이터베이스로 다시 가져와 넣어주기
		 * 
		 * 탈퇴 성공 시 -> 메인 페이지 alert(성공)
		 * 				단, 로그아웃 처리 해야 함 (session의 loginUser라는 키 값의 데이터 
		 */
		
//		팝업창이 이미 떠있는 상태에서 에러 포워딩 하면 다시 시도하려면 재접속 해야함
//		따라서 alert로 띄워주고 마이페이지로 다시 보내주는 게 좋음
		
		if (result > 0) {
			request.getSession().removeAttribute("loginUser");
			request.getSession().setAttribute("alertMsg", "성공적으로 탈퇴 했습니다.");
			response.sendRedirect(request.getContextPath());
		} else {
			request.getSession().setAttribute("alertMsg", "회원 탈퇴에 실패했습니다.");
			response.sendRedirect(request.getContextPath() + "/myPage.me");
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
