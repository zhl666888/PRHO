package com.thinkgem.jeesite.modules.prho.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.User;


/**
 * 建立人物关系Service
 * @author zhl
 * @version 2017-05-13
 */
@Service
@Transactional(readOnly = true)
public class PrhoUserGroupService {
	@Autowired
	private UserDao userdao;
	//获取所有用户
		public List<User> findUserList(User user) {
		List<User> userlist = userdao.findAllList(user);
			return userlist;
		}
}
