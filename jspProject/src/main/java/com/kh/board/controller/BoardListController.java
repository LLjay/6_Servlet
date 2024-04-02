package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.vo.Board;
import com.kh.board.service.BoardService;
import com.kh.common.vo.PageInfo;

/**
 * Servlet implementation class BoardListController
 */
@WebServlet("/list.bo")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ------------------페이징 처리-------------------- //
		// 총 개수를 알아야 페이징을 몇 묶음으로 할지가 나옴
		int listCount; // 현재 총 게시글 수
		// 내가 지금 몇 페이지인지는 사용자가 눌러서 보내줘야 함
		// 처음 보는 건 무조건 1페이지
		int currentPage; // 현재 페이지(사용자가 요청한 페이지)
		// 한 번에 페이지를 몇 개까지 보여줄지
		int pageLimit; // 페이지 하단에 보여질 페이징 바의 개수
		// 한 페이지에 몇 개 보내줄지를 결정해야 페이징 바 개수가 결정됨
		int boardLimit; // 한 페이지 내에 보여질 게시글 최대 개수
		
//		게시글은 db에서, 현재 페이지는 사용자로부터, 페이징 바 항목 개수와 페이징 개수는 결정할 것
		
		int maxPage; // 페이징 바의 마지막 게시글 개수 (2페이지의 20번째 게시글)
		int startPage; // 페이징 바의 시작 수
		// 이번 페이지는 21부터 / 이번 페이지는 91부터야 라고 이번에 보여줘야 할 번수를 알아야 함
		int endPage; // 페이징 바의 마지막 페이지 수
		
		// #listCount : 총 게시글 수
		listCount = new BoardService().selectListCount();
//		템플릿으로 이 과정을 다 빼버리면 훨씬 편함
//		업데이트 해주거나 삭제 하는 과정이 있어야 한다면 이걸 서비스 단으로 옮겨야 함, 하지만 단지
//		데이터를 가져오기만 하는 과정이므로 컨트롤러에 있어도 무방함
		
		// #currentPage : 현재 페이지(사용자가 요청한 페이지)
		currentPage = Integer.parseInt(request.getParameter("cpage"));
		
		// #pageLimit : 페이징 바의 최대 항목 개수
		pageLimit = 10;
		
		// #boardLimit : 한 페이지 내에 보여질 게시글 최대 개수 (사용자에게 클릭하게 해서 받아도 됨)
		boardLimit = 10;
		
		/**
		 * #maxPage : 제일 마지막 페이지 수(총 페이지 수)
		 * 
		 * 총 게시글 개수 (listCount) boardLimit
		 * 	  100					 10			=>  10		10
		 * 	  101					 10 		=>	10.1	11
		 * 	  105					 10			=>  10.5	11
		 * 
		 * 총 게시글 개수 / boardLimit -> 올림 처리
		 */
		
		maxPage = (int)(Math.ceil((double)listCount / boardLimit));
//		int / int 는 int 이므로 올림할 수가 없음
//		이 중 하나를 double로 형변환 해 결과를 double로 만듦
//		maxPage는 int이므로 다시 int로 형변환
		
		/**
		 * #startPage : 페이징 바 시작 수
		 * 
		 * startPage = ((currentPage - 1) / pageLimit) * pageLimit + 1
		 */
		
		startPage = ((currentPage - 1) / pageLimit) * pageLimit + 1;
		
		/**
		 * endPage : 페이징 바의 끝 수
		 * 
		 * pageLimit : 10이라는 가정 하
		 * 
		 * startPage : 1 -> endPage : 10
		 * startPage : 11 -> endPage : 20
		 * startPage : 21 -> endPage : 30
		 */
		
		endPage = startPage + pageLimit - 1;
		
		// startPage가 11이면 endPage는 20(만약 maxPage가 13이라면 endPage도 13이어야 함)
		endPage = endPage > maxPage ? maxPage : endPage;
		
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		ArrayList<Board> list = new BoardService().selectList(pi);
//		pageinfo에 페이징 바의 시작과 끝이 있으므로 그것도 보내줘야 그릴 수 있음
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		System.out.println(pi);
		request.getRequestDispatcher("views/board/boardListView.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
