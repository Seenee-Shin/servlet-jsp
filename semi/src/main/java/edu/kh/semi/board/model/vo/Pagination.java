package edu.kh.semi.board.model.vo;

//페이징 처리에 필요한 모든 객체 
public class Pagination {
	
	private int currentPage; //현재 페이지 번호 
	private int listCount;  //전체 게시글 수 
	
	private int limit = 10;   //한페이지 목록에 보여지는 게시글의 수 
	private int pageSize = 10; //보여질 페이지 번호 개수
	
	private int maxPage; //마지막 페이지 번호
	private int startPage; //보여지는 맨 앞 페이지 번호
	private int endPage; // 보여지는 맨 뒤 페이지 번호 
	
	private int prevPage;  //이전 페이지 번호 맨끝 
	private int nextPage; //다음 페이지 번호 맨 앞
	
	
	
	public Pagination(int listCount, int currentPage) {
		this.listCount = listCount;
		this.currentPage = currentPage;
		
		//객체생성시 전달 받은 값을 이용해 나머지 필드 값 생성
		makePagination();
		
	}
	
	//페이지 사이즈와 limit를 그때그때 작성하는 생성자
	public Pagination(int listCount, int currentPage,int limit, int pageSize) {
		this.listCount = listCount;
		this.currentPage = currentPage;
		this.limit = limit;
		this.pageSize = pageSize;
		makePagination();
	}
	
	
	//getter / setter 
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		makePagination();
	}
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
		makePagination();
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
		makePagination();;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		makePagination();
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
	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	@Override
	public String toString() {
		return "Pagination [currentPage=" + currentPage + ", listCount=" + listCount + ", limit=" + limit
				+ ", pageSize=" + pageSize + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage="
				+ endPage + ", prevPage=" + prevPage + ", nextPage=" + nextPage + "]";
	}
	
	private void makePagination() {
		//한페이지에 글이 10개씩 보여질 경우 1~10까지 한페이지 -> 올림처리
		maxPage =(int)Math.ceil( (double)listCount/limit);
		
		//start page : 페이지 목록에서 가장 첫번째 보여지느 ㄴ목록 
		startPage =(currentPage-1)/pageSize*pageSize +1;
		
		//endPage : 가장 끝 페이지 번호
		endPage = startPage + pageSize - 1;
		
		//계산된 endPage가 전체 페이지수를 초과하는 경우 : 
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		//이전페이지 , 다음 페이지 
//		prevPage = (currentPage - 1) / pageSize * pageSize;
		if(currentPage <= 10) 	prevPage =1;
		else	prevPage = startPage -1 ;
		
		
		if(endPage == maxPage) nextPage = maxPage;
		else nextPage = endPage+1;
		
		
	}
	
	
}
