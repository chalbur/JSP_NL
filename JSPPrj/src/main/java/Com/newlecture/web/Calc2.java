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
		ServletContext application = request.getServletContext(); // ���� ���ø����̼�
		HttpSession session = request.getSession(); // ����
		
		Cookie[] cookies = request.getCookies(); // Ŭ���̾�Ʈ�� ���� ��Ű�� ����
		
		
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
		
		
		int v = 0; // ���ڿ��� �ü��� �����Ƿ� ����ó��
		
		if(!v_.equals("")) v=Integer.parseInt(v_); // ���ڿ��� �ƴϸ�
		
		// = ���
		if(op.equals("=")) {
//			int x = (Integer)application.getAttribute("value"); // ���ø����̼ǿ��� ���� ������
			
//			int x = (Integer)session.getAttribute("value");
			
			// ��Ű�� �������ϼ��� �����Ƿ� �ݺ����� ���� ��Ű�� ã�ƾ���
			int x = 0;
			for(Cookie c : cookies)
				if(c.getName().equals("value")) {
					x = Integer.parseInt(c.getValue()); // ��Ű�� ���ڿ��̱⶧���� ������ ��ȯ
					break;
				}
			String operator = "";
			for(Cookie c : cookies)
				if(c.getName().equals("op")) {
					operator = c.getValue(); // ��Ű�� ���ڿ��̱⶧���� ������ ��ȯ
					break;
				}
			
			int y = v;
//			String oprator = (String) application.getAttribute("op");
//			String oprator = (String) session.getAttribute("op");
			int result = 0;
			
			if(operator.equals("+")) result = x+y;
			else result = x-y;
			
			response.getWriter().printf("result is %d", result); // ���� ������
		}
		// �����̳� ������ ���� �ϴ� ����	
		else {
//			application.setAttribute("value", v); // ���ø����̼ǿ� ���� �����ϴ� �۾�
//			application.setAttribute("op", op);
			
//			session.setAttribute("value", v);
//			session.setAttribute("op", op);
			
			Cookie valuecookie = new Cookie("value",  String.valueOf(v));
			Cookie opcookie = new Cookie("op",  op); // ��ŰŰ���� ���ڿ��� �������� ��Ű �ΰ� ����
			valuecookie.setPath("/calc2"); // ��Ű�� ��� ��쿡 ����ڷκ��� ���޵Ǿ���ϴ����� ���
			opcookie.setPath("/calc2");
			valuecookie.setMaxAge(60*60*24);
			// ��Ű�� ����Ⱓ, ��¥�� ������ ������ �������� ������ �����
			// / : ��� ���������� ��Ű�� ������ /notice/ : notice�� ���Ե� ��θ� ������
			
			// ��Ű�� ���� ���޴�
			response.addCookie(valuecookie); // Ŭ���̾�Ʈ���� ����
			response.addCookie(opcookie);
			
			// application : ������������ ��� was�� �����ؼ� �����Ҷ�����
			// session : ���� �������� ���, ������ �����ؼ� �����Ҷ�����
			// Cookie : ������������ ������ path ���� ����, �������� ������ �ð����� ����ð�����
			// �Ⱓ�� ��� ��Ű�� ����, Ư�� ����url������ ���� ��Ű������
			
			
			response.sendRedirect("calc2.html"); // ����¡(��ȭ�� �ȶ߰�)
			
		} 
	
		
		
		
	}

}
