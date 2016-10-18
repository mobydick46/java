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
import org.csw.entity.baseinfo.DataUnit;

public class DataUnitDao{
	public Boolean AddDataUnit(DataUnit record){
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
            Element DataUnits=config.element("DataUnits");
            if(null == DataUnits){
            	DataUnits = config.addElement("DataUnits");
            }
            Element DataUnit = DataUnits.addElement("DataUnit");//加入DataUnit节点
        	
        	if(record == null ){
        		System.out.println("Dao层函数AddDataUnit参数错误！"+"record:"+record);
        	}
        	DataUnit.addAttribute("id", record.getId()+"");
        	DataUnit.addAttribute("datatype",record.getDatatype());
            DataUnit.addAttribute("standardunit",record.getStandardunit()+"");
            DataUnit.addAttribute("displayunit",record.getDisplayunit());
            DataUnit.addAttribute("times",record.getTimes()+"");
        	DataUnit.addAttribute("controlformat",record.getControlformat());
            DataUnit.addAttribute("figure",record.getFigure()+"");
            
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
	
	public List<DataUnit> GetDataUnit(Map<String,Object> map){
		
		List<DataUnit> listDataUnit = new ArrayList<DataUnit>();
		String s = "/config/DataUnits/DataUnit";
		String str = "/config/DataUnits/DataUnit";
		String[] field = {"id","datatype","standardunit","displayunit","times","controlformat","figure"};
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
				DataUnit DataUnit = new DataUnit();
				DataUnit.setId(e.attributeValue("id"));
				DataUnit.setDatatype(e.attributeValue("datatype"));
				DataUnit.setStandardunit(e.attributeValue("standardunit"));
				DataUnit.setDisplayunit(e.attributeValue("displayunit"));
				DataUnit.setTimes(e.attributeValue("times"));
				DataUnit.setControlformat(e.attributeValue("controlformat"));
				DataUnit.setFigure(e.attributeValue("figure"));
				listDataUnit.add(DataUnit);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listDataUnit;
	}
	
	public String GetMaxID(){
		String id = "";
		String s = "/config/DataUnits/DataUnit/@id[not(/config/DataUnits/DataUnit/@id>.)]";
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
    
	public Boolean DeleteDataUnit(String id){
		Boolean isOK = false;
		String s = "/config/DataUnits/DataUnit[@id='"+id+"']";
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
	
	public Boolean UpdateDataUnit(DataUnit DataUnit){
		Boolean isOK = false;
		String s = "/config/DataUnits/DataUnit[@id='"+DataUnit.getId()+"']";
		try {
			SAXReader reader = new SAXReader();
	        Document doc = reader.read(new File("config.xml"));
	        List<?> list = doc.selectNodes(s);
			Element e = (Element)list.get(0);
			System.out.println(e);
			Attribute atrr = e.attribute("id");
			if(null != DataUnit.getId() && !"".equals(DataUnit.getId())){
				atrr = e.attribute("id");
				atrr.setValue(DataUnit.getId());
			}
			if(null != DataUnit.getFigure()){
				atrr = e.attribute("figure");
				atrr.setValue(String.valueOf(DataUnit.getFigure()));
			}
			if(null != DataUnit.getTimes()){
				atrr = e.attribute("times");
				atrr.setValue(String.valueOf(DataUnit.getTimes()));
			}
			if(null != DataUnit.getControlformat()){
				atrr = e.attribute("controlformat");
				atrr.setValue(DataUnit.getControlformat());
			}
			if(null != DataUnit.getDatatype()){
				atrr = e.attribute("datatype");
				atrr.setValue(DataUnit.getDatatype());
			}
			if(null != DataUnit.getDisplayunit()){
				atrr = e.attribute("displayunit");
				atrr.setValue(DataUnit.getDisplayunit());
			}
			if(null != DataUnit.getStandardunit()){
				atrr = e.attribute("standardunit");
				atrr.setValue(DataUnit.getStandardunit());
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
