/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.prho.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.prho.entity.PrhoPersonalDevelopSpeed;

/**
 * 个人开发速度分析DAO接口
 * @author zhl
 * @version 2017-06-01
 */
@MyBatisDao
public interface PrhoPersonalDevelopSpeedDao extends CrudDao<PrhoPersonalDevelopSpeed> {
	public List<PrhoPersonalDevelopSpeed> findPageBy(PrhoPersonalDevelopSpeed prhoPersonalDevelopSpeed);
}