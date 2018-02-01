package com.javen.serviceImpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javen.mapping.techBookMapper;
import com.javen.model.techBook;
import com.javen.service.techBookService;
import com.javen.util.PageBean;

@Service
public class techBookServiceImpl implements techBookService {
	@Autowired
	private techBookMapper techbookMapper;

	/**
	 * select the count of the tech
	 */
	public int selectCount() {
		// TODO Auto-generated method stub
		return techbookMapper.getTechCount();
	}

	/**
	 * select the data by page
	 */
	public PageBean<techBook> findByPage(int currentPage) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		PageBean<techBook> pageBean = new PageBean<techBook>();
		// 封装当前页数
		pageBean.setCurrPage(currentPage);

		// 每页显示的数据
		int pageSize = 10;
		pageBean.setPageSize(pageSize);

		// 封装总记录数
		int totalCount = techbookMapper.getTechCount();
		pageBean.setTotalCount(totalCount);

		// 封装总页数
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);// 向上取整
		pageBean.setTotalPage(num.intValue());

		map.put("start", (currentPage - 1) * pageSize);
		map.put("size", pageBean.getPageSize());
		// 封装每页显示的数据
		
		List<techBook> lists = techbookMapper.selectTechByPage(map);
		pageBean.setLists(lists);

		return pageBean;
	}

	/**
	 * add a techBook data
	 */

	public Boolean insert(techBook record) {
		// TODO Auto-generated method stub
		System.out.println("进入到tech服务" + record);
		return techbookMapper.insert(record);
	}

	public List<techBook> selectByPrimaryKey(Integer techId) {
		// TODO Auto-generated method stub
		
		return techbookMapper.selectByPrimaryKey(techId);
	}

	public Boolean updateByPrimaryKey(techBook record) {
		// TODO Auto-generated method stub
		return techbookMapper.updateByPrimaryKey(record);
	}
    /**
     * select the techOption by vague
     */
	public List<techBook> selectTechByOption(String techOption) {
		System.out.println("come to the sevice of vague find"+techbookMapper.selectTechByOption(techOption));
		return 	techbookMapper.selectTechByOption(techOption);
	}

}