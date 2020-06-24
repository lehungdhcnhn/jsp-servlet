package com.dhcnhn.paging;

import com.dhcnhn.sort.sorter;

public class PageRequest implements Pageble{
	private Integer page;
	private Integer maxPageItem;
	private sorter sort;
	
	
	public PageRequest(Integer page, Integer maxPageItem,sorter sort) {
		this.page=page;
		this.maxPageItem=maxPageItem;
		this.sort=sort;
	}
	@Override
	public Integer getPage() {
		return this.page;
	}

	@Override
	public Integer getOffset() {
		if(this.page!=null)
		{
			return (this.page-1)*this.maxPageItem;
		}
		return null;
	
	}

	@Override
	public Integer getLimit() {
		return this.maxPageItem;
	}
	public sorter getSort() {
		return sort;
	}
	@Override
	public sorter getSorter() {
		if(this.sort!=null)
		{
			return this.sort;
		}
		return null;
	}

}
