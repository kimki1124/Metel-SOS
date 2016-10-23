package com.metelsos.user.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.metelsos.common.view.MetelSOSJsonModel;
import com.metelsos.customer.service.CustomerService;
import com.metelsos.customer.vo.CustomerVo;
import com.metelsos.engineer.service.EngineerService;
import com.metelsos.user.service.UserService;

@Controller
public class UserController {
	private Log log = LogFactory.getLog(UserController.class);
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="customerService")
	private CustomerService customerService;
	
	@Resource(name="engineerService")
	private EngineerService engineerService;
	
	@RequestMapping(value="/login.do")
	public ModelAndView loginUser(@RequestParam HashMap<String, String> paramMap, HttpServletRequest request, HttpSession session) throws Exception{
		log.info("#operation => loginUser");
		//ModelAndView modelAndView = new ModelAndView();
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		if("Engineer".equals(paramMap.get("userType"))){
			//�����Ͼ �α��� �ϴ� ���
			returnMap = engineerService.checkLogin(paramMap, request, session);
		}else{
			//���� �α��� �ϴ� ���
			returnMap = customerService.checkLogin(paramMap, request, session);
		}
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	
	@RequestMapping(value="/logout.do")
	public ModelAndView logoutUser(HttpServletRequest request, HttpSession session) throws Exception{
		log.info("#operation => logout");
		ModelAndView modelAndView = new ModelAndView();
		request.getSession().invalidate();
		modelAndView.setViewName("redirect:login.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/findId.do")
	public ModelAndView findIdUser(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => findIdUser");
		//MetelSOSJsonModel jsonModel = null;
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		paramMap.put("userName", URLDecoder.decode(paramMap.get("userName"), "UTF-8"));
		paramMap.put("userEmail", URLDecoder.decode(paramMap.get("userEmail"), "UTF-8"));
		
		if("Engineer".equals(paramMap.get("userType"))){
			//���� �����Ͼ� ���̵� ã�� ���
			returnMap = engineerService.findEngineerId(paramMap);
		}else{
			//���� �� ���̵� ã�� ���
			returnMap = customerService.findCustomerId(paramMap);
		}
		
		//jsonModel = new MetelSOSJsonModel(returnMap);
		modelAndView.addAllObjects(returnMap);
		modelAndView.setViewName("/find/FindIdResult");
		return modelAndView;
	}
	
	@RequestMapping(value="/findPasswdPageMove.do")
	public ModelAndView moveFindPasswdPage(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => moveFindPasswdPage");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addAllObjects(paramMap);
		modelAndView.setViewName("/find/FindPasswd");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/sendTempPasswd.do")
	public ModelAndView sendTempUserPasswd(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => sendTempUserPasswd");
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		if("Engineer".equals(paramMap.get("userType"))){
			//���� �����Ͼ� ��й�ȣ ã�� ���
			returnMap = engineerService.sendTempEngineerPasswd(paramMap);
		}else{
			//���� �� ��й�ȣ ã�� ���
			returnMap = customerService.sendTempCustomerPasswd(paramMap);
		}
		
		modelAndView.addAllObjects(returnMap);
		modelAndView.setViewName("/find/FindPasswdResult");
		
		return modelAndView;
	}
}

