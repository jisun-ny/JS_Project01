package com.jiseon.project01.cafe.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jiseon.project01.cafe.dto.CafeForumDto;
import com.jiseon.project01.cafe.dto.CafeSellingDto;
import com.jiseon.project01.cafe.dto.CafeWantedDto;
import com.jiseon.project01.cafe.service.CafeForumService;
import com.jiseon.project01.cafe.service.CafeSellingService;
import com.jiseon.project01.cafe.service.CafeWantedService;

@Controller
public class CafeController {
	
	@Autowired
	private CafeForumService service;
	@Autowired
	private CafeSellingService sellingService;
	
	@Autowired
	private CafeWantedService wantedService;
	
	//<wanted>---------------------------------------------------------------------------------------------
	@RequestMapping("/cafe/wanted/delete")
	public String wantedDelete(int num, HttpServletRequest request) {
		//service에 삭제할 글 번호와 HttpServletRequest 객체를 전달해서 해당 글을 삭제시킴
		wantedService.deleteContent(num, request);
		//글 목록 보기로 리다이렉트 시킨다.
		return "redirect:/cafe/wanted";
	}
	
	@RequestMapping("/cafe/wanted/update")
	public String wantedUpdate(CafeWantedDto dto, HttpServletRequest request) {
		wantedService.updateContent(dto, request);
		return "cafe/wanted/update";
	}
	
	@RequestMapping("/cafe/wanted/updateform")
	public String wantedUpdateform(HttpServletRequest request) {
		wantedService.getData(request);
		return "cafe/wanted/updateform";
	}
	
	@RequestMapping("/cafe/wanted/detail")
	public String wantedDetail(HttpServletRequest request) {
		wantedService.getDetail(request);
		return "cafe/wanted/detail";
	}
	
	@RequestMapping("/cafe/wanted/insert")
	public String wantedInsert(CafeWantedDto dto, HttpServletRequest request) {
		wantedService.saveContent(dto, request);
		return "cafe/wanted/insert";
	}
	
	@RequestMapping("/cafe/wanted/insertform")
	public String wantedInsertform() {
		return "cafe/wanted/insertform";
	}
	
	@RequestMapping("/cafe/wanted")
	public String wantedWanted(HttpServletRequest request) {
		wantedService.getList(request);
		return "cafe/wanted";
	}
	
	
	//<selling>--------------------------------------------------------------------------------------------
	@RequestMapping("/cafe/selling/delete")
	public String sellingDelete(int num, HttpServletRequest request) {
		//service에 삭제할 글 번호와 HttpServletRequest 객체를 전달해서 해당 글을 삭제시킴
		sellingService.deleteContent(num, request);
		//글 목록 보기로 리다이렉트 시킨다.
		return "redirect:/cafe/selling";
	}
	
	@RequestMapping("/cafe/selling/update")
	public String sellingUpdate(CafeSellingDto dto, HttpServletRequest request) {
		sellingService.updateContent(dto, request);
		return "cafe/selling/update";
	}
	
	@RequestMapping("/cafe/selling/updateform")
	public String sellingUpdateform(HttpServletRequest request) {
		sellingService.getData(request);
		return "cafe/selling/updateform";
	}
	
	@RequestMapping("/cafe/selling/detail")
	public String sellingDetail(HttpServletRequest request) {
		sellingService.getDetail(request);
		return "cafe/selling/detail";
	}
	
	@RequestMapping("/cafe/selling/insert")
	public String sellingInsert(CafeSellingDto dto, HttpServletRequest request) {
		sellingService.saveContent(dto, request);
		return "cafe/selling/insert";
	}
	
	@RequestMapping("/cafe/selling/insertform")
	public String sellingInsertform() {
		return "cafe/selling/insertform";
	}
	
	@RequestMapping("/cafe/selling")
	public String sellingSelling(HttpServletRequest request) {
		sellingService.getList(request);
		return "cafe/selling";
	}
	//<forum>----------------------------------------------------------------------------------------------
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
