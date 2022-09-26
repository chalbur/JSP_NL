package Com.newlecture.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/calc2")
public class Calc2 extends HttpServlet {


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ServletContext application = request.getServletContext(); // 서블릿 어플리케이션
		HttpSession session = request.getSession(); // 세션
		
		Cookie[] cookies = request.getCookies(); // 클라이언트가 보낸 쿠키를 읽음
		
		
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
		
		
		int v = 0; // 빈문자열이 올수도 있으므로 예외처리
		
		if(!v_.equals("")) v=Integer.parseInt(v_); // 빈문자열이 아니면
		
		// = 계산
		if(op.equals("=")) {
//			int x = (Integer)application.getAttribute("value"); // 어플리케이션에서 값을 꺼내옴
			
//			int x = (Integer)session.getAttribute("value");
			
			// 쿠키가 여러개일수도 있으므로 반복문을 통해 쿠키를 찾아야함
			int x = 0;
			for(Cookie c : cookies)
				if(c.getName().equals("value")) {
					x = Integer.parseInt(c.getValue()); // 쿠키는 문자열이기때문에 정수로 변환
					break;
				}
			String operator = "";
			for(Cookie c : cookies)
				if(c.getName().equals("op")) {
					operator = c.getValue(); // 쿠키는 문자열이기때문에 정수로 변환
					break;
				}
			
			int y = v;
//			String oprator = (String) application.getAttribute("op");
//			String oprator = (String) session.getAttribute("op");
			int result = 0;
			
			if(operator.equals("+")) result = x+y;
			else result = x-y;
			
			response.getWriter().printf("result is %d", result); // 값을 돌려줌
		}
		// 덧셈이나 뺄셈이 오면 일단 저장	
		else {
//			application.setAttribute("value", v); // 어플리케이션에 값을 저장하는 작업
//			application.setAttribute("op", op);
			
//			session.setAttribute("value", v);
//			session.setAttribute("op", op);
			
			Cookie valuecookie = new Cookie("value",  String.valueOf(v));
			Cookie opcookie = new Cookie("op",  op); // 쿠키키값은 문자열로 보내야함 쿠키 두개 생성
			valuecookie.setPath("/calc2"); // 쿠키가 어느 경우에 사용자로부터 전달되어야하는지의 경로
			opcookie.setPath("/calc2");
			valuecookie.setMaxAge(60*60*24);
			// 쿠키의 만료기간, 날짜를 정하지 않으면 브라우저가 닫힐때 사라짐
			// / : 모든 페이지에서 쿠키를 가져옴 /notice/ : notice가 포함된 경로만 가져옴
			
			// 쿠키를 만들어서 전달달
			response.addCookie(valuecookie); // 클라이언트에게 보냄
			response.addCookie(opcookie);
			
			// application : 전역범위에서 사용 was가 시작해서 종료할때까지
			// session : 세션 범위에서 사용, 세션이 시작해서 종료할때까지
			// Cookie : 웹브라우저에서 지정한 path 범주 공간, 브라우저에 전달한 시간부터 만료시간까지
			// 기간이 길면 쿠키에 저장, 특정 범위url에서만 쓰면 쿠키에저장
			
			
			response.sendRedirect("calc2.html"); // 페이징(흰화면 안뜨게)
			
		} 
	
		
		
		
	}

}
