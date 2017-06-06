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

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectDevelopSpeed;
import com.thinkgem.jeesite.modules.prho.service.PrhoProjectDevelopSpeedService;
import com.thinkgem.jeesite.modules.prho.service.PrhoProjectInfoService;
import com.thinkgem.jeesite.modules.prho.service.PrhoProjectTaskService;

/**
 * 项目开发速度分析Controller
 * @author zhl
 * @version 2017-06-01
 */
@Controller
@RequestMapping(value = "${adminPath}/prho/prhoProjectDevelopSpeed")
public class PrhoProjectDevelopSpeedController extends BaseController {

	@Autowired
	private PrhoProjectDevelopSpeedService prhoProjectDevelopSpeedService;
	@Autowired
	private PrhoProjectTaskService prhoProjectTaskService;
	@Autowired
	private PrhoProjectInfoService prhoProjectInfoService;
	
	@ModelAttribute
	public PrhoProjectDevelopSpeed get(@RequestParam(required=false) String id) {
		PrhoProjectDevelopSpeed entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = prhoProjectDevelopSpeedService.get(id);
		}
		if (entity == null){
			entity = new PrhoProjectDevelopSpeed();
		}
		return entity;
	}
	
	@RequiresPermissions("prho:prhoProjectDevelopSpeed:view")
	@RequestMapping(value = {"list", ""})
	public String list(PrhoProjectDevelopSpeed prhoProjectDevelopSpeed, HttpServletRequest request, HttpServletResponse response, Model model,@RequestParam(required=false) String radioval) {
		if(StringUtils.isNotBlank(radioval)){
			prhoProjectDevelopSpeed.setRadioval(radioval);
		}else{
			prhoProjectDevelopSpeed.setRadioval("day");
		}
		Page<PrhoProjectDevelopSpeed> page = prhoProjectDevelopSpeedService.findPageBy(new Page<PrhoProjectDevelopSpeed>(request, response), prhoProjectDevelopSpeed); 
		
		model.addAttribute("page", page);
		return "modules/prho/prhoProjectDevelopSpeedList";
	}
}