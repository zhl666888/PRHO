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
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectTask;
import com.thinkgem.jeesite.modules.prho.service.PrhoProjectTaskService;

/**
 * 项目任务Controller
 * @author zhl
 * @version 2017-05-12
 */
@Controller
@RequestMapping(value = "${adminPath}/prho/prhoProjectTask")
public class PrhoProjectTaskController extends BaseController {

	@Autowired
	private PrhoProjectTaskService prhoProjectTaskService;
	
	@ModelAttribute
	public PrhoProjectTask get(@RequestParam(required=false) String id) {
		PrhoProjectTask entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = prhoProjectTaskService.get(id);
		}
		if (entity == null){
			entity = new PrhoProjectTask();
		}
		return entity;
	}
	
	@RequiresPermissions("prho:prhoProjectTask:view")
	@RequestMapping(value = {"list", ""})
	public String list(PrhoProjectTask prhoProjectTask, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PrhoProjectTask> page = prhoProjectTaskService.findPageBy(new Page<PrhoProjectTask>(request, response), prhoProjectTask); 
		model.addAttribute("page", page);
		return "modules/prho/prhoProjectTaskList";
	}

	@RequiresPermissions("prho:prhoProjectTask:view")
	@RequestMapping(value = "form")
	public String form(PrhoProjectTask prhoProjectTask, Model model) {
		model.addAttribute("prhoProjectTask", prhoProjectTask);
		return "modules/prho/prhoProjectTaskForm";
	}

	@RequiresPermissions("prho:prhoProjectTask:edit")
	@RequestMapping(value = "save")
	public String save(PrhoProjectTask prhoProjectTask, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, prhoProjectTask)){
			return form(prhoProjectTask, model);
		}
		prhoProjectTaskService.save(prhoProjectTask);
		addMessage(redirectAttributes, "保存项目任务成功");
		return "redirect:"+Global.getAdminPath()+"/prho/prhoProjectTask/?repage";
	}
	
	@RequiresPermissions("prho:prhoProjectTask:edit")
	@RequestMapping(value = "delete")
	public String delete(PrhoProjectTask prhoProjectTask, RedirectAttributes redirectAttributes) {
		prhoProjectTaskService.delete(prhoProjectTask);
		addMessage(redirectAttributes, "删除项目任务成功");
		return "redirect:"+Global.getAdminPath()+"/prho/prhoProjectTask/?repage";
	}

}