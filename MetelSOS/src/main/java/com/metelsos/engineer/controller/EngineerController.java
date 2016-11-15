package com.metelsos.engineer.controller;

import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.metelsos.common.view.MetelSOSJsonModel;
import com.metelsos.engineer.service.EngineerService;
/**
 * 
* <pre>
* com.metelsos.engineer.controller
*   |_ EngineerController.java
* </pre>
* 
* Desc : �����Ͼ� ȸ�� ���� ��Ʈ�ѷ� Ŭ����
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 14. ���� 6:01:25
* @Version :
 */
@Controller
public class EngineerController {
	private Log log = LogFactory.getLog(EngineerController.class);
	
	@Resource(name="engineerService")
	private EngineerService engineerService;
	
	/**
	 * 
	 * Desc : �����Ͼ�ȸ�� ID validation üũ
	 * @Method Name : validateEngineerId
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/engineer/validateEngineerId.do")
	public ModelAndView validateEngineerId(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => validateEngineerId");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = engineerService.validateEngineerId(paramMap);
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	/**
	 * 
	 * Desc : �����Ͼ� ȸ������ �� ȸ������ ���� �ۼ��� ���� INSERT
	 * @Method Name : registerEngineer
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/engineerRegister.do")
	public ModelAndView registerEngineer(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => registerEngineer");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = engineerService.insertEngineer(paramMap);
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	/**
	 * 
	 * Desc : �����Ͼ�ȸ�� ������ ���� �� UPDATE
	 * @Method Name : updateEngineerInfo
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateEngineerInfo.do")
	public ModelAndView updateEngineerInfo(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => updateEngineerInfo");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = engineerService.updateEngineerInfo(paramMap);
		jsonModel = new MetelSOSJsonModel(returnMap);
		return jsonModel;
	}
}
