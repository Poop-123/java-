package com.easyJava.query;

import java.util.Date;
import com.easyJava.enums.DateTimePatternEnum;
import com.easyJava.utils.DateUtils;

/**
  * @Description:员工表
  * @Author:刘耿豪
  * @Date:2025/03/14
  */
public class EmpQuery{
	/**
	 * ID
	 */
	 private Integer id;

	/**
	 * 用户名
	 */
	 private String username;

	 private String usernameFuzzy;

	/**
	 * 密码
	 */
	 private String password;

	 private String passwordFuzzy;

	/**
	 * 姓名
	 */
	 private String name;

	 private String nameFuzzy;

	/**
	 * 性别, 说明: 1 男, 2 女
	 */
	 private Integer gender;

	/**
	 * 图像
	 */
	 private String image;

	 private String imageFuzzy;

	/**
	 * 职位, 说明: 1 班主任,2 讲师, 3 学工主管, 4 教研主管, 5 咨询师
	 */
	 private Integer job;

	/**
	 * 入职时间
	 */
	 private Date entrydate;

	 private String entrydateStart;

	 private String entrydateEnd;

	/**
	 * 部门ID
	 */
	 private Integer deptId;

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
	public void setUsernameFuzzy(String usernameFuzzy){
		this.usernameFuzzy=usernameFuzzy;
	}
	public String getUsernameFuzzy(){
		return this.usernameFuzzy;
	}
	public void setPasswordFuzzy(String passwordFuzzy){
		this.passwordFuzzy=passwordFuzzy;
	}
	public String getPasswordFuzzy(){
		return this.passwordFuzzy;
	}
	public void setNameFuzzy(String nameFuzzy){
		this.nameFuzzy=nameFuzzy;
	}
	public String getNameFuzzy(){
		return this.nameFuzzy;
	}
	public void setImageFuzzy(String imageFuzzy){
		this.imageFuzzy=imageFuzzy;
	}
	public String getImageFuzzy(){
		return this.imageFuzzy;
	}

}