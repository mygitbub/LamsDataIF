package com.bwzk.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bwzk.service.i.GepsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/test*.xml" })
public class MlJdbcTest {
	@Autowired
	private GepsService gepsService;
//updateXmStatus(Integer xmid){// test  1749501150
//	@Test
//	public void test01() {
//		System.out.println(gepsService.updateXmStatus(1749501150));
//	}
	@Test
	public void test02() {
		gepsService.projectList();
	}
}
