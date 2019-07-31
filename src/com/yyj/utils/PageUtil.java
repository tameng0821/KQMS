package com.yyj.utils;

public class PageUtil {
	private int currentPageIndex ; //当前页
	private int pageSize ; //页面大小
	private int startIndex ; //开始索引值

	public PageUtil()
	{
		this.currentPageIndex = 1;
		this.pageSize = 10;
		this.startIndex = (this.currentPageIndex - 1) * this.pageSize; 
	}
	
	public PageUtil(int currentPageIndex )
	{
		this.currentPageIndex = currentPageIndex;
		this.pageSize = 10;
		this.startIndex = 
				(this.currentPageIndex - 1) * this.pageSize; 
	}
	
	public PageUtil(int currentPageIndex , int pageSize )
	{
		this.currentPageIndex = currentPageIndex;
		this.pageSize = pageSize;
		this.startIndex = 
				(this.currentPageIndex - 1) * this.pageSize; 
	}	
	
	public int getCurrentPageIndex() {
		return currentPageIndex;
	}

	public void setCurrentPageIndex(int currentPageIndex) {
		this.currentPageIndex = currentPageIndex;		
		this.startIndex = 
				(this.currentPageIndex - 1) * this.pageSize; 
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		this.startIndex = 
				(this.currentPageIndex - 1) * this.pageSize; 
	}

	public int getStartIndex() {
		return startIndex;
	}

	@Override
	public String toString() {
		return "PageUtil [currentPageIndex=" + currentPageIndex + ", pageSize="
				+ pageSize + ", startIndex=" + startIndex + "]";
	}
}
