package Com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hi") // ������̼��� �̿��� ����
public class Nana extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		// request : �Էµ��� , response : ��µ���
		// queryString�� �������� getParameter�� ���
		// ����ڰ� ������Ʈ���� �Է��ϸ� ������ �� ������Ʈ���� �ν��� �Է¹��� ��ŭ�� �ݺ�
		// ���ڿ��� ���޵Ǳ⶧���� ���������� ��ȯ�������
		//int cnt = Integer.parseInt(request.getParameter("cnt"));
		
		String cnt_ = request.getParameter("cnt");
		int cnt=4;
		// ����Ʈ�� ���� ����
		if(cnt_ !=null && !cnt_.equals(""))
			cnt = Integer.parseInt(cnt_);
		// ���ڰ� �ȿü��� ������ ����ó�� �������
		
		
		for(int i=0;i<cnt;i++) {
			out.println((i+1)+": �ȳ� Servlet!!<br />");
		}
	}
}

// 15�� 4��30��