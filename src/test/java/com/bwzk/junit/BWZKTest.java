package com.bwzk.junit;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bwzk.service.i.ArcService;
import com.bwzk.service.i.BaseDataService;
import com.bwzk.service.i.SingleService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring/test*.xml"})
public class BWZKTest extends AbstractJUnit4SpringContextTests {
//	@Autowired
//	@Value("${interface.log.home.address}")
//	private String logHomeAdd;
//	@Autowired
//	private ArcService arcServcieImpl;
	@Test
	public void test() throws Exception{
//		baseDataServiceImpl.AddUser("111", "111", "111", "", "", "", "", "", "", "", "", "", null, "", "", "");
//		baseDataServiceImpl.AddOrg("111", "111", "111", "111", "111", "111");
//		baseDataServiceImpl.UpdateUser("111", "222", "222", null, null, null, null, null, null, "", "", "", null, "", "", "");
//		baseDataServiceImpl.UpdateOrg("222", "111", "222", "222", "222", "222");
//		baseDataServiceImpl.DelUser("111");
//		baseDataServiceImpl.DelOrg("111");
//		baseDataServiceImpl.AddQzh("01", "00", "aa", 0, "123123");
//		baseDataServiceImpl.UpdateQzh("02", "11", "bb", 1, "12");
//		baseDataServiceImpl.DelQzh("01");
		
//		StringWriter sw = new StringWriter();
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("USERCODE", "1111");
//		map.put("USERNAME", "1111");
//		ObjectMapper mapper = new ObjectMapper(); 
//		mapper.writeValue(sw, map);
//		baseDataServiceImpl.AddUserByJson(sw.toString().toString());
//		sw.close();
//		StringWriter sw = new StringWriter();
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("USERCODE", "1111");
//		map.put("USERNAME", "2222");
//		ObjectMapper mapper = new ObjectMapper(); 
//		mapper.writeValue(sw, map);
//		baseDataServiceImpl.ModifyUserByJson(sw.toString().toString());
//		sw.close();
//		baseDataServiceImpl.DelUserByJson("1111");
//		StringWriter sw = new StringWriter();
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("BZ", "1111");
//		map.put("GNAME", "2222");
//		ObjectMapper mapper = new ObjectMapper(); 
//		mapper.writeValue(sw, map);
//		baseDataServiceImpl.AddGroupByJson(sw.toString().toString());
//		sw.close();
//		StringWriter sw = new StringWriter();
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("BZ", "2222");
//		map.put("GNAME", "3333");
//		ObjectMapper mapper = new ObjectMapper(); 
//		mapper.writeValue(sw, map);
//		baseDataServiceImpl.ModifyGroupByJson(sw.toString().toString());
//		sw.close();
//		baseDataServiceImpl.DelGroupByJson("2222");
//		StringWriter sw = new StringWriter();
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("PRIMARYKEY", "000");
//		map.put("QZH", "000");
//		map.put("QZMC", "000");
//		ObjectMapper mapper = new ObjectMapper(); 
//		mapper.writeValue(sw, map);
//		baseDataServiceImpl.AddQzhByJson(sw.toString().toString());
//		sw.close();
//		StringWriter sw = new StringWriter();
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("PRIMARYKEY", "000");
//		map.put("QZH", "111");
//		map.put("QZMC", "111");
//		ObjectMapper mapper = new ObjectMapper(); 
//		mapper.writeValue(sw, map);
//		baseDataServiceImpl.ModifyQzhByJson(sw.toString().toString());
//		sw.close();
//		baseDataServiceImpl.DelQzhByJson("000");
//		String xml = FileUtils.readFileToString(new File("D:/LamsDataIF/target/classes/XML/S_USER.XML") , "UTF-8");
//		System.out.println(baseDataServiceImpl.AddUserByXml("S_USER.XML", xml));
//		String xml = FileUtils.readFileToString(new File("D:/LamsDataIF/target/classes/XML/D_FILE25.XML") , "UTF-8");
//		System.out.println(arcDataService.fileReciveXml("D_FILE25.xml", xml, "qink"));
//		baseDataServiceImpl.ModifyOrgByXml("D_FILE25.xml", xml);
//		StringWriter sw = new StringWriter();
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("USERCODE", "3333");
//		map.put("USERNAME", "3333");
//		ObjectMapper mapper = new ObjectMapper(); 
//		mapper.writeValue(sw, map);
//		baseDataServiceImpl.AddUserByJson(sw.toString().toString(), "s_user", "00", "321");
//		sw.close();
//		StringWriter sw = new StringWriter();
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("GNAME", "3333");
//		map.put("BZ", "3333");
//		ObjectMapper mapper = new ObjectMapper(); 
//		mapper.writeValue(sw, map);
//		baseDataServiceImpl.AddOrgByJson(sw.toString().toString(), "s_group", "123", "123");
//		sw.close();
//		StringWriter sw = new StringWriter();
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("USERCODE", "133331");
//		map.put("USERNAME", "133331");
//		ObjectMapper mapper = new ObjectMapper(); 
//		mapper.writeValue(sw, map);
//		baseDataServiceImpl.ModifyUserByJson(sw.toString().toString(), "s_user", "321");
//		sw.close();
//		StringWriter sw = new StringWriter();
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("GNAME", "133331");
//		map.put("BZ", "133331");
//		ObjectMapper mapper = new ObjectMapper(); 
//		mapper.writeValue(sw, map);
//		baseDataServiceImpl.ModifyOrgByJson(sw.toString().toString(), "s_group", "123");
//		sw.close();
//		String xml = FileUtils.readFileToString(new File("C:/XML/S_USER.XML") , "UTF-8");
//		baseDataServiceImpl.AddUserByXml("S_USER", xml, "S_USER", "112", "2123");
//		String xml = FileUtils.readFileToString(new File("C:/XML/S_GROUP.XML") , "UTF-8");
//		baseDataServiceImpl.addOrgByTxt("S_GROUP", "&;'gname'&;'bz'&;", "11", "111");
//		baseDataServiceImpl.addUserByTxt("S_USER", "USERCODE&;USERCODE&; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &;", "123", "12233");
//		StringWriter sw = new StringWriter();
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("GNAME", "1133331");
//		map.put("BZ", "1133331");
//		ObjectMapper mapper = new ObjectMapper(); 
//		mapper.writeValue(sw, map);
//		baseDataServiceImpl.addOrgByJson(sw.toString().toString(), "s_group", "888", "1");
//		sw.close();
//		StringWriter sw = new StringWriter();
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("USERCODE", "1133331");
//		map.put("USERNAME", "1133331");
//		ObjectMapper mapper = new ObjectMapper(); 
//		mapper.writeValue(sw, map);
//		baseDataServiceImpl.addUserByJson(sw.toString().toString(), "s_user", "1", "77");
//		sw.close();
//		String xml = FileUtils.readFileToString(new File("C:/XML/S_GROUP.XML") , "UTF-8");
//		baseDataServiceImpl.addOrgByXml("s_group", xml, "123", "123123");
//		String xml = FileUtils.readFileToString(new File("C:/XML/S_USER.XML") , "UTF-8");
//		baseDataServiceImpl.addUserByXml("S_USER", xml, "1132", "1");
//		StringWriter sw = new StringWriter();
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("GNAME", "2133331");
//		map.put("BZ", "2133331");
//		ObjectMapper mapper = new ObjectMapper(); 
//		mapper.writeValue(sw, map);
//		baseDataServiceImpl.modifyOrgByJson(sw.toString().toString(), "s_group", "888");
//		sw.close();
//		StringWriter sw = new StringWriter();
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("USERCODE", "2133331");
//		map.put("USERNAME", "2133331");
//		ObjectMapper mapper = new ObjectMapper(); 
//		mapper.writeValue(sw, map);
//		System.out.println(baseDataServiceImpl.modifyUserByJson(sw.toString().toString(), "s_user", "1"));
//		sw.close();
//		baseDataServiceImpl.modifyOrgByTxt("s_group", "&;gname&;bz&;", "888");
//		baseDataServiceImpl.modifyUserByTxt("s_user", "USERCODE&;USERCODE&; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &;", "1");
//		String xml = FileUtils.readFileToString(new File("C:/XML/S_GROUP.XML") , "UTF-8");
//		System.out.println(baseDataServiceImpl.modifyOrgByXml("s_group", xml, "888"));
//		String xml = FileUtils.readFileToString(new File("C:/XML/S_USER.XML") , "UTF-8");
//		baseDataServiceImpl.modifyUserByXml("s_user", xml, "1");
//		System.out.println(baseDataServiceImpl.delOrg("11"));
		baseDataServiceImpl.delUser("1");
	}
	
	
	
	@Autowired
	private BaseDataService baseDataServiceImpl;
	@Autowired
	private SingleService arcDataService;
}
