package org.csw.service.baseinfo;

import org.csw.entity.baseinfo.FunctionCalculation;

import net.sf.json.JSONObject;

public interface FunctionCalculationService {
    /**
     * @brief
     * @logic
     * @param functionCalculation
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午7:59:17
     */
    public JSONObject addFunctionCalculation(FunctionCalculation functionCalculation);
    
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
     * @param ids
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午8:00:07
     */
    public JSONObject deleteFunctionCalculation(String ids);
    
    /**
     * @brief
     * @logic
     * @param functionCalculation
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午8:01:43
     */
    public JSONObject modifyFunctionCalculation(FunctionCalculation functionCalculation);
    
    /**
     * @brief
     * @logic
     * @param id
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午8:02:53
     */
    public JSONObject getFunctionCalculation(String parentCode,String nodeType,String functionId);
    
    
    /**
     * @brief
     * @logic
     * @param functionId
     * @return
     * @author xianweilv
     * @date 2016年8月30日 下午8:38:29
     */
    public JSONObject refreshFunctionCalculation(String functionId);
    
    /**
     * @brief
     * @logic
     * @param functionId
     * @return
     * @author xianweilv
     * @date 2016年8月30日 下午8:38:39
     */
    public JSONObject getType(String functionId);
    
    /**
     * @brief
     * @logic
     * @param functionId
     * @param isCalculation
     * @return
     * @author xianweilv
     * @date 2016年9月3日 下午3:23:29
     */
    public JSONObject getType2(String functionId,String isCalculation);
    
    public JSONObject getParent(String functionId,String nodeType);
    
}
