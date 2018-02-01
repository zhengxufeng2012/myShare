package com.javen.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javen.model.countBook;
import com.javen.model.sharesBook;
import com.javen.serviceImpl.countBookServiceImpl;
import com.javen.serviceImpl.sharesBookServiceImpl;
import com.javen.util.PageBean;

@RestController
@RequestMapping("/countBook")
public class countBookControl {
	@Autowired
	private countBookServiceImpl countBookImpl;
	@Autowired
	private sharesBookServiceImpl sharesBookImpl;

	/**
	 * the method create by dawn.zheng the method use insert the data of
	 * countBook
	 * 
	 * @param record
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "countBookInsert")
	public boolean countBookInsert(countBook record) {
		System.out.println("the method of countbookInsert" + record.getCountId() + "\n+techlong" + record.getTechLong()
				+ "\n+techOptin+" + record.getTechOption() + "\n+sharedid+" + record.getSharesId() + "\n+sharesName+"
				+ record.getSharesName() + "\n+startTime+" + record.getStartTime() + record.getEndTime());

		countBook countbook = new countBook();
		countbook.setTechLong(record.getTechLong());// set the techlong
		countbook.setTechOption(record.getTechOption());// set the techoption
		countbook.setSharesId(record.getSharesId());// set the sharesid
		sharesBook sharesName = sharesBookImpl.selectByPrimaryKey(record.getSharesId());
		countbook.setSharesName(sharesName.getSharesName());

		double fstarsMoney = (double) (Math.round(record.getStartMoney() * 100) / 100.0);
		countbook.setStartMoney(fstarsMoney);// set the startMoney

		double fstartAddMoney = (double) (Math.round(record.getStartMoney() * 101) / 100.0);
		countbook.setStarAddMoney(fstartAddMoney);// set the
													// start_add_money(增加5%后的钱)

		double fstandMoney = (double) (Math.round(record.getStandMoney() * 100) / 100.0);
		countbook.setStandMoney(fstandMoney);// set the stand_money(标准7天后的钱)
		countbook.setEndMoney((double) (Math.round(record.getEndMoney() * 100) / 100.0));// set
																							// the
																							// end_money
																							// (最后的钱)
		System.out.println("......" + fstarsMoney + "<>" + fstartAddMoney + "<<>>" + fstandMoney);
		Date startTime = record.getStartTime();
		if (startTime.toString() == null) {
			Date date = new Date();
			startTime = date;
		}
		countbook.setStartTime(startTime);// set the start time

		Date endTime = record.getEndTime();
		if (endTime.toString() == null) {
			Date date = new Date();// 取时间
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(calendar.DATE, 12);// 把日期往后增加一天.整数往后推,负数往前移动
			date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
			endTime = date;
		}
		countbook.setEndTime(endTime);// set the end_time
		double Icr = (record.getEndMoney() - record.getStartMoney()) / record.getStartMoney();
		double fIncreateRate = (double) (Math.round(Icr * 100) / 100.0);
		countbook.setIncreateRate(fIncreateRate);// calculation the
													// increate_rate(总共增加的率)

		double ISICR = ((record.getStandMoney() - record.getStartMoney()) / record.getStartMoney());
		double fStandIncreateRate = (double) (Math.round(ISICR * 101) / 100.0);
		countbook.setStandIncreateRate(fStandIncreateRate);// 标准时间增加率

		double IWICR = (record.getEndMoney() - record.getStartMoney() * 1.01) / (record.getStartMoney() * 1.01);
		double fWeightIncreateRate = (double) (Math.round(IWICR * 100) / 100.0);
		countbook.setWeightIncreateRate(fWeightIncreateRate);// 加权后增加率
		double IWSICR = ((record.getStandMoney() - record.getStartMoney() * 1.01) / (record.getStartMoney() * 1.01));
		double fWeightStandIncreateRate = (double) (Math.round(IWSICR * 100) / 100.0);
		countbook.setWeightStandIncreateRate(fWeightStandIncreateRate);// 加权后标准增加率
		System.out.println(fIncreateRate + "+" + fStandIncreateRate + "<>" + fWeightIncreateRate + "<>"
				+ fWeightStandIncreateRate);

		countbook.setUseLong((int) ((endTime.getTime() - startTime.getTime()) / (1000 * 60 * 60 * 24) + 1));
		countBookImpl.insert(countbook);
		boolean flag = false;
		if (countbook.getUseLong() != null) {
			flag = true;
		}
		return flag;

	}

	@RequestMapping("findCountBookBypage")
	public PageBean<countBook> findCountBookBypage(
			@RequestParam(value = "currentPage", defaultValue = "1", required = false) int currentPage,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		PageBean<countBook> pageBean = countBookImpl.countBookFindByPage(currentPage, pageSize);
		return pageBean;
	}
    
	@RequestMapping("calculateData")
	public int calculateData(String tech_long,String tech_option,@RequestParam(value ="startRate", defaultValue = "-10.0", required = false) double startRate,
			@RequestParam(value ="endRate", defaultValue = "10.0", required = false) double endRate	){
		System.out.println("start of control countbook calculate+"+tech_long+"<>"+tech_option+"<>>"+startRate+"<<>>"+endRate);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("tech_long", tech_long);
		map.put("tech_option", tech_option);
		map.put("startRate", startRate);
		map.put("endRate", endRate);
		int calculate=countBookImpl.calculateData(map);
		System.out.println("control countbook calculate+"+calculate);
		return calculate;
		
	}
	@RequestMapping("deleteCountBook")
	public int deleteCountBook(int countId){
		System.out.println("control of countBook method of delete+"+countId);
		countBookImpl.deleteByPrimaryKey(countId);
		return countId;
	}
}
