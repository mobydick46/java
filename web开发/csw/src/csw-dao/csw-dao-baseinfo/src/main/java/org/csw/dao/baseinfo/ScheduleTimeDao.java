package org.csw.dao.baseinfo;
import org.csw.entity.baseinfo.ScheduleTime;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScheduleTimeDao{
	public Boolean AddScheduleTime(ScheduleTime record){
		Boolean isOK = false;
		//建立document对象
        //Document doc = DocumentHelper.createDocument();
        //建立XML文档的根config
        //Element config = doc.addElement("config");
        try {
        	SAXReader reader = new SAXReader();
            Document doc = reader.read(new File("config.xml"));
            List<?> list = doc.selectNodes("/config");
            Element config = (Element)list.get(0);
            Element ScheduleTimes=config.element("ScheduleTimes");
            if(null == ScheduleTimes){
            	ScheduleTimes = config.addElement("ScheduleTimes");
            }
            Element ScheduleTime = ScheduleTimes.addElement("ScheduleTime");//加入ScheduleTime节点
        	
        	if(record == null ){
        		System.out.println("Dao层函数AddScheduleTime参数错误！"+"record:"+record);
        	}
        	ScheduleTime.addAttribute("id", record.getId()+"");
        	ScheduleTime.addAttribute("timeNumber",record.getTimeNumber());
            ScheduleTime.addAttribute("timeUnit",record.getTimeUnit()+"");
            ScheduleTime.addAttribute("defaultNumber",record.getDefaultNumber());
            ScheduleTime.addAttribute("isDefault",record.getIsDefault()+"");
        	ScheduleTime.addAttribute("functionId",record.getFunctionId());
            
        	OutputFormat outputFormat = OutputFormat.createPrettyPrint();  
            outputFormat.setLineSeparator("\r\n");
            XMLWriter writer = new XMLWriter(outputFormat);
            FileOutputStream fos = new FileOutputStream("config.xml");
            writer.setOutputStream(fos);
            writer.write(doc);
            writer.close();
            isOK = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isOK;
	}
	
	public List<ScheduleTime> GetScheduleTime(Map<String,Object> map){
		
		List<ScheduleTime> listScheduleTime = new ArrayList<ScheduleTime>();
		String s = "/config/ScheduleTimes/ScheduleTime";
		String str = "/config/ScheduleTimes/ScheduleTime";
		String[] field = {"id","timeNumber","timeUnit","defaultNumber","isDefault","functionId"};
		try {
			for(String e:field){
				if(!(null == map.get(e)||"".equals(map.get(e)))){
					if(s.equals(str)){
						s = s+"[@"+e+"='"+map.get(e)+"'";
					}else{
						s = s+"and @"+e+"='"+map.get(e)+"'";
					}
				}
				
			}
			if(!s.equals(str)){
				s=s+"]";
			}
			SAXReader reader = new SAXReader();
	        Document doc = reader.read(new File("config.xml"));
	        System.out.println(s);
			List<?> list = doc.selectNodes(s);
			System.out.println(list);
			for(Object o:list){
				Element e = (Element)o;
				System.out.println(e);
				ScheduleTime ScheduleTime = new ScheduleTime();
				ScheduleTime.setId(e.attributeValue("id"));
				ScheduleTime.setTimeNumber(e.attributeValue("timeNumber"));
				ScheduleTime.setTimeUnit(e.attributeValue("timeUnit"));
				ScheduleTime.setDefaultNumber(e.attributeValue("defaultNumber"));
				ScheduleTime.setIsDefault(e.attributeValue("isDefault"));
				ScheduleTime.setFunctionId(e.attributeValue("functionId"));
				listScheduleTime.add(ScheduleTime);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listScheduleTime;
	}
	
	public String GetMaxID(){
		String id = "";
		String s = "/config/ScheduleTimes/ScheduleTime/@id[not(/config/ScheduleTimes/ScheduleTime/@id>.)]";
		try {
			SAXReader reader = new SAXReader();
	        Document doc = reader.read(new File("config.xml"));
			Node node = doc.selectSingleNode(s);
			if(null!=node){
				id = node.getStringValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
    
	public Boolean DeleteScheduleTime(String id){
		Boolean isOK = false;
		String s = "/config/ScheduleTimes/ScheduleTime[@id='"+id+"']";
		try {
			SAXReader reader = new SAXReader();
	        Document doc = reader.read(new File("config.xml"));
	        Node node = doc.selectSingleNode(s);
	        node.getParent().remove(node);
	        System.out.println(node);
	        
	        OutputFormat format = OutputFormat.createPrettyPrint();
	        XMLWriter writer = new XMLWriter(format);
            FileOutputStream fos = new FileOutputStream("config.xml");
            writer.setOutputStream(fos);
            writer.write(doc);
            writer.close();
        	isOK = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isOK;
	}
	
	public Boolean UpdateScheduleTime(ScheduleTime ScheduleTime){
		Boolean isOK = false;
		String s = "/config/ScheduleTimes/ScheduleTime[@id='"+ScheduleTime.getId()+"']";
		try {
			SAXReader reader = new SAXReader();
	        Document doc = reader.read(new File("config.xml"));
	        List<?> list = doc.selectNodes(s);
			Element e = (Element)list.get(0);
			System.out.println(e);
			Attribute atrr = e.attribute("id");
			if(null != ScheduleTime.getId() && !"".equals(ScheduleTime.getId())){
				atrr = e.attribute("id");
				atrr.setValue(ScheduleTime.getId());
			}
			if(null != ScheduleTime.getTimeNumber()){
				atrr = e.attribute("timeNumber");
				atrr.setValue(String.valueOf(ScheduleTime.getTimeNumber()));
			}
			if(null != ScheduleTime.getTimeUnit()){
				atrr = e.attribute("timeUnit");
				atrr.setValue(String.valueOf(ScheduleTime.getTimeUnit()));
			}
			if(null != ScheduleTime.getDefaultNumber()){
				atrr = e.attribute("defaultNumber");
				atrr.setValue(ScheduleTime.getDefaultNumber());
			}
			if(null != ScheduleTime.getIsDefault()){
				atrr = e.attribute("isDefault");
				atrr.setValue(ScheduleTime.getIsDefault());
			}
			if(null != ScheduleTime.getFunctionId()){
				atrr = e.attribute("functionId");
				atrr.setValue(ScheduleTime.getFunctionId());
			}
	        
	        XMLWriter writer = new XMLWriter();
            FileOutputStream fos = new FileOutputStream("config.xml");
            writer.setOutputStream(fos);
            writer.write(doc);
            writer.close();
        	isOK = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isOK;
	}
}
