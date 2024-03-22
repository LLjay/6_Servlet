package com.kh.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet 같은 경우 반드시 contextPath 뒤에 작성 되어야 함
 * http://localhost:8888/1_Servlet/test1.do
 * @어노테이션 : 컴파일러가 알기 위해 쓰는 것
 */

// 이 객체는 서블릿이구나, 요청이 들어오면 이 서블릿이 받아주는구나를 컴파일러가 알 수 있도록
// 맵핑한다...
@WebServlet("/test1.do")
// 서버에 요청해서 받아줄 서블릿이 쓸 url을 입력하는 것, 생성 시에도 설정 가능
public class RequestGetServlet extends HttpServlet { // http 통신에 대한 기능이 들어있음
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestGetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

//    GET이냐 POST냐에 따른 메소드
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * G
	 */
    
//    request는 요청에 대한 객체 변수, response는 응답에 대한 객체 변수
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// GET 방식으로 요청 시 해당 doGet 메소드가 저절로 호출 된다.
		System.out.println("GET 요청 정상적으로 받았습니다.");
		/**
		 * 첫 번째 매개변수인 request에는 요청 시 전달된 내용들이 담겨 있음(사용자가 입력한 값, 요청 방식, 요청자의 ip 주소 등) // 객체에 담겨있는 것
		 * 두 번째 매개변수인 response는 요청 처리 후 응답할 때 사용되는 객체
		 * 
		 * 요청 처리를 위해서 요청 시 전달된 값들 추출
		 * request의 parameter 영역 안에 존재
		 * request.getParameter("키")
		 */
		
//		프론트와 백의 둘 중 하나의 키 값이 맞지 않아서 전달되지 않는 경우가 많음
		
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
		
		// 추출한 값들을 가지고 요청 처리를 해야함(db와 상호작용)
		// > Service > Dao > DB sql로 실행
		
		// int result = new MemberService().insertMember(name, gender, age, city, height, foods)
		// if (result > 0) {
			// 성공 -> 성공 페이지 응답
		// } else {
			// 실패 -> 실패 페이지 응답
		// }
		
		// 위의 결과에 따라 응답 페이지(html)을 만들어서 전송
		// 즉, 여기 Java 코드 내에 사용자가 보게 될 응답 html 구문을 작성\
		
		// 장점 : java 코드 내에 작성하기 때문에 반복문이나 조건문, 유용한 메소드 같은 것들을 활용하여 동적인 응답을 내릴 수 있음
		// 단점 : 불편, 복잡, 혹시라도 차후에 html을 수정 한다면 java 코드를 수정하는 것이기 때문에 수정된 내용을 반영 시키고자
//				 서버를 재실행 해야 함
		
		// => JSP를 그래서 이용하는 것
		// html을 쭉 쓴 다음에 java 코드를 끼워넣을 수 있음
		
		// response 객체를 통해 사용자에게 html(응답 화면) 전달
		// 1) 이제부터 내가 출력할 내용은 문서 형태의 html이고 문자셋은 utf-8이다. -> 선언
		response.setContentType("text/html; charset=utf-8");
		
		// 2) 응답하고자 하는 사용자(요청했던 사람)와의 스트림(클라이언트와의 통로)을 생성
		PrintWriter out = response.getWriter();
		
		// 3) 위에서 가져온 스트림을 통해서 응답 html 구문을 한 줄씩 출력
		out.println("<html>");
		
		out.println("<head>");
			out.println("<style>");
			out.println("</style>");
		out.println("</head>");
		
		out.println("<body>");
			out.println("<h2>개인정보응답화면</h2>");
			out.printf("<span>%s</span>님은", name);
			out.printf("<span>%d</span>살이며", age);
			out.printf("<span>%s</span>에 삽니다.", city);
			
			out.printf("성별은 ");
			if (gender == null) {
				out.println("선택하지 않았습니다.");
			} else {
				if (gender.equals("M")) {
					out.println("<span>남자입니다.</span>");
				} else {
					out.println("<span>여자입니다.</span>");
				}
			}
			
			
			
			
		out.println("</body>");
		
		out.println("</html>");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// test2.do 서블릿을 생성하고 post 메소드에서 system.out.println("post 요청 성공")
		// RequestPostServlet.java
	}

}
