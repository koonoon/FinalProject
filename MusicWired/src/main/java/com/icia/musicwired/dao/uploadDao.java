package com.icia.musicwired.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.icia.musicwired.dto.uploadDto;

@Mapper
public interface uploadDao {
	
    int fileUpload(uploadDto dto);
    
	List<uploadDto> fileList();

	uploadDto musicView(int muCode);


	int fileModify(uploadDto dto);

	int fileDelete(int muCode);

	void muCount(int muCode);

	List<uploadDto> LikeList(int muCode);

	int LikeUp(int muCode);

	List<uploadDto> LikeCheck(int muCode);

	int LikeDown(int muCode);
}
