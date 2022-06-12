package com.icia.musicwired.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icia.musicwired.dao.uploadDao;
import com.icia.musicwired.dto.pagingDto;
import com.icia.musicwired.dto.uploadDto;

@Service
public class uploadServiceimpl implements uploadService {

	@Autowired
	private uploadDao dao;

	private ModelAndView mav = new ModelAndView();

	@Override
	public ModelAndView fileUpload(uploadDto dto) throws IOException {
		System.out.println("2ser" + dto);
		MultipartFile aFile = dto.getMuFile();
		String originalFileName = aFile.getOriginalFilename();
		String uuid = UUID.randomUUID().toString().substring(0, 8);
		String aFileName = uuid + "_" + originalFileName;

		Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/audio/");
		String savePath = path + "/" + aFileName;

		if (!aFile.isEmpty()) {
			dto.setmFile(aFileName);
			aFile.transferTo(new File(savePath));
		} else {
			dto.setmFile("default.mp4");
		}
		MultipartFile aFile1 = dto.getmImage();
		String originalFileName1 = aFile1.getOriginalFilename();
		String uuid1 = UUID.randomUUID().toString().substring(0, 8);
		String aFileName1 = uuid1 + "_" + originalFileName1;

		Path path1 = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/muImage/");
		String savePath1 = path1 + "/" + aFileName1;

		if (!aFile1.isEmpty()) {
			dto.setMuImage(aFileName1);
			aFile1.transferTo(new File(savePath1));
		} else {
			dto.setMuImage("default.png");
		}
		System.out.println("4ser" + mav);
		int result = dao.fileUpload(dto);
		if (result > 0) {
			mav.setViewName("index");
		} else {
			mav.setViewName("index");
		}
		return mav;
	}

	@Override
	public ModelAndView fileList(int page, int limit) {
    	System.out.println("페이징 되라 서비스: "+page);
    	System.out.println("페이징 되라 서비스: "+limit);
    	System.out.println("페이징 되라 서비스: "+mav);

		// 한 화면에 보여줄 페이지 번호 갯수
		int block = 5;

		// 전체 음원목록 갯수
		int muListCount = dao.muListCount();

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		int maxPage = (int) (Math.ceil((double) muListCount / limit)); // Math.ceil 올림
		int startPage = (((int) (Math.ceil((double) page / block))) - 1) * block + 1;
		int endPage = startPage + block - 1;
		// 오류 방지
		if (endPage > maxPage) {
			endPage = maxPage;

		}
		pagingDto paging = new pagingDto();

		paging.setPage(page);
		paging.setStartRow(startRow);
		paging.setEndRow(endRow);
		paging.setMaxPage(maxPage);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setLimit(limit);
		List<uploadDto> upList = dao.fileList(paging);
		System.out.println("paging : " +paging);
		mav.setViewName("up_List");
		mav.addObject("upList", upList);
		mav.addObject("paging", paging);
		return mav;
		
	}

	@Override
	public ModelAndView muView(int muCode) {
		System.out.println("2서비스 : " + muCode);
		uploadDto muView = dao.muView(muCode);

		System.out.println("4서비스 : " + mav);
		mav.setViewName("mu_View");
		mav.addObject("muView", muView);
		return mav;
	}

	// 수정페이지 이동
	@Override
	public ModelAndView fileModiForm(int muCode) {
		System.out.println("2수정" + muCode);
		uploadDto muView = dao.muView(muCode);
		System.out.println("4수정" + muCode);
		mav.setViewName("up_ModiForm");
		mav.addObject("modi", muView);
		return mav;
	}

	// 수정메소드
	@Override
	public ModelAndView fileModify(uploadDto dto) throws IOException {
		System.out.println("2modi" + dto);

		MultipartFile aFile = dto.getMuFile();
		String originalFileName = aFile.getOriginalFilename();
		String uuid = UUID.randomUUID().toString().substring(0, 8);
		String aFileName = uuid + "_" + originalFileName;

		Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/audio/");
		String savePath = path + "/" + aFileName;

		if (!aFile.isEmpty()) {
			dto.setmFile(aFileName);
			aFile.transferTo(new File(savePath));
		} else {
			dto.setmFile("default.mp4");
		}
		MultipartFile aFile1 = dto.getmImage();
		String originalFileName1 = aFile1.getOriginalFilename();
		String uuid1 = UUID.randomUUID().toString().substring(0, 8);
		String aFileName1 = uuid1 + "_" + originalFileName1;

		Path path1 = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/muImage/");
		String savePath1 = path1 + "/" + aFileName1;

		if (!aFile1.isEmpty()) {
			dto.setMuImage(aFileName1);
			aFile1.transferTo(new File(savePath1));
		} else {
			dto.setMuImage("default.png");
		}
		System.out.println("4moid" + mav);
		int result = dao.fileModify(dto);
		if (result > 0) {
			mav.setViewName("index");
		} else {
			mav.setViewName("index");
		}
		return mav;
	}

	@Override
	public ModelAndView fileDelete(int muCode) {

		int result = dao.fileDelete(muCode);
		if (result > 0) {
			mav.setViewName("redirect:/fileList");
		} else {
			mav.setViewName("index");
		}

		return mav;
	}

	@Override
	public void muCount(int muCode) {
		dao.muCount(muCode);
	}

	////////////////////////////////////////////
	// 좋아요 구현
	List<uploadDto> LikeList = new ArrayList<uploadDto>();

	// 좋아요 실행
	@Override
	public List<uploadDto> Like(int muCode) {
		System.out.println("2like" + muCode);
		LikeList = dao.LikeList(muCode);
		System.out.println("4like" + LikeList);
		return LikeList;
	}

	@Override
	public List<uploadDto> LikeUp(int muCode) {

		int result = dao.LikeUp(muCode);
		if (result > 0) {
			LikeList = dao.LikeCheck(muCode);
		} else {
			LikeList = null;
		}
		return LikeList;
	}

	// 좋아요 취소
	@Override
	public List<uploadDto> LikeDown(int muCode) {

		int result = dao.LikeDown(muCode);
		if (result > 0) {
			LikeList = dao.LikeCheck(muCode);
		} else {
			LikeList = null;
		}

		return LikeList;
	}

}
