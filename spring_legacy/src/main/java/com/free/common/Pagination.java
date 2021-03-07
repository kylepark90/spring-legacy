package com.free.common;

public class Pagination {

	private int listSize = 2;	//��� ����
	private int rangeSize = 10; 	// ������ ����
	private int page;	// ���� ������
	private int range;	// �� ������ ���� ���� ��ȣ
	private int listCnt;	// �ѰԽù� ����
	private int pageCnt;	// �� ������ ���� ����
	private int startPage;	// �� ������ ���� ���� ��ȣ
	private int startList;	
	private int endPage;	// ������ ���� �� ��ȣ
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



	// page �� ���� ������ ����, range�� ���� ������ ���� ����, listCnt�� �Խù��� �� ����
	public void pageInfo(int page,int range,int listCnt) {
		this.page = page;
		this.range = range;
		this.listCnt = listCnt;
		
		//��ü ������ ��
		this.pageCnt = (int)Math.ceil(listCnt/listSize);	//Math.ceil �ݿø�, �� ������ �������� ����
		
		//���� ������
		this.startPage = (range -1)*rangeSize + 1; // �� ������ ���۹�ȣ - 1 * ������ ����+ 1
		
		//�� ������, ���� ��ư Ȱ��ȭ 
		this.endPage = range * rangeSize; // ������ ���۹�ȣ * ������ ����
		
		//�Խ��� ���۹�ȣ, mysql ���� �ʿ��� �����͸� �����ϱ� ���ؼ�
		this.startList = (page - 1) * listSize;
		
		//���� ��ư ����
		this.prev = range == 1 ? false : true;	// ��ó������ üũ
		
		// ���� ��ư ����
		this.next = endPage > pageCnt ? false : true;	// �Ǹ����� ���� üũ 
		if(this.endPage > this.pageCnt) {		// �� �������̸� ������ ������������ ������ �ϱ�.
			this.endPage = this.pageCnt;	
			this.next = false;		// ���� ��ư�� ��Ȱ��ȭ.
		}
		
		
	}
}
