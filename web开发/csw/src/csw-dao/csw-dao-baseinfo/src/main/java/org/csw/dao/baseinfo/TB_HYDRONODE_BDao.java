package org.csw.dao.baseinfo;

import java.util.List;
import java.util.Map;

import org.csw.entity.baseinfo.TB_HYDRONODE_B;

public interface TB_HYDRONODE_BDao{
	int deleteByPrimaryKey(String ndcd);

	int insertSelective(TB_HYDRONODE_B record);

	TB_HYDRONODE_B selectByPrimaryKey(String ndcd);

	int updateByPrimaryKeySelective(TB_HYDRONODE_B record);

	public List<TB_HYDRONODE_B> findTB_HYDRONODE_B(Map<String, Object> map);

	public Long getTotalTB_HYDRONODE_B(Map<String, Object> map);
}