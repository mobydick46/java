package org.csw.service.baseinfo.impl;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.csw.dao.baseinfo.CalculationObjectDao;
import org.csw.entity.baseinfo.CalculationObject;
import org.csw.service.baseinfo.CalculationObjectService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class CalculationObjectServiceImpl implements CalculationObjectService {
	private static final Logger logger = Logger.getLogger("com.csw");
	private CalculationObjectDao calculationObjectDao = new CalculationObjectDao();
	
	public void setCalculationObjectDao(CalculationObjectDao calculationObjectDao){
		this.calculationObjectDao = calculationObjectDao;
	}
	
	public JSONObject addCalculationObject(CalculationObject calculationObject){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "CalculationObjectService.addCalculationObject";
		String param = " calculationObject:"+calculationObject;
		logger.info( entity+pos+param  );
		try {
			//String id = String.valueOf(Integer.valueOf(calculationObjectDao.GetMaxID())+1);
			//calculationObject.setId(id);
			if(null == calculationObject.getId() || "".equals(calculationObject.getId())){
				logger.info( "参数错误，无ID"+param  );
			}
			calculationObjectDao.AddCalculationObject(calculationObject);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject getMaxID(){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "CalculationObjectService.getMaxID";
		logger.info( entity+pos );
		try {
			String id = calculationObjectDao.GetMaxID();
			result.put("id", id);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject deleteCalculationObject(String ids){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "CalculationObjectService.deleteCalculationObject";
		String param = " ids:"+ids;
		logger.info( entity+pos+param  );
		try {
			String[] idsStr = ids.split(",");
			Map<String, Object> map = new HashMap<String, Object>();
			for(int i=0;i<idsStr.length;i++){
				map.put("id", idsStr[i]);
				List<CalculationObject> list = calculationObjectDao.GetCalculationObject(map);
				for(CalculationObject e : list){
					calculationObjectDao.DeleteCalculationObject(e.getId());
					map.clear();
					if("1".equals(e.getNodeType())){
						map.put("parentCode", e.getObjectCode());
						if("".equals(e.getObjectCode())){
							map.put("parentCode", "parentCodeparentCode");
							//这里的parentCodeparentCode可以用任何其他不在parentCode里表里的值代替
						}
						List<CalculationObject> list2 = calculationObjectDao.GetCalculationObject(map);
						for(CalculationObject e2 : list2){
							calculationObjectDao.DeleteCalculationObject(e2.getId());
						}
					}
				}
			}
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public JSONObject modifyCalculationObject(String change,CalculationObject calculationObject){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "CalculationObjectService.modifyCalculationObject";
		String param = " change:"+change+", calculationObject:"+calculationObject;
		logger.info( entity+pos+param  );
		try {
			if("isSet".equals(change) && "".equals(calculationObject.getIsSet())){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", calculationObject.getId());
				List<CalculationObject> list = calculationObjectDao.GetCalculationObject(map);
				map.clear();
				for(CalculationObject e : list){
					map.put("parentCode", e.getObjectCode());
					List<CalculationObject> list2 = calculationObjectDao.GetCalculationObject(map);
					for(CalculationObject e2 : list2){
						calculationObjectDao.DeleteCalculationObject(e2.getId());
					}
				}
			}else if("objectCode".equals(change)){
				Map<String, Object> map = new HashMap<String, Object>();
				List<CalculationObject> list = calculationObjectDao.GetCalculationObject(map);
				for(CalculationObject e : list){
					if(e.getObjectCode().equals(calculationObject.getObjectCode())){
						result.put("success", false);
						result.put("reason", "编码已存在，请重新修改！");
						return result;
					}
				}
			}
			calculationObjectDao.UpdateCalculationObject(calculationObject);
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject getCalculationObject(String parentCode,String name,String nodeType){
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		final String entity = "";
		final String pos = "CalculationObjectService.getCalculationObject";
		String param = " parentCode:"+parentCode+" name:"+name+" nodeType:"+nodeType;
		logger.info( entity+pos+param  );
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("parentCode", parentCode);
			map.put("objectName", name);
			map.put("nodeType",nodeType);
			List<CalculationObject> list = calculationObjectDao.GetCalculationObject(map);
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
	
	public JSONObject getParentObject(String isSet,String nodeType){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "CalculationObjectService.getParentObject";
		String param = " isSet:"+isSet+" nodeType:"+nodeType;
		logger.info( entity+pos+param  );
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("isSet", isSet);
			map.put("nodeType",nodeType);
			List<CalculationObject> list = calculationObjectDao.GetCalculationObject(map);
			Set<Map<String, Object>> set = new HashSet<Map<String, Object>>();
			for(CalculationObject e:list){
				Map<String, Object> mapSet = new HashMap<String, Object>();
				mapSet.put("parentCode",e.getObjectCode());
				mapSet.put("parentName",e.getObjectName());
				set.add(mapSet);
			}
			result.put("parentObject", set);
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
