package org.csw.service.baseinfo;

import org.csw.entity.baseinfo.ModelMethod;

import net.sf.json.JSONObject;

public interface ModelMethodService {
    /**
     * @brief
     * @logic
     * @param modelMethod
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午7:59:17
     */
    public JSONObject addModelMethod(ModelMethod modelMethod);
    
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
    public JSONObject deleteModelMethod(String ids);
    
    /**
     * @brief
     * @logic
     * @param modelMethod
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午8:01:43
     */
    public JSONObject modifyModelMethod(ModelMethod modelMethod);
    
    /**
     * @brief
     * @logic
     * @param id
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午8:02:53
     */
    public JSONObject getModelMethod(String id,String name,String combCalculation);
    /**
     * @brief
     * @logic
     * @param combCalculation
     * @return
     * @author xianweilv
     * @date 2016年9月29日 下午5:56:50
     */
    public JSONObject addInputOutput(String combCalculation);
}
