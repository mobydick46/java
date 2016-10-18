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
import org.csw.entity.baseinfo.ModelMethod;

public class ModelMethodDao{
	public Boolean AddModelMethod(ModelMethod record){
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
            Element ModelMethods=config.element("ModelMethods");
            if(null == ModelMethods){
            	ModelMethods = config.addElement("ModelMethods");
            }
            Element ModelMethod = ModelMethods.addElement("ModelMethod");//加入ModelMethod节点
        	
        	if(record == null ){
        		System.out.println("Dao层函数AddModelMethod参数错误！"+"record:"+record);
        	}
        	ModelMethod.addAttribute("id", record.getId()+"");
        	ModelMethod.addAttribute("code",record.getCode());
            ModelMethod.addAttribute("name",record.getName()+"");
            ModelMethod.addAttribute("description",record.getDescription());
            ModelMethod.addAttribute("type",record.getType()+"");
        	ModelMethod.addAttribute("combCalculation",record.getCombCalculation());
            ModelMethod.addAttribute("sourceClass",record.getSourceClass()+"");
            
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
	
	public List<ModelMethod> GetModelMethod(Map<String,Object> map){
		
		List<ModelMethod> listModelMethod = new ArrayList<ModelMethod>();
		String s = "/config/ModelMethods/ModelMethod";
		String str = "/config/ModelMethods/ModelMethod";
		String[] field = {"id","code","name","description","type","combCalculation","sourceClass"};
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
				ModelMethod ModelMethod = new ModelMethod();
				ModelMethod.setId(e.attributeValue("id"));
				ModelMethod.setCode(e.attributeValue("code"));
				ModelMethod.setName(e.attributeValue("name"));
				ModelMethod.setDescription(e.attributeValue("description"));
				ModelMethod.setType(e.attributeValue("type"));
				ModelMethod.setCombCalculation(e.attributeValue("combCalculation"));
				ModelMethod.setSourceClass(e.attributeValue("sourceClass"));
				listModelMethod.add(ModelMethod);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listModelMethod;
	}
	
	public String GetMaxID(){
		String id = "";
		String s = "/config/ModelMethods/ModelMethod/@id[not(/config/ModelMethods/ModelMethod/@id>.)]";
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
    
	public Boolean DeleteModelMethod(String id){
		Boolean isOK = false;
		String s = "/config/ModelMethods/ModelMethod[@id='"+id+"']";
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
	
	public Boolean UpdateModelMethod(ModelMethod ModelMethod){
		Boolean isOK = false;
		String s = "/config/ModelMethods/ModelMethod[@id='"+ModelMethod.getId()+"']";
		try {
			SAXReader reader = new SAXReader();
	        Document doc = reader.read(new File("config.xml"));
	        List<?> list = doc.selectNodes(s);
			Element e = (Element)list.get(0);
			System.out.println(e);
			Attribute atrr = e.attribute("id");
			if(null != ModelMethod.getId() && !"".equals(ModelMethod.getId())){
				atrr = e.attribute("id");
				atrr.setValue(ModelMethod.getId());
			}
			if(null != ModelMethod.getCode()){
				atrr = e.attribute("code");
				atrr.setValue(ModelMethod.getCode());
			}
			if(null != ModelMethod.getName()){
				atrr = e.attribute("name");
				atrr.setValue(ModelMethod.getName());
			}
			if(null != ModelMethod.getDescription()){
				atrr = e.attribute("description");
				atrr.setValue(ModelMethod.getDescription());
			}
			if(null != ModelMethod.getType()){
				atrr = e.attribute("type");
				atrr.setValue(ModelMethod.getType());
			}
			if(null != ModelMethod.getCombCalculation()){
				atrr = e.attribute("combCalculation");
				atrr.setValue(ModelMethod.getCombCalculation());
			}
			if(null != ModelMethod.getSourceClass()){
				atrr = e.attribute("sourceClass");
				atrr.setValue(ModelMethod.getSourceClass());
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
