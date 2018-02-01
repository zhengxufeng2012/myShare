package com.javen.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javen.model.sharesBook;
import com.javen.serviceImpl.sharesBookServiceImpl;
import com.javen.util.PageBean;

@RestController
@RequestMapping("/sharesControl")
public class sharesBookControl {
	@Autowired
	 private sharesBookServiceImpl shareService;
	/**
	 * find the shares_book by page
	 * @param currentPage
	 * @return
	 */

	@RequestMapping("/findSharesByPage")
	public PageBean<sharesBook> findSharesByPage(@RequestParam(value="currentPage",defaultValue="1",required=false)int currentPage){
		
		
		System.out.println("sharebook control>"+currentPage);
		PageBean<sharesBook> list=shareService.selectShares(currentPage);
		return list;
	}
		/**
		 * this method was insert a data to the shares_book
		 */
	@RequestMapping("/insertShares")
	public boolean insertShares(int sharesId,String sharesName){
		
		System.out.println(sharesId+"<control of shares insert a data>"+sharesName);
		sharesBook sB=new sharesBook();
		
		sB.setSharesId(sharesId);
		sB.setSharesName(sharesName);
	   boolean falg=shareService.insert(sB);
		return falg;
		
	}
	@RequestMapping("/sharesSelectByPrimaryKey")
	public sharesBook sharesSelectByPrimaryKey(@RequestParam(value="sharesId",defaultValue="1",required=false)int sharesId){
		 System.out.println("control of shares start+"+sharesId);
		 sharesBook ne= shareService.selectByPrimaryKey(sharesId);
		 if(ne==null){
			 ne=shareService.selectByPrimaryKey(1);
		 }
		 
		 
		
		return ne;
		
	}
	
	}

