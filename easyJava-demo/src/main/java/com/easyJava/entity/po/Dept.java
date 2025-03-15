package com.easyJava.entity.po;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.easyJava.enums.DateTimePatternEnum;
import com.easyJava.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
  * @Description:部门表
  * @Author:刘耿豪
  * @Date:2025/03/15
  */
public class Dept implements Serializable{
	/**
	 * 主键ID
	 */
	 private Integer id;

	/**
	 * 部门名称
	 */
	 private String name;

	/**
	 * 创建时间
	 */
	 @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	 @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	 @JsonIgnore
	 private Date createTime;

	/**
	 * 修改时间
	 */
	 @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	 @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	 @JsonIgnore
	 private Date updateTime;

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
	@Override
	public String toString(){
		return "主键ID:"+(id==null?"空":id)+","+"部门名称:"+(name==null?"空":name)+","+"创建时间:"+(createTime==null?"空":DateUtils.format(createTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+","+"修改时间:"+(updateTime==null?"空":DateUtils.format(updateTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
	}
}