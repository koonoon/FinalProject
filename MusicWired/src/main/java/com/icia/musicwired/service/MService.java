package com.icia.musicwired.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.icia.musicwired.dto.CSDTO;
import com.icia.musicwired.dto.MemberDTO;

public interface MService {

	

	ModelAndView createRoom(String mId);

	ModelAndView chatlist();

	List<CSDTO> chatSave(CSDTO csdto);

	int readupdate(String crId);

}
