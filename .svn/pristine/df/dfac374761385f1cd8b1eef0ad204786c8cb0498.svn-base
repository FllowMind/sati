package com.jyu.sati.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jyu.sati.business.dao.AuditInfoDao;
import com.jyu.sati.entity.AuditInfo;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
public class Test {
	
//	private ApplicationContext ac ;
//
//	@Resource
//	private UserService userService;
////	ApplicationContext ac =null;
	
//	@Before
//	public void init(){
//		ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");
//		userService = ac.getBean(UserService.class);
//	}
	
	@Autowired
	private AuditInfoDao auditDao;
	
	@org.junit.Test
	public void testAudit(){
		AuditInfo info = new AuditInfo();
		info.setUserId("admin");;;
		auditDao.insertSelective(info);
	   System.out.println(info);
	}
	
	
	public void test(){
		
//		User user =	userService.getUserById(1);
//		System.out.println(user);
	}
}
