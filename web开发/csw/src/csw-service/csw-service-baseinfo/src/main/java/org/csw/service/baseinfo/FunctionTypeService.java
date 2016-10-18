package org.csw.service.baseinfo;

import org.csw.entity.baseinfo.FunctionType;

import net.sf.json.JSONObject;

public interface FunctionTypeService {
    /**
     * @brief
     * @logic
     * @param functionType
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午7:59:17
     */
    public JSONObject addFunctionType(FunctionType functionType);
    
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
    public JSONObject deleteFunctionType(String ids);
    
    /**
     * @brief
     * @logic
     * @param functionType
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午8:01:43
     */
    public JSONObject modifyFunctionType(FunctionType functionType);
    
    /**
     * @brief
     * @logic
     * @param id
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午8:02:53
     */
    public JSONObject getFunctionType(String id,String isSet,String projectName);
}
