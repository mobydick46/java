package org.csw.service.baseinfo.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.csw.dao.baseinfo.PredictionSchemeDao;
import org.csw.entity.baseinfo.PredictionScheme;
import org.csw.service.baseinfo.PredictionSchemeService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class PredictionSchemeServiceImpl implements PredictionSchemeService {
	private static final Logger logger = Logger.getLogger("com.csw");
	private PredictionSchemeDao predictionSchemeDao = new PredictionSchemeDao();
	
	public void setPredictionSchemeDao(PredictionSchemeDao predictionSchemeDao){
		this.predictionSchemeDao = predictionSchemeDao;
	}
	
	public JSONObject addPredictionScheme(PredictionScheme predictionScheme){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "PredictionSchemeService.addPredictionScheme";
		String param = " predictionScheme:"+predictionScheme;
		logger.info( entity+pos+param  );
		try {
			//String id = String.valueOf(Integer.valueOf(predictionSchemeDao.GetMaxID())+1);
			//predictionScheme.setId(id);
			if(null == predictionScheme.getId() || "".equals(predictionScheme.getId())){
				logger.info( "参数错误，无ID"+param  );
			}
			predictionSchemeDao.AddPredictionScheme(predictionScheme);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject getMaxID(){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "PredictionSchemeService.getMaxID";
		logger.info( entity+pos );
		try {
			String id = predictionSchemeDao.GetMaxID();
			result.put("id", id);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject deletePredictionScheme(String ids){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "PredictionSchemeService.deletePredictionScheme";
		String param = " ids:"+ids;
		logger.info( entity+pos+param  );
		try {
			String[] idsStr = ids.split(",");
			for(int i=0;i<idsStr.length;i++){
				predictionSchemeDao.DeletePredictionScheme(idsStr[i]);
			}
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public JSONObject modifyPredictionScheme(PredictionScheme predictionScheme){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "PredictionSchemeService.modifyPredictionScheme";
		String param = " predictionScheme:"+predictionScheme;
		logger.info( entity+pos+param  );
		try {
			predictionSchemeDao.UpdatePredictionScheme(predictionScheme);
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject getPredictionScheme(String id,String name,String funCalculationId){
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		final String entity = "";
		final String pos = "PredictionSchemeService.getPredictionScheme";
		String param = " id:"+id+" name:"+name+" funCalculationId:"+funCalculationId;
		logger.info( entity+pos+param  );
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("name", name);
			map.put("funCalculationId", funCalculationId);
			List<PredictionScheme> list = predictionSchemeDao.GetPredictionScheme(map);
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
