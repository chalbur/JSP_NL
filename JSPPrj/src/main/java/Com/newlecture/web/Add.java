package Com.newlecture.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/add")
public class Add extends HttpServlet {


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String x_ = request.getParameter("x");
		String y_ = request.getParameter("y");
		
		
		int x = 0; // 빈문자열이 올수도 있으므로 예외처리
		int y = 0;
		
		if(!x_.equals("")) x=Integer.parseInt(x_); // 빈문자열이 아니면
		if(!y_.equals("")) y=Integer.parseInt(y_);
		
		int result = x+y;
		
		response.getWriter().printf("result is %d", result);
		
	}

}
