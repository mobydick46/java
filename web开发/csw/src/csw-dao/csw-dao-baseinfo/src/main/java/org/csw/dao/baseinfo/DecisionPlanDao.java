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
import org.csw.entity.baseinfo.DecisionPlan;

public class DecisionPlanDao{
	public Boolean AddDecisionPlan(DecisionPlan record){
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
            Element DecisionPlans=config.element("DecisionPlans");
            if(null == DecisionPlans){
            	DecisionPlans = config.addElement("DecisionPlans");
            }
            Element DecisionPlan = DecisionPlans.addElement("DecisionPlan");//加入DecisionPlan节点
        	
        	if(record == null ){
        		System.out.println("Dao层函数AddDecisionPlan参数错误！"+"record:"+record);
        	}
        	DecisionPlan.addAttribute("id", record.getId()+"");
        	DecisionPlan.addAttribute("planName",record.getPlanName());
            DecisionPlan.addAttribute("timeID",record.getTimeID()+"");
            DecisionPlan.addAttribute("reservoirObject",record.getReservoirObject());
            DecisionPlan.addAttribute("objectCode",record.getObjectCode()+"");
        	DecisionPlan.addAttribute("objectNumber",record.getObjectNumber());
        	DecisionPlan.addAttribute("number",record.getNumber());
        	DecisionPlan.addAttribute("functionId",record.getFunctionId());
            
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
	
	public List<DecisionPlan> GetDecisionPlan(Map<String,Object> map){
		
		List<DecisionPlan> listDecisionPlan = new ArrayList<DecisionPlan>();
		String s = "/config/DecisionPlans/DecisionPlan";
		String str = "/config/DecisionPlans/DecisionPlan";
		String[] field = {"id","planName","timeID","reservoirObject","objectCode","objectNumber","number","functionId"};
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
				DecisionPlan DecisionPlan = new DecisionPlan();
				DecisionPlan.setId(e.attributeValue("id"));
				DecisionPlan.setPlanName(e.attributeValue("planName"));
				DecisionPlan.setTimeID(e.attributeValue("timeID"));
				DecisionPlan.setReservoirObject(e.attributeValue("reservoirObject"));
				DecisionPlan.setObjectCode(e.attributeValue("objectCode"));
				DecisionPlan.setObjectNumber(e.attributeValue("objectNumber"));
				DecisionPlan.setNumber(e.attributeValue("number"));
				DecisionPlan.setFunctionId(e.attributeValue("functionId"));
				listDecisionPlan.add(DecisionPlan);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listDecisionPlan;
	}
	
	public String GetMaxID(){
		String id = "";
		String s = "/config/DecisionPlans/DecisionPlan/@id[not(/config/DecisionPlans/DecisionPlan/@id>.)]";
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
	
	public String GetMaxNumber(){
		String number = "";
		String s = "/config/DecisionPlans/DecisionPlan/@number[not(/config/DecisionPlans/DecisionPlan/@number>.)]";
		try {
			SAXReader reader = new SAXReader();
	        Document doc = reader.read(new File("config.xml"));
			Node node = doc.selectSingleNode(s);
			if(null!=node){
				number = node.getStringValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return number;
	}
    
	public Boolean DeleteDecisionPlan(String id){
		Boolean isOK = false;
		String s = "/config/DecisionPlans/DecisionPlan[@id='"+id+"']";
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
	
	public Boolean UpdateDecisionPlan(DecisionPlan DecisionPlan){
		Boolean isOK = false;
		String s = "/config/DecisionPlans/DecisionPlan[@id='"+DecisionPlan.getId()+"']";
		try {
			SAXReader reader = new SAXReader();
	        Document doc = reader.read(new File("config.xml"));
	        List<?> list = doc.selectNodes(s);
			Element e = (Element)list.get(0);
			System.out.println(e);
			Attribute atrr = e.attribute("id");
			if(null != DecisionPlan.getId() && !"".equals(DecisionPlan.getId())){
				atrr = e.attribute("id");
				atrr.setValue(DecisionPlan.getId());
			}
			if(null != DecisionPlan.getObjectCode()){
				atrr = e.attribute("objectCode");
				atrr.setValue(String.valueOf(DecisionPlan.getObjectCode()));
			}
			if(null != DecisionPlan.getObjectNumber()){
				atrr = e.attribute("objectNumber");
				atrr.setValue(DecisionPlan.getObjectNumber());
			}
			if(null != DecisionPlan.getPlanName()){
				atrr = e.attribute("planName");
				atrr.setValue(DecisionPlan.getPlanName());
			}
			if(null != DecisionPlan.getReservoirObject()){
				atrr = e.attribute("reservoirObject");
				atrr.setValue(DecisionPlan.getReservoirObject());
			}
			if(null != DecisionPlan.getTimeID()){
				atrr = e.attribute("timeID");
				atrr.setValue(DecisionPlan.getTimeID());
			}
			if(null != DecisionPlan.getNumber()){
				atrr = e.attribute("number");
				atrr.setValue(DecisionPlan.getNumber());
			}
			if(null != DecisionPlan.getFunctionId()){
				atrr = e.attribute("functionId");
				atrr.setValue(DecisionPlan.getFunctionId());
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
