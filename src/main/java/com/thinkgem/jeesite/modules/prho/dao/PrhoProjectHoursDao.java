/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.prho.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectHours;

/**
 * 项目工时DAO接口
 * @author zhl
 * @version 2017-05-18
 */
@MyBatisDao
public interface PrhoProjectHoursDao extends CrudDao<PrhoProjectHours> {
	
}