/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.prho.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.prho.entity.PrhoTestLog;
import com.thinkgem.jeesite.modules.prho.dao.PrhoTestLogDao;

/**
 * 测试logService
 * @author zhl
 * @version 2017-05-10
 */
@Service
@Transactional(readOnly = true)
public class PrhoTestLogService extends CrudService<PrhoTestLogDao, PrhoTestLog> {

	public PrhoTestLog get(String id) {
		return super.get(id);
	}
	
	public List<PrhoTestLog> findList(PrhoTestLog prhoTestLog) {
		return super.findList(prhoTestLog);
	}
	
	public Page<PrhoTestLog> findPage(Page<PrhoTestLog> page, PrhoTestLog prhoTestLog) {
		return super.findPage(page, prhoTestLog);
	}
	
	@Transactional(readOnly = false)
	public void save(PrhoTestLog prhoTestLog) {
		super.save(prhoTestLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(PrhoTestLog prhoTestLog) {
		super.delete(prhoTestLog);
	}
	
}