package com.easyJava.entity.po;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.easyJava.enums.DateTimePatternEnum;
import com.easyJava.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
  * @Description:员工表
  * @Author:刘耿豪
  * @Date:2025/03/15
  */
public class Emp implements Serializable{
	/**
	 * ID
	 */
	 private Integer id;

	/**
	 * 用户名
	 */
	 private String username;

	/**
	 * 密码
	 */
	 private String password;

	/**
	 * 姓名
	 */
	 private String name;

	/**
	 * 性别, 说明: 1 男, 2 女
	 */
	 private Integer gender;

	/**
	 * 图像
	 */
	 private String image;

	/**
	 * 职位, 说明: 1 班主任,2 讲师, 3 学工主管, 4 教研主管, 5 咨询师
	 */
	 private Integer job;

	/**
	 * 入职时间
	 */
	 @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	 @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	 private Date entrydate;

	/**
	 * 部门ID
	 */
	 private Integer deptId;

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
	public void setUsername(String username){
		this.username=username;
	}
	public String getUsername(){
		return this.username;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public String getPassword(){
		return this.password;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return this.name;
	}
	public void setGender(Integer gender){
		this.gender=gender;
	}
	public Integer getGender(){
		return this.gender;
	}
	public void setImage(String image){
		this.image=image;
	}
	public String getImage(){
		return this.image;
	}
	public void setJob(Integer job){
		this.job=job;
	}
	public Integer getJob(){
		return this.job;
	}
	public void setEntrydate(Date entrydate){
		this.entrydate=entrydate;
	}
	public Date getEntrydate(){
		return this.entrydate;
	}
	public void setDeptId(Integer deptId){
		this.deptId=deptId;
	}
	public Integer getDeptId(){
		return this.deptId;
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
		return "ID:"+(id==null?"空":id)+","+"用户名:"+(username==null?"空":username)+","+"密码:"+(password==null?"空":password)+","+"姓名:"+(name==null?"空":name)+","+"性别, 说明: 1 男, 2 女:"+(gender==null?"空":gender)+","+"图像:"+(image==null?"空":image)+","+"职位, 说明: 1 班主任,2 讲师, 3 学工主管, 4 教研主管, 5 咨询师:"+(job==null?"空":job)+","+"入职时间:"+(entrydate==null?"空":DateUtils.format(entrydate, DateTimePatternEnum.YYYY_MM_DD.getPattern()))+","+"部门ID:"+(deptId==null?"空":deptId)+","+"创建时间:"+(createTime==null?"空":DateUtils.format(createTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+","+"修改时间:"+(updateTime==null?"空":DateUtils.format(updateTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
	}
}