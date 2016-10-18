package org.csw.service.baseinfo.impl;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.csw.dao.baseinfo.InputOutputDao;
import org.csw.dao.baseinfo.ModelMethodDao;
import org.csw.entity.baseinfo.DefaultData;
import org.csw.entity.baseinfo.InputOutput;
import org.csw.entity.baseinfo.ModelMethod;
import org.csw.service.baseinfo.ModelMethodService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class ModelMethodServiceImpl implements ModelMethodService {
	private static final Logger logger = Logger.getLogger("com.csw");
	private ModelMethodDao modelMethodDao = new ModelMethodDao();
	
	public void setModelMethodDao(ModelMethodDao modelMethodDao){
		this.modelMethodDao = modelMethodDao;
	}
    private InputOutputDao inputOutputDao = new InputOutputDao();
	
	public void setInputOutputDao(InputOutputDao inputOutputDao){
		this.inputOutputDao = inputOutputDao;
	}
	
	public JSONObject addModelMethod(ModelMethod modelMethod){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "ModelMethodService.addModelMethod";
		String param = " modelMethod:"+modelMethod;
		logger.info( entity+pos+param  );
		try {
			//String id = String.valueOf(Integer.valueOf(modelMethodDao.GetMaxID())+1);
			//modelMethod.setId(id);
			if(null == modelMethod.getId() || "".equals(modelMethod.getId())){
				logger.info( "参数错误，无ID"+param  );
			}
			modelMethodDao.AddModelMethod(modelMethod);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject getMaxID(){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "ModelMethodService.getMaxID";
		logger.info( entity+pos );
		try {
			String id = modelMethodDao.GetMaxID();
			result.put("id", id);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject deleteModelMethod(String ids){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "ModelMethodService.deleteModelMethod";
		String param = " ids:"+ids;
		logger.info( entity+pos+param  );
		try {
			String[] idsStr = ids.split(",");
			for(int i=0;i<idsStr.length;i++){
				modelMethodDao.DeleteModelMethod(idsStr[i]);
			}
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public JSONObject modifyModelMethod(ModelMethod modelMethod){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "ModelMethodService.modifyModelMethod";
		String param = " modelMethod:"+modelMethod;
		logger.info( entity+pos+param  );
		try {
			modelMethodDao.UpdateModelMethod(modelMethod);
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject getModelMethod(String id,String name,String combCalculation){
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		final String entity = "";
		final String pos = "ModelMethodService.getModelMethod";
		String param = " id:"+id+" name:"+name+" combCalculation"+combCalculation;
		logger.info( entity+pos+param  );
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("name", name);
			map.put("combCalculation", combCalculation);
			List<ModelMethod> list = modelMethodDao.GetModelMethod(map);
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
	
	public JSONObject addInputOutput(String combCalculation){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "ModelMethodService.addInputOutput";
		String param = " combCalculation"+combCalculation;
		logger.info( entity+pos+param  );
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			List<InputOutput> listInOut = inputOutputDao.GetInputOutput(map);
			Set<String> codes = new HashSet<String>();
			for(InputOutput inOut : listInOut){
				codes.add(inOut.getModelCode());
			}
			map.put("combCalculation", combCalculation);
			List<ModelMethod> list = modelMethodDao.GetModelMethod(map);
			
			for(ModelMethod mothed : list){
				int flag = 0;
				for(String code : codes){
					if(code.equals(mothed.getCode())){
						flag = 1;
						codes.remove(code);
						break;
					}
				}
				if(0 == flag){
					InputOutput inputOutput = new InputOutput();
					inputOutput.setModelCode(mothed.getCode());
					logger.info( "111111111111111DefaultData.getSingleInput().length:"+DefaultData.getSingleInput().length );
					for(int i=0;i<DefaultData.getSingleInput().length;i++){
						String id = inputOutputDao.GetMaxID();
						if(null == id || "".equals(id)){
							id = "0";
						}
						inputOutput.setId(String.valueOf(Integer.valueOf(id)+1));
						inputOutput.setType("3");
						inputOutput.setProjectName(DefaultData.getSingleInput()[i][0]);
						inputOutput.setDescription(DefaultData.getSingleInput()[i][1]);
						inputOutput.setIsSet("");
						inputOutputDao.AddInputOutput(inputOutput);
					}
					logger.info( "111111111111111DefaultData.getProcessInput().length:"+DefaultData.getProcessInput().length );
					for(int i=0;i<DefaultData.getProcessInput().length;i++){
						String id = inputOutputDao.GetMaxID();
						if(null == id || "".equals(id)){
							id = "0";
						}
						inputOutput.setId(String.valueOf(Integer.valueOf(id)+1));
						inputOutput.setType("4");
						inputOutput.setProjectName(DefaultData.getProcessInput()[i][0]);
						inputOutput.setDescription(DefaultData.getProcessInput()[i][1]);
						inputOutput.setIsSet("");
						inputOutputDao.AddInputOutput(inputOutput);
					}
					logger.info( "111111111111111DefaultData.getBoundaryConstraints().length:"+DefaultData.getBoundaryConstraints().length );
					for(int i=0;i<DefaultData.getBoundaryConstraints().length;i++){
						String id = inputOutputDao.GetMaxID();
						if(null == id || "".equals(id)){
							id = "0";
						}
						inputOutput.setId(String.valueOf(Integer.valueOf(id)+1));
						inputOutput.setType("0");
						inputOutput.setProjectName(DefaultData.getBoundaryConstraints()[i][0]);
						inputOutput.setDescription(DefaultData.getBoundaryConstraints()[i][1]);
						inputOutput.setIsSet("");
						inputOutputDao.AddInputOutput(inputOutput);
					}
					logger.info( "111111111111111DefaultData.getOutputResult().length:"+DefaultData.getOutputResult().length );
					for(int i=0;i<DefaultData.getOutputResult().length;i++){
						String id = inputOutputDao.GetMaxID();
						if(null == id || "".equals(id)){
							id = "0";
						}
						inputOutput.setId(String.valueOf(Integer.valueOf(id)+1));
						inputOutput.setType("1");
						inputOutput.setProjectName(DefaultData.getOutputResult()[i][0]);
						inputOutput.setDescription(DefaultData.getOutputResult()[i][1]);
						inputOutput.setIsSet("");
						inputOutputDao.AddInputOutput(inputOutput);
					}
				}
			}
			for(String code : codes){
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("modelCode", code);
				List<InputOutput> list2 = inputOutputDao.GetInputOutput(map2);
				for(InputOutput inputOutput : list2){
					inputOutputDao.DeleteInputOutput(inputOutput.getId());
				}
			}
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
