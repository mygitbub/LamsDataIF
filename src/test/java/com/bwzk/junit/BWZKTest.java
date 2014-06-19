package com.bwzk.junit;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bwzk.pojo.MLFieldMapping;
import com.bwzk.service.i.ArcService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring/test*.xml"})
public class BWZKTest extends AbstractJUnit4SpringContextTests {
	@Autowired
	@Value("${interface.log.home.address}")
	private String logHomeAdd;
	@Autowired
	private ArcService arcServcieImpl;
	
	@Autowired
	@Value("${morrowsoft2unis.field.mapping.str}")
	private String fieldStarStr;
	
//	@Test
//	public void test01(){
//		List<MLFieldMapping>  mlFieldMapping = arcServcieImpl.getAndInitMLFieldMapping();
//		for (MLFieldMapping mlFieldMapp : mlFieldMapping) {
//			System.out.println(mlFieldMapp.getChName() + " : " + mlFieldMapp.getdTableName() + " : " + mlFieldMapp.getEnName()
//					 + " : " + mlFieldMapp.getFileType() );
//			Map<String , String> mmmap = mlFieldMapp.getFieldMap();
//			Set<String> tempSet = mmmap.keySet();
//			for (String sstr : tempSet) {
//				System.out.println(sstr + " : " + mmmap.get(sstr));
//			}
//		}
//	}
//	@Test
//	public void test02(){//010500007023
//		System.out.println(arcServcieImpl.getBmidStrByDepCode("5000301"));
//	}
//	@Test
//	public void test03(){
//		System.out.println("========");
//		System.out.println(arcServcieImpl.getBmidStrByUserCode("010500001752"));
//		System.out.println("========");
//	}
	@Test
	public void test04(){
		File file = new File("C:/_mltest/ttttt.xml");
		String xmlStr = null;
		try {
			xmlStr = FileUtils.readFileToString(file , "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(StringUtils.isNotEmpty(xmlStr)){
			try {
				arcServcieImpl.fileRecive(xmlStr);
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
				// TODO: handle exception
			}
		}
		
	}
}
