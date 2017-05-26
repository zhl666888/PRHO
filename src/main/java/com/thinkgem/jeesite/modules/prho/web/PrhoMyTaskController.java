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
import com.thinkgem.jeesite.modules.prho.entity.PrhoMyTask;
import com.thinkgem.jeesite.modules.prho.service.PrhoMyTaskService;
import com.thinkgem.jeesite.modules.prho.service.PrhoProjectTaskService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 我的任务Controller
 * @author zhl
 * @version 2017-05-17
 */
@Controller
@RequestMapping(value = "${adminPath}/prho/prhoMyTask")
public class PrhoMyTaskController extends BaseController {

	@Autowired
	private PrhoProjectTaskService prhoProjectTaskService;
	@Autowired
	private PrhoMyTaskService prhoMyTaskService;
	
	@ModelAttribute
	public PrhoMyTask get(@RequestParam(required=false) String id) {
		PrhoMyTask entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = prhoMyTaskService.get(id);
		}
		if (entity == null){
			entity = new PrhoMyTask();
		}
		return entity;
	}
	
	@RequiresPermissions("prho:prhoMyTask:view")
	@RequestMapping(value = {"list", ""})
	public String list(PrhoMyTask prhoMyTask, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		prhoMyTask.setTaskmanager(user.getId());
		Page<PrhoMyTask> page = prhoMyTaskService.findPageBy(new Page<PrhoMyTask>(request, response), prhoMyTask); 
		model.addAttribute("page", page);
		return "modules/prho/prhoMyTaskList";
	}

	@RequiresPermissions("prho:prhoMyTask:view")
	@RequestMapping(value = "form")
	public String form(PrhoMyTask prhoMyTask, Model model) {
		model.addAttribute("prhoMyTask", prhoMyTask);
		return "modules/prho/prhoMyTaskForm";
	}

	@RequiresPermissions("prho:prhoMyTask:edit")
	@RequestMapping(value = "save")
	public String save(PrhoMyTask prhoMyTask, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, prhoMyTask)){
			return form(prhoMyTask, model);
		}
		prhoMyTaskService.save(prhoMyTask);
		addMessage(redirectAttributes, "保存项目任务成功");
		return "redirect:"+Global.getAdminPath()+"/prho/prhoMyTask/?repage";
	}
	
	@RequiresPermissions("prho:prhoMyTask:edit")
	@RequestMapping(value = "delete")
	public String delete(PrhoMyTask prhoMyTask, RedirectAttributes redirectAttributes) {
		prhoMyTaskService.delete(prhoMyTask);
		addMessage(redirectAttributes, "删除项目任务成功");
		return "redirect:"+Global.getAdminPath()+"/prho/prhoMyTask/?repage";
	}

}