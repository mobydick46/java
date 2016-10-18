package org.csw.dao.baseinfo.impl;

import java.util.List;
import java.util.Map;

import org.csw.dao.baseinfo.TB_HYDRONODE_BDao;
import org.csw.entity.baseinfo.TB_HYDRONODE_B;
import org.mybatis.spring.SqlSessionTemplate;

public class TB_HYDRONODE_BDaoImpl implements TB_HYDRONODE_BDao{
	private SqlSessionTemplate sessionTemplate ;

	public void setSessionTemplate(SqlSessionTemplate sessionTemplate){
		this.sessionTemplate= sessionTemplate;
	}

	@Override
	public int deleteByPrimaryKey(String modelcd) {
		return sessionTemplate.delete("org.csw.dao.baseinfo.TB_HYDRONODE_BDao.deleteByPrimaryKey", modelcd);
	}

	@Override
	public int insertSelective(TB_HYDRONODE_B record) {
		return sessionTemplate.insert("org.csw.dao.baseinfo.TB_HYDRONODE_BDao.insertSelective", record);
	}

	@Override
	public TB_HYDRONODE_B selectByPrimaryKey(String modelcd) {
		return sessionTemplate.selectOne("org.csw.dao.baseinfo.TB_HYDRONODE_BDao.selectByPrimaryKey", modelcd);
	}

	@Override
	public int updateByPrimaryKeySelective(TB_HYDRONODE_B record) {
		return sessionTemplate.update("org.csw.dao.baseinfo.TB_HYDRONODE_BDao.updateByPrimaryKeySelective", record);
	}

	@Override
	public List<TB_HYDRONODE_B> findTB_HYDRONODE_B(Map<String, Object> map) {
		return sessionTemplate.selectList("org.csw.dao.baseinfo.TB_HYDRONODE_BDao.findTB_HYDRONODE_B", map);
	}

	@Override
	public Long getTotalTB_HYDRONODE_B(Map<String, Object> map) {
		return sessionTemplate.selectOne("org.csw.dao.baseinfo.TB_HYDRONODE_BDao.getTotalTB_HYDRONODE_B", map);
	}
}
