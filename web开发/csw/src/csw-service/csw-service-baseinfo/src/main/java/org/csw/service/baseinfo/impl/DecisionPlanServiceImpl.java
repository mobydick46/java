package org.csw.service.baseinfo.impl;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.csw.dao.baseinfo.DecisionPlanDao;
import org.csw.dao.baseinfo.FunctionCalculationDao;
import org.csw.dao.baseinfo.InputOutputDao;
import org.csw.dao.baseinfo.ScheduleTimeDao;
import org.csw.entity.baseinfo.DecisionPlan;
import org.csw.entity.baseinfo.FunctionCalculation;
import org.csw.entity.baseinfo.InputOutput;
import org.csw.entity.baseinfo.ScheduleTime;
import org.csw.service.baseinfo.DecisionPlanService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DecisionPlanServiceImpl implements DecisionPlanService {
	private static final Logger logger = Logger.getLogger("com.csw");
	private DecisionPlanDao decisionPlanDao = new DecisionPlanDao();
	public void setDecisionPlanDao(DecisionPlanDao decisionPlanDao){
		this.decisionPlanDao = decisionPlanDao;
	}
	
    private FunctionCalculationDao functionCalculationDao = new FunctionCalculationDao();
	public void setFunctionCalculationDao(FunctionCalculationDao functionCalculationDao){
		this.functionCalculationDao = functionCalculationDao;
	}
	
    private ScheduleTimeDao scheduleTimeDao = new ScheduleTimeDao();
	public void setScheduleTimeDao(ScheduleTimeDao scheduleTimeDao){
		this.scheduleTimeDao = scheduleTimeDao;
	}
	private InputOutputDao inputOutputDao = new InputOutputDao();
	public void setInputOutputDao(InputOutputDao inputOutputDao){
		this.inputOutputDao = inputOutputDao;
	}
	
	public JSONObject addDecisionPlan(String number,String numberSource){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "DecisionPlanService.addDecisionPlan";
		String param = " number:"+number+" numberSource:"+numberSource;
		logger.info( entity+pos+param  );
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("number", numberSource);
			List<DecisionPlan> planlist = decisionPlanDao.GetDecisionPlan(map);
			DecisionPlan decisionPlan = new DecisionPlan();
			decisionPlan.setNumber(number);
			for(DecisionPlan plan : planlist){
				String id = decisionPlanDao.GetMaxID();
				if(null == id || "".equals(id)){
					id = "0";
				}
				decisionPlan.setId(String.valueOf(Integer.valueOf(id)+1));
				decisionPlan.setFunctionId(plan.getFunctionId());
				decisionPlan.setObjectCode(plan.getObjectCode());
				decisionPlan.setObjectNumber("");
				decisionPlan.setPlanName("");
				decisionPlan.setReservoirObject(plan.getReservoirObject());
				decisionPlan.setTimeID(plan.getTimeID());
				decisionPlanDao.AddDecisionPlan(decisionPlan);
			}
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject addDecisionPlan2(String number,String timeID,String functionId){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "DecisionPlanService.addDecisionPlan2";
		String param = " number:"+number+" timeID:"+timeID+" functionId:"+functionId;
		logger.info( entity+pos+param  );
		try {
			/*加载FunctionCalculation信息*/
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("nodeType", 1);
			map.put("isCalculation", "√");
			List<FunctionCalculation> listcal = functionCalculationDao.GetFunctionCalculation(map);
			/*加载所有的DecisionPlan信息*/
			//List<DecisionPlan> planlist = decisionPlanDao.GetDecisionPlan(map);
			DecisionPlan decisionPlan = new DecisionPlan();
			decisionPlan.setNumber(number);
			decisionPlan.setTimeID(timeID);
			decisionPlan.setFunctionId(functionId);
			for(FunctionCalculation cal : listcal){
				String id = decisionPlanDao.GetMaxID();
				if(null == id || "".equals(id)){
					id = "0";
				}
				decisionPlan.setId(String.valueOf(Integer.valueOf(id)+1));
				//decisionPlan.setFunctionId(plan.getFunctionId());
				decisionPlan.setObjectCode(cal.getObjectCode());
				decisionPlan.setObjectNumber("");
				decisionPlan.setPlanName("");
				decisionPlan.setReservoirObject(cal.getObjectName());
				//decisionPlan.setTimeID(plan.getTimeID());
				decisionPlanDao.AddDecisionPlan(decisionPlan);
			}
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject getMaxID(){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "DecisionPlanService.getMaxID";
		logger.info( entity+pos );
		try {
			String id = decisionPlanDao.GetMaxID();
			result.put("id", id);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject getMaxNumber(){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "DecisionPlanService.getMaxNumber";
		logger.info( entity+pos );
		try {
			String number = decisionPlanDao.GetMaxNumber();
			result.put("number", number);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject deleteDecisionPlan(String ids){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "DecisionPlanService.deleteDecisionPlan";
		String param = " ids:"+ids;
		logger.info( entity+pos+param  );
		try {
			String[] idsStr = ids.split(",");
			Map<String, Object> map = new HashMap<String, Object>();
			for(int i=0;i<idsStr.length;i++){
				map.put("number", idsStr[i]);
				List<DecisionPlan> planlist = decisionPlanDao.GetDecisionPlan(map);
				for(DecisionPlan plan : planlist){
					decisionPlanDao.DeleteDecisionPlan(plan.getId());
				}
			}
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public JSONObject modifyDecisionPlan(String key,String value,String number,String functionId,String timeID){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "DecisionPlanService.modifyDecisionPlan";
		String param = " key:"+key+" value:"+value+" number:"+number+" functionId:"+functionId+" timeID:"+timeID;
		logger.info( entity+pos+param  );
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if("planName".equals(key)){
				/*判断修改的项目名称是否已经存在*/
				map.put("functionId", functionId);
				map.put("timeID", timeID);
				List<DecisionPlan> list = decisionPlanDao.GetDecisionPlan(map);
				int flag = 0;
				for(DecisionPlan plan : list){
					if(value.equals(plan.getPlanName())){
						flag = 1;
						break;
					}
				}
				if(1 == flag){
					result.put("success", false);
					result.put("reason", "已经存在名称为<font color=red>"+value+"</font>的项目，请重新修改！");
					return result;
				}
				/*判断修改的值是否在模型方法 - 结果输出中*/
				map.clear();
				map.put("type", "1");
				List<InputOutput> list2 = inputOutputDao.GetInputOutput(map);
				for(InputOutput inputOutput : list2){
					if(value.equals(inputOutput.getProjectName())){
						flag = 1;
						break;
					}
				}
				if(0 == flag){
					result.put("success", false);
					result.put("reason", "更新值<font color=red>"+value+"</font>不在模型方法 - 结果输出列表中，请重新修改！");
					return result;
				}
				/*加载所有number为number的DecisionPlan信息*/
				map.clear();
				map.put("number", number);
				List<DecisionPlan> list3 = decisionPlanDao.GetDecisionPlan(map);
				/*然后着条更新数据*/
				DecisionPlan decisionPlan = new DecisionPlan();
				decisionPlan.setPlanName(value);
				for(DecisionPlan plan : list3){
					decisionPlan.setId(plan.getId());
					decisionPlanDao.UpdateDecisionPlan(decisionPlan);
				}
			}else{
				map.put("number", number);
				map.put("objectCode", key);
				List<DecisionPlan> list = decisionPlanDao.GetDecisionPlan(map);
				if(1 != list.size()){
					result.put("success", false);
					result.put("reason", "参数错误！");
					return result;
				}
				for(DecisionPlan plan : list){
					plan.setObjectNumber(value);
					decisionPlanDao.UpdateDecisionPlan(plan);
				}
			}
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static boolean isInteger(String str) {    
	    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");    
	    return pattern.matcher(str).matches();    
	  }
	
	private String f(String value,String times){
		String left = "";
		String str = "";
		try {
			int i = value.length() - 1;
			String v = value.substring(i);
			while (isInteger(v) && i >0) {
				v = value.substring(--i);
			}
			v = value.substring(++i);
			Integer length = v.length();
			left = value.substring(0,value.length() - length);
			Long tmp = Long.valueOf(v) + Long.valueOf(times);
			str = tmp.toString();
			Integer l = str.length();
			if(l < length){
				for(int j=0;j<length - l;j++){
					str = "0"+str;
				}
			}
			logger.info( "1111111111111 v=" + v +";length="+length+";left="+left+";tmp="+tmp+";str="+str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return left+str;
	}
	public JSONObject changeDecisionPlan(String key,String value,String numbers,String times){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "DecisionPlanService.changeDecisionPlan";
		String param = " key:"+key+" value:"+value+" numbers:"+numbers+" times:"+times;
		logger.info( entity+pos+param  );
		try {
			String[] numberss = numbers.split(",");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("objectCode", key);
			String objectNumber = value;
			for(int i=1;i<numberss.length;i++){
				map.put("number", numberss[i]);
				List<DecisionPlan> planlist = decisionPlanDao.GetDecisionPlan(map);
				for(DecisionPlan plan : planlist){
					objectNumber = f(objectNumber, times);
					plan.setObjectNumber(objectNumber);
					decisionPlanDao.UpdateDecisionPlan(plan);
				}
			}
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject getDecisionPlan(String id,String timeID,String functionId){
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		final String entity = "";
		final String pos = "DecisionPlanService.getDecisionPlan";
		String param = " id:"+id+" timeID:"+timeID+" functionId:"+functionId;
		logger.info( entity+pos+param  );
		try {
			/*加载所有timeID为timeID的DecisionPlan信息*/
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("timeID", timeID);
			List<DecisionPlan> list = decisionPlanDao.GetDecisionPlan(map);
			/*从所有DecisionPlan信息中获取出不重复的DecisionPlan名称，其数目表示前端展示行数*/
			TreeSet<DecisionPlan> ts = new TreeSet<DecisionPlan>(new MyCmp());
			//Set<String[]> setList = new HashSet<String[]>();
			for(DecisionPlan e : list){
				DecisionPlan plan = new DecisionPlan();
				plan.setNumber(e.getNumber());
				plan.setPlanName(e.getPlanName());
				ts.add(plan);
			}
			long total = ts.size();
			/*根据DecisionPlan名称，获取DecisionPlan，其数目就为新增的列数*/
			for(DecisionPlan plan2 : ts){
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("planName", plan2.getPlanName());
				jsonObj.put("number", plan2.getNumber());
				map.put("planName", plan2.getPlanName());
				map.put("number", plan2.getNumber());
				List<DecisionPlan> listDecision = decisionPlanDao.GetDecisionPlan(map);
				for(DecisionPlan plan : listDecision){
					jsonObj.put(plan.getObjectCode(), plan.getObjectNumber());
				}
				jsonArray.add(jsonObj);
			}
			data.put("rows", jsonArray);
			data.put("total", total);
			result.put("data", data);
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONObject getInitialization(String functionId){
		JSONObject result = new JSONObject();
		final String entity = "";
		final String pos = "DecisionPlanService.getInitialization";
		String param = " functionId:"+functionId;
		logger.info( entity+pos+param  );
		//String planName[] = {"入库流量","出库流量","时段初水位","时段末水位","平均出力"};
		String timeUnit[] = {"月","旬","日","小时","分钟"};
		try {
			/*获取调度期时间ScheduleTime信息*/
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("functionId", functionId);
			List<ScheduleTime> listTime = scheduleTimeDao.GetScheduleTime(map);
			/*获取功能模型-计算方法FunctionCalculation信息*/
			map.put("nodeType", 1);
			map.put("isCalculation", "√");
			List<FunctionCalculation> listcal = functionCalculationDao.GetFunctionCalculation(map);
			/*加载所有的DecisionPlan信息*/
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("functionId", functionId);
			List<DecisionPlan> list = decisionPlanDao.GetDecisionPlan(map1);
			/*从DecisionPlan信息信息中去中提取timeID和objectCode信息*/
			Set<String> setTime = new HashSet<String>();
			Set<String> setcal = new HashSet<String>();
			logger.info( "111111111111111111setTime.size():"+setTime.size() +"setcal.size():" +setcal.size());
			for(DecisionPlan e : list){
				setTime.add(e.getTimeID());
				setcal.add(e.getObjectCode());
			}
			logger.info( "111111111111111111setTime.size():"+setTime.size() +"setcal.size():" +setcal.size());
			/*根据timeID对DecisionPlan信息进行增删改查*/
			for(ScheduleTime time : listTime){
				//int flag = 0;
				for(String t : setTime){
					if(time.getId().equals(t)){
						//flag = 1;
						setTime.remove(t);
						break;
					}
				}
				/*
				if(0 == flag){
					DecisionPlan plan = new DecisionPlan();
					plan.setTimeID(time.getId());
					String id = null;
					String number = null;
					for(int i=0;i<planName.length;i++){
						plan.setPlanName(planName[i]);
						number = decisionPlanDao.GetMaxNumber();
						if(null == number || "".equals(number)){
							number = "0";
						}else{
							number = String.valueOf(Integer.valueOf(number)+1);
						}
						if(0 == listcal.size()){
							id = decisionPlanDao.GetMaxID();
							if(null == id || "".equals(id)){
								id = "0";
							}else{
								id = String.valueOf(Integer.valueOf(id)+1);
							}
							plan.setId(id);
							plan.setReservoirObject("");
							plan.setObjectCode("");
							plan.setObjectNumber("");
							plan.setNumber(number);
							plan.setFunctionId(functionId);
							decisionPlanDao.AddDecisionPlan(plan);
						}
					    for(FunctionCalculation cal : listcal){
					    	id = decisionPlanDao.GetMaxID();
							if(null == id || "".equals(id)){
								id = "0";
							}else{
								id = String.valueOf(Integer.valueOf(id)+1);
							}
							logger.info( "22222222222222222222 i=:"+i +";number = " +number+";id = " +id);
							plan.setId(id);
							plan.setReservoirObject(cal.getObjectName());
							plan.setObjectCode(cal.getObjectCode());
							plan.setObjectNumber("");
							plan.setNumber(number);
							plan.setFunctionId(functionId);
							decisionPlanDao.AddDecisionPlan(plan);
						}
					}
				}*/
			}
			for(String t : setTime){
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("timeID", t);
				List<DecisionPlan> list2 = decisionPlanDao.GetDecisionPlan(map2);
				for(DecisionPlan d : list2){
					list.remove(d);
					decisionPlanDao.DeleteDecisionPlan(d.getId());
				}
			}
			TreeSet<DecisionPlan> ts = new TreeSet<DecisionPlan>(new MyCmp());
			//Set<String[]> setList = new HashSet<String[]>();
			for(DecisionPlan e : list){
				DecisionPlan plan = new DecisionPlan();
				plan.setTimeID(e.getTimeID());
				plan.setNumber(e.getNumber());
				plan.setPlanName(e.getPlanName());
				ts.add(plan);
			}
			logger.info( "2222222223333333333 ts.size()="+ts.size());
			/*根据objectCode对DecisionPlan信息进行增删改查*/
			for(FunctionCalculation cal : listcal){
				int flag = 0;
				for(String c : setcal){
					if(cal.getObjectCode().equals(c)){
						flag = 1;
						setcal.remove(c);
						break;
					}
				}
				if(0 == flag){
					DecisionPlan plan = new DecisionPlan();
					plan.setObjectCode(cal.getObjectCode());
					plan.setReservoirObject(cal.getObjectName());
					String id = null;
					for(DecisionPlan plan2 : ts){
						id = decisionPlanDao.GetMaxID();
						if(null == id || "".equals(id)){
							id = "0";
						}else{
							id = String.valueOf(Integer.valueOf(id)+1);
						}
						plan.setId(id);
						plan.setTimeID(plan2.getTimeID());
						plan.setPlanName(plan2.getPlanName());
						plan.setNumber(plan2.getNumber());
						plan.setObjectNumber("");
						plan.setFunctionId(functionId);
						decisionPlanDao.AddDecisionPlan(plan);
					}
				}
			}
			for(String c : setcal){
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("objectCode", c);
				List<DecisionPlan> list2 = decisionPlanDao.GetDecisionPlan(map2);
				for(DecisionPlan d : list2){
					list.remove(d);
					decisionPlanDao.DeleteDecisionPlan(d.getId());
				}
			}
			/*整合最新的调度期时间ScheduleTime信息和功能模型-计算方法FunctionCalculation信息到前端*/
			Set<Map<String, Object>> set = new HashSet<Map<String, Object>>();
			for(ScheduleTime time : listTime){
				if(!"".equals(time.getTimeNumber()) && !"".equals(time.getTimeUnit())){
					Map<String, Object> mapSet = new HashMap<String, Object>();
					mapSet.put("timeID",time.getId());
					mapSet.put("timeName",time.getTimeNumber() + timeUnit[Integer.valueOf(time.getTimeUnit())]);
					set.add(mapSet);
				}
			}
			result.put("times", set);
			set.clear();
			for(FunctionCalculation calculation : listcal){
				Map<String, Object> mapSet = new HashMap<String, Object>();
				mapSet.put("objectCode",calculation.getObjectCode());
				mapSet.put("objectName",calculation.getObjectName());
				set.add(mapSet);
			}
			result.put("calculations", set);
		    result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
