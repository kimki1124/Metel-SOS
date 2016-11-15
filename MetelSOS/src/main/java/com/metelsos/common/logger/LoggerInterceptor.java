package com.metelsos.common.logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
* <pre>
* com.metelsos.common.logger
*   |_ LoggerInterceptor.java
* </pre>
* 
* Desc : ���ͼ��� Ŭ����, ����Ʈ���� ���� ȣ�� �� ���� üũ ���� ����
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 14. ���� 3:03:10
* @Version :
 */
public class LoggerInterceptor extends HandlerInterceptorAdapter{
	protected Log log = LogFactory.getLog(LoggerInterceptor.class);

	/**
	 * ��ó����. ��Ʈ�ѷ��� ȣ��Ǳ� ���� �����
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("====================== START ======================");
		log.info(" Request URI \t:     "+ request.getRequestURI());
		if("/metelSOS/login.do".equals(request.getRequestURI()) || "/metelSOS/engineerRegister.do".equals(request.getRequestURI())
				|| "/metelSOS/engineer/validateEngineerId.do".equals(request.getRequestURI()) || "/metelSOS/setItemForRegisterForm.do".equals(request.getRequestURI())
				|| "/metelSOS/customerValidateId.do".equals(request.getRequestURI()) || "/metelSOS/customerRegister.do".equals(request.getRequestURI())
				|| "/metelSOS/findId.do".equals(request.getRequestURI()) || "/metelSOS/findPasswdPageMove.do".equals(request.getRequestURI())
				|| "/metelSOS/sendTempPasswd.do".equals(request.getRequestURI())){
			//�� ������ URI�� ���� üũ Ȯ���� �� �ʿ䰡 �����Ƿ� �ٷ� true ���� 
			return true;
		}
		
		
		try{	
			if(request.getSession().getAttribute("SESSION_LOGIN_USER_ID") == null){
				//�α������� ���� ��� �α��� �������� �̵�
				response.sendRedirect("/metelSOS/login.jsp");
				return false;
			}
		}catch(Exception e){
			log.error(e,e);
		}
		return super.preHandle(request, response, handler);
	}

	/**
	 * ��ó����. ��Ʈ�ѷ����� Ŭ���̾�Ʈ�� �����ϱ� ���� �����
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception { 
		log.info("====================== END ======================");
	}
	
	
}
