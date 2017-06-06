/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.prho.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectDevelopSpeed;
import com.thinkgem.jeesite.modules.prho.utils.DateUtils;
import com.thinkgem.jeesite.modules.prho.dao.PrhoProjectDevelopSpeedDao;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;

/**
 * 项目开发速度分析Service
 * @author zhl
 * @version 2017-06-01
 */
@Service
@Transactional(readOnly = true)
public class PrhoProjectDevelopSpeedService extends CrudService<PrhoProjectDevelopSpeedDao, PrhoProjectDevelopSpeed> {
	@Autowired
	private UserDao userDao;
	@Autowired
	private PrhoProjectDevelopSpeedDao dao;
	@Autowired
	private PrhoProjectTaskService prhoProjectTaskService;
	
	public PrhoProjectDevelopSpeed get(String id) {
		return super.get(id);
	}
	
	public List<PrhoProjectDevelopSpeed> findList(PrhoProjectDevelopSpeed prhoProjectDevelopSpeed) {
		return super.findList(prhoProjectDevelopSpeed);
	}
	
	public Page<PrhoProjectDevelopSpeed> findPage(Page<PrhoProjectDevelopSpeed> page, PrhoProjectDevelopSpeed prhoProjectDevelopSpeed) {
		return super.findPage(page, prhoProjectDevelopSpeed);
	}
	
	@Transactional(readOnly = false)
	public void save(PrhoProjectDevelopSpeed prhoProjectDevelopSpeed) {
		super.save(prhoProjectDevelopSpeed);
	}
	
	@Transactional(readOnly = false)
	public void delete(PrhoProjectDevelopSpeed prhoProjectDevelopSpeed) {
		super.delete(prhoProjectDevelopSpeed);
	}
	
	@Transactional(readOnly = false)
	public Page<PrhoProjectDevelopSpeed> findPageBy(Page<PrhoProjectDevelopSpeed> page,PrhoProjectDevelopSpeed prhoProjectDevelopSpeed){
		prhoProjectDevelopSpeed.setPage(page);
		List<PrhoProjectDevelopSpeed> list = dao.findPageBy(prhoProjectDevelopSpeed);
		String monthstarttime="";
		String monthendtime="";
		String monthfw="";
		if(prhoProjectDevelopSpeed.getRadioval().equals("month")){
			if(prhoProjectDevelopSpeed.getMonthstarttime()!=null){
				monthstarttime=DateUtils.formatDate(prhoProjectDevelopSpeed.getMonthstarttime(),"yyyy-MM");
			}
			if(prhoProjectDevelopSpeed.getMonthendtime()!=null){
				monthendtime=DateUtils.formatDate(prhoProjectDevelopSpeed.getMonthendtime(),"yyyy-MM");
			}
			monthfw=monthstarttime+"-"+monthendtime;
		}
		String weekstarttime="";
		String weekendtime="";
		String weekfw="";
		if(prhoProjectDevelopSpeed.getRadioval().equals("week")){
			if(prhoProjectDevelopSpeed.getWeekstarttime()!=null){
				weekstarttime=DateUtils.getWeekOfYearStr(prhoProjectDevelopSpeed.getWeekstarttime());
			}
			if(prhoProjectDevelopSpeed.getWeekendtime()!=null){
				weekendtime=DateUtils.getWeekOfYearStr(prhoProjectDevelopSpeed.getWeekendtime());
			}
			weekfw=weekstarttime+"周-"+weekendtime+"周";
		}
		for(PrhoProjectDevelopSpeed ppds: list){
			ppds.setRadioval(prhoProjectDevelopSpeed.getRadioval());
			ppds.setMonthfw(monthfw);
			ppds.setWeekfw(weekfw);
		}
		page.setList(list);
		return page;
		
	}
	
}