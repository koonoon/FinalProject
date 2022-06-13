package com.icia.musicwired.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.icia.musicwired.dto.CRDTO;
import com.icia.musicwired.dto.CSDTO;
import com.icia.musicwired.dto.MemberDTO;

@Mapper
public interface MDAO {

	
	int createRoom(String mId);

	CRDTO getRoom(String mId);

	List<CRDTO> chatlist();

	int chatSave(CSDTO csdto);

	List<CSDTO> getSaveChat(int crNum);

	int readupdate(String crId);

	int readupdate1(int csNum);

}
