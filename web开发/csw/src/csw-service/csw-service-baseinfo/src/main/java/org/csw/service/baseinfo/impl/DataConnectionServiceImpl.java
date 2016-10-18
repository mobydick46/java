package org.csw.service.baseinfo.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.csw.dao.baseinfo.DataConnectionDao;
import org.csw.entity.baseinfo.DataConnection;
import org.csw.service.baseinfo.DataConnectionService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class DataConnectionServiceImpl implements DataConnectionService {
	private static final Logger logger = Logger.getLogger("com.csw");
	private DataConnectionDao dataConnectionDao = new DataConnectionDao();
	
	public void setDataConnectionDao(DataConnectionDao dataConnectionDao){
		this.dataConnectionDao = dataConnectionDao;
	}
	
	public JSONObject addDataConnection(DataConnection dataConnection){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "DataConnectionService.addDataConnection";
		String param = " dataConnection:"+dataConnection;
		logger.info( entity+pos+param  );
		try {
			//String id = String.valueOf(Integer.valueOf(dataConnectionDao.GetMaxID())+1);
			//dataConnection.setId(id);
			if(null == dataConnection.getId() || "".equals(dataConnection.getId())){
				logger.info( "参数错误，无ID"+param  );
			}
			dataConnectionDao.AddDataConnection(dataConnection);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject getMaxID(){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "DataConnectionService.getMaxID";
		logger.info( entity+pos );
		try {
			String id = dataConnectionDao.GetMaxID();
			result.put("id", id);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject deleteDataConnection(String ids){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "DataConnectionService.deleteDataConnection";
		String param = " ids:"+ids;
		logger.info( entity+pos+param  );
		try {
			String[] idsStr = ids.split(",");
			for(int i=0;i<idsStr.length;i++){
				dataConnectionDao.DeleteDataConnection(idsStr[i]);
			}
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public JSONObject modifyDataConnection(DataConnection dataConnection){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "DataConnectionService.modifyDataConnection";
		String param = " dataConnection:"+dataConnection;
		logger.info( entity+pos+param  );
		try {
			dataConnectionDao.UpdateDataConnection(dataConnection);
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject getDataConnection(String id,String name){
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		final String entity = "";
		final String pos = "DataConnectionService.getDataConnection";
		String param = " id:"+id+" name:"+name;
		logger.info( entity+pos+param  );
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("name", name);
			List<DataConnection> list = dataConnectionDao.GetDataConnection(map);
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
