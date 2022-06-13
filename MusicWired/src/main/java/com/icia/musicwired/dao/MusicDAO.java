package com.icia.musicwired.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.icia.musicwired.dto.MusicDTO;

@Mapper
public interface MusicDAO {

//	boardUpload : 게시글 업로드 메소드
	int boardUpload(MusicDTO board);

	List<MusicDTO> boardList();

	MusicDTO boardModiView(int boCode);

	List<MusicDTO> ajaxBoardList();

	int boLikeUp(int boCode);

	List<MusicDTO> ajaxBoardListSelect(int boCode);

	int boLikeDown(int boCode);

	int boardModify(MusicDTO board);

	int boardDelete(int boCode);

}
