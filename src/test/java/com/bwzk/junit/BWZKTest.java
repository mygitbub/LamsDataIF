package com.bwzk.junit;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bwzk.service.i.ArcService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring/test*.xml"})
public class BWZKTest extends AbstractJUnit4SpringContextTests {
	@Autowired
	@Value("${interface.log.home.address}")
	private String logHomeAdd;
	@Autowired
	private ArcService arcServcieImpl;
	
}
