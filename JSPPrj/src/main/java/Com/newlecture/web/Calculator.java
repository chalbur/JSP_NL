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
	// doGET�� doPOST�� ����ϴٰ� ���� �ϳ��� ó���ϰ������� service�Լ� ���
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies(); // Ŭ���̾�Ʈ�� ���� ��Ű�� ����
		String exp = "0";
		// ��Ű�� �о���� �۾�, null�� ������ ��� ����ó��
		if(cookies != null)
			for(Cookie c : cookies)
				if(c.getName().equals("exp")) {
					exp = c.getValue(); // ��Ű�� ���ڿ��̱⶧���� ������ ��ȯ
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
		out.write("<table> <!-- ���� ����� -->");
		out.write("<tr><!-- colspan : 4ĭ�� ���ڴٴ� �� -->");
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
		Cookie[] cookies = request.getCookies(); // Ŭ���̾�Ʈ�� ���� ��Ű�� ����
		
		
		String value = request.getParameter("value");
		String operator = request.getParameter("operator");
		String dot = request.getParameter("dot");
		
		String exp = "";// ������ �ִ� ��Ű�� �о�ͼ� �߰��� �������� �۾�
		// ��Ű�� �о���� �۾�, null�� ������ ��� ����ó��
		if(cookies != null)
			for(Cookie c : cookies)
				if(c.getName().equals("exp")) {
					exp = c.getValue(); // ��Ű�� ���ڿ��̱⶧���� ������ ��ȯ
					break;
				}
		
		if(operator != null && operator.equals("=")) {
			// �ڹ� ��ũ��Ʈ
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
			try {
				exp = String.valueOf(engine.eval(exp));
			} catch (ScriptException e) {
				e.printStackTrace();
			}
		}
		else if(operator != null && operator.equals("C")) { // ��Ű �����
			exp = "";
		}
		else {
			// ��ư�� �ϳ��� �����⶧���� ���߿� �ϳ����� �ü�����
			exp += (value == null)?"":value;
			exp += (operator == null)?"":operator;
			exp += (dot == null)?"":dot;
		}
		
		Cookie expCookie = new Cookie("exp", exp); // ��Ű ����
		if(operator != null && operator.equals("C")) { // ��Ű �����
			expCookie.setMaxAge(0); // ��Ű�� �����ʰ� �ٷ� �Ҹ�
		}
		
		expCookie.setPath("/calculator"); // �� ������ �̿ܿ� �ٸ� ��Ű�� ���޵��� ����, calculator�� ��Ű ��� ����
		response.addCookie(expCookie);
		response.sendRedirect("calculator"); // �ڱⰡ �ڱ⸦ ȣ����
	}

}