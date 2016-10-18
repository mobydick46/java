package org.csw.service.baseinfo;

import org.csw.entity.baseinfo.FunctionModel;

import net.sf.json.JSONObject;

public interface FunctionModelService {
    /**
     * @brief
     * @logic
     * @param functionModel
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午7:59:17
     */
    public JSONObject addFunctionModel(FunctionModel functionModel);
    
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
    public JSONObject deleteFunctionModel(String ids);
    
    /**
     * @brief
     * @logic
     * @param functionModel
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午8:01:43
     */
    public JSONObject modifyFunctionModel(FunctionModel functionModel);
    
    /**
     * @brief
     * @logic
     * @param id
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午8:02:53
     */
    public JSONObject getFunctionModel(String id,String functionId,String funCalculationId);
    
    /**
     * @brief
     * @logic
     * @param functionId
     * @param funCalculationId
     * @param type
     * @return
     * @author xianweilv
     * @date 2016年9月4日 上午10:39:02
     */
    public JSONObject refreshFunctionModel(String functionId, String funCalculationId,String type,String functionType);
    
    /**
     * @brief
     * @logic
     * @param funCalculationId
     * @param ids
     * @return
     * @author xianweilv
     * @date 2016年9月10日 下午3:50:04
     */
    public JSONObject batchsetFunctionModel(String funCalculationId,String ids);
}
