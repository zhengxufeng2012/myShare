package com.javen.mapping;

import java.util.HashMap;
import java.util.List;

import com.javen.model.countBook;
import com.javen.model.techBook;

public interface countBookMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table count_book
     *
     * @mbggenerated Mon Jan 15 17:01:54 CST 2018
     */
    int deleteByPrimaryKey(Integer countId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table count_book
     *
     * @mbggenerated Mon Jan 15 17:01:54 CST 2018
     */
    int insert(countBook record);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table count_book
     *
     * @mbggenerated Mon Jan 15 17:01:54 CST 2018
     */
    countBook selectByPrimaryKey(Integer countId);

   

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table count_book
     *
     * @mbggenerated Mon Jan 15 17:01:54 CST 2018
     */
    int updateByPrimaryKey(countBook record);
    
    /**
     * this methos was select the data of count_book
     * we need select all data?
     */
    List<countBook> selectCountBookByPage(HashMap<String,Object> map);
    
    /**
     * this method was count the data of count_book
     */
    public int countCountBook();
    
    /**
     * this method was find the data by like
     * 
     */
    public List<countBook> selectall(String all);
    
    /**
     * this method is calculate the data where the stand_increate_rate bigger a data small a data
     * this method also need know the tech_long and the tech_option so we must need a map 
     */
    public int calculateData(HashMap<String,Object> map);
}