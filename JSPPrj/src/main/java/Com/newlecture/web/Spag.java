package Com.newlecture.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// ��Ʈ�ѷ� �κ� , ����
// mvc 2 �ڵ尡 ���������� �и���, ������ ������ ��Ʈ�ѷ�����
@WebServlet("/spag")
public class Spag extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = 0;

		String num_ = request.getParameter("n"); // ����ڷκ��� ���� �Է¹���
		if(num_ != null && !num_.equals("")) // null�� �ƴϰ� �� ���ڿ��� �ƴϸ�
			num = Integer.parseInt(num_);
		
		String result; // spag.jsp�� �����ؾ���
		
		
		if(num%2 !=0)
			result = "Ȧ��";
		else
			result = "¦��";
		
		request.setAttribute("result", result); // ������Ʈ�� ���� ��� �۾�
		
		String[] names = {"newlec", "dragon"};
		request.setAttribute("names", names);
		
		Map<String, Object> notice = new HashMap<String, Object>();
		notice.put("id", 1);
		notice.put("title", "EL�� ���ƿ�");
		request.setAttribute("notice", notice);
		
		// redirect : ���ο� ��û�� �ϰ� ��, ���ο� ��û�� �� �� ���
		// forward : ���� �۾��� ������ �̾ �� �ֵ��� ����, �̾�� ���, result�� �Ѱ������
		RequestDispatcher dispatcher 
		= request.getRequestDispatcher("spag.jsp"); // (spag.jsp ����) �������� ���ϰ� ��δ� ������ ����, ���� ���丮�� �ֱ⶧��
		// dispatcher�� ���� forward�� ����
		dispatcher.forward(request, response);
		
		// ������ ���ؽ�Ʈ : ������ ������ ȥ�� ����Ҷ�
		// ������Ʈ : ��������迡�� ���
		// ����, ������ �� 4���� �������� ����� / ��Ű : Ŭ���̾�Ʈ ����
		
	}
}
