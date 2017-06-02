/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.prho.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.prho.entity.PrhoPersonalDevelopSpeed;
import com.thinkgem.jeesite.modules.prho.dao.PrhoPersonalDevelopSpeedDao;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;

/**
 * 个人开发速度分析Service
 * @author zhl
 * @version 2017-06-01
 */
@Service
@Transactional(readOnly = true)
public class PrhoPersonalDevelopSpeedService extends CrudService<PrhoPersonalDevelopSpeedDao, PrhoPersonalDevelopSpeed> {
	@Autowired
	private UserDao userDao;
	@Autowired
	private PrhoPersonalDevelopSpeedDao dao;
	@Autowired
	private PrhoProjectTaskService prhoProjectTaskService;
	
	public PrhoPersonalDevelopSpeed get(String id) {
		return super.get(id);
	}
	
	public List<PrhoPersonalDevelopSpeed> findList(PrhoPersonalDevelopSpeed prhoPersonalDevelopSpeed) {
		return super.findList(prhoPersonalDevelopSpeed);
	}
	
	public Page<PrhoPersonalDevelopSpeed> findPage(Page<PrhoPersonalDevelopSpeed> page, PrhoPersonalDevelopSpeed prhoPersonalDevelopSpeed) {
		return super.findPage(page, prhoPersonalDevelopSpeed);
	}
	
	@Transactional(readOnly = false)
	public void save(PrhoPersonalDevelopSpeed prhoPersonalDevelopSpeed) {
		super.save(prhoPersonalDevelopSpeed);
	}
	
	@Transactional(readOnly = false)
	public void delete(PrhoPersonalDevelopSpeed prhoPersonalDevelopSpeed) {
		super.delete(prhoPersonalDevelopSpeed);
	}
	
	@Transactional(readOnly = false)
	public Page<PrhoPersonalDevelopSpeed> findPageBy(Page<PrhoPersonalDevelopSpeed> page,PrhoPersonalDevelopSpeed prhoPersonalDevelopSpeed){
		prhoPersonalDevelopSpeed.setPage(page);
		List<PrhoPersonalDevelopSpeed> list = dao.findPageBy(prhoPersonalDevelopSpeed);
	/*	for(PrhoProjectInfo prinfo: list){
			User user=userDao.get(prinfo.getUserId());
			if(!"".equals(user)&&null!=user){
				prinfo.setUser(user);
			}
		}*/
		page.setList(list);
		return page;
		
	}
	
}