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
import com.thinkgem.jeesite.modules.prho.entity.PrhoWorktimeApproval;
import com.thinkgem.jeesite.modules.prho.dao.PrhoWorktimeApprovalDao;

/**
 * 工时审批Service
 * @author zhl
 * @version 2017-05-26
 */
@Service
@Transactional(readOnly = true)
public class PrhoWorktimeApprovalService extends CrudService<PrhoWorktimeApprovalDao, PrhoWorktimeApproval> {
	@Autowired
    private PrhoWorktimeApprovalDao dao;
	public PrhoWorktimeApproval get(String id) {
		return super.get(id);
	}
	
	public List<PrhoWorktimeApproval> findList(PrhoWorktimeApproval prhoWorktimeApproval) {
		return super.findList(prhoWorktimeApproval);
	}
	
	public Page<PrhoWorktimeApproval> findPage(Page<PrhoWorktimeApproval> page, PrhoWorktimeApproval prhoWorktimeApproval) {
		return super.findPage(page, prhoWorktimeApproval);
	}
	
	@Transactional(readOnly = false)
	public void save(PrhoWorktimeApproval prhoWorktimeApproval) {
		super.save(prhoWorktimeApproval);
	}
	
	@Transactional(readOnly = false)
	public void delete(PrhoWorktimeApproval prhoWorktimeApproval) {
		super.delete(prhoWorktimeApproval);
	}
	/*public Page<PrhoWorktimeApproval> findPageBy(Page<PrhoWorktimeApproval> page,PrhoWorktimeApproval prhoWorktimeApproval){
		prhoWorktimeApproval.setPage(page);
		List<PrhoWorktimeApproval> list=dao.findPageBy(prhoWorktimeApproval);
		
		page.setList(list);
		return page;
		
	}*/
	
}