package com.icia.musicwired.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.icia.musicwired.dto.MusicDTO;
import com.icia.musicwired.service.MusicService;

@Controller
public class MusicController {

	private ModelAndView mav = new ModelAndView();
	
	@Autowired
	private MusicService musvc;
	
	List<MusicDTO> boardList = new ArrayList<MusicDTO>();
	
	
	
//	boardUploadPage : 게시글 업로드 페이지 이동 메소드
	@GetMapping("/boardUploadPage")
	public String boardUploadPage() {
		return "board_UploadPage";
	}
	
//	boardUpload : 게시글 업로드 메소드
	@PostMapping("/boardUpload")
	public ModelAndView boardUpload(@ModelAttribute MusicDTO board) throws IllegalStateException, IOException {
		System.out.println("[1] 업로드 C : " + board);
		
		mav = musvc.boardUpload(board);
		
		System.out.println("[4] 업로드 C : " + mav);
		
		return mav;
	}
	
//	boardList : 게시글 목록 메소드
	@GetMapping("/boardList")
	public ModelAndView boardList() {
		
		mav = musvc.boardList();
		
		return mav;
	}
	
//	boardModiForm : 게시글 수정페이지 이동 메소드
	@GetMapping("/boardModiForm")
	public ModelAndView boardModiForm(@RequestParam("boCode") int boCode) {
		System.out.println("[1] 수정페이지 C : " + boCode);
		mav = musvc.boardModiForm(boCode);
		System.out.println("[4] 수정페이지 C : " + mav);
		return mav;
	}
	
//	boardModify : 게시글 수정 메소드
	@PostMapping("/boardModify")
	public ModelAndView boardModify(@ModelAttribute MusicDTO board) throws IllegalStateException, IOException {
		System.out.println("[1] 수정 C : " + board);
		mav = musvc.boardModify(board);
		System.out.println("[4] 수정 C : " + mav);
		return mav;
	}
	
//	boardDelete : 게시글 삭제 메소드
	@GetMapping("/boardDelete")
	public ModelAndView boardDelete(@RequestParam("boCode") int boCode) {
		
		mav = musvc.boardDelete(boCode);
		
		return mav;
	}
	
//	ajaxBoardList : 게시글 리스트 메소드(ajax)
	@PostMapping("/ajaxBoardList")
	public @ResponseBody List<MusicDTO> ajaxBoardList(){
		
		boardList = musvc.ajaxBoardList();
		
		return boardList;
	}
	
//	boLikeUp : 게시글 좋아요 증가(ajax)
	@PostMapping("/boLikeUp")
	public @ResponseBody List<MusicDTO> boLikeUp(@RequestParam("boCode") int boCode){
		System.out.println("[1] 좋아요증가 C : " + boCode);
		
		boardList = musvc.boLikeUp(boCode);
		
		System.out.println("[4] 좋아요증가 C : " + boardList);
		
		return boardList;
	}
	
//	boLikeDown : 게시글 좋아요 감소(ajax)
	@PostMapping("/boLikeDown")
	public @ResponseBody List<MusicDTO> boLikeDown(@RequestParam("boCode") int boCode){
		System.out.println("[1] 좋아요감소 C : " + boCode);
		boardList = musvc.boLikeDown(boCode);
		System.out.println("[4] 좋아요감소 C : " + boardList);
		return boardList;
	}
	
	
}
