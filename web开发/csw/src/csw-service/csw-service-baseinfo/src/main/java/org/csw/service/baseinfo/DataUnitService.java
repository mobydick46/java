package org.csw.service.baseinfo;

import org.csw.entity.baseinfo.DataUnit;

import net.sf.json.JSONObject;

public interface DataUnitService {
    /**
     * @brief
     * @logic
     * @param dataUnit
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午7:59:17
     */
    public JSONObject addDataUnit(DataUnit dataUnit);
    
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
    public JSONObject deleteDataUnit(String ids);
    
    /**
     * @brief
     * @logic
     * @param dataUnit
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午8:01:43
     */
    public JSONObject modifyDataUnit(DataUnit dataUnit);
    
    /**
     * @brief
     * @logic
     * @param id
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午8:02:53
     */
    public JSONObject getDataUnit(String id,String name);
}
