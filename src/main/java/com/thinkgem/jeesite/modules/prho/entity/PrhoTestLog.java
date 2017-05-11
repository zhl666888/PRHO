/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.prho.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 测试logEntity
 * @author zhl
 * @version 2017-05-10
 */
public class PrhoTestLog extends DataEntity<PrhoTestLog> {
	
	private static final long serialVersionUID = 1L;
	private String logcode;		// logcode
	private String experimentid;		// experimentid
	private String problemfeedback;		// problemfeedback
	private String problemstatus;		// problemstatus
	private String infourl;		// infourl
	private String liabler;		// liabler
	private String updaterBy;		// updater_by
	
	public PrhoTestLog() {
		super();
	}

	public PrhoTestLog(String id){
		super(id);
	}

	@Length(min=0, max=50, message="logcode长度必须介于 0 和 50 之间")
	public String getLogcode() {
		return logcode;
	}

	public void setLogcode(String logcode) {
		this.logcode = logcode;
	}
	
	@Length(min=0, max=64, message="experimentid长度必须介于 0 和 64 之间")
	public String getExperimentid() {
		return experimentid;
	}

	public void setExperimentid(String experimentid) {
		this.experimentid = experimentid;
	}
	
	@Length(min=0, max=1000, message="problemfeedback长度必须介于 0 和 1000 之间")
	public String getProblemfeedback() {
		return problemfeedback;
	}

	public void setProblemfeedback(String problemfeedback) {
		this.problemfeedback = problemfeedback;
	}
	
	@Length(min=0, max=50, message="problemstatus长度必须介于 0 和 50 之间")
	public String getProblemstatus() {
		return problemstatus;
	}

	public void setProblemstatus(String problemstatus) {
		this.problemstatus = problemstatus;
	}
	
	@Length(min=0, max=300, message="infourl长度必须介于 0 和 300 之间")
	public String getInfourl() {
		return infourl;
	}

	public void setInfourl(String infourl) {
		this.infourl = infourl;
	}
	
	@Length(min=0, max=64, message="liabler长度必须介于 0 和 64 之间")
	public String getLiabler() {
		return liabler;
	}

	public void setLiabler(String liabler) {
		this.liabler = liabler;
	}
	
	@Length(min=0, max=50, message="updater_by长度必须介于 0 和 50 之间")
	public String getUpdaterBy() {
		return updaterBy;
	}

	public void setUpdaterBy(String updaterBy) {
		this.updaterBy = updaterBy;
	}
	
}