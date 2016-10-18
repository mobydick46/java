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
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.csw.entity.baseinfo.BasicAttribute;

public class BasicAttributeDao{
	public Boolean AddBasicAttribute(BasicAttribute record){
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
            Element basicattributes=config.element("basicattributes");
            if(null == basicattributes){
            	basicattributes = config.addElement("basicattributes");
            }
            Element basicattribute = basicattributes.addElement("basicattribute");//加入basicattribute节点
        	
        	if(record == null ){
        		System.out.println("Dao层函数AddBasicAttribute参数错误！"+"record:"+record);
        	}
        	basicattribute.addAttribute("id", record.getId()+"");
        	basicattribute.addAttribute("name",record.getName());
            basicattribute.addAttribute("content",record.getContent()+"");
            basicattribute.addAttribute("remark",record.getRemark());
            
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
	
	public List<BasicAttribute> GetBasicAttribute(Map<String,Object> map){
		
		List<BasicAttribute> listBasicAttribute = new ArrayList<BasicAttribute>();
		String s = "/config/basicattributes/basicattribute";
		String str = "/config/basicattributes/basicattribute";
		try {
			if(!(null == map.get("id")||"".equals(map.get("id")))){
				s = s+"[@id='"+map.get("id")+"'";
				//s = s+"@MAX(id)";
			}
			if(!(null == map.get("name")||"".equals(map.get("name")))){
				if(s.equals(str)){
					s = s+"[@name='"+map.get("name")+"'";
				}else{
					s = s+"and @name='"+map.get("name")+"'";
				}
			}
			if(!(null == map.get("content")||"".equals(map.get("content")))){
				if(s.equals(str)){
					s = s+"[@content='"+map.get("content")+"'";
				}else{
					s = s+"and @content='"+map.get("content")+"'";
				}
			}
			if(!(null == map.get("remark")||"".equals(map.get("remark")))){
				if(s.equals(str)){
					s = s+"[@remark='"+map.get("remark")+"'";
				}else{
					s = s+"and @remark='"+map.get("remark")+"'";
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
				BasicAttribute basicAttribute = new BasicAttribute();
				basicAttribute.setId(e.attributeValue("id"));
				basicAttribute.setName(e.attributeValue("name"));
				basicAttribute.setContent(e.attributeValue("content"));
				basicAttribute.setRemark(e.attributeValue("remark"));
				listBasicAttribute.add(basicAttribute);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listBasicAttribute;
	}
	
	public String GetMaxID(){
		String id = "";
		String s = "/config/basicattributes/basicattribute/@id[not(/config/basicattributes/basicattribute/@id>.)]";
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
    
	public Boolean DeleteBasicAttribute(String id){
		Boolean isOK = false;
		String s = "/config/basicattributes/basicattribute[@id='"+id+"']";
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
	
	public Boolean UpdateBasicAttribute(BasicAttribute basicAttribute){
		Boolean isOK = false;
		String s = "/config/basicattributes/basicattribute[@id='"+basicAttribute.getId()+"']";
		try {
			SAXReader reader = new SAXReader();
	        Document doc = reader.read(new File("config.xml"));
	        List<?> list = doc.selectNodes(s);
			Element e = (Element)list.get(0);
			System.out.println(e);
			if(null != basicAttribute.getId() && !"".equals(basicAttribute.getId())){
				Attribute idAtrr = e.attribute("id");
				idAtrr.setValue(basicAttribute.getId());
			}
			if(null != basicAttribute.getName()){
				Attribute nameAtrr = e.attribute("name");
				nameAtrr.setValue(basicAttribute.getName());
			}
			if(null != basicAttribute.getContent()){
				Attribute contentAtrr = e.attribute("content");
				contentAtrr.setValue(basicAttribute.getContent());
			}
			if(null != basicAttribute.getRemark()){
				Attribute remarkAtrr = e.attribute("remark");
				remarkAtrr.setValue(basicAttribute.getRemark());
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
	
	public Boolean download(String path){
		Boolean isOK = false;
		try {
			SAXReader reader = new SAXReader();
	        Document doc = reader.read(new File("config.xml"));
	        
	        OutputFormat format = OutputFormat.createPrettyPrint();
	        format.setLineSeparator("\r\n");
	        XMLWriter writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(path),"utf-8"),format);
	        writer.write(doc);
	        writer.close();
	        /*
	        OutputFormat outputFormat = OutputFormat.createPrettyPrint();
	        outputFormat.setEncoding("gb2312");
            outputFormat.setLineSeparator("\r\n");//这是为了换行操作  
            Writer writer = new FileWriter(path);  
            XMLWriter outPut = new XMLWriter(writer,outputFormat);  
            outPut.write(doc);  
            outPut.close();*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isOK;
	}
}
