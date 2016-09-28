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
 * 代理类，用于处理请求的参数 把参数填充到anctionform里 通过反射去拿到该类的详细数据在赋值 返回
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
		System.out.println("动态代理开始");
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
		
		Object result = arg1.invoke(obj, arg2);// 这是执行刚才的execute
		return result;
	}
}

