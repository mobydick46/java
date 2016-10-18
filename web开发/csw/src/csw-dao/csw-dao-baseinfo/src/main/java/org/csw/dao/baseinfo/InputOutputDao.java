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
import org.csw.entity.baseinfo.InputOutput;

public class InputOutputDao{
	public Boolean AddInputOutput(InputOutput record){
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
            Element InputOutputs=config.element("InputOutputs");
            if(null == InputOutputs){
            	InputOutputs = config.addElement("InputOutputs");
            }
            Element InputOutput = InputOutputs.addElement("InputOutput");//加入InputOutput节点
        	
        	if(record == null ){
        		System.out.println("Dao层函数AddInputOutput参数错误！"+"record:"+record);
        	}
        	InputOutput.addAttribute("id", record.getId()+"");
        	InputOutput.addAttribute("projectName",record.getProjectName());
            InputOutput.addAttribute("isSet",record.getIsSet()+"");
            InputOutput.addAttribute("description",record.getDescription());
            InputOutput.addAttribute("modelCode",record.getModelCode()+"");
            InputOutput.addAttribute("type",record.getType());
            
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
	
	public List<InputOutput> GetInputOutput(Map<String,Object> map){
		
		List<InputOutput> listInputOutput = new ArrayList<InputOutput>();
		String s = "/config/InputOutputs/InputOutput";
		String str = "/config/InputOutputs/InputOutput";
		String[] field = {"id","projectName","isSet","description","modelCode","type"};
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
				InputOutput InputOutput = new InputOutput();
				InputOutput.setId(e.attributeValue("id"));
				InputOutput.setProjectName(e.attributeValue("projectName"));
				InputOutput.setIsSet(e.attributeValue("isSet"));
				InputOutput.setDescription(e.attributeValue("description"));
				InputOutput.setModelCode(e.attributeValue("modelCode"));
				InputOutput.setType(e.attributeValue("type"));
				listInputOutput.add(InputOutput);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listInputOutput;
	}
	
	public String GetMaxID(){
		String id = "";
		String s = "/config/InputOutputs/InputOutput/@id[not(/config/InputOutputs/InputOutput/@id>.)]";
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
    
	public Boolean DeleteInputOutput(String id){
		Boolean isOK = false;
		String s = "/config/InputOutputs/InputOutput[@id='"+id+"']";
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
	
	public Boolean UpdateInputOutput(InputOutput InputOutput){
		Boolean isOK = false;
		String s = "/config/InputOutputs/InputOutput[@id='"+InputOutput.getId()+"']";
		try {
			SAXReader reader = new SAXReader();
	        Document doc = reader.read(new File("config.xml"));
	        List<?> list = doc.selectNodes(s);
			Element e = (Element)list.get(0);
			System.out.println(e);
			Attribute atrr = e.attribute("id");
			if(null != InputOutput.getId() && !"".equals(InputOutput.getId())){
				atrr = e.attribute("id");
				atrr.setValue(InputOutput.getId());
			}
			if(null != InputOutput.getProjectName()){
				atrr = e.attribute("projectName");
				atrr.setValue(String.valueOf(InputOutput.getProjectName()));
			}
			if(null != InputOutput.getIsSet()){
				atrr = e.attribute("isSet");
				atrr.setValue(String.valueOf(InputOutput.getIsSet()));
			}
			if(null != InputOutput.getDescription()){
				atrr = e.attribute("description");
				atrr.setValue(InputOutput.getDescription());
			}
			if(null != InputOutput.getModelCode()){
				atrr = e.attribute("modelCode");
				atrr.setValue(InputOutput.getModelCode());
			}
			if(null != InputOutput.getType()){
				atrr = e.attribute("type");
				atrr.setValue(InputOutput.getType());
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
