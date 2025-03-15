package com.easyJava.query;

import java.util.Date;
import com.easyJava.enums.DateTimePatternEnum;
import com.easyJava.utils.DateUtils;

/**
  * @Description:部门表
  * @Author:刘耿豪
  * @Date:2025/03/14
  */
public class DeptQuery{
	/**
	 * 主键ID
	 */
	 private Integer id;

	/**
	 * 部门名称
	 */
	 private String name;

	 private String nameFuzzy;

	/**
	 * 创建时间
	 */
	 private Date createTime;

	 private String createTimeStart;

	 private String createTimeEnd;

	/**
	 * 修改时间
	 */
	 private Date updateTime;

	 private String updateTimeStart;

	 private String updateTimeEnd;

	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return this.id;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return this.name;
	}
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}
	public Date getUpdateTime(){
		return this.updateTime;
	}
	public void setNameFuzzy(String nameFuzzy){
		this.nameFuzzy=nameFuzzy;
	}
	public String getNameFuzzy(){
		return this.nameFuzzy;
	}

}