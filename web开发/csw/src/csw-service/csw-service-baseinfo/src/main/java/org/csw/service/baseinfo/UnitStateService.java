package org.csw.service.baseinfo;

import org.csw.entity.baseinfo.UnitState;

import net.sf.json.JSONObject;

public interface UnitStateService {
    /**
     * @brief
     * @logic
     * @param unitState
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午7:59:17
     */
    public JSONObject addUnitState(UnitState unitState,Integer flag);
    
    /**
     * @brief
     * @logic
     * @return
     * @author xianweilv
     * @date 2016年8月10日 下午10:41:11
     */
    public JSONObject getMaxID(Integer flag); 
    /**
     * @brief
     * @logic
     * @param ids
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午8:00:07
     */
    public JSONObject deleteUnitState(String ids,Integer flag);
    
    /**
     * @brief
     * @logic
     * @param unitState
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午8:01:43
     */
    public JSONObject modifyUnitState(UnitState unitState,Integer flag);
    
    /**
     * @brief
     * @logic
     * @param id
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午8:02:53
     */
    public JSONObject getUnitState(String id,String name,Integer flag);
}
