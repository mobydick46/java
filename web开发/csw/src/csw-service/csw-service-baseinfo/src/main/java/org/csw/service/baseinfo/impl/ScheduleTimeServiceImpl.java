package org.csw.service.baseinfo.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.csw.dao.baseinfo.ScheduleTimeDao;
import org.csw.entity.baseinfo.ScheduleTime;
import org.csw.service.baseinfo.ScheduleTimeService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class ScheduleTimeServiceImpl implements ScheduleTimeService {
	private static final Logger logger = Logger.getLogger("com.csw");
	private ScheduleTimeDao scheduleTimeDao = new ScheduleTimeDao();
	
	public void setScheduleTimeDao(ScheduleTimeDao scheduleTimeDao){
		this.scheduleTimeDao = scheduleTimeDao;
	}
	
	public JSONObject addScheduleTime(ScheduleTime scheduleTime){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "ScheduleTimeService.addScheduleTime";
		String param = " scheduleTime:"+scheduleTime;
		logger.info( entity+pos+param  );
		try {
			//String id = String.valueOf(Integer.valueOf(scheduleTimeDao.GetMaxID())+1);
			//scheduleTime.setId(id);
			if(null == scheduleTime.getId() || "".equals(scheduleTime.getId())){
				logger.info( "参数错误，无ID"+param  );
			}
			scheduleTimeDao.AddScheduleTime(scheduleTime);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject getMaxID(){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "ScheduleTimeService.getMaxID";
		logger.info( entity+pos );
		try {
			String id = scheduleTimeDao.GetMaxID();
			result.put("id", id);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject deleteScheduleTime(String ids){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "ScheduleTimeService.deleteScheduleTime";
		String param = " ids:"+ids;
		logger.info( entity+pos+param  );
		try {
			String[] idsStr = ids.split(",");
			for(int i=0;i<idsStr.length;i++){
				scheduleTimeDao.DeleteScheduleTime(idsStr[i]);
			}
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public JSONObject modifyScheduleTime(ScheduleTime scheduleTime){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "ScheduleTimeService.modifyScheduleTime";
		String param = " scheduleTime:"+scheduleTime;
		logger.info( entity+pos+param  );
		try {
			scheduleTimeDao.UpdateScheduleTime(scheduleTime);
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject getScheduleTime(String id,String name,String functionId){
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		final String entity = "";
		final String pos = "ScheduleTimeService.getScheduleTime";
		String param = " id:"+id+" name:"+name+" functionId"+functionId;
		logger.info( entity+pos+param  );
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("name", name);
			map.put("functionId", functionId);
			List<ScheduleTime> list = scheduleTimeDao.GetScheduleTime(map);
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
