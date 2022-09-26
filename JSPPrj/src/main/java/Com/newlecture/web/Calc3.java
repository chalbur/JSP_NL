package Com.newlecture.web;

import java.io.IOException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/calc3")
public class Calc3 extends HttpServlet {
	// post요청만 처리 : 서버로 정보를 올리려고 설계된 방법

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cookie[] cookies = request.getCookies(); // 클라이언트가 보낸 쿠키를 읽음
		
		
		String value = request.getParameter("value");
		String operator = request.getParameter("operator");
		String dot = request.getParameter("dot");
		
		String exp = "";// 기존에 있던 쿠키를 읽어와서 추가로 덧붙히는 작업
		// 쿠키를 읽어오는 작업, null이 들어오는 경우 예외처리
		if(cookies != null)
			for(Cookie c : cookies)
				if(c.getName().equals("exp")) {
					exp = c.getValue(); // 쿠키는 문자열이기때문에 정수로 변환
					break;
				}
		
		if(operator != null && operator.equals("=")) {
			// 자바 스크립트
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
			try {
				exp = String.valueOf(engine.eval(exp));
			} catch (ScriptException e) {
				e.printStackTrace();
			}
		}
		else if(operator != null && operator.equals("C")) { // 쿠키 지우기
			exp = "";
		}
		else {
			// 버튼은 하나만 누르기때문에 셋중에 하나씩만 올수있음
			exp += (value == null)?"":value;
			exp += (operator == null)?"":operator;
			exp += (dot == null)?"":dot;
		}
		
		Cookie expCookie = new Cookie("exp", exp); // 쿠키 생성
		if(operator != null && operator.equals("C")) { // 쿠키 지우기
			expCookie.setMaxAge(0); // 쿠키가 남지않고 바로 소멸
		}
		
		expCookie.setPath("/calc3"); // 쿠키 경로 설정은 한개만 가능함
		response.addCookie(expCookie);
		response.sendRedirect("calcpage"); // 페이징(흰화면 안뜨게), 경로가 같음
			
		 
	
	}

}
