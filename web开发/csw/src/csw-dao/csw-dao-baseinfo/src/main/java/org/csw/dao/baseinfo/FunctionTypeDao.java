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
import org.csw.entity.baseinfo.FunctionType;

public class FunctionTypeDao{
	public Boolean AddFunctionType(FunctionType record){
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
            Element FunctionTypes=config.element("FunctionTypes");
            if(null == FunctionTypes){
            	FunctionTypes = config.addElement("FunctionTypes");
            }
            Element FunctionType = FunctionTypes.addElement("FunctionType");//加入FunctionType节点
        	
        	if(record == null ){
        		System.out.println("Dao层函数AddFunctionType参数错误！"+"record:"+record);
        	}
        	FunctionType.addAttribute("id", record.getId()+"");
        	FunctionType.addAttribute("projectName",record.getProjectName());
            FunctionType.addAttribute("type",record.getType()+"");
            FunctionType.addAttribute("isSet",record.getIsSet());
            FunctionType.addAttribute("remark",record.getRemark()+"");
            
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
	
	public List<FunctionType> GetFunctionType(Map<String,Object> map){
		
		List<FunctionType> listFunctionType = new ArrayList<FunctionType>();
		String s = "/config/FunctionTypes/FunctionType";
		String str = "/config/FunctionTypes/FunctionType";
		String[] field = {"id","projectName","type","isSet","remark"};
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
				FunctionType FunctionType = new FunctionType();
				FunctionType.setId(e.attributeValue("id"));
				FunctionType.setProjectName(e.attributeValue("projectName"));
				FunctionType.setType(e.attributeValue("type"));
				FunctionType.setIsSet(e.attributeValue("isSet"));
				FunctionType.setRemark(e.attributeValue("remark"));
				listFunctionType.add(FunctionType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listFunctionType;
	}
	
	public String GetMaxID(){
		String id = "";
		String s = "/config/FunctionTypes/FunctionType/@id[not(/config/FunctionTypes/FunctionType/@id>.)]";
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
    
	public Boolean DeleteFunctionType(String id){
		Boolean isOK = false;
		String s = "/config/FunctionTypes/FunctionType[@id='"+id+"']";
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
	
	public Boolean UpdateFunctionType(FunctionType FunctionType){
		Boolean isOK = false;
		String s = "/config/FunctionTypes/FunctionType[@id='"+FunctionType.getId()+"']";
		try {
			SAXReader reader = new SAXReader();
	        Document doc = reader.read(new File("config.xml"));
	        List<?> list = doc.selectNodes(s);
			Element e = (Element)list.get(0);
			System.out.println(e);
			Attribute atrr = e.attribute("id");
			if(null != FunctionType.getId() && !"".equals(FunctionType.getId())){
				atrr = e.attribute("id");
				atrr.setValue(FunctionType.getId());
			}
			if(null != FunctionType.getProjectName()){
				atrr = e.attribute("projectName");
				atrr.setValue(String.valueOf(FunctionType.getProjectName()));
			}
			if(null != FunctionType.getType()){
				atrr = e.attribute("type");
				atrr.setValue(String.valueOf(FunctionType.getType()));
			}
			if(null != FunctionType.getIsSet()){
				atrr = e.attribute("isSet");
				atrr.setValue(FunctionType.getIsSet());
			}
			if(null != FunctionType.getRemark()){
				atrr = e.attribute("remark");
				atrr.setValue(FunctionType.getRemark());
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
