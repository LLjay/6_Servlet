package com.kh.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PizzaServlet
 */
@WebServlet("/confirmPizza.do") // 라는 요청이 오면 이 Servlet이 받아주겠다.
// 페이지가 안 뜨면 이 어노테이션 경로가 잘못 됐을 확률이 큼
public class PizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PizzaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//    컨트롤러는 데이터를 받아서 가공해 Service로 넘겨줌
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("pizza 주문 도착");
		// 1) 인코딩 처리 => 전달값 중 한글이 있을 수 있음 (post일 때만 / get일 때 해도 상관 없음)
		// 따라서 그냥 습관적으로 해줄 것
		request.setCharacterEncoding("UTF-8");
		
		// 2) 요청 시 전달값 추출, 데이터 가공 처리 => 변수나 객체에 넘겨 받은 값을 기록함
		// request.getParameter("키"); => value 반환
			// Servlet으로 받은 데이터들은 전부 parameter 영역으로 들어감
		// request.getParameterValues("키"); => ["value", "value" ... ]
		// key가 존재하지 않으면 null
		
		// 넘겨 받은 값 꺼내기
		String name = request.getParameter("userName"); // 이지수
		String phone = request.getParameter("phone"); // 01000000000
		String address = request.getParameter("address"); // "서울시 구로구..."
		String message = request.getParameter("message"); // 밑반찬 빼주세요
		
		String pizza = request.getParameter("pizza"); // 고구마피자
		String[] topping = request.getParameterValues("topping"); // ["치즈크러스트", "치즈 추가"]
		String[] side = request.getParameterValues("side"); // ["고구마 무스", "갈릭소스"]
		String payment = request.getParameter("payment"); // 카드결제
		
		// 3) 요청 처리 (db에 sql문 실행 > service > dao)
		
		int price = 0;
		
		switch(pizza) {
		case "콤비네이션" : price += 5000; break;
		case "치즈피자" : 
		case "포테이토피자" : price += 6000; break;
		case "고구마피자" : price += 7000; break;
		case "하와이안피자" : price += 8000; break;
		}
		
		if(topping != null) {
//			아무것도 선택하지 않았다면 값이 null일 것이기 때문에 length에서 오류가 날 것
			for (int i = 0; i < topping.length; i++) {
				switch(topping[i]) {
				case "치즈추가" :
				case "고구마무스" : price += 1500; break;
				case "치즈크러스트" : 
				case "치즈바이트" : price += 2000; break;
				case "베이컨/소세지" : 
				case "파인애플" : price += 2500; break;
				}
			}
		}
		
		if(side != null) {
			for (int i = 0; i < side.length; i++) {
				switch(side[i]) {
				case "콜라" :
				case "사이다" : price += 1500; break;
				case "갈릭소스" : 
				case "핫소스" : price += 2000; break;
				case "피클" : 
				case "파마산" : price += 2500; break;
				}
			}
		}
		
		// 4) 요청 처리 후 사용자가 보게 될 응답 페이지(결제 페이지) 만들어서 넘겨주기
		// 응답 페이지(jsp)를 선택해서 포워딩
		// 단, 응답 페이지에 필요한 데이터가 없다면 담아서 포워딩 할 것
		
		request.setAttribute("name", name);
		request.setAttribute("phone", phone);
		request.setAttribute("address", address);
		request.setAttribute("message", message);
		
		request.setAttribute("pizza", pizza);
		request.setAttribute("topping", topping);
		request.setAttribute("side", side);
		request.setAttribute("payment", payment);
		request.setAttribute("price", price);
		
//		응답 페이지에 필요한 데이터만 담아서 출력해도 됨
		// dispacher : 응답할 뷰와 Servlet을 연결해주는 객체
		
		// 응답할 뷰 선택
		RequestDispatcher view = request.getRequestDispatcher("views/pizza/pizzaPayment.jsp");
		// 선택된 뷰가 사용자에게 보여지도록 포워딩
		view.forward(request, response);
	}

//	자바코드 수정한 거니까 restart 해야 함
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
