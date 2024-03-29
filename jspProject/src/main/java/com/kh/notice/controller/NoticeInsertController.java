package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.vo.Member;
import com.kh.notice.service.NoticeService;

/**
 * Servlet implementation class NoticeInsertController
 */
@WebServlet("/insert.no")
public class NoticeInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 데이터 추출 -> 객체로 만들어서 서비스로 전달
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// 로그인한 회원 정보를 얻어내는 방법
		// 1. input type="hidden"으로 처음부터 요청 시 숨겨서 전달하는 방법
		// 2. session 영역에서 저장되어 있는 회원 객체로부터 얻어오는 방법
			// 한 회원 정보를 여러 경로를 통해 충분히 진입할 수 있음
			// hidden 이면 다 여기로 보내는 키를 가지고 있어야 함?
			// 이 페이지에서는 다 로그인 되어 있다고 가정이 되면 session으로 어디서든 접근하게 해도 됨
		
		HttpSession session = request.getSession();
		
		// 강사님은 컨트롤러에서 userNo을 바로 추출해서 Notice 객체에 담아 한 번에 보내셨음
		// 데이터를 가공해주는 역할이 Controller
		// Notice n = new Notice();
		// n.setNoticeTitle(noticeTitle);
		// n.setNoticeContent(noticeContent);
		// n.setNoticeWriter(String.valueOf(userNo));
		
		Member loginUser = (Member)session.getAttribute("loginUser");
		int result = new NoticeService().insertNotice(title, content, loginUser);
		
		if (result > 0) {
//			request.setAttribute("alertMsg", "공지사항 등록에 성공하였습니다.");
			session.setAttribute("alertMsg", "공지사항 등록에 성공하였습니다.");
//			왜 세션이지?
			
			response.sendRedirect(request.getContextPath() + "/list.no");
//			url로 보내기 때문에
//			브라우저에서 이 경로를 통해 저 페이지를 찾기 때문에 전체 경로를 보내줘야 잘 감
//			따라서 상대경로가 될 수 없는 것
//			webapp에서 시작하기 때문에 상대경로가 되는 건 맞음?
			
			
		} else {
//			Attribute에 담아야 이 정보를 응답뷰로 보내줄 수 있으니까
			request.setAttribute("errorMsg", "공지사항 등록에 실패하였습니다.");
//			response.sendRedirect("views/common/errorPage.jsp");
//			왜 뭐는 상대경로고 뭐는 홈페이지 contextPath지?
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
//			따로 url이 필요하지 않으니까? 포워딩
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
