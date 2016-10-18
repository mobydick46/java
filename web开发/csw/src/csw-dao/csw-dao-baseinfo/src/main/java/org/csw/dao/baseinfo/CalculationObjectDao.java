package org.csw.dao.baseinfo;
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
import org.csw.entity.baseinfo.CalculationObject;

public class CalculationObjectDao{
	public Boolean AddCalculationObject(CalculationObject record){
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
            Element CalculationObjects=config.element("CalculationObjects");
            if(null == CalculationObjects){
            	CalculationObjects = config.addElement("CalculationObjects");
            }
            Element CalculationObject = CalculationObjects.addElement("CalculationObject");//加入CalculationObject节点
        	
        	if(record == null ){
        		System.out.println("Dao层函数AddCalculationObject参数错误！"+"record:"+record);
        	}
        	CalculationObject.addAttribute("id", record.getId()+"");
        	CalculationObject.addAttribute("objectCode",record.getObjectCode());
            CalculationObject.addAttribute("objectName",record.getObjectName()+"");
            CalculationObject.addAttribute("isSet",record.getIsSet());
            CalculationObject.addAttribute("nodeType",record.getNodeType()+"");
            CalculationObject.addAttribute("parentCode",record.getParentCode());
            CalculationObject.addAttribute("parentName",record.getParentName()+"");
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
	
	public List<CalculationObject> GetCalculationObject(Map<String,Object> map){
		
		List<CalculationObject> listCalculationObject = new ArrayList<CalculationObject>();
		String s = "/config/CalculationObjects/CalculationObject";
		String str = "/config/CalculationObjects/CalculationObject";
		String[] field = {"id","obiectCode","objectName","isSet","nodeType","parentCode","parentName"};
		try {
			for(String e:field){
				if(!(null == map.get(e))){
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
				CalculationObject CalculationObject = new CalculationObject();
				CalculationObject.setId(e.attributeValue("id"));
				CalculationObject.setObjectCode(e.attributeValue("objectCode"));
				CalculationObject.setObjectName(e.attributeValue("objectName"));
				CalculationObject.setIsSet(e.attributeValue("isSet"));
				CalculationObject.setNodeType(e.attributeValue("nodeType"));
				CalculationObject.setParentCode(e.attributeValue("parentCode"));
				CalculationObject.setParentName(e.attributeValue("parentName"));
				listCalculationObject.add(CalculationObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listCalculationObject;
	}
	
	public String GetMaxID(){
		String id = "";
		String s = "/config/CalculationObjects/CalculationObject/@id[not(/config/CalculationObjects/CalculationObject/@id>.)]";
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
    
	public Boolean DeleteCalculationObject(String id){
		Boolean isOK = false;
		String s = "/config/CalculationObjects/CalculationObject[@id='"+id+"']";
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
	
	public Boolean UpdateCalculationObject(CalculationObject CalculationObject){
		Boolean isOK = false;
		String s = "/config/CalculationObjects/CalculationObject[@id='"+CalculationObject.getId()+"']";
		try {
			SAXReader reader = new SAXReader();
	        Document doc = reader.read(new File("config.xml"));
	        List<?> list = doc.selectNodes(s);
			Element e = (Element)list.get(0);
			System.out.println(e);
			Attribute atrr = e.attribute("id");
			if(null != CalculationObject.getId() && !"".equals(CalculationObject.getId())){
				atrr = e.attribute("id");
				atrr.setValue(CalculationObject.getId());
			}
			if(null != CalculationObject.getObjectCode()){
				atrr = e.attribute("objectCode");
				atrr.setValue(CalculationObject.getObjectCode());
			}
			if(null != CalculationObject.getObjectName()){
				atrr = e.attribute("objectName");
				atrr.setValue(CalculationObject.getObjectName());
			}
			if(null != CalculationObject.getIsSet()){
				atrr = e.attribute("isSet");
				atrr.setValue(CalculationObject.getIsSet());
			}
			if(null != CalculationObject.getNodeType()){
				atrr = e.attribute("nodeType");
				atrr.setValue(CalculationObject.getNodeType());
			}
			if(null != CalculationObject.getParentCode()){
				atrr = e.attribute("parentCode");
				atrr.setValue(CalculationObject.getParentCode());
			}
			if(null != CalculationObject.getParentName()){
				atrr = e.attribute("parentName");
				atrr.setValue(CalculationObject.getParentName());
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
