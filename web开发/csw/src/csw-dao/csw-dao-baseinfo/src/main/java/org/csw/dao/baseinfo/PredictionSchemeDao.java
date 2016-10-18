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
import org.csw.entity.baseinfo.PredictionScheme;

public class PredictionSchemeDao{
	public Boolean AddPredictionScheme(PredictionScheme record){
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
            Element PredictionSchemes=config.element("PredictionSchemes");
            if(null == PredictionSchemes){
            	PredictionSchemes = config.addElement("PredictionSchemes");
            }
            Element PredictionScheme = PredictionSchemes.addElement("PredictionScheme");//加入PredictionScheme节点
        	
        	if(record == null ){
        		System.out.println("Dao层函数AddPredictionScheme参数错误！"+"record:"+record);
        	}
        	PredictionScheme.addAttribute("id", record.getId()+"");
        	PredictionScheme.addAttribute("predictionCode",record.getPredictionCode());
            PredictionScheme.addAttribute("typeNumber",record.getTypeNumber()+"");
            PredictionScheme.addAttribute("typeUnit",record.getTypeUnit());
            PredictionScheme.addAttribute("isDefault",record.getIsDefault()+"");
        	PredictionScheme.addAttribute("funCalculationId",record.getFunCalculationId());
            
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
	
	public List<PredictionScheme> GetPredictionScheme(Map<String,Object> map){
		
		List<PredictionScheme> listPredictionScheme = new ArrayList<PredictionScheme>();
		String s = "/config/PredictionSchemes/PredictionScheme";
		String str = "/config/PredictionSchemes/PredictionScheme";
		String[] field = {"id","predictionCode","typeNumber","typeUnit","isDefault","funCalculationId"};
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
				PredictionScheme PredictionScheme = new PredictionScheme();
				PredictionScheme.setId(e.attributeValue("id"));
				PredictionScheme.setPredictionCode(e.attributeValue("predictionCode"));
				PredictionScheme.setTypeNumber(e.attributeValue("typeNumber"));
				PredictionScheme.setTypeUnit(e.attributeValue("typeUnit"));
				PredictionScheme.setIsDefault(e.attributeValue("isDefault"));
				PredictionScheme.setFunCalculationId(e.attributeValue("funCalculationId"));
				listPredictionScheme.add(PredictionScheme);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPredictionScheme;
	}
	
	public String GetMaxID(){
		String id = "";
		String s = "/config/PredictionSchemes/PredictionScheme/@id[not(/config/PredictionSchemes/PredictionScheme/@id>.)]";
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
    
	public Boolean DeletePredictionScheme(String id){
		Boolean isOK = false;
		String s = "/config/PredictionSchemes/PredictionScheme[@id='"+id+"']";
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
	
	public Boolean UpdatePredictionScheme(PredictionScheme PredictionScheme){
		Boolean isOK = false;
		String s = "/config/PredictionSchemes/PredictionScheme[@id='"+PredictionScheme.getId()+"']";
		try {
			SAXReader reader = new SAXReader();
	        Document doc = reader.read(new File("config.xml"));
	        List<?> list = doc.selectNodes(s);
			Element e = (Element)list.get(0);
			System.out.println(e);
			Attribute atrr = e.attribute("id");
			if(null != PredictionScheme.getId() && !"".equals(PredictionScheme.getId())){
				atrr = e.attribute("id");
				atrr.setValue(PredictionScheme.getId());
			}
			if(null != PredictionScheme.getPredictionCode()){
				atrr = e.attribute("predictionCode");
				atrr.setValue(String.valueOf(PredictionScheme.getPredictionCode()));
			}
			if(null != PredictionScheme.getTypeNumber()){
				atrr = e.attribute("typeNumber");
				atrr.setValue(String.valueOf(PredictionScheme.getTypeNumber()));
			}
			if(null != PredictionScheme.getTypeUnit()){
				atrr = e.attribute("typeUnit");
				atrr.setValue(PredictionScheme.getTypeUnit());
			}
			if(null != PredictionScheme.getIsDefault()){
				atrr = e.attribute("isDefault");
				atrr.setValue(PredictionScheme.getIsDefault());
			}
			if(null != PredictionScheme.getFunCalculationId()){
				atrr = e.attribute("funCalculationId");
				atrr.setValue(PredictionScheme.getFunCalculationId());
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
