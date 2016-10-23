package com.metelsos.common.logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggerInterceptor extends HandlerInterceptorAdapter{
	protected Log log = LogFactory.getLog(LoggerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// ��ó����. ��Ʈ�ѷ��� ȣ��Ǳ� ���� �����
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

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//  ��ó����. ��Ʈ�ѷ����� Ŭ���̾�Ʈ�� �����ϱ� ���� �����
		log.info("====================== END ======================");
	}
	
	
}
