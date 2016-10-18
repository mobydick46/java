package org.csw.service.baseinfo;

import org.csw.entity.baseinfo.InputOutput;

import net.sf.json.JSONObject;

public interface InputOutputService {
    /**
     * @brief
     * @logic
     * @param inputOutput
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午7:59:17
     */
    public JSONObject addInputOutput(InputOutput inputOutput);
    
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
    public JSONObject deleteInputOutput(String ids);
    
    /**
     * @brief
     * @logic
     * @param inputOutput
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午8:01:43
     */
    public JSONObject modifyInputOutput(InputOutput inputOutput);
    
    /**
     * @brief
     * @logic
     * @param id
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午8:02:53
     */
    public JSONObject getInputOutput(String id,String name,String type,String modelCode);
    
    /**
     * @brief 把一个模型的输入输出复制到其他模型
     * @logic
     * @param codes
     * @param code
     * @param type
     * @return
     * @author xianweilv
     * @date 2016年8月24日 下午5:37:22
     */
    public JSONObject copyInputOutput(String codes,String code,String type);
}
