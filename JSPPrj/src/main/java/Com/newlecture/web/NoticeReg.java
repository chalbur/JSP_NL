package Com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/notice-reg") // ������̼��� �̿��� ����
public class NoticeReg extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request : Ŭ���̾�Ʈ�� �����P���� ���� , reponse : ������ Ŭ���̾�Ʈ�� ����
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//request.setCharacterEncoding("UTF-8");
		// ����� submit�� ������ �ѱ۷� ǥ��� �ѱ��� �ѱ��ڿ� 2����Ʈ�̱� ������
		
		PrintWriter out = response.getWriter();
		
		// request : �Էµ��� , response : ��µ���
		// queryString�� �������� getParameter�� ���
		// ����ڰ� ������Ʈ���� �Է��ϸ� ������ �� ������Ʈ���� �ν��� �Է¹��� ��ŭ�� �ݺ�
		// ���ڿ��� ���޵Ǳ⶧���� ���������� ��ȯ�������
		//int cnt = Integer.parseInt(request.getParameter("cnt"));
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		out.println(title);
		out.println(content);
		
	}
}

// 0908 : 21��