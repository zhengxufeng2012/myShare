package com.javen.serviceImpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javen.mapping.sharesBookMapper;
import com.javen.model.sharesBook;
import com.javen.model.techBook;
import com.javen.service.sharesBookService;
import com.javen.util.PageBean;
@Service
public class sharesBookServiceImpl implements sharesBookService {
	@Autowired
	private sharesBookMapper sharesbookmapper;

	public int countShares() {
		// TODO Auto-generated method stub
		return sharesbookmapper.countShares();
	}
    /**
     * this method is to find the sharsBook by page
     * 
     */
	public PageBean<sharesBook> selectShares(int currentPage) {
		
	        	// TODO Auto-generated method stub
				HashMap<String, Object> map = new HashMap<String, Object>();
				PageBean<sharesBook> pageBean = new PageBean<sharesBook>();
				// 封装当前页数
				pageBean.setCurrPage(currentPage);

				// 每页显示的数据
				int pageSize = 10;
				pageBean.setPageSize(pageSize);

				// 封装总记录数
				int totalCount = sharesbookmapper.countShares();
				pageBean.setTotalCount(totalCount);
            
				// 封装总页数
				double tc = totalCount;
				Double num = Math.ceil(tc / pageSize);// 向上取整
				pageBean.setTotalPage(num.intValue());
               
				
				map.put("start", (currentPage - 1) * pageSize);
				map.put("size", pageBean.getPageSize());
				// 封装每页显示的数据
				List<sharesBook> lists = sharesbookmapper.selectShares(map);
				System.out.println("shares of service>"+num+"<>"+currentPage+"<>"+totalCount);
				pageBean.setLists(lists);
				return pageBean;
	}
   /**
    * this method was insert a data to the sharesBook 
    * we need get the sharesBook
    */
	public boolean insert(sharesBook record) {
		// TODO Auto-generated method stub
		System.out.println("service of shares"+record.toString());
		sharesbookmapper.insert(record);
		
		return true;
	}
    @Override
    public sharesBook selectByPrimaryKey(Integer sharesId) {
	// TODO Auto-generated method stub
    	System.out.println("start impl of shares+"+sharesId);
	return sharesbookmapper.selectByPrimaryKey(sharesId);
}

}
