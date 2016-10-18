package org.csw.service.baseinfo.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.csw.dao.baseinfo.BasicAttributeDao;
import org.csw.entity.baseinfo.BasicAttribute;
import org.csw.service.baseinfo.BasicAttributeService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class BasicAttributeServiceImpl implements BasicAttributeService {
	private static final Logger logger = Logger.getLogger("com.csw");
	private BasicAttributeDao basicAttributeDao = new BasicAttributeDao();
	
	public void setBasicAttributeDao(BasicAttributeDao basicAttributeDao){
		this.basicAttributeDao = basicAttributeDao;
	}
	
	public JSONObject addBasicAttribute(BasicAttribute basicAttribute){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "BasicAttributeService.addBasicAttribute";
		String param = " basicAttribute:"+basicAttribute;
		logger.info( entity+pos+param  );
		try {
			//String id = String.valueOf(Integer.valueOf(basicAttributeDao.GetMaxID())+1);
			//basicAttribute.setId(id);
			if(null == basicAttribute.getId() || "".equals(basicAttribute.getId())){
				logger.info( "参数错误，无ID"+param  );
			}
			basicAttributeDao.AddBasicAttribute(basicAttribute);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject getMaxID(){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "BasicAttributeService.getMaxID";
		logger.info( entity+pos );
		try {
			String id = basicAttributeDao.GetMaxID();
			result.put("id", id);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject deleteBasicAttribute(String ids){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "BasicAttributeService.deleteBasicAttribute";
		String param = " ids:"+ids;
		logger.info( entity+pos+param  );
		try {
			String[] idsStr = ids.split(",");
			for(int i=0;i<idsStr.length;i++){
				basicAttributeDao.DeleteBasicAttribute(idsStr[i]);
			}
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public JSONObject modifyBasicAttribute(BasicAttribute basicAttribute){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "BasicAttributeService.modifyBasicAttribute";
		String param = " basicAttribute:"+basicAttribute;
		logger.info( entity+pos+param  );
		try {
			basicAttributeDao.UpdateBasicAttribute(basicAttribute);
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject getBasicAttribute(String id,String name){
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		final String entity = "";
		final String pos = "BasicAttributeService.getBasicAttribute";
		String param = " id:"+id+" name:"+name;
		logger.info( entity+pos+param  );
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("name", name);
			List<BasicAttribute> list = basicAttributeDao.GetBasicAttribute(map);
			long total = list.size();
			JsonConfig jsonConfig = new JsonConfig();
			JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
			data.put("rows", jsonArray);
			data.put("total", total);
			result.put("data", data);
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject download(String path){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "BasicAttributeService.downLoad";
		String param = " path:"+path;
		logger.info( entity+pos+param  );
		try {
			String PATH = path;
			if(null == path || "".equals(path)){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", "4");
				List<BasicAttribute> list = basicAttributeDao.GetBasicAttribute(map);
				PATH = list.get(0).getId();
			}
			if(basicAttributeDao.download(PATH)){
				result.put("success", true);
			}else{
				result.put("success", false);
			}
			result.put("path", PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
