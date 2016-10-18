package org.csw.service.baseinfo.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.csw.dao.baseinfo.DataUnitDao;
import org.csw.entity.baseinfo.DataUnit;
import org.csw.service.baseinfo.DataUnitService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class DataUnitServiceImpl implements DataUnitService {
	private static final Logger logger = Logger.getLogger("com.csw");
	private DataUnitDao dataUnitDao = new DataUnitDao();
	
	public void setDataUnitDao(DataUnitDao dataUnitDao){
		this.dataUnitDao = dataUnitDao;
	}
	
	public JSONObject addDataUnit(DataUnit dataUnit){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "DataUnitService.addDataUnit";
		String param = " dataUnit:"+dataUnit;
		logger.info( entity+pos+param  );
		try {
			//String id = String.valueOf(Integer.valueOf(dataUnitDao.GetMaxID())+1);
			//dataUnit.setId(id);
			if(null == dataUnit.getId() || "".equals(dataUnit.getId())){
				logger.info( "参数错误，无ID"+param  );
			}
			dataUnitDao.AddDataUnit(dataUnit);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject getMaxID(){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "DataUnitService.getMaxID";
		logger.info( entity+pos );
		try {
			String id = dataUnitDao.GetMaxID();
			result.put("id", id);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject deleteDataUnit(String ids){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "DataUnitService.deleteDataUnit";
		String param = " ids:"+ids;
		logger.info( entity+pos+param  );
		try {
			String[] idsStr = ids.split(",");
			for(int i=0;i<idsStr.length;i++){
				dataUnitDao.DeleteDataUnit(idsStr[i]);
			}
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public JSONObject modifyDataUnit(DataUnit dataUnit){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "DataUnitService.modifyDataUnit";
		String param = " dataUnit:"+dataUnit;
		logger.info( entity+pos+param  );
		try {
			dataUnitDao.UpdateDataUnit(dataUnit);
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject getDataUnit(String id,String name){
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		final String entity = "";
		final String pos = "DataUnitService.getDataUnit";
		String param = " id:"+id+" name:"+name;
		logger.info( entity+pos+param  );
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("name", name);
			List<DataUnit> list = dataUnitDao.GetDataUnit(map);
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
