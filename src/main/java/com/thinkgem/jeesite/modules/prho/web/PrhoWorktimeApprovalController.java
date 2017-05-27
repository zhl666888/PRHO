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
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.prho.entity.PrhoWorktimeApproval;
import com.thinkgem.jeesite.modules.prho.service.PrhoProjectHoursService;
import com.thinkgem.jeesite.modules.prho.service.PrhoProjectInfoService;
import com.thinkgem.jeesite.modules.prho.service.PrhoProjectTaskService;
import com.thinkgem.jeesite.modules.prho.service.PrhoWorktimeApprovalService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 工时审批Controller
 * @author zhl
 * @version 2017-05-26
 */
@Controller
@RequestMapping(value = "${adminPath}/prho/prhoWorktimeApproval")
public class PrhoWorktimeApprovalController extends BaseController {
	@Autowired
	private PrhoWorktimeApprovalService prhoWorktimeApprovalService;
	@Autowired
	private PrhoProjectHoursService prhoProjectHoursService;
	@Autowired
	private PrhoProjectInfoService prhoProjectInfoService;
	@Autowired
	private PrhoProjectTaskService prhoProjectTaskService;
	@ModelAttribute
	public PrhoWorktimeApproval get(@RequestParam(required=false) String id) {
		PrhoWorktimeApproval entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = prhoWorktimeApprovalService.get(id);
		}
		if (entity == null){
			entity = new PrhoWorktimeApproval();
		}
		return entity;
	}
	
	@RequiresPermissions("prho:prhoWorktimeApproval:view")
	@RequestMapping(value = {"list", ""})
	public String list(PrhoWorktimeApproval prhoWorktimeApproval, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		prhoWorktimeApproval.setProjectmanagerId(user.getId());
		Page<PrhoWorktimeApproval> page = prhoWorktimeApprovalService.findPage(new Page<PrhoWorktimeApproval>(request, response), prhoWorktimeApproval); 
		model.addAttribute("page", page);
		return "modules/prho/prhoWorktimeApprovalList";
	}

	@RequiresPermissions("prho:prhoWorktimeApproval:view")
	@RequestMapping(value = "form")
	public String form(PrhoWorktimeApproval prhoWorktimeApproval, Model model) {
		
		model.addAttribute("prhoWorktimeApproval", prhoWorktimeApproval);
		return "modules/prho/prhoWorktimeApprovalForm";
	}

	@RequiresPermissions("prho:prhoWorktimeApproval:edit")
	@RequestMapping(value = "save")
	public String save(PrhoWorktimeApproval prhoWorktimeApproval, Model model, RedirectAttributes redirectAttributes,@RequestParam(required = false) String staffId) {
		if (!beanValidator(model, prhoWorktimeApproval)){
			return form(prhoWorktimeApproval, model);
		}
		String date=DateUtils.getDate();
		prhoWorktimeApproval.setApprovaltime(DateUtils.parseDate(date));
		prhoWorktimeApprovalService.save(prhoWorktimeApproval);
		addMessage(redirectAttributes, "保存工时审批成功");
		return "redirect:"+Global.getAdminPath()+"/prho/prhoWorktimeApproval/?repage";
	}
	
	@RequiresPermissions("prho:prhoWorktimeApproval:edit")
	@RequestMapping(value = "delete")
	public String delete(PrhoWorktimeApproval prhoWorktimeApproval, RedirectAttributes redirectAttributes) {
		prhoWorktimeApprovalService.delete(prhoWorktimeApproval);
		addMessage(redirectAttributes, "删除项目工时成功");
		return "redirect:"+Global.getAdminPath()+"/prho/prhoWorktimeApproval/?repage";
	}
	
	/*@ResponseBody
	@RequiresPermissions("prho:prhoProjectHours:view")
	@RequestMapping(value="getWorkType")
	public PrhoProjectTask getWorkType(String taskId){
		PrhoProjectTask prhoProjectTask=new PrhoProjectTask();
		if(StringUtils.isNotBlank(taskId)){
			prhoProjectTask=prhoProjectTaskService.get(taskId);
		}
		return prhoProjectTask;
	}*/
}