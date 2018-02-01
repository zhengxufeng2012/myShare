package com.javen.mapping;

import java.util.HashMap;
import java.util.List;

import com.javen.model.sharesBook;

public interface sharesBookMapper {
	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table shares_book
	 *
	 * @mbggenerated Wed Dec 20 08:14:39 CST 2017
	 */
	int insert(sharesBook record);
	
	
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shares_book
     *
     * @mbggenerated Wed Dec 20 08:14:39 CST 2017
     */
    int deleteByPrimaryKey(Integer sharesId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shares_book
     *
     * @mbggenerated Wed Dec 20 08:14:39 CST 2017
     */
    sharesBook selectByPrimaryKey(Integer sharesId);
    /**
     * find the count of shares
     */
    int countShares();
    
    /**
     * this method was find all of shareBook
     */
    List<sharesBook> selectShares(HashMap<String,Object> map);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shares_book
     *
     * @mbggenerated Wed Dec 20 08:14:39 CST 2017
     */
    int updateByPrimaryKey(sharesBook record);
}