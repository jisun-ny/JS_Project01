package com.jiseon.project01.cafe.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jiseon.project01.cafe.dto.CafeForumDto;
import com.jiseon.project01.cafe.service.CafeForumService;

@Controller
public class CafeController {
	
	@Autowired
	private CafeForumService service;
	
	@RequestMapping("/cafe/forum/delete")
	public String delete(int num, HttpServletRequest request) {
		//service에 삭제할 글 번호와 HttpServletRequest 객체를 전달해서 해당 글을 삭제시킴
		service.deleteContent(num, request);
		//글 목록 보기로 리다이렉트 시킨다.
		return "redirect:/cafe/forum";
	}
	
	@RequestMapping("/cafe/forum/update")
	public String update(CafeForumDto dto, HttpServletRequest request) {
		service.updateContent(dto, request);
		return "cafe/forum/update";
	}
	
	@RequestMapping("/cafe/forum/updateform")
	public String updateform(HttpServletRequest request) {
		service.getData(request);
		return "cafe/forum/updateform";
	}
	
	@RequestMapping("/cafe/forum/detail")
	public String detail(HttpServletRequest request) {
		service.getDetail(request);
		return "cafe/forum/detail";
	}
	
	@RequestMapping("/cafe/forum/insert")
	public String insert(CafeForumDto dto, HttpServletRequest request) {
		service.saveContent(dto, request);
		return "cafe/forum/insert";
	}
	
	@RequestMapping("/cafe/forum/insertform")
	public String insertform() {
		return "cafe/forum/insertform";
	}
	
	@RequestMapping("/cafe/forum")
	public String forum(HttpServletRequest request) {
		service.getList(request);
		return "cafe/forum";
	}
}
