package com.dhcnhn.paging;

import com.dhcnhn.sort.sorter;

public interface Pageble {
	Integer getPage();
	Integer getOffset();
	Integer getLimit();
	sorter getSorter();
}
