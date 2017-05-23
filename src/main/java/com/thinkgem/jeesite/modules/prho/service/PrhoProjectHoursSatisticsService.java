/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.prho.service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectHoursStatics;
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectInfo;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.prho.dao.PrhoProjectHoursSatisticsDao;
import com.thinkgem.jeesite.common.service.BaseService;
import org.springframework.beans.factory.InitializingBean;
import com.thinkgem.jeesite.modules.prho.utils.DateUtils;
import com.thinkgem.jeesite.modules.prho.utils.StringUtils;


/**
 * Service 统计
 * @author ldx
 * @version 2017-05-17
 */
@Service
@Transactional(readOnly = true)
public class PrhoProjectHoursSatisticsService extends CrudService<PrhoProjectHoursSatisticsDao, PrhoProjectHoursStatics>{
	@Autowired
	private PrhoProjectHoursSatisticsDao dao;
	
	public PrhoProjectHoursStatics get(String id) {
		return super.get(id);
	}
	
	
	@Transactional(readOnly = false)
	public Page<PrhoProjectHoursStatics> findPageBy(Page<PrhoProjectHoursStatics> page,PrhoProjectHoursStatics prhoProjectHoursStatics){
		prhoProjectHoursStatics.setPage(page);
		List<PrhoProjectHoursStatics> list = dao.findPageBy(prhoProjectHoursStatics);
		page.setList(list);
		return page;
		
	}
	
	@Transactional(readOnly = false)
	public  List<PrhoProjectHoursStatics> findHoursSatisticsList(PrhoProjectHoursStatics prhoProjectHoursStatics){
		List<PrhoProjectHoursStatics> list=dao.findHoursSatisticsList(prhoProjectHoursStatics);
		return list;
		
	}
	
	
	public Page<PrhoProjectHoursStatics> findHoursSatisticsListForExport(Page<PrhoProjectHoursStatics> page, PrhoProjectHoursStatics prhoProjectHoursStatics) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		//prhoProjectHoursStatics.getSqlMap().put("dsf", dataScopeFilter("", "o", "a"));
		// 设置分页参数
		prhoProjectHoursStatics.setPage(page);
		// 执行分页查询
		page.setList(dao.findHoursSatisticsList(prhoProjectHoursStatics));
		return page;
	}
	
	
	
	
	
	
}