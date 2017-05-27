/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.prho.service;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectDailyStatics;
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectHoursStatics;
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectInfo;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.prho.dao.PrhoProjectDailySatisticsDao;
import com.thinkgem.jeesite.modules.prho.dao.PrhoProjectHoursSatisticsDao;
import com.thinkgem.jeesite.common.service.BaseService;
import org.springframework.beans.factory.InitializingBean;
import com.thinkgem.jeesite.modules.prho.utils.DateUtils;
import com.thinkgem.jeesite.modules.prho.utils.StringUtils;


/**
 * Service 统计
 * @author ldx
 * @version 2017-05-23
 */
@Service
@Transactional(readOnly = true)
public class PrhoProjectDailySatisticsService extends CrudService<PrhoProjectDailySatisticsDao, PrhoProjectDailyStatics>{
	@Autowired
	private PrhoProjectDailySatisticsDao dao;
	
	public PrhoProjectDailyStatics get(String id) {
		return super.get(id);
	}
	
	/**
	 * 日报统计查询
	 * @param page
	 * @param prhoProjectDailyStatics
	 * @return
	 */
	@Transactional(readOnly = false)
	public Page<PrhoProjectDailyStatics> findPageBy(Page<PrhoProjectDailyStatics> page,PrhoProjectDailyStatics prhoProjectDailyStatics){
		prhoProjectDailyStatics.setPage(page);
		List<PrhoProjectDailyStatics> list = dao.findPageBy(prhoProjectDailyStatics);
		String weekName="";
		if(null!=list && list.size()>0){
			for(int i=0;i<list.size();i++){
				weekName = list.get(i).getWeekName();
				if(null != weekName && !weekName.equals("")){
					if(weekName.equals("星期1")){
						list.get(i).setWeekName("星期一");
					}else if(weekName.equals("星期2")){
						list.get(i).setWeekName("星期二");
					}else if(weekName.equals("星期3")){
						list.get(i).setWeekName("星期三");
					}else if(weekName.equals("星期4")){
						list.get(i).setWeekName("星期四");
					}else if(weekName.equals("星期5")){
						list.get(i).setWeekName("星期五");
					}else if(weekName.equals("星期6")){
						list.get(i).setWeekName("星期六");
					}else if(weekName.equals("星期7")){
						list.get(i).setWeekName("星期日");
					}
				}
				
			}
		}
		page.setList(list);
		return page;
		
	}
	
	
	public Page<PrhoProjectDailyStatics> findDailySatisticsListForExport(Page<PrhoProjectDailyStatics> page, PrhoProjectDailyStatics prhoProjectDailyStatics){
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		//prhoProjectHoursStatics.getSqlMap().put("dsf", dataScopeFilter("", "o", "a"));
		// 设置分页参数
		prhoProjectDailyStatics.setPage(page);
		
		List<PrhoProjectDailyStatics> list = dao.findPageBy(prhoProjectDailyStatics);
		String weekName="";
		//String newWorkTime="";
		if(null!=list && list.size()>0){
			for(int i=0;i<list.size();i++){
				weekName = list.get(i).getWeekName();
				/*if(null!=list.get(i).getWorkTime()){
				  newWorkTime = DateUtils.formatDate(list.get(i).getWorkTime());
				}*/
				if(null != weekName && !weekName.equals("")){
					if(weekName.equals("星期1")){
						list.get(i).setWeekName("星期一");
					}else if(weekName.equals("星期2")){
						list.get(i).setWeekName("星期二");
					}else if(weekName.equals("星期3")){
						list.get(i).setWeekName("星期三");
					}else if(weekName.equals("星期4")){
						list.get(i).setWeekName("星期四");
					}else if(weekName.equals("星期5")){
						list.get(i).setWeekName("星期五");
					}else if(weekName.equals("星期6")){
						list.get(i).setWeekName("星期六");
					}else if(weekName.equals("星期7")){
						list.get(i).setWeekName("星期日");
					}
				}
				
				/*if(null!=newWorkTime&&!newWorkTime.equals("")){
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		            Date time = df.parse(newWorkTime);
		            list.get(i).setWorkTime(time);	
				}*/
				
			}
		}
		
		// 执行分页查询
		page.setList(list);
		return page;
	}
	
	
	
}