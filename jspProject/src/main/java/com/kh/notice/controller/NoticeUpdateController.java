package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.vo.Notice;
import com.kh.notice.service.NoticeService;

/**
 * Servlet implementation class NoticeUpdateController
 */
@WebServlet("/update.no")
public class NoticeUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 데이터 추출해서 객체로 변환
		int noticeNo = Integer.parseInt(request.getParameter("num"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Notice n = new Notice(noticeNo, title, content);
		
		// 실제 데이터베이스에서 update -> updateNotice()
//		Notice updateN = new NoticeService().updateNotice(n);
		int result = new NoticeService().updateNotice(n);
		
//		if (updateN != null) {
		if (result > 0) {
		// 성공 시 -> /kh/detail.no?num=
//			request.setAttribute("notice", updateN);
			// 재요청(redirect)을 보내야 한다.
			// 지금 페이지 경로 kh/update.no -> 수정 요청 페이지
			// 하지만 다음으로 보여주고 싶은 페이지 -> 상세 페이지(kh/detail.no)
			// url 경로가 다르므로 재요청을 통해 화면과 url을 맞춰줘야 함
//			request.setAttribute("notice", noticeNo);
			
			request.getSession().setAttribute("alertMsg", "성공적으로 공지사항이 변경 되었습니다.");
			
//			response.sendRedirect(request.getContextPath() + "/detail.no?num=" + updateN.getNoticeNo());
			response.sendRedirect(request.getContextPath() + "/detail.no?num=" + noticeNo);
//			재요청 해서 응답을 받는 것이기 때문에
//			request.getAttribute()로 값을 넣어주니까 alert가 안 뜨고
//			request.getSession()으로 값을 넣어주니까 alert가 뜸
//			당연하지 session이 아니라 저기로 값이 넘어가지 못하고 소멸 됐으니까 alertMsg를 null로 인식하고 그냥 메소드를 넘긴 것
			
		} else {
		// 실패 시 -> error 페이지
			// 에러페이지는 따로 url이 필요하지 않다. (errorPage를 직접 찾아갈 일이 없으므로)
			// => 포워딩 => 포워딩 시에는 request를 전달할 수 있으므로
			request.setAttribute("errorMsg", "공지사항 업데이트에 실패했습니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
