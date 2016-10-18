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
import org.csw.entity.baseinfo.FunctionModel;

public class FunctionModelDao{
	public Boolean AddFunctionModel(FunctionModel record){
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
            Element FunctionModels=config.element("FunctionModels");
            if(null == FunctionModels){
            	FunctionModels = config.addElement("FunctionModels");
            }
            Element FunctionModel = FunctionModels.addElement("FunctionModel");//加入FunctionModel节点
        	
        	if(record == null ){
        		System.out.println("Dao层函数AddFunctionModel参数错误！"+"record:"+record);
        	}
        	FunctionModel.addAttribute("id", record.getId()+"");
        	FunctionModel.addAttribute("modelCode",record.getModelCode());
            FunctionModel.addAttribute("modelName",record.getModelName()+"");
            FunctionModel.addAttribute("description",record.getDescription());
            FunctionModel.addAttribute("modelType",record.getModelType()+"");
        	FunctionModel.addAttribute("isApply",record.getIsApply());
            FunctionModel.addAttribute("funCalculationId",record.getFunCalculationId()+"");
            FunctionModel.addAttribute("functionId",record.getFunctionId()+"");
            FunctionModel.addAttribute("type",record.getType());
            
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
	
	public List<FunctionModel> GetFunctionModel(Map<String,Object> map){
		
		List<FunctionModel> listFunctionModel = new ArrayList<FunctionModel>();
		String s = "/config/FunctionModels/FunctionModel";
		String str = "/config/FunctionModels/FunctionModel";
		String[] field = {"id","modelCode","modelName","description","modelType","isApply","funCalculationId","functionId","type"};
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
				FunctionModel FunctionModel = new FunctionModel();
				FunctionModel.setId(e.attributeValue("id"));
				FunctionModel.setModelCode(e.attributeValue("modelCode"));
				FunctionModel.setModelName(e.attributeValue("modelName"));
				FunctionModel.setDescription(e.attributeValue("description"));
				FunctionModel.setModelType(e.attributeValue("modelType"));
				FunctionModel.setIsApply(e.attributeValue("isApply"));
				FunctionModel.setFunCalculationId(e.attributeValue("funCalculationId"));
				FunctionModel.setFunctionId(e.attributeValue("functionId"));
				FunctionModel.setType(e.attributeValue("type"));
				listFunctionModel.add(FunctionModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listFunctionModel;
	}
	
	public String GetMaxID(){
		String id = "";
		String s = "/config/FunctionModels/FunctionModel/@id[not(/config/FunctionModels/FunctionModel/@id>.)]";
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
    
	public Boolean DeleteFunctionModel(String id){
		Boolean isOK = false;
		String s = "/config/FunctionModels/FunctionModel[@id='"+id+"']";
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
	
	public Boolean UpdateFunctionModel(FunctionModel FunctionModel){
		Boolean isOK = false;
		String s = "/config/FunctionModels/FunctionModel[@id='"+FunctionModel.getId()+"']";
		try {
			SAXReader reader = new SAXReader();
	        Document doc = reader.read(new File("config.xml"));
	        List<?> list = doc.selectNodes(s);
			Element e = (Element)list.get(0);
			System.out.println(e);
			Attribute atrr = e.attribute("id");
			if(null != FunctionModel.getId() && !"".equals(FunctionModel.getId())){
				atrr = e.attribute("id");
				atrr.setValue(FunctionModel.getId());
			}
			if(null != FunctionModel.getModelCode()){
				atrr = e.attribute("modelCode");
				atrr.setValue(String.valueOf(FunctionModel.getModelCode()));
			}
			if(null != FunctionModel.getModelName()){
				atrr = e.attribute("modelName");
				atrr.setValue(String.valueOf(FunctionModel.getModelName()));
			}
			if(null != FunctionModel.getDescription()){
				atrr = e.attribute("description");
				atrr.setValue(FunctionModel.getDescription());
			}
			if(null != FunctionModel.getModelType()){
				atrr = e.attribute("modelType");
				atrr.setValue(FunctionModel.getModelType());
			}
			if(null != FunctionModel.getIsApply()){
				atrr = e.attribute("isApply");
				atrr.setValue(FunctionModel.getIsApply());
			}
			if(null != FunctionModel.getFunCalculationId()){
				atrr = e.attribute("funCalculationId");
				atrr.setValue(FunctionModel.getFunCalculationId());
			}
			if(null != FunctionModel.getFunCalculationId()){
				atrr = e.attribute("functionId");
				atrr.setValue(FunctionModel.getFunCalculationId());
			}
			if(null != FunctionModel.getType()){
				atrr = e.attribute("type");
				atrr.setValue(FunctionModel.getType());
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
