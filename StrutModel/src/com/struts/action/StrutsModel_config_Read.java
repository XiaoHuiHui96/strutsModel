package com.struts.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.struts.entity.ActionMapping;

/**
 * ∂¡»°Struts≈‰÷√
 * 
 * @author Administrator
 *
 */
public class StrutsModel_config_Read {
	
	private static StrutsModel_config_Read read=null;
	private static Map<String, ActionMapping> map=new HashMap<String, ActionMapping>();
	private StrutsModel_config_Read(){
		SAXBuilder sax=new SAXBuilder();
		Document doc=null;
		try {
			doc=sax.build(this.getClass().getClassLoader().getResourceAsStream("StrutsModel-config.xml"));
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element doce=doc.getRootElement();
		List<Element> list=doce.getChild("form-beans").getChildren("form-bean");
		Map<String, String> m=new HashMap<String, String>();
		for(Element e:list){
			String name=e.getAttributeValue("name");
	    	   String type=e.getAttributeValue("type");
	    	   m.put(name, type);
		}
		List<Element> actionlist=doce.getChild("action-mappings").getChildren("action");
		for(Element e:actionlist){
			String path=e.getAttributeValue("path");
			String type=e.getAttributeValue("type");
			String name=e.getAttributeValue("name");
			String scope=e.getAttributeValue("scope");
			List<Element> listforward=e.getChildren("forward");
			String successValue = null,errorValue = null;
			for(Element efor:listforward){
				if(efor.getAttributeValue("name").equals("success")){
					successValue=efor.getAttributeValue("path");
				}else if(efor.getAttributeValue("name").equals("error")){
					errorValue=efor.getAttributeValue("path");
				}
			}
			String formtype=m.get(name.toString());
			if(name==null){
				ActionMapping stCon=new ActionMapping(path, type, successValue, errorValue);
				map.put(path, stCon);
			}else{
				ActionMapping stCon=new ActionMapping(path, type, name, formtype, successValue, errorValue);
				map.put(path, stCon);
			}
		}
	}
	public static StrutsModel_config_Read getStrutsModel_config_Read(){
		if(read==null){
			read=new StrutsModel_config_Read();
		}
		return read;
	}
	public static Map<String, ActionMapping> getMap(){
		return map;
	}
}
