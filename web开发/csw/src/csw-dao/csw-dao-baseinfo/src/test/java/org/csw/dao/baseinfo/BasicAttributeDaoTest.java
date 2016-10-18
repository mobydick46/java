package org.csw.dao.baseinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.csw.entity.baseinfo.TB_MODEL_B;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class BasicAttributeDaoTest {
	private ApplicationContext ctx =null;
	static TB_MODEL_BDao tB_MODEL_BDao;
	
	@Before
	public void before(){
		ctx =new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
		tB_MODEL_BDao=ctx.getBean(TB_MODEL_BDao.class);
	}
    
    @Test
    public void Test() {
    	insert();
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<TB_MODEL_B> records=tB_MODEL_BDao.findTB_MODEL_B(map);
    	if(records.size()!=0)
    		System.out.println(records.get(0).toString());
    }
    
    public void insert() {
		TB_MODEL_B record=new TB_MODEL_B();
		record.setModelcd("测试");
		record.setModelinf("11");
		record.setModelnm("22");
		record.setModeltp(122);
		
		tB_MODEL_BDao.insertSelective(record);
	}
}
