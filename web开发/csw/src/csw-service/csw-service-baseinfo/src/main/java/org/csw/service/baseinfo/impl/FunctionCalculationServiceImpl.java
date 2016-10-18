package org.csw.service.baseinfo.impl;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.csw.dao.baseinfo.CalculationObjectDao;
import org.csw.dao.baseinfo.FunctionCalculationDao;
import org.csw.dao.baseinfo.FunctionTypeDao;
import org.csw.entity.baseinfo.CalculationObject;
import org.csw.entity.baseinfo.FunctionCalculation;
import org.csw.entity.baseinfo.FunctionType;
import org.csw.service.baseinfo.FunctionCalculationService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class FunctionCalculationServiceImpl implements FunctionCalculationService {
	private static final Logger logger = Logger.getLogger("com.csw");
	private FunctionCalculationDao functionCalculationDao = new FunctionCalculationDao();
	
	public void setFunctionCalculationDao(FunctionCalculationDao functionCalculationDao){
		this.functionCalculationDao = functionCalculationDao;
	}
	
    private CalculationObjectDao calculationObjectDao = new CalculationObjectDao();
	
	public void setCalculationObjectDao(CalculationObjectDao calculationObjectDao){
		this.calculationObjectDao = calculationObjectDao;
	}
	
   private FunctionTypeDao functionTypeDao = new FunctionTypeDao();
	
	public void setFunctionTypeDao(FunctionTypeDao functionTypeDao){
		this.functionTypeDao = functionTypeDao;
	}
	
	public JSONObject addFunctionCalculation(FunctionCalculation functionCalculation){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "FunctionCalculationService.addFunctionCalculation";
		String param = " functionCalculation:"+functionCalculation;
		logger.info( entity+pos+param  );
		try {
			//String id = String.valueOf(Integer.valueOf(functionCalculationDao.GetMaxID())+1);
			//functionCalculation.setId(id);
			if(null == functionCalculation.getId() || "".equals(functionCalculation.getId())){
				logger.info( "参数错误，无ID"+param  );
			}
			functionCalculationDao.AddFunctionCalculation(functionCalculation);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject getMaxID(){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "FunctionCalculationService.getMaxID";
		logger.info( entity+pos );
		try {
			String id = functionCalculationDao.GetMaxID();
			result.put("id", id);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject deleteFunctionCalculation(String ids){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "FunctionCalculationService.deleteFunctionCalculation";
		String param = " ids:"+ids;
		logger.info( entity+pos+param  );
		try {
			String[] idsStr = ids.split(",");
			for(int i=0;i<idsStr.length;i++){
				functionCalculationDao.DeleteFunctionCalculation(idsStr[i]);
			}
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public JSONObject modifyFunctionCalculation(FunctionCalculation functionCalculation){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "FunctionCalculationService.modifyFunctionCalculation";
		String param = " functionCalculation:"+functionCalculation;
		logger.info( entity+pos+param  );
		try {
			functionCalculationDao.UpdateFunctionCalculation(functionCalculation);
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject getFunctionCalculation(String parentCode,String nodeType,String functionId){
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		final String entity = "";
		final String pos = "FunctionCalculationService.getFunctionCalculation";
		String param = " parentCode:"+parentCode+" nodeType:"+nodeType+" functionId:"+functionId;
		logger.info( entity+pos+param  );
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("parentCode", parentCode);
			map.put("nodeType", nodeType);
			map.put("functionId", functionId);
			List<FunctionCalculation> list = functionCalculationDao.GetFunctionCalculation(map);
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
	
	public JSONObject refreshFunctionCalculation(String functionId){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "FunctionCalculationService.refreshFunctionCalculation";
		String param = " functionId:"+functionId;
		logger.info( entity+pos+param  );
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			List<FunctionCalculation> listAll = functionCalculationDao.GetFunctionCalculation(map);
			logger.info( "hhhhhhhhhhhhhhhhhList<FunctionCalculation> listAll"+listAll.size());
			map.put("isSet", "√");
			List<FunctionType> listFType = functionTypeDao.GetFunctionType(map);
			logger.info( "hhhhhhhhhhhhhhhhhList<FunctionType>"+listFType.size() );
			List<CalculationObject> list = calculationObjectDao.GetCalculationObject(map);
			logger.info( "hhhhhhhhhhhhhhhhhList<CalculationObject>"+list.size() );
			for(FunctionType FType : listFType){
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("functionId", FType.getId());
				List<FunctionCalculation> list2 = functionCalculationDao.GetFunctionCalculation(map2);
				logger.info( "hhhhhhhhhhhhhhhhhList<FunctionCalculation> list2"+list2.size());
				listAll.removeAll(list2);
				logger.info( "hhhhhhhhhhhhhhhhhList<FunctionCalculation> listAll"+listAll.size());
				for(CalculationObject calculationObject:list){
					int flag = 0;
					for(FunctionCalculation functionCalculation : list2){
						if(calculationObject.getObjectCode().equals(functionCalculation.getObjectCode())
						   && calculationObject.getNodeType().equals(functionCalculation.getNodeType())
						   && calculationObject.getParentCode().equals(functionCalculation.getParentCode())){
							flag = 1;
							list2.remove(functionCalculation);
							break;
						}
					}
					if(0==flag){
						FunctionCalculation functionCalculation = new FunctionCalculation();
						String id = functionCalculationDao.GetMaxID();
						if(null == id || "".equals(id)){
							id = "0";
						}else{
							id = String.valueOf(Integer.valueOf(id)+1);
						}
						functionCalculation.setId(id);
						functionCalculation.setObjectCode(calculationObject.getObjectCode());
						functionCalculation.setObjectName(calculationObject.getObjectName());
						functionCalculation.setParentCode(calculationObject.getParentCode());
						functionCalculation.setParentName(calculationObject.getParentName());
						functionCalculation.setIsCalculation("√");
						functionCalculation.setNodeType(calculationObject.getNodeType());
						functionCalculation.setFunctionId(FType.getId());
						functionCalculationDao.AddFunctionCalculation(functionCalculation);
					}
				}
				for(FunctionCalculation functionCalculation : list2){
					functionCalculationDao.DeleteFunctionCalculation(functionCalculation.getId());
				}
			}
			logger.info( "hhhhhhhhhhhhhhhhhList<FunctionCalculation> listAll"+listAll.size());
			for(FunctionCalculation functionCalculation : listAll){
				functionCalculationDao.DeleteFunctionCalculation(functionCalculation.getId());
			}
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject getType(String functionId){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "FunctionCalculationService.getType";
		String param = " functionId:"+functionId;
		logger.info( entity+pos+param  );
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("functionId", functionId);
			List<FunctionCalculation> list = functionCalculationDao.GetFunctionCalculation(map);
			Set<String> set = new HashSet<String>();
			for(FunctionCalculation e:list){
				set.add(e.getNodeType());
			}
			result.put("data", set);
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject getType2(String functionId,String isCalculation){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "FunctionCalculationService.getType";
		String param = " functionId:"+functionId+" isCalculation:"+isCalculation;
		logger.info( entity+pos+param  );
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("functionId", functionId);
			map.put("isCalculation", isCalculation);
			map.put("nodeType", '1');
			List<FunctionCalculation> list = functionCalculationDao.GetFunctionCalculation(map);
			Set<Map<String, Object>> set = new HashSet<Map<String, Object>>();
			for(FunctionCalculation e:list){
				Map<String, Object> mapSet = new HashMap<String, Object>();
				mapSet.put("funCalculationId",e.getId());
				mapSet.put("objectName",e.getObjectName());
				set.add(mapSet);
			}
			result.put("data", set);
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject getParent(String functionId,String nodeType){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "FunctionCalculationService.getType";
		String param = " functionId:"+functionId+" nodeType:"+nodeType;
		logger.info( entity+pos+param  );
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("functionId", functionId);
			map.put("nodeType", nodeType);
			List<FunctionCalculation> list = functionCalculationDao.GetFunctionCalculation(map);
			Set<Map<String, Object>> set = new HashSet<Map<String, Object>>();
			for(FunctionCalculation e:list){
				Map<String, Object> mapSet = new HashMap<String, Object>();
				mapSet.put("parentName",e.getParentName());
				mapSet.put("parentCode",e.getParentCode());
				set.add(mapSet);
			}
			result.put("data", set);
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
