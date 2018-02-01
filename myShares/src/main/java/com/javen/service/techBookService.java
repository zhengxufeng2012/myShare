package com.javen.service;

import java.util.List;

import com.javen.model.techBook;
import com.javen.util.PageBean;

public interface techBookService {
	int selectCount();
	PageBean<techBook> findByPage(int currentPage);
	Boolean insert(techBook record);
	List<techBook> selectByPrimaryKey(Integer techId);
	Boolean updateByPrimaryKey(techBook record);
	List<techBook> selectTechByOption(String techOptin);
}
