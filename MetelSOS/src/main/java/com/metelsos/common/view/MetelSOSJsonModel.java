package com.metelsos.common.view;

import org.springframework.web.servlet.ModelAndView;
/**
 * 
* <pre>
* com.metelsos.common.view
*   |_ MetelSOSJsonModel.java
* </pre>
* 
* Desc : jsonView�� ����ϱ� ���� Model Ŭ����
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 14. ���� 3:21:45
* @Version :
 */
public class MetelSOSJsonModel extends ModelAndView{
	public MetelSOSJsonModel(Object modelObject){
		super("jsonView", "AJAX_MODEL", modelObject);
	}
}
