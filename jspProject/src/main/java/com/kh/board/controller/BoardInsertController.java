package com.kh.board.controller;

import java.io.File;
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
 * Servlet implementation class BoardInsertController
 */
@WebServlet("/insert.bo")
public class BoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 일반 방식이 아니라 multipart / form-data로 전송하는 경우 request로부터 값 추출이 불가
//		값 찍어보면 null이라고 나옴
		
		// MultipartRequest multiRequest = new MultipartRequest(request, 저장할 폴더의 경로, 용량 제한, 인코딩, new DefaultFileRenamePolicy());
//		DefaultFileRenamePolicy() : 어차피 저장하는 폴더는 하나, 파일 이름이 겹칠 수 있음, 하지만 파일 이름이 겹친다고 해서 올릴 수 없으면 안 됨
//		따라서 다 겹치지 않는 이름으로 바꿔서 저장해야 함
//		시간 데이터가 겹칠 확률이 낮으므로 시간 데이터를 사용
//		이 객체를 오버라이딩 하여 사용
		
		// enctype이 multipart/form-data로 잘 전송 되었을 경우 전반적인 내용이 실행 되도록 코드 작성
		if (ServletFileUpload.isMultipartContent(request)) {
			// 파일 업로드를 위한 라이브러리 : cos.jar()
			// www.servlets.com 다운로드
			
			// 1. 전달 되는 파일을 처리할 작업 내용 (전달 되는 파일의 용량 제한, 저장 시킬 폴더 경로 설정)
			// 1-1) 용량제한 (byte 단위)
			
			// byte -> kbyte -> mbyte -> gbyte -> tbyte ...
			int maxSize = 1024 * 1024 * 10; // 10mb
			
			// 1-2) 전달된 파일을 저장할 폴더 경로 알아내기
			String savePath = request.getSession().getServletContext().getRealPath("/resources/board_upfile/");
//			board-upfile 안에 저장하므로 마지막에 슬래시까지 써줘야 함
//			웹앱? 루트 디렉터리가 웹앱? webapp
			
			/*
			 * 2. 전달된 파일의 파일명 수정 및 서버에 업로드 하는 작업
			 * 
			 * HttpServletRequest request => MultipartRequest multiRequest 변환
			 * 
			 * 위 구문 한 줄 실행 만으로 넘어온 첨부파일이 해당 폴더에 무조건 업로드 됨
			 * 단 업로드 시 파일 명을 수정해야 함 (겹칠 수 있어서) 파일 명을 수정하는 객체를 제시해야 함
			 * -> 같은 파일 명이 있을 경우 덮어씌움(물어볼 수 없으므로 강제로), 파일 명에 한글/특수문자/띄어쓰기가 포함될 경우 서버에 따라 에러가 발생할 수 있음
			 * 
			 * 기본적으로 파일 명이 겹치지 않도록 수정해주는 객체
			 * -> DefaultFileRenamePolicy 객체 (cos에서 제공)
			 * -> 내부적으로 rename(원본파일명){
			 * 	기존 파일 중 동일한 파일 명이 존재할 경우
			 *	파일 명 뒤에 숫자를 붙여줌
			 *	ex) aaa.jpg, aaa1.jpg, aaa2.jpg
			 *		꽃.jpg, 꽃1.jpg, 꽃2.jpg
			 *		return 수정 파일
			 * }
			 * 
			 * 나만의 방식 대로 절대 겹치지 않도록 rename 할 수 있도록 FileRenamePolicy 클래스 만들기
			 * com.kh.common.MyFileRenamePolicy
			 */
			// 전달 되는 파일을 처리할 작업 내용 (전달 되는 파일의 용량 제한, 저장 시킬 폴더 경로 설정)
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
//			이 파일 경로를 db에 저장해야 나중에 가져올 수 있음
			
			// 3. db에 기록할 데이터를 추출해서 vo에 담기
			// > 카테고리 번호, 제목, 내용, 작성자 번호
			// > 첨부파일이 있다면 언본명, 수정명, 저장 폴더 경로
			
			String category = multiRequest.getParameter("category");
			String boardTitle = multiRequest.getParameter("title");
			String boardContent = multiRequest.getParameter("content");
			String boardWriter = multiRequest.getParameter("userNo");
			
			Board b = new Board();
			b.setCategoryNo(category);
			b.setBoardTitle(boardTitle);
			b.setBoardContent(boardContent);
			b.setBoardWriter(boardWriter);
//			얘가 먼저 있어야 at에 필요한 키가 생성됨?
			
			Attachment at = null;
			// 파일이 있을 때만 생성해주는 객체
			
			if (multiRequest.getOriginalFileName("upfile") != null) {
//			첨부파일 name 키를 써주면 첨부파일의 원래 원본 이름이 꺼내짐 -> null이면 파일이 없다는 소리
			at = new Attachment();
			at.setOriginName(multiRequest.getOriginalFileName("upfile"));
			at.setChangeName(multiRequest.getFilesystemName("upfile"));
//			filesystemName 바뀐 파일 이름
			at.setFilePath("resources/board_upfile/");
			}
			
			// 4. 서비스 요청
			int result = new BoardService().insertBoard(b, at);
			
			// 5. 응답뷰 요청
			if (result > 0) { // 성공 -> 목록페이지(kh/list.bo?cpage=1)
				request.getSession().setAttribute("alertMsg", "일반 게시글 작성 성공");
				response.sendRedirect(request.getContextPath() + "/list.bo?cpage=1");
			} else { // 실패 -> 업로드 된 파일 삭제하고 에러 페이지
				if (at != null) {
					new File(savePath + at.getChangeName()).delete();
//					이 경로의 파일 삭제
				}
				request.setAttribute("errorMsg", "일반 게시글 작성 실패");
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
