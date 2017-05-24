/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.prho.service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectDailyStatics;
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
	
	@Transactional(readOnly = false)
	public  List<PrhoProjectDailyStatics> findHoursSatisticsList(PrhoProjectDailyStatics prhoProjectDailyStatics){
		List<PrhoProjectDailyStatics> list=dao.findHoursSatisticsList(prhoProjectDailyStatics);
		return list;
		
	}
	
	
	
	
	
	
}