package Com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")
public class Calculator extends HttpServlet {
	// doGET과 doPOST를 사용하다가 둘을 하나로 처리하고싶을때 service함수 사용
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies(); // 클라이언트가 보낸 쿠키를 읽음
		String exp = "0";
		// 쿠키를 읽어오는 작업, null이 들어오는 경우 예외처리
		if(cookies != null)
			for(Cookie c : cookies)
				if(c.getName().equals("exp")) {
					exp = c.getValue(); // 쿠키는 문자열이기때문에 정수로 변환
					break;
				}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		out.write("<!DOCTYPE html>");
		out.write("<html>");
		out.write("<head>");
		out.write("<meta charset=\"UTF-8\">");
		out.write("<title>Insert title here</title>");
		out.write("<style>");
		out.write("input{");
		out.write("width : 50px;");
		out.write("height : 50px;");
		out.write("}");
		out.write(".output{");
		out.write("height: 50px;");
		out.write("background: #e9e9e9;");
		out.write("font-size: 24px;");
		out.write("font-weight: bold;");
		out.write("text-align: right;");
		out.write("padding: 0px 5px;");
		out.write("}");

		out.write("</style>");
		out.write("</head>");
		out.write("<body>");
		out.write("<form method=\"post\">");
		out.write("<table> <!-- 계산기 만들기 -->");
		out.write("<tr><!-- colspan : 4칸을 쓰겠다는 뜻 -->");
		out.printf("<td class=\"output\" colspan=\"4\">%s</td>", exp);
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td><input type=\"submit\" name=\"operator\" value=\"CE\" /></td>");
		out.write("<td><input type=\"submit\" name=\"operator\" value=\"C\" /></td>");
		out.write("<td><input type=\"submit\" name=\"operator\" value=\"BS\" /></td>");
		out.write("<td><input type=\"submit\" name=\"operator\" value=\"/\" /></td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("	<td><input type=\"submit\" name=\"value\" value=\"7\" /></td>");
		out.write("	<td><input type=\"submit\" name=\"value\" value=\"8\" /></td>");
		out.write("	<td><input type=\"submit\" name=\"value\" value=\"9\" /></td>");
		out.write("	<td><input type=\"submit\" name=\"operator\" value=\"*\" /></td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("	<td><input type=\"submit\" name=\"value\" value=\"4\" /></td>");
		out.write("	<td><input type=\"submit\" name=\"value\" value=\"5\" /></td>");
		out.write("	<td><input type=\"submit\" name=\"value\" value=\"6\" /></td>");
		out.write("	<td><input type=\"submit\" name=\"operator\" value=\"-\" /></td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("	<td><input type=\"submit\" name=\"value\" value=\"1\" /></td>");
		out.write("	<td><input type=\"submit\" name=\"value\" value=\"2\" /></td>");
		out.write("	<td><input type=\"submit\" name=\"value\" value=\"3\" /></td>");
		out.write("	<td><input type=\"submit\" name=\"operator\" value=\"+\" /></td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("	<td></td>");
		out.write("	<td><input type=\"submit\" name=\"value\" value=\"0\" /></td>");
		out.write("	<td><input type=\"submit\" name=\"dot\" value=\".\" /></td>");
		out.write("	<td><input type=\"submit\" name=\"operator\" value=\"=\" /></td>");
		out.write("</tr>");
		out.write("</table>");
		out.write("</form>");

		out.write("</body>");
		out.write("</html>");
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		expCookie.setPath("/calculator"); // 이 페이지 이외엔 다른 쿠키가 전달되지 않음, calculator만 쿠키 사용 가능
		response.addCookie(expCookie);
		response.sendRedirect("calculator"); // 자기가 자기를 호출함
	}

}
