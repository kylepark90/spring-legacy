package com.free.common;

public class Pagination {

	private int listSize = 2;	//목록 개수
	private int rangeSize = 10; 	// 페이지 범위
	private int page;	// 현재 페이지
	private int range;	// 각 페이지 범위 시작 번호
	private int listCnt;	// 총게시물 갯수
	private int pageCnt;	// 총 페이지 범위 개수
	private int startPage;	// 각 페이지 범위 시작 번호
	private int startList;	
	private int endPage;	// 페이지 범위 끝 번호
	private boolean prev;
	private boolean next;
	
	

	public int getListSize() {
		return listSize;
	}



	public void setListSize(int listSize) {
		this.listSize = listSize;
	}



	public int getRangeSize() {
		return rangeSize;
	}



	public void setRangeSize(int rangeSize) {
		this.rangeSize = rangeSize;
	}



	public int getPage() {
		return page;
	}



	public void setPage(int page) {
		this.page = page;
	}



	public int getRange() {
		return range;
	}



	public void setRange(int range) {
		this.range = range;
	}



	public int getListCnt() {
		return listCnt;
	}



	public void setListCnt(int listCnt) {
		this.listCnt = listCnt;
	}



	public int getPageCnt() {
		return pageCnt;
	}



	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}



	public int getStartPage() {
		return startPage;
	}



	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}



	public int getStartList() {
		return startList;
	}



	public void setStartList(int startList) {
		this.startList = startList;
	}



	public int getEndPage() {
		return endPage;
	}



	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}



	public boolean isPrev() {
		return prev;
	}



	public void setPrev(boolean prev) {
		this.prev = prev;
	}



	public boolean isNext() {
		return next;
	}



	public void setNext(boolean next) {
		this.next = next;
	}



	// page 는 현재 페이지 정보, range는 형재 페이지 범위 정보, listCnt는 게시물의 총 개수
	public void pageInfo(int page,int range,int listCnt) {
		this.page = page;
		this.range = range;
		this.listCnt = listCnt;
		
		//전체 페이지 수
		this.pageCnt = (int)Math.ceil(listCnt/listSize);	//Math.ceil 반올림, 몇 페이지 나오는지 개산
		
		//시작 페이지
		this.startPage = (range -1)*rangeSize + 1; // 각 페이지 시작번호 - 1 * 페이지 범위+ 1
		
		//끝 페이지, 다음 버튼 활성화 
		this.endPage = range * rangeSize; // 페이지 시작번호 * 페이지 범위
		
		//게시판 시작번호, mysql 에서 필요한 데이터만 쿼리하기 위해서
		this.startList = (page - 1) * listSize;
		
		//이전 버튼 상태
		this.prev = range == 1 ? false : true;	// 맨처음인지 체크
		
		// 다음 버튼 상태
		this.next = endPage > pageCnt ? false : true;	// 맨마지막 인지 체크 
		if(this.endPage > this.pageCnt) {		// 맨 마지막이면 마지막 페이지까지만 나오게 하기.
			this.endPage = this.pageCnt;	
			this.next = false;		// 다음 버튼은 비활성화.
		}
		
		
	}
}
