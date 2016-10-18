package org.csw.service.baseinfo.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.csw.dao.baseinfo.InputOutputDao;
import org.csw.entity.baseinfo.InputOutput;
import org.csw.service.baseinfo.InputOutputService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class InputOutputServiceImpl implements InputOutputService {
	private static final Logger logger = Logger.getLogger("com.csw");
	private InputOutputDao inputOutputDao = new InputOutputDao();
	
	public void setInputOutputDao(InputOutputDao inputOutputDao){
		this.inputOutputDao = inputOutputDao;
	}
	
	public JSONObject addInputOutput(InputOutput inputOutput){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "InputOutputService.addInputOutput";
		String param = " inputOutput:"+inputOutput;
		logger.info( entity+pos+param  );
		try {
			//String id = String.valueOf(Integer.valueOf(inputOutputDao.GetMaxID())+1);
			//inputOutput.setId(id);
			if(null == inputOutput.getId() || "".equals(inputOutput.getId())){
				logger.info( "参数错误，无ID"+param  );
			}
			inputOutputDao.AddInputOutput(inputOutput);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject getMaxID(){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "InputOutputService.getMaxID";
		logger.info( entity+pos );
		try {
			String id = inputOutputDao.GetMaxID();
			result.put("id", id);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject deleteInputOutput(String ids){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "InputOutputService.deleteInputOutput";
		String param = " ids:"+ids;
		logger.info( entity+pos+param  );
		try {
			String[] idsStr = ids.split(",");
			for(int i=0;i<idsStr.length;i++){
				inputOutputDao.DeleteInputOutput(idsStr[i]);
			}
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public JSONObject modifyInputOutput(InputOutput inputOutput){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "InputOutputService.modifyInputOutput";
		String param = " inputOutput:"+inputOutput;
		logger.info( entity+pos+param  );
		try {
			inputOutputDao.UpdateInputOutput(inputOutput);
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject getInputOutput(String id,String name,String type,String modelCode){
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		final String entity = "";
		final String pos = "InputOutputService.getInputOutput";
		String param = " id:"+id+" name:"+name+" type:"+type+" modelCode:"+modelCode;
		logger.info( entity+pos+param  );
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("name", name);
			map.put("type", type);
			map.put("modelCode", modelCode);
			List<InputOutput> list = inputOutputDao.GetInputOutput(map);
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
	
	public JSONObject copyInputOutput(String codes,String code,String type){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "InputOutputService.copyInputOutput";
		String param = " codes:"+codes+" code:"+code+" type:"+type;
		logger.info( entity+pos+param);
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("modelCode", code);
			map.put("type", type);
			map.put("isSet", "√");
			List<InputOutput> list = inputOutputDao.GetInputOutput(map);
			String[] modelCodes = codes.split(",");
			for(String modelCode : modelCodes){
				map.clear();
				map.put("modelCode", modelCode);
				map.put("type", type);
				for(InputOutput inputOutput : list){
					map.put("projectName", inputOutput.getProjectName());
					List<InputOutput> list2 = inputOutputDao.GetInputOutput(map);
					for(InputOutput inputOutput2 : list2){
						if(!inputOutput2.getIsSet().equals(inputOutput.getIsSet())){
							inputOutput2.setIsSet(inputOutput.getIsSet());
							inputOutputDao.UpdateInputOutput(inputOutput2);
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
}
