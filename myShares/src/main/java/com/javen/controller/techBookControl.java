package com.javen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javen.model.techBook;
import com.javen.serviceImpl.techBookServiceImpl;
import com.javen.util.PageBean;

@RestController
@RequestMapping("/techBook")
public class techBookControl {
	@Autowired
	private techBookServiceImpl techbookService;
	/**
	 * find the techBook by page 
	 * one page have 5 data  the url only give me the page 
	 * we will get the techId techOption techLong techShow
	 * @param currentPage
	 * @return
	 */
	@RequestMapping("/findBypage")
	 public PageBean<techBook> main(@RequestParam(value="currentPage",defaultValue="1",required=false)int currentPage){
		 PageBean<techBook>  pageBean =   techbookService.findByPage(currentPage);//回显分页数据
		 return pageBean;
	    }
	 /**
	  * the method use to insert a data to the techBook 
	  * the url give me the techLong techOption 
	  * and we need give the techBook the techShow  the techId will default
	  * @param time
	  * @param detaile
	  */
	 @RequestMapping("/addTech")
	 public boolean addTech(String time,String detaile){
		 
		 techBook techbook=new techBook();
		 techbook.setTechLong(time);
		 techbook.setTechOption(detaile);
		 techbook.setTechShow(1);
		// techbook.setTechId(6);
		 System.out.println("进入到tech控制"+detaile+"<>"+time);
	    Boolean falg=techbookService.insert(techbook);
		 return  falg;
	 }
	 /**
	  * the method will find a data will the url give you the techId 
	  * @param techId
	  * @return
	  */
	 @RequestMapping("/selectByPrimaryKey")
	 public List<techBook> selectByPrimaryKey(@RequestParam(value="techId",defaultValue="1",required=false)int techId){
		
		 techbookService.selectByPrimaryKey(techId);
	   
		 return techbookService.selectByPrimaryKey(techId);
		 
	 }
	 /**
	  * the method use to update the data 
	  * the url give the techBook
	  * @param record
	  * @return
	  */
	 
	 @RequestMapping("/updateTech")
	 public boolean updateTech(techBook record){
		 System.out.println(record+"<>"+record.getTechLong()+"<>"+record.getTechOption()+"<>"+record.getTechShow()+"<>"+record.getTechId());
		 techBook techbook= new techBook();
		 techbook.setTechLong(record.getTechLong());
		 techbook.setTechOption(record.getTechOption());
		 techbook.setTechShow(record.getTechShow());
		 techbook.setTechId(record.getTechId());
		boolean falg=  techbookService.updateByPrimaryKey(techbook);
		if(falg==true){return true;
		}else{
			return false;
		}		 
	 }
	 /**
	  * select the data by techOption but vague
	  */
	 @RequestMapping("/techByOption")
	 public List<techBook> selectTechByOption(@RequestParam(value="techLong",defaultValue="长",required=false)String tehcLong){
		System.out.println("vague select >"+tehcLong);
		if(tehcLong.equals("long")){
			tehcLong=null;
			List<techBook> currPage=techbookService.selectTechByOption(tehcLong);
			System.out.println("进入到这里了"+currPage);
			return currPage; 
		}else{
			List<techBook> currPage=techbookService.selectTechByOption(tehcLong);
		return currPage; 
		}
	 }
	
}
