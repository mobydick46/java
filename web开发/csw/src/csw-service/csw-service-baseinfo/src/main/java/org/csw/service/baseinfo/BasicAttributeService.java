package org.csw.service.baseinfo;

import org.csw.entity.baseinfo.BasicAttribute;

import net.sf.json.JSONObject;

public interface BasicAttributeService {
    /**
     * @brief
     * @logic
     * @param basicAttribute
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午7:59:17
     */
    public JSONObject addBasicAttribute(BasicAttribute basicAttribute);
    
    public JSONObject getMaxID(); 
    /**
     * @brief
     * @logic
     * @param ids
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午8:00:07
     */
    public JSONObject deleteBasicAttribute(String ids);
    
    /**
     * @brief
     * @logic
     * @param basicAttribute
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午8:01:43
     */
    public JSONObject modifyBasicAttribute(BasicAttribute basicAttribute);
    
    /**
     * @brief
     * @logic
     * @param id
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午8:02:53
     */
    public JSONObject getBasicAttribute(String id,String name);
    
    public JSONObject download(String path);
}
