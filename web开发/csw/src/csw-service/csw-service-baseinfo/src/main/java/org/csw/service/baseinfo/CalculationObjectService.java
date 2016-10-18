package org.csw.service.baseinfo;

import org.csw.entity.baseinfo.CalculationObject;

import net.sf.json.JSONObject;

public interface CalculationObjectService {
    /**
     * @brief
     * @logic
     * @param calculationObject
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午7:59:17
     */
    public JSONObject addCalculationObject(CalculationObject calculationObject);
    
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
    public JSONObject deleteCalculationObject(String ids);
    
    /**
     * @brief
     * @logic
     * @param calculationObject
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午8:01:43
     */
    public JSONObject modifyCalculationObject(String change,CalculationObject calculationObject);
    
    /**
     * @brief
     * @logic
     * @param id
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午8:02:53
     */
    public JSONObject getCalculationObject(String id,String name,String nodeType);
    /**
     * @brief
     * @logic
     * @param isSet
     * @param nodeType
     * @return
     * @author xianweilv
     * @date 2016年9月26日 下午1:24:30
     */
    public JSONObject getParentObject(String isSet,String nodeType);
}
