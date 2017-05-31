/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.prho.web;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectInfo;
import com.thinkgem.jeesite.modules.prho.service.PrhoProjectInfoService;
import com.thinkgem.jeesite.modules.prho.service.PrhoProjectTaskService;

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
	@Autowired
	private PrhoProjectTaskService prhoProjectTaskService;
	
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
		//prhoProjectInfoService.save(prhoProjectInfo);
		prhoProjectInfoService.saveNew(prhoProjectInfo);
		//添加项目后，在任务中保存公有任务多条(依据工作类型)
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
	@RequiresPermissions("prho:prhoProjectInfo:edit")
	@RequestMapping(value="import",method=RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:"+Global.getAdminPath()+"/prho/prhoProjectInfo/list?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<PrhoProjectInfo> list = ei.getDataList(PrhoProjectInfo.class);
			for (PrhoProjectInfo prhoProjectInfo : list){
				if(StringUtils.isNotBlank(prhoProjectInfo.getUserId())){
				 	PrhoProjectInfo ppi=prhoProjectInfoService.getProjectManagerId(prhoProjectInfo);
				 	prhoProjectInfo.setUserId(ppi.getUserId());
					prhoProjectInfoService.save(prhoProjectInfo);
					successNum++;
				}else{
					failureMsg.append("<br/>项目负责人不能为空; ");
					failureNum++;
				}
				
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条项目，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条项目"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入项目信息失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/prho/prhoProjectInfo/list?repage";
    }
	
	/**
	 * 下载项目信息导入模板
	 * @param redirectAttributes
	 * @param prhoProjectInfo
	 * @return
	 */
	@RequiresPermissions("prho:prhoProjectInfo:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response,HttpServletRequest request, RedirectAttributes redirectAttributes,PrhoProjectInfo prhoProjectInfo) {
		try {
            String fileName = "项目信息导入模板.xlsx";
            Page<PrhoProjectInfo> page = prhoProjectInfoService.findPageBy(new Page<PrhoProjectInfo>(request, response,-1), prhoProjectInfo); 
    		//List<PrhoProjectInfo> ppiList = Lists.newArrayList(); 
    		//ppiList.add(UserUtils.getUser());
    		new ExportExcel("项目信息", PrhoProjectInfo.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/prho/prhoProjectInfo/list?repage";
    }
	
	
}