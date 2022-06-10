package com.icia.musicwired.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.icia.musicwired.dto.MusicDTO;

public interface MusicService {

	ModelAndView boardUpload(MusicDTO board) throws IllegalStateException, IOException;

	ModelAndView boardList();

	ModelAndView boardModiForm(int boCode);

	List<MusicDTO> ajaxBoardList();

	List<MusicDTO> boLikeUp(int boCode);

	List<MusicDTO> boLikeDown(int boCode);

	ModelAndView boardModify(MusicDTO board) throws IllegalStateException, IOException;

	ModelAndView boardDelete(int boCode);

}
