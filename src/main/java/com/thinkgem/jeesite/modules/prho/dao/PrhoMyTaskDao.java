/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.prho.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.prho.entity.PrhoMyTask;

/**
 * 我的任务DAO接口
 * @author zhl
 * @version 2017-05-17
 */
@MyBatisDao
public interface PrhoMyTaskDao extends CrudDao<PrhoMyTask> {
	public List<PrhoMyTask> findPageBy(PrhoMyTask prhoMyTask);
}