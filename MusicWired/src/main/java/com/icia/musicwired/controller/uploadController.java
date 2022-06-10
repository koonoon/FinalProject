package com.icia.musicwired.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.icia.musicwired.dto.uploadDto;
import com.icia.musicwired.service.uploadService;


@Controller
public class uploadController {
	
    @Autowired
    private uploadService svc;
    
    private ModelAndView mav = new ModelAndView();
    
    @Autowired
    private HttpSession session;

    //fUpload
    @RequestMapping(value = "/fUpload", method = RequestMethod.GET)
    public String fUpload() {

        return "up_Upload";
    }
    //fileUpload
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public ModelAndView fileUpload(@ModelAttribute uploadDto dto) throws IOException {
    	System.out.println("1con" + dto);
        mav = svc.fileUpload(dto);
    	System.out.println("5con" + mav);
        return mav;
    }
    
    //fileList 업로드 목록
    @GetMapping("/fileList")
    public ModelAndView fileList() {
    	mav = svc.fileList();
    	return mav;
    }
    //musicView 
    @GetMapping("/musicView")
    public ModelAndView musicView(@RequestParam("muCode")int muCode	) {
    	System.out.println("1con" + muCode);
    	mav = svc.musicView(muCode);
        svc.muCount(muCode);
    	System.out.println("5con" + mav);
		return mav;
    }
    //fileModiForm : 수정페이지 이동
    @RequestMapping(value = "/fileModiForm", method = RequestMethod.GET)
    public ModelAndView fileModiForm(@RequestParam ("muCode") int muCode) {
        System.out.println("1수정" + muCode);
        mav=svc.fileModiForm(muCode);
        System.out.println("5수정" + muCode);
        return mav;
    }
    ///fileModify : 수정메소드
    @RequestMapping(value = "/fileModify", method = RequestMethod.POST)
    public ModelAndView fileModify(@ModelAttribute uploadDto dto) throws IOException {
        System.out.println("1modi" + dto);
        mav = svc.fileModify(dto);
        System.out.println("5modi" + mav);
        return mav;
    }
    //fileDelete
    @RequestMapping(value = "/fileDelete", method = RequestMethod.GET)
    public ModelAndView fileDelete(@RequestParam ("muCode") int muCode) {
        System.out.println("1삭제" + muCode);
        mav=svc.fileDelete(muCode);
        System.out.println("5삭제" + muCode);
        return mav;
    }
    List<uploadDto> LikeList = new ArrayList<uploadDto>();

    //Like : 댓글 목록 불러오기
    @RequestMapping(value="/Like", method=RequestMethod.GET)
    public @ResponseBody List<uploadDto> LikeList(@RequestParam(value="muCode", required=true)int muCode ) {
        System.out.println("[1] like:" + muCode);
        LikeList = svc.Like(muCode);
        System.out.println("[5] like :" + LikeList);
        return LikeList;
    }
    //LikeUp
    @RequestMapping(value="/LikeUp", method=RequestMethod.POST)
    public @ResponseBody List<uploadDto> LikeUp(@RequestParam(value="muCode", required=true)int muCode ) {
        System.out.println("[1] likeup:" + muCode);
        LikeList = svc.LikeUp(muCode);
        System.out.println("[5] likeup :" + LikeList);
        return LikeList;
    }
    //LikeDown
    @RequestMapping(value="/LikeDown", method=RequestMethod.POST)
    public @ResponseBody List<uploadDto> LikeDown(@RequestParam(value="muCode", required=true)int muCode ) {
        System.out.println("[1] likeup:" + muCode);
        LikeList = svc.LikeDown(muCode);
        System.out.println("[5] likeup :" + LikeList);
        return LikeList;
    }
}
