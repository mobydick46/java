package org.csw.service.baseinfo;

import net.sf.json.JSONObject;

public interface DecisionPlanService {
    /**
     * @brief
     * @logic
     * @param decisionPlan
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午7:59:17
     */
    public JSONObject addDecisionPlan(String number,String numberSource);
    /**
     * @brief
     * @logic
     * @param timeID
     * @param functionId
     * @return
     * @author xianweilv
     * @date 2016年9月29日 上午10:38:38
     */
    public JSONObject addDecisionPlan2(String number,String timeID,String functionId);
    
    /**
     * @brief
     * @logic
     * @return
     * @author xianweilv
     * @date 2016年8月10日 下午10:41:11
     */
    public JSONObject getMaxID(); 
    /**
     * @brief
     * @logic
     * @return
     * @author xianweilv
     * @date 2016年9月22日 下午2:31:35
     */
    public JSONObject getMaxNumber();
    /**
     * @brief
     * @logic
     * @param ids
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午8:00:07
     */
    public JSONObject deleteDecisionPlan(String ids);
    
    /**
     * @brief
     * @logic
     * @param key
     * @param value
     * @param numbers
     * @return
     * @author xianweilv
     * @date 2016年9月24日 下午2:42:06
     */
    public JSONObject changeDecisionPlan(String key,String value,String numbers,String times); 
    /**
     * @brief
     * @logic
     * @param decisionPlan
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午8:01:43
     */
    public JSONObject modifyDecisionPlan(String key,String value,String number,String functionId,String timeTD);
    
    /**
     * @brief
     * @logic
     * @param id
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午8:02:53
     */
    public JSONObject getDecisionPlan(String id,String timeID,String functionId);
    
    /**
     * @brief
     * @logic
     * @param functionId
     * @return
     * @author xianweilv
     * @date 2016年9月19日 下午7:55:33
     */
    public JSONObject getInitialization(String functionId);
}
