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
import org.csw.entity.baseinfo.DataConnection;

public class DataConnectionDao{
	public Boolean AddDataConnection(DataConnection record){
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
            Element DataConnections=config.element("DataConnections");
            if(null == DataConnections){
            	DataConnections = config.addElement("DataConnections");
            }
            Element DataConnection = DataConnections.addElement("DataConnection");//加入DataConnection节点
        	
        	if(record == null ){
        		System.out.println("Dao层函数AddDataConnection参数错误！"+"record:"+record);
        	}
        	DataConnection.addAttribute("id", record.getId()+"");
        	DataConnection.addAttribute("name",record.getName());
            DataConnection.addAttribute("content",record.getContent()+"");
            DataConnection.addAttribute("remark",record.getRemark());
            
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
	
	public List<DataConnection> GetDataConnection(Map<String,Object> map){
		
		List<DataConnection> listDataConnection = new ArrayList<DataConnection>();
		String s = "/config/DataConnections/DataConnection";
		String str = "/config/DataConnections/DataConnection";
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
				DataConnection DataConnection = new DataConnection();
				DataConnection.setId(e.attributeValue("id"));
				DataConnection.setName(e.attributeValue("name"));
				DataConnection.setContent(e.attributeValue("content"));
				DataConnection.setRemark(e.attributeValue("remark"));
				listDataConnection.add(DataConnection);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listDataConnection;
	}
	
	public String GetMaxID(){
		String id = "";
		String s = "/config/DataConnections/DataConnection/@id[not(/config/DataConnections/DataConnection/@id>.)]";
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
    
	public Boolean DeleteDataConnection(String id){
		Boolean isOK = false;
		String s = "/config/DataConnections/DataConnection[@id='"+id+"']";
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
	
	public Boolean UpdateDataConnection(DataConnection DataConnection){
		Boolean isOK = false;
		String s = "/config/DataConnections/DataConnection[@id='"+DataConnection.getId()+"']";
		try {
			SAXReader reader = new SAXReader();
	        Document doc = reader.read(new File("config.xml"));
	        List<?> list = doc.selectNodes(s);
			Element e = (Element)list.get(0);
			System.out.println(e);
			if(null != DataConnection.getId() && !"".equals(DataConnection.getId())){
				Attribute idAtrr = e.attribute("id");
				idAtrr.setValue(DataConnection.getId());
			}
			if(null != DataConnection.getName()){
				Attribute nameAtrr = e.attribute("name");
				nameAtrr.setValue(DataConnection.getName());
			}
			if(null != DataConnection.getContent()){
				Attribute contentAtrr = e.attribute("content");
				contentAtrr.setValue(DataConnection.getContent());
			}
			if(null != DataConnection.getRemark()){
				Attribute remarkAtrr = e.attribute("remark");
				remarkAtrr.setValue(DataConnection.getRemark());
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
