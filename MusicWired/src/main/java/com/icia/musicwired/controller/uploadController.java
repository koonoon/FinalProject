package com.icia.musicwired.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public ModelAndView fileList(@RequestParam(value="page",required = false,defaultValue="1")int page,
    		@RequestParam(value="limit", required = false, defaultValue = "5")int limit) {
    	mav = svc.fileList(page,limit);
    	System.out.println("페이징 되라 컨트롤러: "+page);
    	System.out.println("페이징 되라 컨트롤러: "+limit);
    	System.out.println("페이징 되라 컨트롤러: "+mav);
    	return mav;
    }
    //muView 
    @GetMapping("/muView")
    public ModelAndView muView(@RequestParam("muCode")int muCode,HttpServletRequest request, HttpServletResponse response ) {
    	System.out.println("1con" + muCode);
    	Cookie viewCookie = null;
		Cookie[] cookies = request.getCookies();
		System.out.println("cookie : " + cookies);
		mav = svc.muView(muCode);
		if(cookies !=null) {
			for(int i=0; i<cookies.length; i++) {
//System.out.println("쿠키 이름 : "+cookies[i].getName());
                
                //만들어진 쿠키들을 확인하며, 만약 들어온 적 있다면 생성되었을 쿠키가 있는지 확인
				if(cookies[i].getName().equals("|"+muCode + "|")){
					System.out.println("if문 쿠키 이름" + cookies[i].getName());
					  //찾은 쿠키를 변수에 저장
					viewCookie=cookies[i];
				}
			}
		}else {
			System.out.println("cookies 확인 로직 : 쿠키가 없습니다.");
		}
    	
    	
		//만들어진 쿠키가 없음을 확인
		if(viewCookie==null) {
        
          	System.out.println("viewCookie 확인 로직 : 쿠키 없당");
			
            try {
            
            	//이 페이지에 왔다는 증거용(?) 쿠키 생성
				Cookie newCookie=new Cookie("|"+muCode+"|","readCount");
				response.addCookie(newCookie);
                
                //쿠키가 없으니 증가 로직 진행
			
				svc.muCount(muCode);
			} catch (Exception e) {
				System.out.println("쿠키 넣을때 오류 나나? : "+e.getMessage());
				e.getStackTrace();

			}
		
        //만들어진 쿠키가 있으면 증가로직 진행하지 않음
		}else {
			System.out.println("viewCookie 확인 로직 : 쿠키 있당");
			String value=viewCookie.getValue();
			System.out.println("viewCookie 확인 로직 : 쿠키 value : "+value);
		}
		
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
