/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.prho.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectDailyStatics;

/**
 * 统计DAO接口
 * @author ldx
 * @version 2017-05-17
 */
@MyBatisDao
public interface PrhoProjectDailySatisticsDao extends CrudDao<PrhoProjectDailyStatics> {
	
	public List<PrhoProjectDailyStatics> findPageBy(PrhoProjectDailyStatics prhoProjectDailyStatics);
	
    public List<PrhoProjectDailyStatics> findHoursSatisticsList(PrhoProjectDailyStatics prhoProjectDailyStatics);
}