package Com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/notice-reg") // 어노테이션을 이용한 맵핑
public class NoticeReg extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request : 클라이언트가 서버쯕으로 전달 , reponse : 서버가 클라이언트로 전달
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//request.setCharacterEncoding("UTF-8");
		// 해줘야 submit을 했을때 한글로 표기됨 한글은 한글자에 2바이트이기 때문에
		
		PrintWriter out = response.getWriter();
		
		// request : 입력도구 , response : 출력도구
		// queryString을 읽을때는 getParameter를 사용
		// 사용자가 쿼리스트링을 입력하면 서버는 그 쿼리스트링을 인식해 입력받은 만큼만 반복
		// 문자열로 전달되기때문에 정수형으로 변환해줘야함
		//int cnt = Integer.parseInt(request.getParameter("cnt"));
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		out.println(title);
		out.println(content);
		
	}
}

// 0908 : 21강