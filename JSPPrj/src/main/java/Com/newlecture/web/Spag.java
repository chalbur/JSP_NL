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

// 컨트롤러 부분 , 서블릿
// mvc 2 코드가 물리적으로 분리됨, 실행은 무조건 컨트롤러에서
@WebServlet("/spag")
public class Spag extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = 0;

		String num_ = request.getParameter("n"); // 사용자로부터 값을 입력받음
		if(num_ != null && !num_.equals("")) // null이 아니고 빈 문자열이 아니면
			num = Integer.parseInt(num_);
		
		String result; // spag.jsp에 전달해야함
		
		
		if(num%2 !=0)
			result = "홀수";
		else
			result = "짝수";
		
		request.setAttribute("result", result); // 리퀘스트에 값을 담는 작업
		
		String[] names = {"newlec", "dragon"};
		request.setAttribute("names", names);
		
		Map<String, Object> notice = new HashMap<String, Object>();
		notice.put("id", 1);
		notice.put("title", "EL은 좋아요");
		request.setAttribute("notice", notice);
		
		// redirect : 새로운 요청을 하게 함, 새로운 요청을 할 때 사용
		// forward : 현재 작업한 내용을 이어갈 수 있도록 공유, 이어갈때 사용, result를 넘겨줘야함
		RequestDispatcher dispatcher 
		= request.getRequestDispatcher("spag.jsp"); // (spag.jsp 서블릿) 페이지명만 정하고 경로는 정하지 않음, 같은 디렉토리에 있기때문
		// dispatcher를 통해 forward가 가능
		dispatcher.forward(request, response);
		
		// 페이지 컨텍스트 : 페이지 내에서 혼자 사용할때
		// 리퀘스트 : 포워드관계에서 사용
		// 세션, 페이지 총 4개는 서버상의 저장소 / 쿠키 : 클라이언트 저장
		
	}
}
