package com.struts.SumAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.struts.action.Action;
import com.struts.action.ActionForm;
import com.struts.actionform.LoginActionForm;
import com.struts.entity.ActionMapping;

public class logAction implements Action{
	@Override
	public String execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("Âß¼­Àà");
		LoginActionForm laf=(LoginActionForm)form;  
        String username=laf.getUsername();  
        String password=laf.getPassword();
        request.getSession().setAttribute("name", username);
        request.getSession().setAttribute("pwd", password);
		return mapping.getSuccess();
	}

}
