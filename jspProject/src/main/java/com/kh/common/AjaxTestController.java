package com.kh.common;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class AjaxTestController
 */
@WebServlet("/jqAjax.do")
public class AjaxTestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxTestController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		
		// 여러 개를 응답하고 싶지만 할 수 없음
//		response.getWriter().print(name);
//		response.getWriter().print(age);
//		-> name과 age가 붙어서 응답하게 됨, 여러 개가 하나로 가게 됨
//		[{k:v}, {k:v}...] 형식으로 키밸류가 배열에 들어가 있는 경우가 많음
		
		/*
		 * JSON(자바 스크립트에서 객체를 표기했던 방법, 실제로는 파일 형식을 의미함)
		 * ajax 통신 시 데이터 전송에 가장 많이 사용되는 포맷 형식 중 하나
		 * 
		 * {key:value, key:value ...} -> JSONObject
		 * [value, value, value...] -> JSONArray
		 * 
		 */
		
//		어떤 라이브러리를 사용 했느냐에 따라 사용 방식이 조금씩 달라질 수 있음
//		JSONArray jArr = new JSONArray();
//		jArr.add(name);
//		jArr.add(age);
//		
//		response.setContentType("text/html; charset=UTF-8");
//		response.getWriter().print(jArr);
		
//		JSONObject jobj = new JSONObject(); // {}
//		jobj.put("name", name); // {name : 이지수}
//		jobj.put("age", age); // {name : 이지수, age : 27}
//		
//		response.setContentType("text/html; charset=UTF-8");
//		response.getWriter().print(jobj);
		
		ArrayList<Member> list = new ArrayList<>();
		list.add(new Member(1, "이지수", "01012341234"));
		list.add(new Member(2, "전은지", "01013435674"));
		list.add(new Member(3, "윤현지", "01012845334"));
		
		JSONArray jArr = new JSONArray();
		for (Member m : list) {
			JSONObject jobj = new JSONObject();
			jobj.put("userNo", m.getUserNo());
			jobj.put("userName", m.getUserName());
			jobj.put("phone", m.getPhone());
			
			jArr.add(jobj);
		}
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(jArr);
//		[{
//			userName : "이지수",
//			userNo : 1,
//			phone : 01012341234
//		},{
//			userName : "이지수",
//			userNo : 1,
//			phone : 01012341234
//		},{
//			userName : "이지수",
//			userNo : 1,
//			phone : 01012341234
//		}]
		response.setContentType("text/html; charset=UTF-8");
		new Gson().toJson(list, response.getWriter());
//		위에 배열에 넣은 과정을 이 메소드가 대신 해준 것
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
