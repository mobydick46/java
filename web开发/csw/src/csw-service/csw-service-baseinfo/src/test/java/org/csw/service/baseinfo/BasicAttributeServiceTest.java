package org.csw.service.baseinfo;

import org.csw.entity.baseinfo.BasicAttribute;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BasicAttributeServiceTest {
	private ApplicationContext ctx = null;
    private BasicAttributeService basicAttributeService = null;
    
    @Before
    public void before(){
    	ctx = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
    	basicAttributeService = ctx.getBean(BasicAttributeService.class);
    }
    
    @Test
    public void test(){
    	//add();
    	//delete();
    	//modify();
    	get();
    }
    
    private void add(){
    	BasicAttribute basicAttribute = new BasicAttribute();
		basicAttribute.setName("系统名称4");
		basicAttribute.setContent("综合调度系统4");
		basicAttribute.setRemark("一期工程4");
		basicAttributeService.addBasicAttribute(basicAttribute);
    }
    
    private void delete(){
    	String ids = "4,5";
    	basicAttributeService.deleteBasicAttribute(ids);
    }
    
    private void modify(){
    	BasicAttribute basicAttribute = new BasicAttribute();
    	basicAttribute.setId("1");
		basicAttribute.setName("系统名称1");
		basicAttribute.setContent("综合调度系统1");
		basicAttribute.setRemark("一期工程1");
    	basicAttributeService.modifyBasicAttribute(basicAttribute);
    }
    
    private void get(){
    	String id ="";
    	String name = "222";
    	System.out.println(basicAttributeService.getBasicAttribute(id, name));
    }
}
