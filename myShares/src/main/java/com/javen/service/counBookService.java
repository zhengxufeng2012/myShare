package com.javen.service;

import java.util.HashMap;

import com.javen.model.countBook;
import com.javen.util.PageBean;

public interface counBookService {

	int insert(countBook record);// insert a data
	int deleteByPrimaryKey(Integer countId);//delete a data;

	PageBean<countBook> countBookFindByPage(int currentPage, int pageSize);

	public int calculateData(HashMap<String, Object> map);

}
