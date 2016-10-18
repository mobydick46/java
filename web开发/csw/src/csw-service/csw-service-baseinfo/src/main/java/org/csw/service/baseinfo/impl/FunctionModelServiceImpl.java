package org.csw.service.baseinfo.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.csw.dao.baseinfo.FunctionModelDao;
import org.csw.dao.baseinfo.ModelMethodDao;
import org.csw.entity.baseinfo.FunctionModel;
import org.csw.entity.baseinfo.ModelMethod;
import org.csw.service.baseinfo.FunctionModelService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class FunctionModelServiceImpl implements FunctionModelService {
	private static final Logger logger = Logger.getLogger("com.csw");
	private FunctionModelDao functionModelDao = new FunctionModelDao();
	
	public void setFunctionModelDao(FunctionModelDao functionModelDao){
		this.functionModelDao = functionModelDao;
	}
	
	private ModelMethodDao modelMethodDao = new ModelMethodDao();
	public void setModelMethodDao(ModelMethodDao modelMethodDao){
		this.modelMethodDao = modelMethodDao;
	}
	
	public JSONObject addFunctionModel(FunctionModel functionModel){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "FunctionModelService.addFunctionModel";
		String param = " functionModel:"+functionModel;
		logger.info( entity+pos+param  );
		try {
			//String id = String.valueOf(Integer.valueOf(functionModelDao.GetMaxID())+1);
			//functionModel.setId(id);
			if(null == functionModel.getId() || "".equals(functionModel.getId())){
				logger.info( "参数错误，无ID"+param  );
			}
			functionModelDao.AddFunctionModel(functionModel);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject getMaxID(){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "FunctionModelService.getMaxID";
		logger.info( entity+pos );
		try {
			String id = functionModelDao.GetMaxID();
			result.put("id", id);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject deleteFunctionModel(String ids){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "FunctionModelService.deleteFunctionModel";
		String param = " ids:"+ids;
		logger.info( entity+pos+param  );
		try {
			String[] idsStr = ids.split(",");
			for(int i=0;i<idsStr.length;i++){
				functionModelDao.DeleteFunctionModel(idsStr[i]);
			}
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public JSONObject modifyFunctionModel(FunctionModel functionModel){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "FunctionModelService.modifyFunctionModel";
		String param = " functionModel:"+functionModel;
		logger.info( entity+pos+param  );
		try {
			functionModelDao.UpdateFunctionModel(functionModel);
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject getFunctionModel(String type,String functionId,String funCalculationId){
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		final String entity = "";
		final String pos = "FunctionModelService.getFunctionModel";
		String param = " type:"+type+" functionId:"+functionId+" funCalculationId:"+funCalculationId;
		logger.info( entity+pos+param  );
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("type", type);
			map.put("functionId", functionId);
			map.put("funCalculationId", funCalculationId);
			List<FunctionModel> list = functionModelDao.GetFunctionModel(map);
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
	
	public JSONObject refreshFunctionModel(String functionId, String funCalculationId,String type,String functionType){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "FunctionModelService.refreshFunctionModel";
		String param = " functionId:"+functionId+" funCalculationId:"+funCalculationId+" type:"+type+" functionType:"+functionType;
		logger.info( entity+pos+param  );
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("combCalculation", "");
			map.put("type", functionType);
			if("2".equals(type)){
				map.put("combCalculation", "√");
			}
			List<ModelMethod> list = modelMethodDao.GetModelMethod(map);
			logger.info( "hhhhhhhhhhhhhhhhhList<ModelMethod>"+list.size() );
			
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("functionId", functionId);
			map2.put("funCalculationId", funCalculationId);
			map2.put("type", type);
			List<FunctionModel> list2 = functionModelDao.GetFunctionModel(map2);
			logger.info( "hhhhhhhhhhhhhhhhhList<FunctionModel>"+list2.size());
			for(ModelMethod modelMethod:list){
				int flag = 0;
				for(FunctionModel functionModel : list2){
					if(modelMethod.getCode().equals(functionModel.getModelCode())){
						flag = 1;
						list2.remove(functionModel);
						break;
					}
				}
				if(0==flag){
					FunctionModel functionModel = new FunctionModel();
					String id = functionModelDao.GetMaxID();
					if(null == id || "".equals(id)){
						id = "1";
					}
					functionModel.setId(String.valueOf(Integer.valueOf(id)+1));
					functionModel.setModelCode(modelMethod.getCode());
					functionModel.setModelName(modelMethod.getName());
					functionModel.setDescription(modelMethod.getDescription());
					functionModel.setModelType("F");
					functionModel.setIsApply("");
					functionModel.setFunCalculationId(funCalculationId);
					functionModel.setFunctionId(functionId);
					functionModel.setType(type);
					functionModelDao.AddFunctionModel(functionModel);
				}
			}
			for(FunctionModel functionModel : list2){
				functionModelDao.DeleteFunctionModel(functionModel.getId());
			}
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject batchsetFunctionModel(String funCalculationId,String ids){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "FunctionModelService.batchsetFunctionModel";
		String param = " funCalculationId:"+funCalculationId+" ids:"+ids;
		logger.info( entity+pos+param  );
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("funCalculationId", funCalculationId);
			List<FunctionModel> list = functionModelDao.GetFunctionModel(map);
			logger.info( "hhhhhhhhhhhhhhhhhList<FunctionModel1>"+list.size() );
			
			String[] idsStr = ids.split(",");
			for(int i=0;i<idsStr.length;i++){
				map.put("funCalculationId", idsStr[i]);
				List<FunctionModel> list2 = functionModelDao.GetFunctionModel(map);
				logger.info( "hhhhhhhhhhhhhhhhhList<FunctionModel2>"+list2.size() );
				for(FunctionModel functionModel1:list){
					int flag = 0;
					for(FunctionModel functionModel2 : list2){
						if(functionModel1.getModelCode().equals(functionModel2.getModelCode())){
							flag = 1;
							list2.remove(functionModel2);
							break;
						}
					}
					if(0==flag){
						//FunctionModel functionModel = new FunctionModel();
						String id = functionModelDao.GetMaxID();
						if(null == id || "".equals(id)){
							id = "0";
						}
						functionModel1.setId(String.valueOf(Integer.valueOf(id)+1));
						functionModel1.setFunCalculationId(idsStr[i]);
						functionModelDao.AddFunctionModel(functionModel1);
					}
				}
			}
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
}
