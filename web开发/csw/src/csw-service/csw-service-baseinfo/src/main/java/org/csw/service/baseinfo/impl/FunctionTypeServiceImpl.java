package org.csw.service.baseinfo.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.csw.dao.baseinfo.FunctionTypeDao;
import org.csw.entity.baseinfo.FunctionType;
import org.csw.service.baseinfo.FunctionTypeService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class FunctionTypeServiceImpl implements FunctionTypeService {
	private static final Logger logger = Logger.getLogger("com.csw");
	private FunctionTypeDao functionTypeDao = new FunctionTypeDao();
	
	public void setFunctionTypeDao(FunctionTypeDao functionTypeDao){
		this.functionTypeDao = functionTypeDao;
	}
	
	public JSONObject addFunctionType(FunctionType functionType){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "FunctionTypeService.addFunctionType";
		String param = " functionType:"+functionType;
		logger.info( entity+pos+param  );
		try {
			//String id = String.valueOf(Integer.valueOf(functionTypeDao.GetMaxID())+1);
			//functionType.setId(id);
			if(null == functionType.getId() || "".equals(functionType.getId())){
				logger.info( "参数错误，无ID"+param  );
			}
			functionTypeDao.AddFunctionType(functionType);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject getMaxID(){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "FunctionTypeService.getMaxID";
		logger.info( entity+pos );
		try {
			String id = functionTypeDao.GetMaxID();
			result.put("id", id);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject deleteFunctionType(String ids){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "FunctionTypeService.deleteFunctionType";
		String param = " ids:"+ids;
		logger.info( entity+pos+param  );
		try {
			String[] idsStr = ids.split(",");
			for(int i=0;i<idsStr.length;i++){
				functionTypeDao.DeleteFunctionType(idsStr[i]);
			}
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public JSONObject modifyFunctionType(FunctionType functionType){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "FunctionTypeService.modifyFunctionType";
		String param = " functionType:"+functionType;
		logger.info( entity+pos+param  );
		try {
			functionTypeDao.UpdateFunctionType(functionType);
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject getFunctionType(String id,String isSet,String projectName){
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		final String entity = "";
		final String pos = "FunctionTypeService.getFunctionType";
		String param = " id:"+id+" isSet:"+isSet+" projectName:"+projectName;
		logger.info( entity+pos+param  );
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("isSet", isSet);
			map.put("projectName", projectName);
			List<FunctionType> list = functionTypeDao.GetFunctionType(map);
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
}
