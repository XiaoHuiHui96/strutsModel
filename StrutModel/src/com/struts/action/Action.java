package com.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.struts.entity.ActionMapping;
/**
 * 业务类的接口
 * @author Administrator
 *
 */
public interface Action {
	
	public String execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response);
}
