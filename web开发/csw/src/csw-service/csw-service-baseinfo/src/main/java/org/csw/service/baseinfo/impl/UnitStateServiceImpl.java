package org.csw.service.baseinfo.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.csw.dao.baseinfo.UnitStateDao;
import org.csw.entity.baseinfo.UnitState;
import org.csw.service.baseinfo.UnitStateService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class UnitStateServiceImpl implements UnitStateService {
	private static final Logger logger = Logger.getLogger("com.csw");
	private UnitStateDao unitStateDao = new UnitStateDao();
	
	public void setUnitStateDao(UnitStateDao unitStateDao){
		this.unitStateDao = unitStateDao;
	}
	
	public JSONObject addUnitState(UnitState unitState,Integer flag){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "UnitStateService.addUnitState";
		String param = " unitState:"+unitState;
		logger.info( entity+pos+param  );
		try {
			//String id = String.valueOf(Integer.valueOf(unitStateDao.GetMaxID())+1);
			//unitState.setId(id);
			if(null == unitState.getId() || "".equals(unitState.getId())){
				logger.info( "参数错误，无ID"+param  );
			}
			unitStateDao.AddUnitState(unitState,flag);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject getMaxID(Integer flag){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "UnitStateService.getMaxID";
		logger.info( entity+pos );
		try {
			String id = unitStateDao.GetMaxID(flag);
			result.put("id", id);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject deleteUnitState(String ids,Integer flag){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "UnitStateService.deleteUnitState";
		String param = " ids:"+ids;
		logger.info( entity+pos+param  );
		try {
			String[] idsStr = ids.split(",");
			for(int i=0;i<idsStr.length;i++){
				unitStateDao.DeleteUnitState(idsStr[i],flag);
			}
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public JSONObject modifyUnitState(UnitState unitState,Integer flag){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "UnitStateService.modifyUnitState";
		String param = " unitState:"+unitState;
		logger.info( entity+pos+param  );
		try {
			unitStateDao.UpdateUnitState(unitState,flag);
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject getUnitState(String id,String name,Integer flag){
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		final String entity = "";
		final String pos = "UnitStateService.getUnitState";
		String param = " id:"+id+" name:"+name;
		logger.info( entity+pos+param  );
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("name", name);
			List<UnitState> list = unitStateDao.GetUnitState(map,flag);
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
