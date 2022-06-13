package com.icia.musicwired.dto;

import org.apache.ibatis.type.Alias;

@Alias("paging")
public class pagingDto {
	private int page;
	private int startRow;
	private int endRow;
	private int maxPage;
	private int startPage;
	private int endPage;
	private int limit;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	@Override
	public String toString() {
		return "pagingDto [page=" + page + ", startRow=" + startRow + ", endRow=" + endRow + ", maxPage=" + maxPage
				+ ", startPage=" + startPage + ", endPage=" + endPage + ", limit=" + limit + "]";
	}
	
	
}
