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
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectInfo;
import com.thinkgem.jeesite.modules.prho.dao.PrhoProjectInfoDao;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 项目信息Service
 * @author zhl
 * @version 2017-05-12
 */
@Service
@Transactional(readOnly = true)
public class PrhoProjectInfoService extends CrudService<PrhoProjectInfoDao, PrhoProjectInfo> {
	@Autowired
	private UserDao userDao;
	@Autowired
	private PrhoProjectInfoDao dao;
	
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
	
	@Transactional(readOnly = false)
	public Page<PrhoProjectInfo> findPageBy(Page<PrhoProjectInfo> page,PrhoProjectInfo prhoProjectInfo){
		prhoProjectInfo.setPage(page);
		List<PrhoProjectInfo> list = dao.findPageBy(prhoProjectInfo);
		for(PrhoProjectInfo prinfo: list){
			User user=userDao.get(prinfo.getUserId());
			if(!"".equals(user)&&null!=user){
				prinfo.setUser(user);
			}
		}
		page.setList(list);
		return page;
		
	}
	@Transactional(readOnly = false)
	public  List<PrhoProjectInfo> findProjectNameList(PrhoProjectInfo prhoProjectInfo){
		List<PrhoProjectInfo> list=dao.findProjectNameList(prhoProjectInfo);
		return list;
		
	}
	
}