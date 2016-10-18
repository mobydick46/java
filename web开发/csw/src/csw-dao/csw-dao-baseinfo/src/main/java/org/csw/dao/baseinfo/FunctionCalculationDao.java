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
import org.csw.entity.baseinfo.FunctionCalculation;

public class FunctionCalculationDao{
	public Boolean AddFunctionCalculation(FunctionCalculation record){
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
            Element FunctionCalculations=config.element("FunctionCalculations");
            if(null == FunctionCalculations){
            	FunctionCalculations = config.addElement("FunctionCalculations");
            }
            Element FunctionCalculation = FunctionCalculations.addElement("FunctionCalculation");//加入FunctionCalculation节点
        	
        	if(record == null ){
        		System.out.println("Dao层函数AddFunctionCalculation参数错误！"+"record:"+record);
        	}
        	FunctionCalculation.addAttribute("id", record.getId()+"");
        	FunctionCalculation.addAttribute("objectCode",record.getObjectCode());
            FunctionCalculation.addAttribute("objectName",record.getObjectName()+"");
            FunctionCalculation.addAttribute("isCalculation",record.getIsCalculation());
            FunctionCalculation.addAttribute("nodeType",record.getNodeType()+"");
        	FunctionCalculation.addAttribute("functionId",record.getFunctionId());
        	FunctionCalculation.addAttribute("parentCode",record.getParentCode()+"");
        	FunctionCalculation.addAttribute("parentName",record.getParentName());
            
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
	
	public List<FunctionCalculation> GetFunctionCalculation(Map<String,Object> map){
		
		List<FunctionCalculation> listFunctionCalculation = new ArrayList<FunctionCalculation>();
		String s = "/config/FunctionCalculations/FunctionCalculation";
		String str = "/config/FunctionCalculations/FunctionCalculation";
		String[] field = {"id","objectCode","parentName","isCalculation","nodeType","functionId","objectName","parentCode"};
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
				FunctionCalculation FunctionCalculation = new FunctionCalculation();
				FunctionCalculation.setId(e.attributeValue("id"));
				FunctionCalculation.setObjectCode(e.attributeValue("objectCode"));
				FunctionCalculation.setObjectName(e.attributeValue("objectName"));
				FunctionCalculation.setIsCalculation(e.attributeValue("isCalculation"));
				FunctionCalculation.setNodeType(e.attributeValue("nodeType"));
				FunctionCalculation.setFunctionId(e.attributeValue("functionId"));
				FunctionCalculation.setParentCode(e.attributeValue("parentCode"));
				FunctionCalculation.setParentName(e.attributeValue("parentName"));
				listFunctionCalculation.add(FunctionCalculation);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listFunctionCalculation;
	}
	
	public String GetMaxID(){
		String id = "";
		String s = "/config/FunctionCalculations/FunctionCalculation/@id[not(/config/FunctionCalculations/FunctionCalculation/@id>.)]";
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
    
	public Boolean DeleteFunctionCalculation(String id){
		Boolean isOK = false;
		String s = "/config/FunctionCalculations/FunctionCalculation[@id='"+id+"']";
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
	
	public Boolean UpdateFunctionCalculation(FunctionCalculation FunctionCalculation){
		Boolean isOK = false;
		String s = "/config/FunctionCalculations/FunctionCalculation[@id='"+FunctionCalculation.getId()+"']";
		try {
			SAXReader reader = new SAXReader();
	        Document doc = reader.read(new File("config.xml"));
	        List<?> list = doc.selectNodes(s);
			Element e = (Element)list.get(0);
			System.out.println(e);
			Attribute atrr = e.attribute("id");
			if(null != FunctionCalculation.getId() && !"".equals(FunctionCalculation.getId())){
				atrr = e.attribute("id");
				atrr.setValue(FunctionCalculation.getId());
			}
			if(null != FunctionCalculation.getObjectCode()){
				atrr = e.attribute("objectCode");
				atrr.setValue(String.valueOf(FunctionCalculation.getObjectCode()));
			}
			if(null != FunctionCalculation.getObjectName()){
				atrr = e.attribute("objectName");
				atrr.setValue(String.valueOf(FunctionCalculation.getObjectName()));
			}
			if(null != FunctionCalculation.getIsCalculation()){
				atrr = e.attribute("isCalculation");
				atrr.setValue(FunctionCalculation.getIsCalculation());
			}
			if(null != FunctionCalculation.getNodeType()){
				atrr = e.attribute("nodeType");
				atrr.setValue(FunctionCalculation.getNodeType());
			}
			if(null != FunctionCalculation.getFunctionId()){
				atrr = e.attribute("functionId");
				atrr.setValue(FunctionCalculation.getFunctionId());
			}
			if(null != FunctionCalculation.getParentCode()){
				atrr = e.attribute("parentCode");
				atrr.setValue(FunctionCalculation.getParentCode());
			}
			if(null != FunctionCalculation.getParentName()){
				atrr = e.attribute("parentName");
				atrr.setValue(FunctionCalculation.getParentName());
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
