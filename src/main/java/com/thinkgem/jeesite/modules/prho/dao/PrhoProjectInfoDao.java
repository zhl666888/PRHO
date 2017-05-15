/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.prho.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectInfo;

/**
 * 项目信息DAO接口
 * @author zhl
 * @version 2017-05-12
 */
@MyBatisDao
public interface PrhoProjectInfoDao extends CrudDao<PrhoProjectInfo> {
	public List<PrhoProjectInfo> findPageBy(PrhoProjectInfo prhoProjectInfo);
    public List<PrhoProjectInfo> findProjectNameList(PrhoProjectInfo prhoProjectInfo);
}