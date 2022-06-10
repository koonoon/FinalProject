package com.icia.musicwired.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icia.musicwired.dao.MemberDAO;
import com.icia.musicwired.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService{

	private ModelAndView mav = new ModelAndView();
	
	@Autowired
	private MemberDAO mdao;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private PasswordEncoder pwEnc;
	
	@Autowired
	private JavaMailSender mailsender;

	@Override
	public ModelAndView memberJoin(MemberDTO member) throws IllegalStateException, IOException {
		UUID uuid = UUID.randomUUID();
		
		MultipartFile mProfile = member.getmProfile();
		
		String mProfileName = uuid.toString().substring(0,8) + "_" + mProfile.getOriginalFilename();
		
		Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/profile");
		String savePath = path + "/" + mProfileName;
		
		if(!mProfile.isEmpty()) {
			mProfile.transferTo(new File(savePath));
			member.setmProfileName(mProfileName);
		} else {
			member.setmProfileName("default.png");
		}
		
		member.setmPw(pwEnc.encode(member.getmPw()));
		
		int result = mdao.memberJoin(member);
		
		if(result>0) {
			mav.setViewName("index");
		} else {
			mav.setViewName("index");
		}
		
		return mav;
	}

	@Override
	public ModelAndView memLogin(MemberDTO member) {
		String ePw = mdao.mEpw(member.getmId());
		System.out.println(member);
		if(pwEnc.matches(member.getmPw(), ePw)) {
			
			MemberDTO loginMember = mdao.memberView(member.getmId());
			session.setAttribute("login", loginMember);
			mav.setViewName("redirect:/index");
		} else {
			session.invalidate();
			mav.addObject("error", "아이디/비밀번호가 틀립니다.");
			mav.setViewName("Mem_Login");
		}
		
		System.out.println(member);
		System.out.println(mav);
		return mav;
	}

	@Override
	public String checkId(String mId) {
		String id = mdao.checkId(mId);
		
		String msg = null;
		
		if(id==null) {
			// 사용할 수 있는 아이디
			msg = "OK";
		} else {
			msg = "NO";
		}
		return msg;
	}

	@Override
	public ModelAndView memberView(String mId) {
		MemberDTO member = mdao.memberView(mId);
		
		mav.setViewName("Mem_View");
		mav.addObject("view", member);
		return mav;
	}

	@Override
	public ModelAndView memberModiForm(String mId) {
		MemberDTO member = mdao.memberView(mId);
		
		mav.setViewName("Mem_Modi");
		mav.addObject("modi", member);
		return mav;
	}

	@Override
	public ModelAndView memberModify(MemberDTO member) throws IllegalStateException, IOException {
		UUID uuid = UUID.randomUUID();
		
		MultipartFile mProfile = member.getmProfile();
		
		String mProfileName = uuid.toString().substring(0,8)+"_"+mProfile.getOriginalFilename();
		
		Path path = Paths.get(System.getProperty("user.dir"),"src/main/resources/static/profile");
		String savePath = path + "/" + mProfileName;
		
		if(!mProfile.isEmpty()) {
			mProfile.transferTo(new File(savePath));
			member.setmProfileName(mProfileName);
		} else {
			member.setmProfileName("default.png");
		}
		
		member.setmPw(pwEnc.encode(member.getmPw()));
		
		int result = mdao.memberModify(member);
		
		if(result>0) {
			mav.setViewName("redirect:/memberViewMe");
		} else {
			mav.setViewName("Mem_Modi");
		}
		
		return mav;
	}

	@Override
	public ModelAndView memberDelete(String mId) {
		System.out.println("2"+mId);
		int result = mdao.memberDelete(mId);
		System.out.println("4"+result);
		
		if(result>0) {
			mav.setViewName("index");
		} else {
			mav.setViewName("index");
			System.out.println("수정실패");
		}
		
		System.out.println(mav);
		return mav;
	}

	@Override
	public ModelAndView memberList() {
		List<MemberDTO> memberList = mdao.memberList();
		
		mav.addObject("member", memberList);
		mav.setViewName("Mem_List");
		return mav;
	}

	@Override
	public ModelAndView memIdfind(String mEmail) {
		
		String mId = mdao.memIdfind(mEmail);
		
		System.out.println(mId);
		if(mId == null) {
			mav.addObject("error", "가입된 아이디가 없습니다.");
			mav.setViewName("Mem_Idfind");
		} else {
			mav.addObject("find", mId);
			mav.setViewName("Mem_Find");
		}
		return mav;
	}

	@Override
	public ModelAndView memPwfind(MemberDTO member) {
		
		
		if(member.getmId()==null) {
			mav.addObject("error", "가입된 아이디가 없습니다.");
			mav.setViewName("Mem_Pwfind");
		} else {
			String mPw = "";
			for (int i=0; i<12; i++) {
				mPw += (char) ((Math.random() * 26) + 97);
				mav.addObject("reset", mPw);
				mav.setViewName("Mem_Findpw");
			}
			System.out.println(member);
			
			member.setmPw(mPw);
			mdao.memPwfind(member);
		}
		return mav;
	}

	

	
}
