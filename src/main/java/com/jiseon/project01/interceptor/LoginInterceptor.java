package com.jiseon.project01.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {
	//Controller 메소드 수행 직전에 로그인 된 사용자인지 검증을 한다.
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//세션 객체의 참조값을 얻어와서 세션영역에 저장된 값을 보고 로그인 유무를 알아낸다.
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		//만일 로그인 하지 않았다면
		if(id == null) {
			//로그인 페이지로 리다이렉트 이동 시키고 false를 리턴한다
			
			//원래 가려던 url정보 읽어오기
			String url = request.getRequestURI();
			//GET 방식 전송 파라미터를 query문자열로 앍어오기 (a=xxx&b=xxx&c=xxx)
			String query = request.getQueryString();
			//특수 문자는 인코딩을 해야한다.
			String encodedUrl = null;
			if(query == null) {
				//전송 파라미터가 없다면
				encodedUrl = URLEncoder.encode(url);
			} else {
				//원래 목적지가 /test/xxx.jsp라고 가정하면 아래와 같은 형식의 문자열을 만든다.
				// "/test/xxx.jsp?a=xxx&b=xxx.."
				encodedUrl = URLEncoder.encode(url+"?"+query);
			}
			//3. 로그인 하지 않았다면 users/loginform페이지로 리다이렉트 이동시킨다.
			String cPath = request.getContextPath();
			response.sendRedirect(cPath+"/users/loginform?url="+encodedUrl);
			return false;
			
		}
		//로그인을 했다면 흐름을 이어간다.
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
