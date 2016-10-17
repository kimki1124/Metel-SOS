package com.metelsos.user.service;

import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.metelsos.user.controller.UserController;
import com.metelsos.user.dao.UserDao;
import com.metelsos.user.vo.UserVo;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	private Log log = LogFactory.getLog(UserServiceImpl.class); 
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	/**
	 * �α��� �� ������ �����ϴ��� üũ
	 */
	public HashMap<String, Object> loginUser(HashMap<String, String> paramMap, HttpServletRequest request, HttpSession session) throws Exception{
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		UserVo userVo = userDao.checkUser(paramMap);
		
		if(userVo != null){
			session.setAttribute("SESSION_LOGIN_USER_ID", paramMap.get("userID"));
			returnMap.put("resultMsg", "SUCCESS");
			returnMap.put("userName", userVo.getUser_name());
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
	
	/**
	 * ȸ������ 
	 */
	public HashMap<String, Object> insertUser(HashMap<String, String> paramMap) throws Exception{
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		
		//�ѱ� ���� ���� �ذ�
		String userName = paramMap.get("userName");
		log.info("#userName ===== >>>>>"+userName);
		//paramMap.put("userName", new String(userName.getBytes("iso-8859-1"), "utf-8"));
		
		//���� �Ҽ��̸� user_dept�� Head�� ����
		if("Head".equals(paramMap.get("userCode"))){
			paramMap.put("userDept", "Head");
		}
		
		//ȸ�� ������ �� ���� 
		paramMap.put("USER_CREATE_DATE", df.format(now));
		//������ ������ �� 00000000000000���� ����
		paramMap.put("LAST_CONNECT_DATE", "00000000000000");
		
		int result = userDao.insertUser(paramMap);
		
		if(result > 0){
			returnMap.put("resultMsg", "SUCCESS");
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
				
		
		return returnMap;
	}
}
