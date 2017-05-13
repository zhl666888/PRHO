package com.thinkgem.jeesite.modules.prho.utils;

import java.util.List;

import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.prho.service.PrhoUserGroupService;
import com.thinkgem.jeesite.modules.sys.entity.User;

public class CusUtil {
	private static PrhoUserGroupService prhoUserGroupService = SpringContextHolder.getBean(PrhoUserGroupService.class);
	public static final String USER_LIST_CACHE = "userListCache";
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
}
