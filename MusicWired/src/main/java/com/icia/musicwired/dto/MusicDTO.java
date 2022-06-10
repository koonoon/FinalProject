package com.icia.musicwired.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("Music")
public class MusicDTO {
	
	private int boCode;			// 게시글 번호
	private String boWriter;	// 게시글 작성자
	private String boTitle;		// 글 제목
	private String boContent;	// 글 내용
	private String boLike;		// 게시글 좋아요 수
	private Date boDate;		// 게시글 작성일
	
	private MultipartFile boImageFile;	// 게시글 이미지 파일
	private String boImage;		// 게시글 이미지(이름)
	public int getBoCode() {
		return boCode;
	}
	public void setBoCode(int boCode) {
		this.boCode = boCode;
	}
	public String getBoWriter() {
		return boWriter;
	}
	public void setBoWriter(String boWriter) {
		this.boWriter = boWriter;
	}
	public String getBoTitle() {
		return boTitle;
	}
	public void setBoTitle(String boTitle) {
		this.boTitle = boTitle;
	}
	public String getBoContent() {
		return boContent;
	}
	public void setBoContent(String boContent) {
		this.boContent = boContent;
	}
	public String getBoLike() {
		return boLike;
	}
	public void setBoLike(String boLike) {
		this.boLike = boLike;
	}
	public Date getBoDate() {
		return boDate;
	}
	public void setBoDate(Date boDate) {
		this.boDate = boDate;
	}
	public MultipartFile getBoImageFile() {
		return boImageFile;
	}
	public void setBoImageFile(MultipartFile boImageFile) {
		this.boImageFile = boImageFile;
	}
	public String getBoImage() {
		return boImage;
	}
	public void setBoImage(String boImage) {
		this.boImage = boImage;
	}
	@Override
	public String toString() {
		return "MusicDTO [boCode=" + boCode + ", boWriter=" + boWriter + ", boTitle=" + boTitle + ", boContent="
				+ boContent + ", boLike=" + boLike + ", boDate=" + boDate + ", boImageFile=" + boImageFile
				+ ", boImage=" + boImage + "]";
	}

	
	
}
