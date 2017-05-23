/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.prho.entity;


import java.util.Date;

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
	private Double realhours;	// 投入工时（小时）
	private String taskName;// 任务名称
	private String jobType; //工作类型
	private Date taskStartTime;		// 任务开始时间
	private Date taskEndTime;		// 任务结束时间
	
	//工时类型 holidayOvertime节假日加班 
	//workingDayOvertime 工作日加班 workingDay工作日
	private String workHoursType; 
	
	
	//扩展字段 
	private String projectname;		// 项目名称
	
	private Double workOverTime;	// 工作日加班（小时）
	private Double vacationOverTime;	// 节假日加班（小时）
	private Double actualWorkDay;	// 实际工作日（天）
	private Double workLoad;	// 实际工作量（天）
	
	
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
	@ExcelField(title="投入工时(小时)", align=2, sort=40)
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
	@ExcelField(title="工作日加班(小时)", align=2, sort=40)
	public Double getWorkOverTime() {
		return workOverTime;
	}

	public void setWorkOverTime(Double workOverTime) {
		this.workOverTime = workOverTime;
	}
	@ExcelField(title="节假日加班(小时)", align=2, sort=40)
	public Double getVacationOverTime() {
		return vacationOverTime;
	}

	public void setVacationOverTime(Double vacationOverTime) {
		this.vacationOverTime = vacationOverTime;
	}
	
	@ExcelField(title="实际工作日(天)", align=2, sort=40)

	public Double getActualWorkDay() {
		return actualWorkDay;
	}

	public void setActualWorkDay(Double actualWorkDay) {
		this.actualWorkDay = actualWorkDay;
	}
	@ExcelField(title="实际工作量(天)", align=2, sort=40)
	public Double getWorkLoad() {
		return workLoad;
	}

	public void setWorkLoad(Double workLoad) {
		this.workLoad = workLoad;
	}

	public Date getTaskStartTime() {
		return taskStartTime;
	}

	public void setTaskStartTime(Date taskStartTime) {
		this.taskStartTime = taskStartTime;
	}

	public Date getTaskEndTime() {
		return taskEndTime;
	}

	public void setTaskEndTime(Date taskEndTime) {
		this.taskEndTime = taskEndTime;
	}

	public String getWorkHoursType() {
		return workHoursType;
	}

	public void setWorkHoursType(String workHoursType) {
		this.workHoursType = workHoursType;
	}

	
	
	
	
	
}