package org.csw.dao.baseinfo;

import java.util.List;
import java.util.Map;

import org.csw.entity.baseinfo.TB_MODEL_B;

public interface TB_MODEL_BDao {
    int deleteByPrimaryKey(String modelcd);

    int insertSelective(TB_MODEL_B record);

    TB_MODEL_B selectByPrimaryKey(String modelcd);

    int updateByPrimaryKeySelective(TB_MODEL_B record);

    public List<TB_MODEL_B> findTB_MODEL_B(Map<String, Object> map);
    
    public Long getTotalTB_MODEL_B(Map<String, Object> map);
}