package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class ThumbnailInsertController
 */
@WebServlet("/insert.th")
public class ThumbnailInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThumbnailInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
//		멀티파트로 온 거니까 확인, 업로드 된 파일의 request가 멀티파트가 맞다면 
		if (ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 1024 * 1024 * 10;
			
			String savePath = request.getSession().getServletContext().getRealPath("/resources/thumbnail_upfile");
//			realPath까지가 webapp 경로
			
			// 전달된 파일 업로드 (request 변환)
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
//			1. 인코딩 형식이 달라 멀티 파트 객체를 거쳐야 꺼내올 수 있음
//			2. 파일을 자동으로 업로드 해주는 기능
			
			Board b = new Board();
			b.setBoardWriter(multiRequest.getParameter("userNo"));
			b.setBoardTitle(multiRequest.getParameter("title"));
			b.setBoardContent(multiRequest.getParameter("content"));
			
//			사진이 여러 개 이므로
			ArrayList<Attachment> list = new ArrayList<>();
			for(int i = 1; i <= 4; i++) {
				String key = "file" + i;
//				file1, file2, ... 의 키가 됨
				if (multiRequest.getOriginalFileName(key) != null) {
//					첨부 파일 name을 써넣으면 자동으로 파일 원본 이름이 가져와짐
					// 첨부 파일이 존재할 경우
					// Attachment 생성 -> 원본명, 수정된 파일 명, 폴더 경로, 파일 레벨도 2로 바꿔야 함 -> list에 담기
					Attachment at = new Attachment();
					at.setOriginName(multiRequest.getOriginalFileName(key));
					at.setChangeName(multiRequest.getFilesystemName(key));
//					첨부 파일 name의 객체를 통해 새로 시스템에서 만들어진 이름을 가져와줘
					at.setFilePath("resources/thumbnail_upfile/");
					
					at.setFileLevel(i == 1 ? 1 : 2);
					
					list.add(at);
				}
			}
			
			int result = new BoardService().insertThumbnailBoard(b, list);
			
			// 응답뷰 요청
			if(result > 0) { // 성공 -> /kh/list/th 재요청
				request.getSession().setAttribute("alertMsg", "성공적으로 게시글 등록하였습니다.");
				response.sendRedirect(request.getContextPath() + "/list.th");
			} else {
				request.setAttribute("errorMsg", "사진 게시글 작성 실패");
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
