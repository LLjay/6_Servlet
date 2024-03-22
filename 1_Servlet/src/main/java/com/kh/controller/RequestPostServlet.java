package com.kh.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestPostServlet
 */
@WebServlet("/test2.do")
public class RequestPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get 호출 완료");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name"); // 제이 | ""

		String gender = request.getParameter("gender"); // "M" | "F" | null // 빈값이 오는 것과 null인 것이 있음
		int age = Integer.parseInt(request.getParameter("age")); // "23" => 23 변환 필요, 다 구분 없이 스트링으로 보내줌
		String city = request.getParameter("city"); // "경기", "서울" 등등
		double height = Double.parseDouble(request.getParameter("height")); // "170" => 170.0000
		
//		체크 박스와 같이 복수의 밸류 값들을 추출하고자 할 때
		String[] foods = request.getParameterValues("food"); // ["한식", "분식"] | null
//				반환형이 다르기 때문에 메소드도 달라짐, 단일 변수의 반환과 복수 변수의 반환
//				반환형은 오버로딩의 대상이 되지 않음
		//	키가 없어야 null
		
		System.out.println("name : " + name);
		System.out.println("gender : " + gender);
		System.out.println("age : " + age);
		System.out.println("city : " + city);
		System.out.println("height : " + height);
//		System.out.println("foods : " + foods);
//		System.out.println("foods : " + String.join("/", foods));
//		String.join은 구분자/를 사용해 안의 value들을 나열해줌
		
		if (foods == null) {
			System.out.println("foods : 없음");
		} else {
			System.out.println("foods : " + String.join("/", foods));
		}
	
		// 요청 처리가 다 되었다는 가정 하에 사용자가 보게 될 응답 html 작성
		
		// 순수 Servlet 방식 : java 코드 내에 html을 기술
		// JSP(Java Server Page) 방식 : html 내에 java 코드를 쓸 수 있음
		
		// 응답 페이지를 만드는 과정을 jsp에 위임(떠넘김)
		// 단, 응답화면(jsp)에서 필요로 하는 데이터들을 차곡차곡 담아서 전달해줘야 함
		// 데이터들을 담기 위한 공간 == request의 attribute 영역
		// request.setAttribute("키", 밸류)
		
		request.setAttribute("name", name);
//		attribute 영역에 "name"이라는 키워드로 name을 담겠다
		request.setAttribute("age", age);
		request.setAttribute("city", city);
		request.setAttribute("height", height);
		request.setAttribute("gender", gender);
		request.setAttribute("foods", foods);
		
		// 응답하고자 하는 뷰 (jsp) 선택해서 넘겨줌 => RequestDispatcher 객체 생성
		RequestDispatcher view = request.getRequestDispatcher("/views/responsePage.jsp"); // 이 객체는 request 객체에서 가져옴
//		절대경로의 기준은 이 파일이 어디서 도느냐의 차이, 웹 브라우저에서 도느냐 이 서버에서 도느냐
		view.forward(request, response);
		// jsp 파일에 forwarding이 된 것
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post 호출 완료");
		doGet(request, response);
		
		
	}

}
