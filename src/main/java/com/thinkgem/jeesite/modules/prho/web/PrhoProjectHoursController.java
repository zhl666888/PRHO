/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.prho.web;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectHours;
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectInfo;
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectTask;
import com.thinkgem.jeesite.modules.prho.service.PrhoProjectHoursService;
import com.thinkgem.jeesite.modules.prho.service.PrhoProjectInfoService;
import com.thinkgem.jeesite.modules.prho.service.PrhoProjectTaskService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 项目工时Controller
 * @author zhl
 * @version 2017-05-18
 */
@Controller
@RequestMapping(value = "${adminPath}/prho/prhoProjectHours")
public class PrhoProjectHoursController extends BaseController {

	@Autowired
	private PrhoProjectHoursService prhoProjectHoursService;
	@Autowired
	private PrhoProjectInfoService prhoProjectInfoService;
	@Autowired
	private PrhoProjectTaskService prhoProjectTaskService;
	@ModelAttribute
	public PrhoProjectHours get(@RequestParam(required=false) String id) {
		PrhoProjectHours entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = prhoProjectHoursService.get(id);
		}
		if (entity == null){
			entity = new PrhoProjectHours();
		}
		return entity;
	}
	
	@RequiresPermissions("prho:prhoProjectHours:view")
	@RequestMapping(value = {"list", ""})
	public String list(PrhoProjectHours prhoProjectHours, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		prhoProjectHours.setStaff(user.getId());
		Page<PrhoProjectHours> page = prhoProjectHoursService.findPage(new Page<PrhoProjectHours>(request, response), prhoProjectHours); 
		model.addAttribute("page", page);
		return "modules/prho/prhoProjectHoursList";
	}

	@RequiresPermissions("prho:prhoProjectHours:view")
	@RequestMapping(value = "form")
	public String form(PrhoProjectHours prhoProjectHours, Model model) {
		if(StringUtils.isBlank(prhoProjectHours.getId())){
			String date=DateUtils.getDate();
			prhoProjectHours.setWorktime(DateUtils.parseDate(date));
			prhoProjectHours.setTaskstarttime(DateUtils.parseDate(date));
			prhoProjectHours.setTaskendtime(DateUtils.parseDate(date));
			prhoProjectHours.setTaskcompleteschedule("100");
			prhoProjectHours.setWorkhourstype("workingDay");
		}else{
			//修改时任务名称列表回显
			List<PrhoProjectTask> myTaskList=new ArrayList<PrhoProjectTask>();
			  myTaskList=prhoProjectTaskService.findProjectTaskList(prhoProjectHours.getProjectId());
			  if(!"".equals(myTaskList)&&null!=myTaskList){
				  model.addAttribute("myTaskList", myTaskList);
			  }
		}
		model.addAttribute("prhoProjectHours", prhoProjectHours);
		return "modules/prho/prhoProjectHoursForm";
	}

	@RequiresPermissions("prho:prhoProjectHours:edit")
	@RequestMapping(value = "save")
	public String save(PrhoProjectHours prhoProjectHours, Model model, RedirectAttributes redirectAttributes,@RequestParam(required = false) String staffId) {
		if (!beanValidator(model, prhoProjectHours)){
			return form(prhoProjectHours, model);
		}
		if(staffId!=""&&staffId!=null){
			prhoProjectHours.setStaff(staffId);
		}
		prhoProjectHours.setApprovalstatus("0");
		prhoProjectHours.setWorktime(prhoProjectHours.getTaskstarttime());
		prhoProjectHoursService.save(prhoProjectHours);
		//保存私有任务完成进度、私有任务完成时间
		String taskId=prhoProjectHours.getTaskId();
		PrhoProjectTask prhoProjectTask=  prhoProjectTaskService.get(taskId);
		if(("private").equals(prhoProjectTask.getTasktype())||("1").equals(prhoProjectTask.getTasktype())){
			prhoProjectTaskService.updateProjectProgress(prhoProjectHours,taskId);
		}
		addMessage(redirectAttributes, "保存项目工时成功");
		return "redirect:"+Global.getAdminPath()+"/prho/prhoProjectHours/?repage";
	}
	
	@RequiresPermissions("prho:prhoProjectHours:edit")
	@RequestMapping(value = "delete")
	public String delete(PrhoProjectHours prhoProjectHours, RedirectAttributes redirectAttributes) {
		prhoProjectHoursService.delete(prhoProjectHours);
		addMessage(redirectAttributes, "删除项目工时成功");
		return "redirect:"+Global.getAdminPath()+"/prho/prhoProjectHours/?repage";
	}
	/**
	 * 我的任务点击工时填报
	 * @param pmtid
	 * @param prhoProjectHours
	 * @param model
	 * @return
	 */
	@RequiresPermissions("prho:prhoProjectHours:view")
	@RequestMapping(value = "saveMyHours")
	public String saveMyHours(String pmtid,PrhoProjectHours prhoProjectHours, Model model) {
		if(StringUtils.isNotBlank(pmtid)){
			PrhoProjectTask	prhoProjectTask	=prhoProjectTaskService.get(pmtid);
			prhoProjectHours.setJobtype(prhoProjectTask.getWorktype());
			prhoProjectHours.setTaskId(prhoProjectTask.getId());
			prhoProjectHours.setTaskcompleteschedule("100");
			prhoProjectHours.setJobtypelabel(DictUtils.getDictLabel(prhoProjectTask.getWorktype(), "work_type", ""));
			model.addAttribute("tnReadonly", prhoProjectHours.getTaskId());
			
			PrhoProjectInfo	prhoProjectInfo =prhoProjectInfoService.get(prhoProjectTask.getProjectId());
			prhoProjectHours.setProjectId(prhoProjectInfo.getId());
			prhoProjectHours.setProjectmanagerId(prhoProjectInfo.getUserId());
			model.addAttribute("pnReadonly", prhoProjectHours.getProjectId());
			return "modules/prho/prhoFillWorkhoursForm";
		}
		String date=DateUtils.getDate();
		prhoProjectHours.setWorktime(DateUtils.parseDate(date));
		prhoProjectHours.setTaskstarttime(DateUtils.parseDate(date));
		prhoProjectHours.setTaskendtime(DateUtils.parseDate(date));
		model.addAttribute("prhoProjectHours", prhoProjectHours);
		return "modules/prho/prhoProjectHoursForm";
	}
	/*
	 * 项目名称级联审批人
	 */
	@ResponseBody
	@RequiresPermissions("prho:prhoProjectHours:view")
	@RequestMapping(value="getProjectManager")
	public PrhoProjectInfo getProjectManager(String projectId,Model model){
		PrhoProjectInfo prhoProjectInfo=new PrhoProjectInfo();
		if(StringUtils.isNotBlank(projectId)){
		 prhoProjectInfo=prhoProjectInfoService.get(projectId);
		}
		List<PrhoProjectTask> myTaskList=new ArrayList<PrhoProjectTask>();
		  myTaskList=prhoProjectTaskService.findProjectTaskList(projectId);
		  if(!"".equals(myTaskList)&&null!=myTaskList){
			  prhoProjectInfo.setMyTaskList(myTaskList);
		  }
		return prhoProjectInfo;
	}
	@ResponseBody
	@RequiresPermissions("prho:prhoProjectHours:view")
	@RequestMapping(value="getWorkType")
	public PrhoProjectTask getWorkType(String taskId){
		PrhoProjectTask prhoProjectTask=new PrhoProjectTask();
		if(StringUtils.isNotBlank(taskId)){
			prhoProjectTask=prhoProjectTaskService.get(taskId);
		}
		return prhoProjectTask;
	}
}