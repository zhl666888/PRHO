/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.prho.entity;


import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.common.supcan.annotation.treelist.cols.SupCol;

/**
 * 工时表Entity
 * @author ldx
 * @version 2017-05-17
 */
public class PrhoProjectHoursStatics extends DataEntity<PrhoProjectHoursStatics> {
	
	private static final long serialVersionUID = 1L;
	private String projectid;		// 项目id
	private String taskid;		// 任务id
	private String staff;		// 人员名称
	private Double realhours;	// 投入工时
	private String taskName;// 任务名称
	private String jobType; //工作类型
	
	//扩展字段 
	private String projectname;		// 项目名称
	
	
	@SupCol(isUnique="true", isHide="true")
	@ExcelField(title="ID", type=1, align=2, sort=1)
	public String getId() {
		return id;
	}
	
	public String getProjectid() {
		return projectid;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	@ExcelField(title="人员名称", align=2, sort=40)
	public String getStaff() {
		return staff;
	}
	public void setStaff(String staff) {
		this.staff = staff;
	}
	@ExcelField(title="项目名称", align=2, sort=40)
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	@ExcelField(title="投入工时", align=2, sort=40)
	public Double getRealhours() {
		return realhours;
	}
	public void setRealhours(Double realhours) {
		this.realhours = realhours;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
}