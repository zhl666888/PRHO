/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.prho.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.modules.sys.entity.User;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 项目工时Entity
 * @author zhl
 * @version 2017-05-18
 */
public class PrhoProjectHours extends DataEntity<PrhoProjectHours> {
	
	private static final long serialVersionUID = 1L;
	private String projectId;		// 项目id
	private String taskId;		// 任务id
	private String staff;		// 人员 默认当前用户，不可修改
	private String realhours;		// 实际用时 decimal 工时
	private String taskname;		// 任务名称
	private String jobtype;		// 工作类型
	private String workhourstype;		// 工时类型
	private String workdesc;		// 工作描述
	private Date taskstarttime;		// 任务开始时间
	private Date taskendtime;		// 任务结束时间
	private String approvalstatus;		// 审核状态 待审核、通过、未通过
	private String approvalopinion;		// 审核意见
	private User user;		// 审核人员id
	private Date approvaltime;		// 审核时间
	private Date filltime;		// 填报时间
	private Date worktime;		// 工作日期
	private PrhoProjectInfo prhoProjectInfo;
	private String projectmanagerId;//项目负责人id
	private String taskcompleteschedule;//任务完成进度
	private String projectName; //显示的项目名称
	private String projectmanager; //显示的项目负责人
	private Date starttime; //查询的任务开始时间
	private Date endtime; //查询的任务结束时间
	public PrhoProjectHours() {
		super();
	}

	public PrhoProjectHours(String id){
		super(id);
	}

	@Length(min=0, max=64, message="项目id长度必须介于 0 和 64 之间")
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	@Length(min=0, max=64, message="任务id长度必须介于 0 和 64 之间")
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	@Length(min=0, max=64, message="人员 默认当前用户，不可修改长度必须介于 0 和 64 之间")
	public String getStaff() {
		return staff;
	}

	public void setStaff(String staff) {
		this.staff = staff;
	}
	
	public String getRealhours() {
		return realhours;
	}

	public void setRealhours(String realhours) {
		this.realhours = realhours;
	}
	
	@Length(min=0, max=255, message="任务名称长度必须介于 0 和 255 之间")
	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	
	@Length(min=0, max=64, message="工作类型长度必须介于 0 和 64 之间")
	public String getJobtype() {
		return jobtype;
	}

	public void setJobtype(String jobtype) {
		this.jobtype = jobtype;
	}
	
	@Length(min=0, max=64, message="工时类型长度必须介于 0 和 64 之间")
	public String getWorkhourstype() {
		return workhourstype;
	}

	public void setWorkhourstype(String workhourstype) {
		this.workhourstype = workhourstype;
	}
	
	@Length(min=0, max=255, message="工作描述长度必须介于 0 和 255 之间")
	public String getWorkdesc() {
		return workdesc;
	}

	public void setWorkdesc(String workdesc) {
		this.workdesc = workdesc;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTaskstarttime() {
		return taskstarttime;
	}

	public void setTaskstarttime(Date taskstarttime) {
		this.taskstarttime = taskstarttime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTaskendtime() {
		return taskendtime;
	}

	public void setTaskendtime(Date taskendtime) {
		this.taskendtime = taskendtime;
	}
	
	@Length(min=0, max=64, message="审核状态 待审核、通过、未通过长度必须介于 0 和 64 之间")
	public String getApprovalstatus() {
		return approvalstatus;
	}

	public void setApprovalstatus(String approvalstatus) {
		this.approvalstatus = approvalstatus;
	}
	
	@Length(min=0, max=255, message="审核意见长度必须介于 0 和 255 之间")
	public String getApprovalopinion() {
		return approvalopinion;
	}

	public void setApprovalopinion(String approvalopinion) {
		this.approvalopinion = approvalopinion;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getApprovaltime() {
		return approvaltime;
	}

	public void setApprovaltime(Date approvaltime) {
		this.approvaltime = approvaltime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getFilltime() {
		return filltime;
	}

	public void setFilltime(Date filltime) {
		this.filltime = filltime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getWorktime() {
		return worktime;
	}

	public void setWorktime(Date worktime) {
		this.worktime = worktime;
	}

	public PrhoProjectInfo getPrhoProjectInfo() {
		return prhoProjectInfo;
	}

	public void setPrhoProjectInfo(PrhoProjectInfo prhoProjectInfo) {
		this.prhoProjectInfo = prhoProjectInfo;
	}
	
	@Length(min=0, max=64, message="项目负责人id长度必须介于 0 和 64 之间")
	public String getProjectmanagerId() {
		return projectmanagerId;
	}

	public void setProjectmanagerId(String projectmanagerId) {
		this.projectmanagerId = projectmanagerId;
	}
	@Length(min=0, max=50, message="任务完成进度长度必须介于 0 和 50 之间")
	public String getTaskcompleteschedule() {
		return taskcompleteschedule;
	}

	public void setTaskcompleteschedule(String taskcompleteschedule) {
		this.taskcompleteschedule = taskcompleteschedule;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectmanager() {
		return projectmanager;
	}

	public void setProjectmanager(String projectmanager) {
		this.projectmanager = projectmanager;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	
}