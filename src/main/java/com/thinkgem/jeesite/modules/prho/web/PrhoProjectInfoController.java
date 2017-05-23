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
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectInfo;
import com.thinkgem.jeesite.modules.prho.service.PrhoProjectInfoService;

/**
 * 项目信息Controller
 * @author zhl
 * @version 2017-05-12
 */
@Controller
@RequestMapping(value = "${adminPath}/prho/prhoProjectInfo")
public class PrhoProjectInfoController extends BaseController {

	@Autowired
	private PrhoProjectInfoService prhoProjectInfoService;
	
	@ModelAttribute
	public PrhoProjectInfo get(@RequestParam(required=false) String id) {
		PrhoProjectInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = prhoProjectInfoService.get(id);
		}
		if (entity == null){
			entity = new PrhoProjectInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("prho:prhoProjectInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(PrhoProjectInfo prhoProjectInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PrhoProjectInfo> page = prhoProjectInfoService.findPageBy(new Page<PrhoProjectInfo>(request, response), prhoProjectInfo); 
		model.addAttribute("page", page);
		return "modules/prho/prhoProjectInfoList";
	}

	@RequiresPermissions("prho:prhoProjectInfo:view")
	@RequestMapping(value = "form")
	public String form(PrhoProjectInfo prhoProjectInfo, Model model) {
		model.addAttribute("prhoProjectInfo", prhoProjectInfo);
		return "modules/prho/prhoProjectInfoForm";
	}

	@RequiresPermissions("prho:prhoProjectInfo:edit")
	@RequestMapping(value = "save")
	public String save(PrhoProjectInfo prhoProjectInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, prhoProjectInfo)){
			return form(prhoProjectInfo, model);
		}
		prhoProjectInfoService.save(prhoProjectInfo);
		addMessage(redirectAttributes, "保存项目信息成功");
		return "redirect:"+Global.getAdminPath()+"/prho/prhoProjectInfo/?repage";
	}
	
	@RequiresPermissions("prho:prhoProjectInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(PrhoProjectInfo prhoProjectInfo, RedirectAttributes redirectAttributes) {
		prhoProjectInfoService.delete(prhoProjectInfo);
		addMessage(redirectAttributes, "删除项目信息成功");
		return "redirect:"+Global.getAdminPath()+"/prho/prhoProjectInfo/?repage";
	}
	@RequiresPermissions("prho:prhoProjectInfo:edit")
	@RequestMapping(value = "addStaff")
	public String addStuff(PrhoProjectInfo prhoProjectInfo, Model model) {
		//添加人员时，项目有人员则回显人员
		prhoProjectInfo = prhoProjectInfoService.getStaffFromProject(prhoProjectInfo);
		model.addAttribute("prhoProjectInfo", prhoProjectInfo);
		return "modules/prho/prhoProjectInfoAddStaff";
	}
	
	@RequiresPermissions("prho:prhoProjectInfo:edit")
	@RequestMapping(value = "saveProjectByStaff")
	public String saveProjectByStaff(PrhoProjectInfo prhoProjectInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, prhoProjectInfo)){
			return addStuff(prhoProjectInfo, model);
		}
		prhoProjectInfoService.saveProjectByStaff(prhoProjectInfo);
		addMessage(redirectAttributes, "保存项目所属人员信息成功");
		return "redirect:"+Global.getAdminPath()+"/prho/prhoProjectInfo/?repage";
	}
}