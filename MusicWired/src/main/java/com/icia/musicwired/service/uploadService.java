package com.icia.musicwired.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.icia.musicwired.dto.uploadDto;

public interface uploadService {
    ModelAndView fileUpload(uploadDto dto) throws IOException;

	ModelAndView fileList(int page, int limit);

	ModelAndView muView(int muCode);

	//수정페이지이동
	ModelAndView fileModiForm(int muCode);

	//수정메소드
	ModelAndView fileModify(uploadDto dto) throws IOException;


	ModelAndView fileDelete(int muCode);

	void muCount(int muCode);

	List<uploadDto> Like(int muCode);

	List<uploadDto> LikeUp(int muCode);

	List<uploadDto> LikeDown(int muCode);
}
