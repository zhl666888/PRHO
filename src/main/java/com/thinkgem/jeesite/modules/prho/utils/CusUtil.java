package com.thinkgem.jeesite.modules.prho.utils;

import java.util.ArrayList;
import java.util.List;

import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectInfo;
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectTask;
import com.thinkgem.jeesite.modules.prho.service.PrhoProjectInfoService;
import com.thinkgem.jeesite.modules.prho.service.PrhoProjectTaskService;
import com.thinkgem.jeesite.modules.prho.service.PrhoUserGroupService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;


public class CusUtil {
	private static PrhoUserGroupService prhoUserGroupService = SpringContextHolder.getBean(PrhoUserGroupService.class);
	private static PrhoProjectInfoService prhoProjectInfoService = SpringContextHolder.getBean(PrhoProjectInfoService.class);
	private static PrhoProjectTaskService prhoProjectTaskService = SpringContextHolder.getBean(PrhoProjectTaskService.class);

	public static final String USER_LIST_CACHE = "userListCache";
	public static final String PRHOPROJECTINFO_LIST_CACHE = "prhoProjectInfoListCache";
	public static final String PRHOPROJECTTASK_LIST_CACHE = "prhoProjectTaskListCache";
	/**
	 * 返回所有用户
	 */
	public static List<User> getAllUser(){
	@SuppressWarnings("unchecked")
		List<User> userList = (List<User>) CacheUtils.get(USER_LIST_CACHE, "userlistcache");
		if (userList == null||userList.size()==0) {
			User user =new User();
			user.setDelFlag("0");
			userList =prhoUserGroupService.findUserList(user);
		}
		return userList;
	}
	/**
	 * 
	 * 获取项目信息的所有项目名称
	 */
	public static List<PrhoProjectInfo> getAllProjectName(){
		@SuppressWarnings("unchecked")
			List<PrhoProjectInfo> prList = (List<PrhoProjectInfo>) CacheUtils.get(PRHOPROJECTINFO_LIST_CACHE, "prhoProjectInfoListCache");
			if (prList == null||prList.size()==0) {
				PrhoProjectInfo ppi = new PrhoProjectInfo();
				prList = prhoProjectInfoService.findProjectNameList(ppi);
			}
			return prList;
		}
	/**
	 * 
	 * 获取项目任务的所有任务名称(任务名称去空)
	 */
	public static List<PrhoProjectTask> getAllTaskName(){
		@SuppressWarnings("unchecked")
			List<PrhoProjectTask> prtList = (List<PrhoProjectTask>) CacheUtils.get(PRHOPROJECTTASK_LIST_CACHE, "prhoProjectTaskListCache");
			if (prtList == null||prtList.size()==0) {
				PrhoProjectTask ppt = new PrhoProjectTask();
				prtList = prhoProjectTaskService.findList(ppt);
			}
			List<PrhoProjectTask> prtListnew=new ArrayList<PrhoProjectTask>();
			for(PrhoProjectTask pptnew:prtList){
				if(StringUtils.isNotBlank(pptnew.getTaskname())){
					prtListnew.add(pptnew);
				}
			}
			return prtListnew;
		}
	
	
	/**
	 * 
	 * 获取当前用户下的项目名称
	 */
	public static List<PrhoProjectInfo> getPersonalProjectName(){
		@SuppressWarnings("unchecked")
			List<PrhoProjectInfo> ppiList = (List<PrhoProjectInfo>) CacheUtils.get(PRHOPROJECTINFO_LIST_CACHE, "prhoProjectInfoListCache");
			User user = UserUtils.getUser();
			if (ppiList == null||ppiList.size()==0) {
				PrhoProjectInfo pri = new PrhoProjectInfo();
				pri.setUserId(user.getId());
				ppiList = prhoProjectInfoService.getPersonalProjectName(pri);
			}
			return ppiList;
		}
}
