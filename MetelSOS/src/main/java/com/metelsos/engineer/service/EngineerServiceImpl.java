package com.metelsos.engineer.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.metelsos.common.aes.AesUtil;
import com.metelsos.common.util.MetelSOSUtil;
import com.metelsos.engineer.dao.EngineerDao;
import com.metelsos.engineer.vo.EngineerVo;
/**
 * 
* <pre>
* com.metelsos.engineer.service
*   |_ EngineerServiceImpl.java
* </pre>
* 
* Desc : �����Ͼ� ȸ������ ���� ���� Ŭ����
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 14. ���� 6:10:40
* @Version :
 */
@Service("engineerService")
public class EngineerServiceImpl implements EngineerService{

	@Resource(name="engineerDao")
	private EngineerDao engineerDao;
	
	/**
	 * 
	 * Desc : �Է¹��� ID�� ��й�ȣ�� DB�� ��ȸ�ؼ� ȸ���� ������ ���� �ο��ϰ� �α��� ó��
	 * @Method Name : checkLogin
	 * @param paramMap
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> checkLogin(HashMap<String, String> paramMap, HttpServletRequest request,
			HttpSession session) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		AesUtil aesUtil = new AesUtil();
		String securityPasswd = aesUtil.encrypt(paramMap.get("engineerPasswd"));
		paramMap.put("engineerPasswd", securityPasswd);
		
		EngineerVo engineerVo = engineerDao.checkLogin(paramMap);
		
		if(engineerVo != null){
			session.setAttribute("SESSION_LOGIN_USER_ID", engineerVo.getEngineer_id());
			session.setAttribute("SESSION_LOGIN_USER_NAME", engineerVo.getEngineer_name());
			session.setAttribute("SESSION_LOGIN_USER_TYPE", "engineer");
			returnMap.put("resultMsg", "SUCCESS");
			returnMap.put("engineerName", engineerVo.getEngineer_name());
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : �����Ͼ�ȸ�� ID validation
	 * @Method Name : validateEngineerId
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> validateEngineerId(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		EngineerVo engineerVo = engineerDao.validateEngineerId(paramMap);
		
		if(engineerVo != null){
			returnMap.put("resultMsg", "FAILED");
		}else{
			returnMap.put("resultMsg", "SUCCESS");
		}
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : �����Ͼ� ȸ�� ���� �� ȸ������ ���� ���� ���� DB�� INSERT
	 * @Method Name : insertEngineer
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> insertEngineer(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		
		//���������� set
		paramMap.put("engineerCreateDate", df.format(now));
		
		//���� �н����� ��ȣȭ �� set
		AesUtil aesUtil = new AesUtil();
		String securityPasswd = aesUtil.encrypt(paramMap.get("engineerPasswd"));
		paramMap.put("engineerPasswd", securityPasswd);
		
		int result = engineerDao.insertUser(paramMap);
		
		if(result > 0){
			returnMap.put("resultMsg", "SUCCESS");
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : �����Ͼ� ȸ�� ���̵� ã��
	 * @Method Name : findEngineerId
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> findEngineerId(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		List<EngineerVo> list = engineerDao.findEngineerId(paramMap);
		
		returnMap.put("userType", paramMap.get("userType"));
		returnMap.put("findUserList", list);
		returnMap.put("userCount", list.size());
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : �����Ͼ� ȸ������ �̸��Ϸ� �ӽ� ��й�ȣ ������
	 * @Method Name : sendTempEngineerPasswd
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> sendTempEngineerPasswd(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		//1. ���̵� ���� EngineerVo�� ã�Ƴ�
		EngineerVo vo = engineerDao.findEngineer(paramMap);
		
		//2. ���ϵǴ� EngineerVo�� ������ �ӽ� ��й�ȣ ����, ������ ���� �޽��� put�ؼ� ����
		if(vo != null){
			MetelSOSUtil util = new MetelSOSUtil();
			AesUtil aesUtil = new AesUtil();
			String tempPasswd = util.generateTempPasswd("P", 20);
			
			//3. EngineerVo�� ��й�ȣ�� �ӽ� ��й�ȣ�� �����ؼ� METELSOS_ENGINEER ���̺� UPDATE
			vo.setEngineer_passwd(aesUtil.encrypt(tempPasswd));
			engineerDao.updateEngineerPasswd(vo);
			
			//4. EngineerVo�� �̸��Ϸ� �ӽ� ��й�ȣ ����
			String title = "�ӽ� ��й�ȣ�Դϴ�.";
			StringBuffer content = new StringBuffer();
			content.append("<h1>�ӽ� ��й�ȣ�Դϴ�.</h1><br />");
			content.append("<strong>"+tempPasswd+"</strong>");
			content.append("<br /><br />");
			content.append("�ӽ� ��й�ȣ�� ���� �� ���������ʿ��� ��й�ȣ�� ������ �ֽñ� �ٶ��ϴ�. <br />");
			content.append("from. MetelSOS ADMIN");
			util.sendEmail(vo.getEngineer_email(), title, content.toString());
			returnMap.put("tempPasswd", tempPasswd);
			returnMap.put("resultMsg", "SUCCESS");
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : �����Ͼ�ȸ�� �������� ���� 
	 * @Method Name : updateEngineerInfo
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> updateEngineerInfo(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		AesUtil aesUtil = new AesUtil();
		paramMap.put("engineerPasswd", aesUtil.encrypt(paramMap.get("currPasswd")));
		EngineerVo vo = engineerDao.checkLogin(paramMap);
		
		if(vo != null){
			String changePwd = aesUtil.encrypt(paramMap.get("newPasswd"));
			vo.setEngineer_passwd(changePwd);
			vo.setEngineer_dept(paramMap.get("engineerDept"));
			vo.setEngineer_email(paramMap.get("engineerEmail"));
			vo.setEngineer_phone(paramMap.get("engineerPhone"));
			
			engineerDao.updateEngineerInfo(vo);
			returnMap.put("resultMsg", "SUCCESS");
			returnMap.put("userType", "engineer");
			returnMap.put("userId", vo.getEngineer_id());
		}else{
			returnMap.put("resultMsg", "FAILED");
			returnMap.put("errMsg", "���� ��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� Ȯ�����ּ���.");
		}
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : �����Ͼ� ȸ�� Ż�� �� �ش� ȸ���� ���� DB���� DELETE
	 * @Method Name : deleteEngineerAccount
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> deleteEngineerAccount(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		AesUtil aesUtil = new AesUtil();
		paramMap.put("userPasswd", aesUtil.encrypt(paramMap.get("inputPasswd")));
		
		int result = engineerDao.deleteEngineerAccount(paramMap);
		
		if(result > 0){
			returnMap.put("resultMsg", "SUCCESS");
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}

}
