package com.jiseon.project01.users.controller;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jiseon.project01.users.dto.UsersDto;
import com.jiseon.project01.users.service.UsersService;

@Controller
public class UsersController {
	
	@Autowired
	private UsersService service;
	
	@RequestMapping("/users/delete")
	public ModelAndView delete(HttpSession session, ModelAndView mView) {
		service.deleteUser(session, mView);
		mView.setViewName("users/delete");
		return mView;
	}
	
	@RequestMapping(value = "/users/update", method = RequestMethod.POST )
	public ModelAndView update(UsersDto dto, HttpSession session, ModelAndView mView) {
		//서비스를 이용해서 개인정보를 수정 반영하고
		service.updateUser(dto, session);
		//개인 정보 보기로 리다이렉트 이동 시킨다.
		mView.setViewName("redirect:/users/info");
		return mView;
	}
	
	@RequestMapping(value= "/users/profile_upload", method=RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> profileUpload(HttpServletRequest request, MultipartFile image) {
		//서비스를 이용해서 이미지를 upoad 폴더에 저장하고 리턴되는 Map을 리턴해서 json 문자열 응답하기
		return service.saveProfileImage(request, image);
		}
	
	@RequestMapping("/users/updateform")
	public ModelAndView updateForm (HttpSession session, ModelAndView mView ) {
		service.getInfo(session, mView);
		mView.setViewName("users/updateform");
		return mView;
	}
	
	@RequestMapping("/users/pwd_update")
	public ModelAndView pwdUpdate(UsersDto dto, HttpSession session, ModelAndView mView ) {
		//서비스에 필여한 객체의 참조값을 전달해서 비밀번호 수정 로직을 처리한다.
		service.updateUserPwd(session, dto, mView);
		//view page로 forward 이동해서 작업 결과를 응답한다.
		mView.setViewName("users/pwd_update");
		return mView;
	}
	
	@RequestMapping("/users/pwd_updateform")
	public String pwdUpdateForm() {
		return "users/pwd_updateform";
	}
	
	@RequestMapping("/users/info")
	public ModelAndView info (ModelAndView mView, HttpSession session) {
		service.getInfo(session, mView);
		mView.setViewName("users/info");
		return mView;
	}
	
	@RequestMapping("/users/logout")
	public String logout(HttpSession session) {
		//세션에서 id라는 키값으로 저장된 값 삭제
		session.removeAttribute("id");
		return "users/logout";
	}
	
	@RequestMapping("/users/login")
	public ModelAndView login(ModelAndView mView, UsersDto dto, String url, HttpSession session) {
		service.loginProcess(dto, session);
		//로그인 후에 가야할 목적지 정보를 인코딩하지 않는 것과 인코딩한 것을 모두 ModelAndView 객체에 담고
		String encodedUrl = URLEncoder.encode(url);
		mView.addObject("url", url);
		mView.addObject("encodedUrl", encodedUrl);
		
		//mView page로 forward 이동해서 응답한다.
		mView.setViewName("users/login");
		return mView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/users/loginform")
	public String loginform() {
		return "users/loginform";
	}
	
	@RequestMapping(method= RequestMethod.POST, value="/users/signup")
	public ModelAndView signup(ModelAndView mView, UsersDto dto) {
		service.addUser(dto);
		mView.setViewName("users/signup");		
		return mView;
	}
	
	@RequestMapping("/users/signup_form")
	public String signupForm() {
		return "users/signup_form";
	}
	
	
}
