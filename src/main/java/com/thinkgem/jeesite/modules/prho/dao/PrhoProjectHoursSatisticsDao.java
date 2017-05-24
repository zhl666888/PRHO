/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.prho.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectHoursStatics;

/**
 * 统计DAO接口
 * @author ldx
 * @version 2017-05-17
 */
@MyBatisDao
public interface PrhoProjectHoursSatisticsDao extends CrudDao<PrhoProjectHoursStatics> {
	
	/**
	 * 项目工时统计查询
	 * @param prhoProjectHoursStatics
	 * @return
	 */
	public List<PrhoProjectHoursStatics> findPageBy(PrhoProjectHoursStatics prhoProjectHoursStatics);
	
    public List<PrhoProjectHoursStatics> findHoursSatisticsList(PrhoProjectHoursStatics prhoProjectHoursStatics);
}