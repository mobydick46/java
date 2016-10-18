package org.csw.service.baseinfo;

import org.csw.entity.baseinfo.ScheduleTime;

import net.sf.json.JSONObject;

public interface ScheduleTimeService {
    /**
     * @brief
     * @logic
     * @param scheduleTime
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午7:59:17
     */
    public JSONObject addScheduleTime(ScheduleTime scheduleTime);
    
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
    public JSONObject deleteScheduleTime(String ids);
    
    /**
     * @brief
     * @logic
     * @param scheduleTime
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午8:01:43
     */
    public JSONObject modifyScheduleTime(ScheduleTime scheduleTime);
    
    /**
     * @brief
     * @logic
     * @param id
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午8:02:53
     */
    public JSONObject getScheduleTime(String id,String name,String functionId);
}
