/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.prho.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 个人开发速度分析Entity
 * @author zhl
 * @version 2017-06-01
 */
public class PrhoPersonalDevelopSpeed extends DataEntity<PrhoPersonalDevelopSpeed> {
	
	private static final long serialVersionUID = 1L;
	private String projectId;		// 项目id
	private String taskmanager;		// 任务负责人
	private String taskname;		// 任务名称
	private Date tastplanbegintime;		// 计划开始时间
	private Date taskplanendtime;		// 计划结束时间
	private String expectedhour;		// 预计用时 decimal
	private String taskcompleteschedule;		// 任务完成进度
	private String taskstatus;		// 任务状态 
	private PrhoProjectInfo prhoProjectInfo;  //项目信息对象
	private String userName;  //显示的项目负责人
	private String projectName;//显示的 项目名称
	private Date taskcompletetime; //任务完成时间，(依据工时填报的私有任务结束时间)
	private String tasktype;  //任务类型
	private String worktype;  //工作类型
	private Date monthstarttime; //起始时间 月
	private Date monthendtime; //结束时间  月
	private String staffid;  //人员id
	private Date daytime; //查询条件天
	private Date weekstarttime; //起始时间	周
	private Date weekendtime; //结束时间	周
	private String radioval;//单选按钮 选择的值
	public PrhoPersonalDevelopSpeed() {
		super();
	}

	public PrhoPersonalDevelopSpeed(String id){
		super(id);
	}

	@Length(min=0, max=64, message="项目id长度必须介于 0 和 64 之间")
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	@Length(min=0, max=200, message="任务负责人长度必须介于 0 和 200 之间")
	public String getTaskmanager() {
		return taskmanager;
	}

	public void setTaskmanager(String taskmanager) {
		this.taskmanager = taskmanager;
	}
	
	@Length(min=0, max=200, message="任务名称长度必须介于 0 和 200 之间")
	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTastplanbegintime() {
		return tastplanbegintime;
	}

	public void setTastplanbegintime(Date tastplanbegintime) {
		this.tastplanbegintime = tastplanbegintime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTaskplanendtime() {
		return taskplanendtime;
	}

	public void setTaskplanendtime(Date taskplanendtime) {
		this.taskplanendtime = taskplanendtime;
	}
	@ExcelField(title="预估工时", align=2, sort=15)
	public String getExpectedhour() {
		return expectedhour;
	}

	public void setExpectedhour(String expectedhour) {
		this.expectedhour = expectedhour;
	}
	
	@Length(min=0, max=50, message="任务完成进度长度必须介于 0 和 50 之间")
	public String getTaskcompleteschedule() {
		return taskcompleteschedule;
	}

	public void setTaskcompleteschedule(String taskcompleteschedule) {
		this.taskcompleteschedule = taskcompleteschedule;
	}
	@Length(min=0, max=64, message="任务状态长度必须介于 0 和 64之间")
	public String getTaskstatus() {
		return taskstatus;
	}

	public void setTaskstatus(String taskstatus) {
		this.taskstatus = taskstatus;
	}

	public PrhoProjectInfo getPrhoProjectInfo() {
		return prhoProjectInfo;
	}

	public void setPrhoProjectInfo(PrhoProjectInfo prhoProjectInfo) {
		this.prhoProjectInfo = prhoProjectInfo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@ExcelField(title="项目名称", align=2, sort=5)
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="项目完成时间", align=2, sort=20)
	public Date getTaskcompletetime() {
		return taskcompletetime;
	}

	public void setTaskcompletetime(Date taskcompletetime) {
		this.taskcompletetime = taskcompletetime;
	}
	@Length(min=0, max=64, message="任务类型必须介于 0 和 64之间")
	public String getTasktype() {
		return tasktype;
	}

	public void setTasktype(String tasktype) {
		this.tasktype = tasktype;
	}
	
	@Length(min=0, max=64, message="工作类型必须介于 0 和 64之间")
	public String getWorktype() {
		return worktype;
	}

	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}

	public Date getMonthstarttime() {
		return monthstarttime;
	}

	public void setMonthstarttime(Date monthstarttime) {
		this.monthstarttime = monthstarttime;
	}

	public Date getMonthendtime() {
		return monthendtime;
	}

	public void setMonthendtime(Date monthendtime) {
		this.monthendtime = monthendtime;
	}
	@ExcelField(title="人员", align=2, sort=10)
	public String getStaffid() {
		return staffid;
	}

	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}

	public Date getDaytime() {
		return daytime;
	}

	public void setDaytime(Date daytime) {
		this.daytime = daytime;
	}

	public Date getWeekstarttime() {
		return weekstarttime;
	}

	public void setWeekstarttime(Date weekstarttime) {
		this.weekstarttime = weekstarttime;
	}

	public Date getWeekendtime() {
		return weekendtime;
	}

	public void setWeekendtime(Date weekendtime) {
		this.weekendtime = weekendtime;
	}

	public String getRadioval() {
		return radioval;
	}

	public void setRadioval(String radioval) {
		this.radioval = radioval;
	}

}