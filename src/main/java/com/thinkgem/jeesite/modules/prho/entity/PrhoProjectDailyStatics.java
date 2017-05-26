/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.prho.entity;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.common.supcan.annotation.treelist.cols.SupCol;

/**
 * 工时表Entity
 * @author ldx
 * @version 2017-05-17
 */
public class PrhoProjectDailyStatics extends DataEntity<PrhoProjectDailyStatics> {
	
	private static final long serialVersionUID = 1L;
	private String projectid;		// 项目id
	private String taskid;		// 任务id
	private String staff;		// 人员名称
	private Double realhours;	// 投入工时（小时）
	private String taskName;// 任务名称
	private String jobType; //工作类型
	private Date taskStartTime;		// 任务开始时间
	private Date taskEndTime;		// 任务结束时间
	private String workDesc; //工作描述
	
	
	//工时类型 holidayOvertime节假日加班 
	//workingDayOvertime 工作日加班 workingDay工作日
	private String workHoursType; 
	
	
	//扩展字段 
	private String projectname;		// 项目名称
	private String userid;		

	private Double workOverTime;	// 工作日加班（小时）
	private Double vacationOverTime;	// 节假日加班（小时）
	private Double actualWorkDay;	// 实际工作日（天）
	private Double workLoad;	// 实际工作量（天）
	private String weekName;		// 星期几
	private Double overTime;	// 加班小时数
	private String ifOverTime;		// 是否加班
	private Date starttime; //查询的任务开始时间
	private Date endtime; //查询的任务结束时间
	private String name;		// 姓名
	private Date workTime;		// 工作日期
	
	@SupCol(isUnique="true", isHide="true")
	@ExcelField(title="ID", type=1, align=2, sort=1)
	public String getId() {
		return id;
	}
	@ExcelField(title="姓名", align=2, sort=40)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ExcelField(title="日期",type=0,align=1, sort=110)
	public Date getWorkTime() {
		return workTime;
	}
	public void setWorkTime(Date workTime) {
		this.workTime = workTime;
	}
    
	@ExcelField(title="星期", align=2, sort=40)
	public String getWeekName() {
		return weekName;
	}

	public void setWeekName(String weekName) {
		this.weekName = weekName;
	}
	
	@ExcelField(title="系统名称", align=2, sort=40)
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	
	@ExcelField(title="工作描述", align=2, sort=900)
	public String getWorkDesc() {
		return workDesc;
	}

	public void setWorkDesc(String workDesc) {
		this.workDesc = workDesc;
	}
	@ExcelField(title="实际工作量(小时)", align=2, sort=40)
	public Double getWorkLoad() {
		return workLoad;
	}

	public void setWorkLoad(Double workLoad) {
		this.workLoad = workLoad;
	}
	
	@ExcelField(title="加班小时数", align=2, sort=40)
	public Double getOverTime() {
		return overTime;
	}

	public void setOverTime(Double overTime) {
		this.overTime = overTime;
	}
	@ExcelField(title="是否加班", align=2, sort=40)
	public String getIfOverTime() {
		return ifOverTime;
	}

	public void setIfOverTime(String ifOverTime) {
		this.ifOverTime = ifOverTime;
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
	public String getStaff() {
		return staff;
	}
	public void setStaff(String staff) {
		this.staff = staff;
	}
	
	//@ExcelField(title="投入工时(小时)", align=2, sort=40)
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
	//@ExcelField(title="工作日加班(小时)", align=2, sort=40)
	public Double getWorkOverTime() {
		return workOverTime;
	}

	public void setWorkOverTime(Double workOverTime) {
		this.workOverTime = workOverTime;
	}
	//@ExcelField(title="节假日加班(小时)", align=2, sort=40)
	public Double getVacationOverTime() {
		return vacationOverTime;
	}

	public void setVacationOverTime(Double vacationOverTime) {
		this.vacationOverTime = vacationOverTime;
	}
	
	//@ExcelField(title="实际工作日(天)", align=2, sort=40)

	public Double getActualWorkDay() {
		return actualWorkDay;
	}

	public void setActualWorkDay(Double actualWorkDay) {
		this.actualWorkDay = actualWorkDay;
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
	

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
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