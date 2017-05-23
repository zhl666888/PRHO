/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.prho.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectHours;
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectTask;
import com.thinkgem.jeesite.modules.prho.service.PrhoProjectHoursService;
import com.thinkgem.jeesite.modules.prho.service.PrhoProjectInfoService;
import com.thinkgem.jeesite.modules.prho.service.PrhoProjectTaskService;
import com.thinkgem.jeesite.modules.sys.entity.User;
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
	public String form(String ppiid,PrhoProjectHours prhoProjectHours, Model model) {
		if(null != ppiid && !"".equals(ppiid)){
			/*PrhoProjectInfo	prhoProjectInfo	=prhoProjectInfoService.get(ppiid);
			prhoProjectHours.setJobtype(prhoProjectInfo.get);*/
			model.addAttribute("ppiid",ppiid);
		}
		model.addAttribute("prhoProjectHours", prhoProjectHours);
		return "modules/prho/prhoProjectHoursForm";
	}

	@RequiresPermissions("prho:prhoProjectHours:edit")
	@RequestMapping(value = "save")
	public String save(String ppiid,PrhoProjectHours prhoProjectHours, Model model, RedirectAttributes redirectAttributes,@RequestParam(required = false) String staffId) {
		if (!beanValidator(model, prhoProjectHours)){
			return form(ppiid,prhoProjectHours, model);
		}
		if(staffId!=""&&staffId!=null){
			prhoProjectHours.setStaff(staffId);
		}
		prhoProjectHoursService.save(prhoProjectHours);
		//保存项目完成进度(项目完成时间)
		String taskId=prhoProjectHours.getTaskId();
		PrhoProjectTask prhoProjectTask=  prhoProjectTaskService.get(taskId);
		if(("private").equals(prhoProjectTask.getTasktype())){
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

}