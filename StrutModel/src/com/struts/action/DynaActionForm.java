package com.struts.action;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.struts.actionform.LoginActionForm;
import com.struts.entity.ActionMapping;

/**
 * �����࣬���ڴ�������Ĳ��� �Ѳ�����䵽anctionform�� ͨ������ȥ�õ��������ϸ�����ڸ�ֵ ����
 * 
 * @author Administrator
 *
 */
public class DynaActionForm implements InvocationHandler {
	private 
	
	private Object obj;

	public DynaActionForm(Object obj) {
		this.obj = obj;
	}

	public Object getInvocation() {
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {
		System.out.println("��̬����ʼ");
		ActionMapping mapping = (ActionMapping) arg2[0];
		String nameType = mapping.getNameType();
		System.out.println(nameType);
		if (nameType != null) {
			Class typeClass = Class.forName(nameType);
			Object actionForm = typeClass.newInstance();
			HttpServletRequest request = (HttpServletRequest) arg2[2];
			HashMap map = new HashMap();
			Enumeration names = request.getParameterNames();
			while (names.hasMoreElements()) {
				String name = (String) names.nextElement();
				System.out.println(name);
				System.out.println(request.getParameterValues(name).toString());
				map.put(name, request.getParameterValues(name));
			}
			BeanUtils.populate(actionForm, map);
		arg2[1]=actionForm;
		}
		
		Object result = arg1.invoke(obj, arg2);// ����ִ�иղŵ�execute
		return result;
	}
}

