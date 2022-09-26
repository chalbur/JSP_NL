package Com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hi") // 어노테이션을 이용한 맵핑
public class Nana extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		// request : 입력도구 , response : 출력도구
		// queryString을 읽을때는 getParameter를 사용
		// 사용자가 쿼리스트링을 입력하면 서버는 그 쿼리스트링을 인식해 입력받은 만큼만 반복
		// 문자열로 전달되기때문에 정수형으로 변환해줘야함
		//int cnt = Integer.parseInt(request.getParameter("cnt"));
		
		String cnt_ = request.getParameter("cnt");
		int cnt=4;
		// 디폴트값 설정 가능
		if(cnt_ !=null && !cnt_.equals(""))
			cnt = Integer.parseInt(cnt_);
		// 숫자가 안올수도 있으니 예외처리 해줘야함
		
		
		for(int i=0;i<cnt;i++) {
			out.println((i+1)+": 안녕 Servlet!!<br />");
		}
	}
}

// 15강 4분30초