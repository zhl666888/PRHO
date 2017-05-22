/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.prho.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectHours;
import com.thinkgem.jeesite.modules.prho.dao.PrhoProjectHoursDao;

/**
 * 项目工时Service
 * @author zhl
 * @version 2017-05-18
 */
@Service
@Transactional(readOnly = true)
public class PrhoProjectHoursService extends CrudService<PrhoProjectHoursDao, PrhoProjectHours> {

	public PrhoProjectHours get(String id) {
		return super.get(id);
	}
	
	public List<PrhoProjectHours> findList(PrhoProjectHours prhoProjectHours) {
		return super.findList(prhoProjectHours);
	}
	
	public Page<PrhoProjectHours> findPage(Page<PrhoProjectHours> page, PrhoProjectHours prhoProjectHours) {
		return super.findPage(page, prhoProjectHours);
	}
	
	@Transactional(readOnly = false)
	public void save(PrhoProjectHours prhoProjectHours) {
		super.save(prhoProjectHours);
	}
	
	@Transactional(readOnly = false)
	public void delete(PrhoProjectHours prhoProjectHours) {
		super.delete(prhoProjectHours);
	}
	
}