package com.metelsos.user.service;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.metelsos.user.dao.UserDao;
import com.metelsos.user.vo.UserVo;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	/**
	 * �α��� �� ������ �����ϴ��� üũ
	 */
	public HashMap<String, Object> loginUser(HashMap<String, String> paramMap, HttpServletRequest request, HttpSession session) throws Exception{
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		UserVo userVo = userDao.checkUser(paramMap);
		
		if(userVo != null){
			session.setAttribute("SESSION_LOGIN_USER_ID", paramMap.get("userId"));
			returnMap.put("resultMsg", "SUCCESS");
			returnMap.put("user", userVo);
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}
	
	/**
	 * ȸ������ �� �� ���̵� �ߺ�Ȯ�� üũ
	 */
	public HashMap<String, Object> validateId(HashMap<String, String> paramMap) throws Exception{
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		UserVo userVo = userDao.checkUserById(paramMap);
		
		if(userVo != null){
			//���̵� ������ ��
			returnMap.put("resultMsg", "SUCCESS");
		}else{
			//���̵� �������� ���� �� �ߺ�Ȯ�� ����
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}
}
