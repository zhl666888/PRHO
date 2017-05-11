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
import com.thinkgem.jeesite.modules.prho.entity.PrhoTestLog;
import com.thinkgem.jeesite.modules.prho.service.PrhoTestLogService;

/**
 * 测试logController
 * @author zhl
 * @version 2017-05-10
 */
@Controller
@RequestMapping(value = "${adminPath}/prho/prhoTestLog")
public class PrhoTestLogController extends BaseController {

	@Autowired
	private PrhoTestLogService prhoTestLogService;
	
	@ModelAttribute
	public PrhoTestLog get(@RequestParam(required=false) String id) {
		PrhoTestLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = prhoTestLogService.get(id);
		}
		if (entity == null){
			entity = new PrhoTestLog();
		}
		return entity;
	}
	
	@RequiresPermissions("prho:prhoTestLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(PrhoTestLog prhoTestLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PrhoTestLog> page = prhoTestLogService.findPage(new Page<PrhoTestLog>(request, response), prhoTestLog); 
		model.addAttribute("page", page);
		return "modules/prho/prhoTestLogList";
	}

	@RequiresPermissions("prho:prhoTestLog:view")
	@RequestMapping(value = "form")
	public String form(PrhoTestLog prhoTestLog, Model model) {
		model.addAttribute("prhoTestLog", prhoTestLog);
		return "modules/prho/prhoTestLogForm";
	}

	@RequiresPermissions("prho:prhoTestLog:edit")
	@RequestMapping(value = "save")
	public String save(PrhoTestLog prhoTestLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, prhoTestLog)){
			return form(prhoTestLog, model);
		}
		prhoTestLogService.save(prhoTestLog);
		addMessage(redirectAttributes, "保存测试log成功");
		return "redirect:"+Global.getAdminPath()+"/prho/prhoTestLog/?repage";
	}
	
	@RequiresPermissions("prho:prhoTestLog:edit")
	@RequestMapping(value = "delete")
	public String delete(PrhoTestLog prhoTestLog, RedirectAttributes redirectAttributes) {
		prhoTestLogService.delete(prhoTestLog);
		addMessage(redirectAttributes, "删除测试log成功");
		return "redirect:"+Global.getAdminPath()+"/prho/prhoTestLog/?repage";
	}

}