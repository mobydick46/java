package org.csw.dao.baseinfo.impl;
import java.util.List;
import java.util.Map;

import org.csw.dao.baseinfo.TB_MODEL_BDao;
import org.csw.entity.baseinfo.TB_MODEL_B;
import org.mybatis.spring.SqlSessionTemplate;

public class TB_MODEL_BDaoImpl implements  TB_MODEL_BDao {
	private SqlSessionTemplate sessionTemplate ;

	public void setSessionTemplate(SqlSessionTemplate sessionTemplate){
		this.sessionTemplate= sessionTemplate;
	}

	@Override
	public int deleteByPrimaryKey(String modelcd) {
		return sessionTemplate.delete("org.csw.dao.baseinfo.TB_MODEL_BDao.deleteByPrimaryKey", modelcd);
	}

	@Override
	public int insertSelective(TB_MODEL_B record) {
		return sessionTemplate.insert("org.csw.dao.baseinfo.TB_MODEL_BDao.insertSelective", record);
	}

	@Override
	public TB_MODEL_B selectByPrimaryKey(String modelcd) {
		return sessionTemplate.selectOne("org.csw.dao.baseinfo.TB_MODEL_BDao.selectByPrimaryKey", modelcd);
	}

	@Override
	public int updateByPrimaryKeySelective(TB_MODEL_B record) {
		return sessionTemplate.update("org.csw.dao.baseinfo.TB_MODEL_BDao.updateByPrimaryKeySelective", record);
	}

	@Override
	public List<TB_MODEL_B> findTB_MODEL_B(Map<String, Object> map) {
		return sessionTemplate.selectList("org.csw.dao.baseinfo.TB_MODEL_BDao.findTB_MODEL_B", map);
	}

	@Override
	public Long getTotalTB_MODEL_B(Map<String, Object> map) {
		return sessionTemplate.selectOne("org.csw.dao.baseinfo.TB_MODEL_BDao.getTotalTB_MODEL_B", map);
	}
}
