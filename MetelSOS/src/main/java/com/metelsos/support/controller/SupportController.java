package com.metelsos.support.controller;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.metelsos.common.view.MetelSOSJsonModel;
import com.metelsos.menu.service.MenuService;
import com.metelsos.support.service.SupportService;
import com.metelsos.support.vo.FileVo;
/**
 * 
* <pre>
* com.metelsos.support.controller
*   |_ SupportController.java
* </pre>
* 
* Desc : �� ���� ��û �� ���� ���� ��Ʈ�ѷ� Ŭ����
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 15. ���� 11:21:19
* @Version :
 */
@Controller
public class SupportController {
	private Log log = LogFactory.getLog(SupportController.class);
	
	@Resource(name="supportService")
	private SupportService supportService;
	
	@Resource(name="menuService")
	private MenuService menuService;
	/**
	 * 
	 * Desc : ������� ���� ������û ����Ʈ ������
	 * @Method Name : getWaitSupportList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getWaitSupportList.do")
	public ModelAndView getWaitSupportList(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => getWaitSupportList");
		MetelSOSJsonModel jsonModel = null;
		paramMap.put("support_state", "�������");
		HashMap<String, Object> returnMap = supportService.getCusSupportList(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		return jsonModel;
	}
	/**
	 * 
	 * Desc : �����Ϸ� ������ ������û ����Ʈ ������
	 * @Method Name : getCusAcceptSupportList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getCusAcceptSupportList.do")
	public ModelAndView getCusAcceptSupportList(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => getCusAcceptSupportList");
		MetelSOSJsonModel jsonModel = null;
		paramMap.put("support_state", "�����Ϸ�");
		HashMap<String, Object> returnMap = supportService.getCusSupportList(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		return jsonModel;
	}
	/**
	 * 
	 * Desc : ���� �� ������ ������û ����Ʈ ��������
	 * @Method Name : getCusSupportingList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getCusSupportingList.do")
	public ModelAndView getCusSupportingList(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => getCusSupportingList");
		MetelSOSJsonModel jsonModel = null;
		paramMap.put("support_state", "���� ��");
		HashMap<String, Object> returnMap = supportService.getCusSupportList(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		return jsonModel;
	}
	/**
	 * 
	 * Desc : ���� �Ϸ� ������ ������û ����Ʈ ��������
	 * @Method Name : getCusCompleteSupportList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getCusCompleteSupportList.do")
	public ModelAndView getCusCompleteSupportList(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => getCusCompleteSupportList");
		MetelSOSJsonModel jsonModel = null;
		paramMap.put("support_state", "���� �Ϸ�");
		HashMap<String, Object> returnMap = supportService.getCusSupportList(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		return jsonModel;
	}
	/**
	 * 
	 * Desc : �����κ��� ���� ���� ��û ���� INSERT
	 * @Method Name : insertSupportRequest
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/insertSupportRequest.do")
	public ModelAndView insertSupportRequest(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => insertSupportRequest");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = supportService.insertSupportRequest(paramMap);
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	/**
	 * 
	 * Desc : ������û �� ÷������ ���ε�
	 * @Method Name : uploadSupportFile
	 * @param paramMap
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/uploadSupportFile.do")
	public ModelAndView uploadSupportFile(@RequestParam HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception{
		log.info("#operation => uploadNoticeFile");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = supportService.uploadSupportFile(paramMap, request);
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	/**
	 * 
	 * Desc : (��)���� ��û ���������� �̵�
	 * @Method Name : moveSupportDetailPage
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/moveSupportDetailPage.do")
	public ModelAndView moveSupportDetailPage(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => moveSupportDetailPage");
		ModelAndView model = new ModelAndView();
		paramMap.put("userType", "customer");
		paramMap.put("menuTitle", "���� �����丮");
		paramMap.put("menuIcon", "fa fa-lg fa-fw fa-history");
		HashMap<String, Object> returnMap = menuService.getMainPanelItems(paramMap);
		supportService.setSupportDetailPageItems(returnMap, paramMap);
		
		model.addAllObjects(returnMap);
		model.setViewName("/customer/support/RequestSupportDetail");
		return model;
	}
	/**
	 * 
	 * Desc : ������û �ۿ� ÷�ε� ���� �ٿ�ε�
	 * @Method Name : downloadSupportFile
	 * @param paramMap
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/downloadSupportFile.do")
	public void downloadSupportFile(@RequestParam HashMap<String, Object> paramMap, HttpServletResponse response) throws Exception{
		log.info("#operation => downloadSupportFile");
		HashMap<String, Object> returnMap = supportService.selectSupportFileInfo(paramMap);
		FileVo vo = (FileVo)returnMap.get("fileVo");
		
		String storedFileName = vo.getStored_file_name();
		String originalFileName = vo.getOriginal_file_name();
		
		byte fileByte[] = FileUtils.readFileToByteArray(new File("C:\\dev\\file\\support\\"+storedFileName));
		
		response.setContentType("application/octet-stream");
	    response.setContentLength(fileByte.length);
	    response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(originalFileName,"UTF-8")+"\";");
	    response.setHeader("Content-Transfer-Encoding", "binary");
	    response.getOutputStream().write(fileByte);
	     
	    response.getOutputStream().flush();
	    response.getOutputStream().close();
	}
	/**
	 * 
	 * Desc : ���� ��û ���� �������� �ʿ��� ������ SET�ؼ� ��� �ѱ�
	 * @Method Name : moveSupportUpdatePage
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/moveSupportUpdatePage.do")
	public ModelAndView moveSupportUpdatePage(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => moveSupportUpdatePage ");
		ModelAndView model = new ModelAndView();
		paramMap.put("menuTitle", URLDecoder.decode(paramMap.get("menuTitle"), "UTF-8"));
		
		HashMap<String, Object> returnMap = menuService.getMainPanelItems(paramMap);
		supportService.setSupportDetailPageItems(returnMap, paramMap);
		
		model.addAllObjects(returnMap);
		model.setViewName("/customer/support/RequestSupportUpdateForm");
		
		return model;
	}
	/**
	 * 
	 * Desc : ������û �ۿ� ÷���� ���� ����
	 * @Method Name : deleteSupportFile
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteSupportFile.do")
	public ModelAndView deleteSupportFile(@RequestParam HashMap<String, Object> paramMap) throws Exception{
		log.info("#operation => deleteSupportFile");
		MetelSOSJsonModel jsonModel = null;
		
		HashMap<String, Object> returnMap = supportService.deleteSupportFile(paramMap);
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	/**
	 * 
	 * Desc : ������û �� ����
	 * @Method Name : updateSupportRequest
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateSupportRequest.do")
	public ModelAndView updateSupportRequest(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => updateSupportRequest");
		MetelSOSJsonModel jsonModel = null;
		
		HashMap<String, Object> returnMap = supportService.updateSupportRequest(paramMap);
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	/**
	 * 
	 * Desc : ���� ��û �� ����
	 * @Method Name : deleteSupportHistory
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteSupportHistory.do")
	public ModelAndView deleteSupportHistory(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => deleteSupportHistory");
		MetelSOSJsonModel jsonModel = null;
		
		HashMap<String, Object> returnMap = supportService.deleteSupportHistory(paramMap);
		jsonModel = new MetelSOSJsonModel(returnMap);
		return jsonModel;
	}
	/**
	 * 
	 * Desc : ���� ��� ������ ���� ��û �� ����Ʈ ��������
	 * @Method Name : getUnSupportList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getUnSupportList.do")
	public ModelAndView getUnSupportList(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => getUnSupportList");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = supportService.getUnSupportList(paramMap);
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	/**
	 * 
	 * Desc : ���� ��� ������ �� �󼼺��� �������� �ʿ��� ������ SET�ؼ� ��� �ѱ� 
	 * @Method Name : moveUnsupportDetailPage
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/moveUnsupportDetailPage.do")
	public ModelAndView moveUnsupportDetailPage(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => moveUnsupportDetailPage");
		ModelAndView model = new ModelAndView();
		HashMap<String, Object> returnMap = menuService.getMainPanelItems(paramMap);
		supportService.setUnsupportDetailItems(returnMap, paramMap);
		
		model.addAllObjects(returnMap);
		model.setViewName("/engineer/support/UnsupportDetail");
		return model;
	}
	/**
	 * 
	 * Desc : ���� ��� ������ ���� ��û�� ����
	 * @Method Name : acceptSupport
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/acceptSupport.do")
	public ModelAndView acceptSupport(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => acceptSupport");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = supportService.acceptSupport(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		return jsonModel;
	}
	/**
	 * 
	 * Desc : �����Ϸ� ������ ���� ��û ����Ʈ ������ 
	 * @Method Name : getAcceptSupportList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getAcceptSupportList.do")
	public ModelAndView getAcceptSupportList(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => getAcceptSupportList");
		MetelSOSJsonModel jsonModel = null;
		paramMap.put("support_state", "�����Ϸ�");
		HashMap<String, Object> returnMap = supportService.getAcceptSupportList(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	/**
	 * 
	 * Desc : ���� �� ������ ���� ��û ����Ʈ ������
	 * @Method Name : getSupportingList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getSupportingList.do")
	public ModelAndView getSupportingList(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => getSupportingList");
		MetelSOSJsonModel jsonModel = null;
		paramMap.put("support_state", "���� ��");
		HashMap<String, Object> returnMap = supportService.getAcceptSupportList(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	/**
	 * 
	 * Desc : ���� �Ϸ� ������ ���� ��û ����Ʈ ������
	 * @Method Name : getCompleteSupportList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getCompleteSupportList.do")
	public ModelAndView getCompleteSupportList(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => getCompleteSupportList");
		MetelSOSJsonModel jsonModel = null;
		paramMap.put("support_state", "���� �Ϸ�");
		HashMap<String, Object> returnMap = supportService.getAcceptSupportList(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		
		return jsonModel;
	}
	/**
	 * 
	 * Desc : �����Ͼ� ȸ���� �����Ϸ�� ���� ��û �� �󼼺��� �� �� ������ SET
	 * @Method Name : mvEngAcptSprtDtlPage
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/mvEngAcptSprtDtlPage.do")
	public ModelAndView mvEngAcptSprtDtlPage(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => mvEngAcptSprtDtlPage");
		ModelAndView model = new ModelAndView();
		
		paramMap.put("userType", "engineer");
		paramMap.put("menuTitle", "SupportList");
		paramMap.put("menuIcon", "fa fa-lg fa-fw fa-pencil-square-o");
		HashMap<String, Object> returnMap = menuService.getMainPanelItems(paramMap);
		supportService.setUpdateSupportingFormItems(returnMap, paramMap);
		
		model.addAllObjects(returnMap);
		model.setViewName("/engineer/support/UpdateAcceptSupportForm");
		return model;
	}
	/**
	 * 
	 * Desc : ���� ���� ������Ʈ
	 * @Method Name : updateSupportState
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateSupportState.do")
	public ModelAndView updateSupportState(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => updateSupportState");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = supportService.updateSupportState(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		return jsonModel;
	}
	/**
	 * 
	 * Desc : ���� ���� ���� ��û �� �󼼺���
	 * @Method Name : mvEngSprtngDtlPage
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/mvEngSprtngDtlPage.do")
	public ModelAndView mvEngSprtngDtlPage(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => mvEngSprtngDtlPage");
		ModelAndView model = new ModelAndView();
		
		paramMap.put("userType", "engineer");
		paramMap.put("menuTitle", "SupportList");
		paramMap.put("menuIcon", "fa fa-lg fa-fw fa-pencil-square-o");
		HashMap<String, Object> returnMap = menuService.getMainPanelItems(paramMap);
		supportService.setUpdateCompleteSupportFormItems(returnMap, paramMap);
		
		model.addAllObjects(returnMap);
		model.setViewName("/engineer/support/UpdateSupportingForm");
		return model;
	}
	/**
	 * 
	 * Desc : ���� �� ������ ���� ��û �� ������Ʈ
	 * @Method Name : updateSupportingState
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateSupportingState.do")
	public ModelAndView updateSupportingState(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => updateSupportingState");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = supportService.updateSupportingState(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		return jsonModel;
	}
	/**
	 * 
	 * Desc : ���� �Ϸ� ������ ���� ��û �� �󼼺���
	 * @Method Name : getCompleteSupDetail
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getCompleteSupDetail.do")
	public ModelAndView getCompleteSupDetail(@RequestParam HashMap<String, String> paramMap) throws Exception{
		log.info("#operation => getCompleteSupDetail");
		MetelSOSJsonModel jsonModel = null;
		HashMap<String, Object> returnMap = supportService.getCompleteSupDetail(paramMap);
		
		jsonModel = new MetelSOSJsonModel(returnMap);
		return jsonModel;
	}
}
