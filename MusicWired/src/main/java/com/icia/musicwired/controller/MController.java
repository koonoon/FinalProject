package com.icia.musicwired.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.icia.musicwired.dto.CSDTO;
import com.icia.musicwired.service.MService;


@Controller
public class MController {

	private ModelAndView mav = new ModelAndView();
	@Autowired
	private MService msvc;
	@Autowired
	private HttpSession session;
	
	
	
	
	// moveChating 채팅방 만들기
		@RequestMapping(value="/moveChating", method = RequestMethod.GET)
		public ModelAndView chating(@RequestParam("mId")String mId) {
		System.out.println("아이디" + mId);
		mav = msvc.createRoom(mId);
		return mav;

		}
	
	// chat 채팅방 열기	
	@RequestMapping(value = "/chat", method = RequestMethod.GET)
	public ModelAndView chat() {
		mav.setViewName("Chat");
		return mav;
	}

	
	
	
	// chatlist 채팅방 목록
	@RequestMapping(value="/chatlist", method = RequestMethod.GET)
	public ModelAndView chatlist() {
	mav = msvc.chatlist();
	return mav;

	}
	
	List<CSDTO> chatsave  = new ArrayList<CSDTO>();
	
	// chatSave 채팅방 저장
	@RequestMapping(value="/chatSave", method = RequestMethod.POST)
	public @ResponseBody List<CSDTO> chatSave(@ModelAttribute CSDTO csdto) {
	System.out.println("채팅저장" + csdto);
	chatsave = msvc.chatSave(csdto);
	return chatsave;

	}
	
	@RequestMapping(value="/readupdate", method = RequestMethod.POST)
	public @ResponseBody int readupdate(@RequestParam("crId")String crId) {
	System.out.println("새메세지" + crId);
	int readupdate = msvc.readupdate(crId);

	return readupdate;

	}
}
