/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.prho.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectTask;

/**
 * 项目任务DAO接口
 * @author zhl
 * @version 2017-05-12
 */
@MyBatisDao
public interface PrhoProjectTaskDao extends CrudDao<PrhoProjectTask> {
	public List<PrhoProjectTask> findPageBy(PrhoProjectTask prhoProjectTask);
	public void updateProjectProgress(PrhoProjectTask prhoProjectTask);
	public List<PrhoProjectTask> findProjectTaskList(PrhoProjectTask prhoProjectTask);
}