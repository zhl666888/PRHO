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
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectHours;
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectInfo;
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectTask;
import com.thinkgem.jeesite.modules.prho.dao.PrhoProjectInfoDao;
import com.thinkgem.jeesite.modules.prho.dao.PrhoProjectTaskDao;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;

/**
 * 项目任务Service
 * @author zhl
 * @version 2017-05-12
 */
@Service
@Transactional(readOnly = true)
public class PrhoProjectTaskService extends CrudService<PrhoProjectTaskDao, PrhoProjectTask> {
	@Autowired
    private PrhoProjectInfoDao prInfoDao;
	@Autowired
	private PrhoProjectTaskDao prTaskDao;
	public PrhoProjectTask get(String id) {
		return super.get(id);
	}
	
	public List<PrhoProjectTask> findList(PrhoProjectTask prhoProjectTask) {
		return super.findList(prhoProjectTask);
	}
	
	public Page<PrhoProjectTask> findPage(Page<PrhoProjectTask> page, PrhoProjectTask prhoProjectTask) {
		return super.findPage(page, prhoProjectTask);
	}
	
	@Transactional(readOnly = false)
	public void save(PrhoProjectTask prhoProjectTask) {
		super.save(prhoProjectTask);
	}
	
	@Transactional(readOnly = false)
	public void delete(PrhoProjectTask prhoProjectTask) {
		super.delete(prhoProjectTask);
	}
	
	public Page<PrhoProjectTask> findPageBy(Page<PrhoProjectTask> page,PrhoProjectTask prhoProjectTask){
		prhoProjectTask.setPage(page);
		List<PrhoProjectTask> list = prTaskDao.findPageBy(prhoProjectTask);
		/*for(PrhoProjectTask prtask: list){
			PrhoProjectInfo prhoProjectInfo=prInfoDao.get(prtask.getProjectId());
			if(!"".equals(prhoProjectInfo)&&null!=prhoProjectInfo){
				prtask.setPrhoProjectInfo(prhoProjectInfo);
			}
		}*/
		page.setList(list);
		return page;
		
	}
	/**
	 * 更新任务完成时间和任务完成进度
	 * @param prhoProjectHours
	 * @param taskId
	 */
	@Transactional(readOnly = false)
	public void updateProjectProgress(PrhoProjectHours prhoProjectHours,String taskId){
		PrhoProjectTask prhoProjectTask=new PrhoProjectTask();
		prhoProjectTask.setId(taskId);
		prhoProjectTask.setTaskcompleteschedule(prhoProjectHours.getTaskcompleteschedule());
		prhoProjectTask.setTaskcompletetime(prhoProjectHours.getTaskendtime());
		prTaskDao.updateProjectProgress(prhoProjectTask);
	}
	/**
	 * 添加项目后，在任务中保存公有任务多条(依据工作类型)
	 * @param prhoProjectInfo
	 */
	@Transactional(readOnly = false)
	public void savePersonalTask(PrhoProjectInfo prhoProjectInfo){
		List<Dict> dictList=DictUtils.getDictList("work_type");
		for(int i=0;i<dictList.size();i++){
			PrhoProjectTask prhoProjectTask=new PrhoProjectTask();
			prhoProjectTask.setProjectId(prhoProjectInfo.getId());
			prhoProjectTask.setWorktype(dictList.get(i).getValue());
			prhoProjectTask.setTasktype("public");
			save(prhoProjectTask);
		}
	}
}