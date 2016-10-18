package org.csw.service.baseinfo;

import org.csw.entity.baseinfo.PredictionScheme;

import net.sf.json.JSONObject;

public interface PredictionSchemeService {
    /**
     * @brief
     * @logic
     * @param predictionScheme
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午7:59:17
     */
    public JSONObject addPredictionScheme(PredictionScheme predictionScheme);
    
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
    public JSONObject deletePredictionScheme(String ids);
    
    /**
     * @brief
     * @logic
     * @param predictionScheme
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午8:01:43
     */
    public JSONObject modifyPredictionScheme(PredictionScheme predictionScheme);
    
    /**
     * @brief
     * @logic
     * @param id
     * @return
     * @author xianweilv
     * @date 2016年8月6日 下午8:02:53
     */
    public JSONObject getPredictionScheme(String id,String name,String funCalculationId);
}
