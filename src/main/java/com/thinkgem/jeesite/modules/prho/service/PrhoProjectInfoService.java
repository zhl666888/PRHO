/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.prho.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectInfo;
import com.thinkgem.jeesite.modules.prho.dao.PrhoProjectInfoDao;

/**
 * 项目信息Service
 * @author zhl
 * @version 2017-05-12
 */
@Service
@Transactional(readOnly = true)
public class PrhoProjectInfoService extends CrudService<PrhoProjectInfoDao, PrhoProjectInfo> {

	public PrhoProjectInfo get(String id) {
		return super.get(id);
	}
	
	public List<PrhoProjectInfo> findList(PrhoProjectInfo prhoProjectInfo) {
		return super.findList(prhoProjectInfo);
	}
	
	public Page<PrhoProjectInfo> findPage(Page<PrhoProjectInfo> page, PrhoProjectInfo prhoProjectInfo) {
		return super.findPage(page, prhoProjectInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(PrhoProjectInfo prhoProjectInfo) {
		super.save(prhoProjectInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(PrhoProjectInfo prhoProjectInfo) {
		super.delete(prhoProjectInfo);
	}
	
}