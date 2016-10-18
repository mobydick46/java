package org.csw.service.baseinfo;

import org.csw.entity.baseinfo.DataConnection;

import net.sf.json.JSONObject;

public interface DataConnectionService {
    /**
     * @brief
     * @logic
     * @param dataConnection
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午7:59:17
     */
    public JSONObject addDataConnection(DataConnection dataConnection);
    
    /**
     * @brief
     * @logic
     * @return
     * @author xianweilv
     * @date 2016年8月10日 下午7:15:35
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
    public JSONObject deleteDataConnection(String ids);
    
    /**
     * @brief
     * @logic
     * @param dataConnection
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午8:01:43
     */
    public JSONObject modifyDataConnection(DataConnection dataConnection);
    
    /**
     * @brief
     * @logic
     * @param id
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午8:02:53
     */
    public JSONObject getDataConnection(String id,String name);
}
