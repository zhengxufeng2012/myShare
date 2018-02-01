package com.javen.serviceImpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javen.mapping.countBookMapper;
import com.javen.model.countBook;

import com.javen.service.counBookService;
import com.javen.util.PageBean;

@Service
public class countBookServiceImpl implements counBookService {
	@Autowired
    private countBookMapper countbookMapper;
	
	@Override
	public int insert(countBook record) {
		// TODO Auto-generated method stub
		System.out.println("the method of countBookServiceImpl >+ "+record);
		countbookMapper.insert(record);
		
		return 0;
	}
	@Override
	public int deleteByPrimaryKey(Integer countId){
		System.out.println("impl of countbook method of delete a data+"+countId);
		countbookMapper.deleteByPrimaryKey(countId);
		return countId;
		
	}

	@Override
	public PageBean<countBook> countBookFindByPage(int currentPage, int pageSize) {
		
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		PageBean<countBook> pageBean = new PageBean<countBook>();
		// 封装当前页数
		pageBean.setCurrPage(currentPage);
		//每页多少数量
		pageBean.setPageSize(pageSize);
		// 封装总记录数
		int totalCount = countbookMapper.countCountBook();
		
		Double num = Math.ceil(totalCount / pageSize);// 向上取整
		pageBean.setTotalPage(num.intValue());
		
		map.put("start", (currentPage - 1) * pageSize);
		map.put("size", pageBean.getPageSize());
		// 封装每页显示的数据
		System.out.println("count_book impl findbyPage inter+"+pageSize+"<>"+currentPage);
		List<countBook> lists= countbookMapper.selectCountBookByPage(map);
		
		pageBean.setLists(lists);
		System.out.println("count_book impl findbypage result="+pageBean);
		return pageBean;
	}

	@Override
	public int calculateData(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		System.out.println("calculateData of impl +" +map);
		return countbookMapper.calculateData(map);
	}

}
