package com.javen.service;

import com.javen.model.sharesBook;
import com.javen.util.PageBean;

public interface sharesBookService {
	int countShares();
	PageBean<sharesBook> selectShares(int currentPage);
	boolean insert(sharesBook record);
	sharesBook selectByPrimaryKey(Integer sharesId);

}
