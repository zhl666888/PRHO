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
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectDevelopSpeed;
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectInfo;
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
	public String list(PrhoProjectDevelopSpeed prhoProjectDevelopSpeed, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PrhoProjectDevelopSpeed> page = prhoProjectDevelopSpeedService.findPageBy(new Page<PrhoProjectDevelopSpeed>(request, response), prhoProjectDevelopSpeed); 
		model.addAttribute("page", page);
		return "modules/prho/prhoProjectDevelopSpeedList";
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
				if(StringUtils.isNotBlank(prhoProjectInfo.getUserId())&&StringUtils.isNotBlank(prhoProjectInfo.getEstimatehours())){
				 	PrhoProjectInfo ppi=prhoProjectInfoService.getProjectManagerId(prhoProjectInfo);
				 	prhoProjectInfo.setUserId(ppi.getUserId());
					prhoProjectInfoService.save(prhoProjectInfo);
					successNum++;
				}else{
					failureMsg.append("<br/>项目负责人与预估工时不能为空; ");
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条项目，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条项目"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入项目信息失败！失败信息：");//+e.getMessage()
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