/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.prho.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 项目信息Entity
 * @author zhl
 * @version 2017-05-12
 */
public class PrhoProjectInfo extends DataEntity<PrhoProjectInfo> {
	
	private static final long serialVersionUID = 1L;
	private String projectcode;		// 项目编号
	private User user;		// 用户id
	private String projectname;		// 项目名称
	private Date projectbegintime;		// 项目启动日期
	private Date starttime;    // 查询条件的开始时间
	private Date projectplanendtime;		// 预计结束日期
	private Date endtime;    //查询条件的结束时间
	private Date projectendtime;		// 实际结束日期
	private String projectstatus;		// 项目状态 未开始1、进行中2、已结束3
	private String userId; //项目负责人
	private String userName;//显示的项目负责人
	private String estimatehours;  //预估工时 新增字段
	private String staffId; //项目所属人员id
	private List<User> userList = Lists.newArrayList();  //项目与人员关系列表
	
	public PrhoProjectInfo() {
		super();
	}

	public PrhoProjectInfo(String id){
		super(id);
	}

	@Length(min=0, max=64, message="项目编号长度必须介于 0 和 64 之间")
	public String getProjectcode() {
		return projectcode;
	}

	public void setProjectcode(String projectcode) {
		this.projectcode = projectcode;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=200, message="项目名称长度必须介于 0 和 200 之间")
	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getProjectbegintime() {
		return projectbegintime;
	}

	public void setProjectbegintime(Date projectbegintime) {
		this.projectbegintime = projectbegintime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getProjectplanendtime() {
		return projectplanendtime;
	}

	public void setProjectplanendtime(Date projectplanendtime) {
		this.projectplanendtime = projectplanendtime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getProjectendtime() {
		return projectendtime;
	}

	public void setProjectendtime(Date projectendtime) {
		this.projectendtime = projectendtime;
	}
	
	@Length(min=0, max=64, message="项目状态 未开始1、进行中2、已结束3长度必须介于 0 和 64 之间")
	public String getProjectstatus() {
		return projectstatus;
	}

	public void setProjectstatus(String projectstatus) {
		this.projectstatus = projectstatus;
	}
	
	@Length(min=0, max=64, message="项目负责人，用的是用户ID")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEstimatehours() {
		return estimatehours;
	}

	public void setEstimatehours(String estimatehours) {
		this.estimatehours = estimatehours;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

}