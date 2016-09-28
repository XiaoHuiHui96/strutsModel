package com.struts.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.struts.entity.ActionMapping;

/**
 * 核心控制器
 * Servlet implementation class ActionServlet
 */
@WebServlet("/ActionServlet")
public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Map<String, ActionMapping> map=null;   
    /**
     * 初始化，读取配置文件
     * @see HttpServlet#HttpServlet()
     */
    public ActionServlet() {
        super();
        if(map==null){
        	map=StrutsModel_config_Read.getStrutsModel_config_Read().getMap();
        }
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * 控制器
	 * 对请求进行拦截和进行控制处理和分发
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//截断路径
		System.out.println(map.size());
		String uri=request.getRequestURI();
		String action=uri.substring(uri.lastIndexOf("/")+1, uri.lastIndexOf("."));
		String resutl=null;//结果
		System.out.println("控制器："+action);
		ActionMapping mapping=map.get(action);
		System.out.println(mapping.getType()+":"+mapping.getName()+":"+mapping.getNameType());
		try{
			String type=mapping.getType();
			if(type!=null){
				Class ActionC=Class.forName(type);
				String nameType=mapping.getNameType();
				ActionForm af=null;
				DynaActionForm dy=new DynaActionForm(ActionC.newInstance());//动态代理
				Action newac=(Action)dy.getInvocation();//使用动态代理得到接口，在时使用代理去执行
				resutl=newac.execute(mapping, af, request, response);//会先去代理里的invoke
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		if(resutl!=null)
			request.getRequestDispatcher(resutl).forward(request, response);//请求转发
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
