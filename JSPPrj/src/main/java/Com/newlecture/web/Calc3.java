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
	// post��û�� ó�� : ������ ������ �ø����� ����� ���

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		
		expCookie.setPath("/calc3"); // ��Ű ��� ������ �Ѱ��� ������
		response.addCookie(expCookie);
		response.sendRedirect("calcpage"); // ����¡(��ȭ�� �ȶ߰�), ��ΰ� ����
			
		 
	
	}

}
