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
import com.thinkgem.jeesite.modules.prho.entity.PrhoMyTask;
import com.thinkgem.jeesite.modules.prho.dao.PrhoMyTaskDao;
import com.thinkgem.jeesite.modules.prho.dao.PrhoProjectInfoDao;

/**
 * 我的任务Service
 * @author zhl
 * @version 2017-05-17
 */
@Service
@Transactional(readOnly = true)
public class PrhoMyTaskService extends CrudService<PrhoMyTaskDao, PrhoMyTask> {
	@Autowired
    private PrhoProjectInfoDao prInfoDao;
	@Autowired
	private PrhoMyTaskDao prhoMyTaskDao;
	public PrhoMyTask get(String id) {
		return super.get(id);
	}
	
	public List<PrhoMyTask> findList(PrhoMyTask prhoMyTask) {
		return super.findList(prhoMyTask);
	}
	
	public Page<PrhoMyTask> findPage(Page<PrhoMyTask> page, PrhoMyTask prhoMyTask) {
		return super.findPage(page, prhoMyTask);
	}
	
	@Transactional(readOnly = false)
	public void save(PrhoMyTask prhoMyTask) {
		super.save(prhoMyTask);
	}
	
	@Transactional(readOnly = false)
	public void delete(PrhoMyTask prhoMyTask) {
		super.delete(prhoMyTask);
	}
	
	@Transactional(readOnly = false)
	public Page<PrhoMyTask> findPageBy(Page<PrhoMyTask> page,PrhoMyTask prhoMyTask){
		prhoMyTask.setPage(page);
		List<PrhoMyTask> list = prhoMyTaskDao.findPageBy(prhoMyTask);
		/*for(PrhoProjectTask prtask: list){
			PrhoProjectInfo prhoProjectInfo=prInfoDao.get(prtask.getProjectId());
			if(!"".equals(prhoProjectInfo)&&null!=prhoProjectInfo){
				prtask.setPrhoProjectInfo(prhoProjectInfo);
			}
		}*/
		page.setList(list);
		return page;
		
	}
}