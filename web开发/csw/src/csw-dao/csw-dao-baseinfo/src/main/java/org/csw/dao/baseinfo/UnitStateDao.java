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
import org.csw.entity.baseinfo.UnitState;

/**
 * 
 * @brief  状态Dao层
 * @author xianweilv
 * @date 2016年8月17日 下午9:05:33
 */
public class UnitStateDao{
	/**
	 * @brief 
	 * @logic
	 * @param record
	 * @param flag   0代表机组状态  1代表闸门状态
	 * @return
	 * @author xianweilv
	 * @date 2016年8月17日 下午9:15:14
	 */
	public Boolean AddUnitState(UnitState record,Integer flag){
		String elementNames = null;
		String elementName = null;
		Boolean isOK = false;
		switch(flag){
		    case 0:elementNames = "UnitStates";elementName = "UnitState";break;
		    case 1:elementNames = "GateStates";elementName = "GateState";break;
		}
		
		//建立document对象
        //Document doc = DocumentHelper.createDocument();
        //建立XML文档的根config
        //Element config = doc.addElement("config");
        try {
        	SAXReader reader = new SAXReader();
            Document doc = reader.read(new File("config.xml"));
            List<?> list = doc.selectNodes("/config");
            Element config = (Element)list.get(0);
            Element UnitStates=config.element(elementNames);
            if(null == UnitStates){
            	UnitStates = config.addElement(elementNames);
            }
            Element UnitState = UnitStates.addElement(elementName);//加入UnitState节点
        	
        	if(record == null ){
        		System.out.println("Dao层函数AddUnitState参数错误！"+"record:"+record);
        	}
        	UnitState.addAttribute("id", record.getId()+"");
        	UnitState.addAttribute("stateDefinition",record.getStateDefinition());
            UnitState.addAttribute("stateID",record.getStateID()+"");
            UnitState.addAttribute("stateName",record.getStateName());
            
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
	
	public List<UnitState> GetUnitState(Map<String,Object> map,Integer flag){
		
		List<UnitState> listUnitState = new ArrayList<UnitState>();
		String s = null;
		String str = null;
		switch(flag){
		    case 0:s = "/config/UnitStates/UnitState";str = "/config/UnitStates/UnitState";break;
		    case 1:s = "/config/GateStates/GateState";str = "/config/GateStates/GateState";break;
		}
		String[] field = {"id","stateDefinition","stateID","stateName"};
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
				UnitState UnitState = new UnitState();
				UnitState.setId(e.attributeValue("id"));
				UnitState.setStateDefinition(e.attributeValue("stateDefinition"));
				UnitState.setStateID(e.attributeValue("stateID"));
				UnitState.setStateName(e.attributeValue("stateName"));
				listUnitState.add(UnitState);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listUnitState;
	}
	
	public String GetMaxID(Integer flag){
		String id = "";
		String s = null;
		switch(flag){
		    case 0:s = "/config/UnitStates/UnitState/@id[not(/config/UnitStates/UnitState/@id>.)]";break;
		    case 1:s = "/config/GateStates/GateState/@id[not(/config/GateStates/GateState/@id>.)]";break;
		}
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
    
	public Boolean DeleteUnitState(String id,Integer flag){
		Boolean isOK = false;
		String s = null;
		switch(flag){
		    case 0:s = "/config/UnitStates/UnitState[@id='"+id+"']";break;
		    case 1:s = "/config/GateStates/GateState[@id='"+id+"']";break;
		}
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
	
	public Boolean UpdateUnitState(UnitState UnitState,Integer flag){
		Boolean isOK = false;
		String s = null;
		switch(flag){
		    case 0:s = "/config/UnitStates/UnitState[@id='"+UnitState.getId()+"']";break;
		    case 1:s = "/config/GateStates/GateState[@id='"+UnitState.getId()+"']";break;
		}
		try {
			SAXReader reader = new SAXReader();
	        Document doc = reader.read(new File("config.xml"));
	        List<?> list = doc.selectNodes(s);
			Element e = (Element)list.get(0);
			System.out.println(e);
			Attribute atrr = e.attribute("id");
			if(null != UnitState.getId() && !"".equals(UnitState.getId())){
				atrr = e.attribute("id");
				atrr.setValue(UnitState.getId());
			}
			if(null != UnitState.getStateDefinition()){
				atrr = e.attribute("stateDefinition");
				atrr.setValue(UnitState.getStateDefinition());
			}
			if(null != UnitState.getStateID()){
				atrr = e.attribute("stateID");
				atrr.setValue(UnitState.getStateID());
			}
			if(null != UnitState.getStateName()){
				atrr = e.attribute("stateName");
				atrr.setValue(UnitState.getStateName());
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
