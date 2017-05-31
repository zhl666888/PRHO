/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.prho.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
	@Autowired
	private PrhoProjectTaskService prhoProjectTaskService;
	
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
	/*	for(PrhoProjectInfo prinfo: list){
			User user=userDao.get(prinfo.getUserId());
			if(!"".equals(user)&&null!=user){
				prinfo.setUser(user);
			}
		}*/
		page.setList(list);
		return page;
		
	}
	@Transactional(readOnly = false)
	public  List<PrhoProjectInfo> findProjectNameList(PrhoProjectInfo prhoProjectInfo){
		List<PrhoProjectInfo> list=dao.findProjectNameList(prhoProjectInfo);
		return list;
		
	}
	@Transactional(readOnly = false)
	public void saveProjectByStaff(PrhoProjectInfo prhoProjectInfo){
		//添加项目与人员关系
		List<User> listu = prhoProjectInfo.getUserList();
		//添加之前先删除，防止重复数据
		if(null != prhoProjectInfo.getId() && !"".equals(prhoProjectInfo.getId())){
			User user =new User();
			user.setProjectId(prhoProjectInfo.getId());
			dao.deleteprinfo(user);
		}
		//循环插入数据
		if(null != listu && listu.size() >0){
			for (User us : listu) {
				User user =new User();
				user.setProjectId(prhoProjectInfo.getId());
				user.setId(us.getId());
				dao.saveProjectByStaff(user);
			}
		}
	}
	@Transactional(readOnly = false)
	public  List<PrhoProjectInfo> getPersonalProjectName(PrhoProjectInfo prhoProjectInfo){
		List<PrhoProjectInfo> list=dao.getPersonalProjectName(prhoProjectInfo);
		return list;
	}
	/**
	 * 添加人员时，项目有人员则回显人员
	 * @param prhoProjectInfo
	 * @return
	 */
	public PrhoProjectInfo getStaffFromProject(PrhoProjectInfo prhoProjectInfo){
		if(StringUtils.isNotBlank(prhoProjectInfo.getId())){
			List<PrhoProjectInfo>  ppiList=dao.getPersonProlist(prhoProjectInfo.getId());
			List<User> listuser = new ArrayList<User>();
			for(PrhoProjectInfo pr:ppiList){
				listuser.add(userDao.get(pr.getUserId()));
			}
			prhoProjectInfo.setUserList(listuser);
		}
		return prhoProjectInfo;
	}
	@Transactional(readOnly = false)
	public void saveNew(PrhoProjectInfo prhoProjectInfo) {
		if (prhoProjectInfo.getIsNewRecord()){
			prhoProjectInfo.preInsert();
			//添加项目后，在任务中保存公有任务多条(依据工作类型)
			PrhoProjectInfo prhoProjectInfonew=get(prhoProjectInfo.getId());
			if(null==prhoProjectInfonew){
				prhoProjectTaskService.savePersonalTask(prhoProjectInfo);
			}
			dao.insert(prhoProjectInfo);
		}else{
			prhoProjectInfo.preUpdate();
			dao.update(prhoProjectInfo);
		}
	}
	public PrhoProjectInfo getProjectManagerId(PrhoProjectInfo prhoProjectInfo){
		PrhoProjectInfo ppi=dao.getProjectManagerId(prhoProjectInfo);
		return ppi;
	}
}