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
 * ���Ŀ�����
 * Servlet implementation class ActionServlet
 */
@WebServlet("/ActionServlet")
public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Map<String, ActionMapping> map=null;   
    /**
     * ��ʼ������ȡ�����ļ�
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
	 * ������
	 * ������������غͽ��п��ƴ���ͷַ�
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�ض�·��
		System.out.println(map.size());
		String uri=request.getRequestURI();
		String action=uri.substring(uri.lastIndexOf("/")+1, uri.lastIndexOf("."));
		String resutl=null;//���
		System.out.println("��������"+action);
		ActionMapping mapping=map.get(action);
		System.out.println(mapping.getType()+":"+mapping.getName()+":"+mapping.getNameType());
		try{
			String type=mapping.getType();
			if(type!=null){
				Class ActionC=Class.forName(type);
				String nameType=mapping.getNameType();
				ActionForm af=null;
				DynaActionForm dy=new DynaActionForm(ActionC.newInstance());//��̬����
				Action newac=(Action)dy.getInvocation();//ʹ�ö�̬����õ��ӿڣ���ʱʹ�ô���ȥִ��
				resutl=newac.execute(mapping, af, request, response);//����ȥ�������invoke
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		if(resutl!=null)
			request.getRequestDispatcher(resutl).forward(request, response);//����ת��
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
