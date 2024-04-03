package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.board.service.BoardService;
import com.kh.common.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class BoardUpdateController
 */
@WebServlet("/update.bo")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		if (ServletFileUpload.isMultipartContent(request)) {
			// 이게 true라면 실행하게 해줌
			
			// 1) 용량 제한 크기 설정
			int maxSize = 10 * 1024 * 1024;
			
			// 2) 물리적 저장 경로
			String savePath = request.getSession().getServletContext().getRealPath("/resources/board_upfile/");
//					이 경로가 webapp
//			세션에서 꺼내온 서블릿 컨텍스트에서 리얼 파일 경로를 여기에 저장하겠다?
			
			// 3) 전달된 파일 명 수정 작업 후 서버에 업로드
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			// 4) sql 문 실행에 필요한 값 추출해서 vo에 저장
			
			int boardNo = Integer.parseInt(multiRequest.getParameter("bno"));
			String category = multiRequest.getParameter("category");
			String boardTitle = multiRequest.getParameter("title");
			String boardContent = multiRequest.getParameter("content");
			
			Board b = new Board();
			b.setBoardNo(boardNo);
			b.setBoardTitle(boardTitle);
			b.setBoardContent(boardContent);
			b.setCategoryNo(category);
			
			Attachment at = null; // 일단 null로 만들고 넘어온 파일이 있다면 그때 생성
			
			if (multiRequest.getOriginalFileName("upfile") != null) {
				// 새로 넘어온 첨부 파일이 있을 때
				at = new Attachment();
				at.setOriginName(multiRequest.getOriginalFileName("upfile"));
				at.setChangeName(multiRequest.getFilesystemName("upfile"));
//				myFileRenamePolicy에서 저장한 그 이름
				at.setFilePath("resources/board_upfile/");
				
				// 기존 첨부 파일이 있을 때 -> update Attachment(기존 첨부파일 번호 부여)
//				기존 첨부 파일을 넣은 행에서 파일 정보를 수정해줄 거니까
//				기존 첨부 파일을 찾기 위해 기존 첨부 파일 번호를 넘겨주는 것
				if (multiRequest.getParameter("originFileNo") != null) {
					at.setFileNo(Integer.parseInt(multiRequest.getParameter("originFileNo")));
				} else { // 기존 첨부 파일이 없을 때 -> insert
					at.setRefBoardNo(boardNo);
				}
			} // 새로운 첨부 파일이 없다면 at = null
			
			int result = new BoardService().updateBoard(b, at);
			// 새로운 첨부 파일 X							b, null -> board update
			// 새로운 첨부 파일 O, 기존 첨부 파일 O			b, fileNo at -> board update, attachment update
			// 새로운 첨부 파일 O, 기존 첨부 파일 X			b, refBoardNo가 담긴 at -> board update, attachment insert
			
			if (result > 0) { // 성공 -> kh/detail.bo?bno=게시글 번호
				request.getSession().setAttribute("alertMsg", "성공적으로 수정했습니다.");
				response.sendRedirect(request.getContextPath() + "/detail.bo?bno=" + b.getBoardNo());
			} else { // 실패 -> error페이지
				request.setAttribute("errorMsg", "게시글 수정 실패");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}
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
