package com.bwzk.junit;

import com.bwzk.pojo.EFile;
import com.bwzk.service.BaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/test*.xml" })
public class BWZKTest extends AbstractJUnit4SpringContextTests {
	// @Autowired
	// @Value("${interface.log.home.address}")
	// private String logHomeAdd;
	// @Autowired
	// private ArcService arcServcieImpl;
	@Test
	public void test() throws Exception {
//		String xml = FileUtils.readFileToString(new File("C:/XML/S_GROUP.XML"),
//				"UTF-8");
//		baseDataServiceImpl.addDeptByXml(xml, "123", "123", "123");
//		StringWriter sw = new StringWriter();
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("usercode", "1111");
//		map.put("username", "1111");
////		map.put("gid", "1234");
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.writeValue(sw, map);
//		baseDataServiceImpl.updateUserByJson(sw.toString().toString(), "5555");
		// baseDataServiceImpl.AddUser("111", "111", "111", "", "", "", "", "",
		// "", "", "", "", null, "", "", "");
		// baseDataServiceImpl.AddOrg("111", "111", "111", "111", "111", "111");
		// baseDataServiceImpl.UpdateUser("111", "222", "222", null, null, null,
		// null, null, null, "", "", "", null, "", "", "");
		// baseDataServiceImpl.UpdateOrg("222", "111", "222", "222", "222",
		// "222");
		// baseDataServiceImpl.DelUser("111");
		// baseDataServiceImpl.DelOrg("111");
		// baseDataServiceImpl.AddQzh("01", "00", "aa", 0, "123123");
		// baseDataServiceImpl.UpdateQzh("02", "11", "bb", 1, "12");
		// baseDataServiceImpl.DelQzh("01");

		// StringWriter sw = new StringWriter();
		// Map<String, String> map = new HashMap<String, String>();
		// map.put("USERCODE", "1111");
		// map.put("USERNAME", "1111");
		// ObjectMapper mapper = new ObjectMapper();
		// mapper.writeValue(sw, map);
		// baseDataServiceImpl.AddUserByJson(sw.toString().toString());
		// sw.close();
		// StringWriter sw = new StringWriter();
		// Map<String, String> map = new HashMap<String, String>();
		// map.put("USERCODE", "1111");
		// map.put("USERNAME", "2222");
		// ObjectMapper mapper = new ObjectMapper();
		// mapper.writeValue(sw, map);
		// baseDataServiceImpl.ModifyUserByJson(sw.toString().toString());
		// sw.close();
		// baseDataServiceImpl.DelUserByJson("1111");
		// StringWriter sw = new StringWriter();
		// Map<String, String> map = new HashMap<String, String>();
		// map.put("BZ", "1111");
		// map.put("GNAME", "2222");
		// ObjectMapper mapper = new ObjectMapper();
		// mapper.writeValue(sw, map);
		// baseDataServiceImpl.AddGroupByJson(sw.toString().toString());
		// sw.close();
		// StringWriter sw = new StringWriter();
		// Map<String, String> map = new HashMap<String, String>();
		// map.put("BZ", "2222");
		// map.put("GNAME", "3333");
		// ObjectMapper mapper = new ObjectMapper();
		// mapper.writeValue(sw, map);
		// baseDataServiceImpl.ModifyGroupByJson(sw.toString().toString());
		// sw.close();
		// baseDataServiceImpl.DelGroupByJson("2222");
		// StringWriter sw = new StringWriter();
		// Map<String, String> map = new HashMap<String, String>();
		// map.put("PRIMARYKEY", "000");
		// map.put("QZH", "000");
		// map.put("QZMC", "000");
		// ObjectMapper mapper = new ObjectMapper();
		// mapper.writeValue(sw, map);
		// baseDataServiceImpl.AddQzhByJson(sw.toString().toString());
		// sw.close();
		// StringWriter sw = new StringWriter();
		// Map<String, String> map = new HashMap<String, String>();
		// map.put("PRIMARYKEY", "000");
		// map.put("QZH", "111");
		// map.put("QZMC", "111");
		// ObjectMapper mapper = new ObjectMapper();
		// mapper.writeValue(sw, map);
		// baseDataServiceImpl.ModifyQzhByJson(sw.toString().toString());
		// sw.close();
		// baseDataServiceImpl.DelQzhByJson("000");
		// String xml = FileUtils.readFileToString(new
		// File("D:/LamsDataIF/target/classes/XML/S_USER.XML") , "UTF-8");
		// System.out.println(baseDataServiceImpl.AddUserByXml("S_USER.XML",
		// xml));
		// String xml = FileUtils.readFileToString(new
		// File("D:/LamsDataIF/target/classes/XML/D_FILE25.XML") , "UTF-8");
		// System.out.println(arcDataService.fileReciveXml("D_FILE25.xml", xml,
		// "qink"));
		// baseDataServiceImpl.ModifyOrgByXml("D_FILE25.xml", xml);
		// StringWriter sw = new StringWriter();
		// Map<String, String> map = new HashMap<String, String>();
		// map.put("USERCODE", "3333");
		// map.put("USERNAME", "3333");
		// ObjectMapper mapper = new ObjectMapper();
		// mapper.writeValue(sw, map);
		// baseDataServiceImpl.AddUserByJson(sw.toString().toString(), "s_user",
		// "00", "321");
		// sw.close();
		// StringWriter sw = new StringWriter();
		// Map<String, String> map = new HashMap<String, String>();
		// map.put("GNAME", "3333");
		// map.put("BZ", "3333");
		// ObjectMapper mapper = new ObjectMapper();
		// mapper.writeValue(sw, map);
		// baseDataServiceImpl.AddOrgByJson(sw.toString().toString(), "s_group",
		// "123", "123");
		// sw.close();
		// StringWriter sw = new StringWriter();
		// Map<String, String> map = new HashMap<String, String>();
		// map.put("USERCODE", "133331");
		// map.put("USERNAME", "133331");
		// ObjectMapper mapper = new ObjectMapper();
		// mapper.writeValue(sw, map);
		// baseDataServiceImpl.ModifyUserByJson(sw.toString().toString(),
		// "s_user", "321");
		// sw.close();
		// StringWriter sw = new StringWriter();
		// Map<String, String> map = new HashMap<String, String>();
		// map.put("GNAME", "133331");
		// map.put("BZ", "133331");
		// ObjectMapper mapper = new ObjectMapper();
		// mapper.writeValue(sw, map);
		// baseDataServiceImpl.ModifyOrgByJson(sw.toString().toString(),
		// "s_group", "123");
		// sw.close();
		// String xml = FileUtils.readFileToString(new File("C:/XML/S_USER.XML")
		// , "UTF-8");
		// baseDataServiceImpl.addUserByXml(dataXml, dept_zj, user_zj);
		// String xml = FileUtils.readFileToString(new
		// File("C:/XML/S_GROUP.XML") , "UTF-8");
		// baseDataServiceImpl.addOrgByTxt("S_GROUP", "&;'gname'&;'bz'&;", "11",
		// "111");
		// baseDataServiceImpl
		// .addUserByTxt(
		// "USERCODE&;USERCODE&; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &;",
		// "123", "12233");
		// StringWriter sw = new StringWriter();
		// Map<String, String> map = new HashMap<String, String>();
		// map.put("GNAME", "1133331");
		// map.put("BZ", "1133331");
		// ObjectMapper mapper = new ObjectMapper();
		// mapper.writeValue(sw, map);
		// baseDataServiceImpl.addOrgByJson(sw.toString().toString(), "s_group",
		// "888", "1");
		// sw.close();
		// StringWriter sw = new StringWriter();
		// Map<String, String> map = new HashMap<String, String>();
		// map.put("USERCODE", "1133331");
		// map.put("USERNAME", "1133331");
		// ObjectMapper mapper = new ObjectMapper();
		// mapper.writeValue(sw, map);
		// baseDataServiceImpl.addUserByJson(sw.toString().toString(), "s_user",
		// "1", "77");
		// sw.close();
		// String xml = FileUtils.readFileToString(new
		// File("C:/XML/S_GROUP.XML") , "UTF-8");
		// baseDataServiceImpl.addOrgByXml("s_group", xml, "123", "123123");
		// String xml = FileUtils.readFileToString(new File("C:/XML/S_USER.XML")
		// , "UTF-8");
		// baseDataServiceImpl.addUserByXml("S_USER", xml, "1132", "1");
		// StringWriter sw = new StringWriter();
		// Map<String, String> map = new HashMap<String, String>();
		// map.put("GNAME", "2133331");
		// map.put("BZ", "2133331");
		// ObjectMapper mapper = new ObjectMapper();
		// mapper.writeValue(sw, map);
		// baseDataServiceImpl.modifyOrgByJson(sw.toString().toString(),
		// "s_group", "888");
		// sw.close();
		// StringWriter sw = new StringWriter();
		// Map<String, String> map = new HashMap<String, String>();
		// map.put("USERCODE", "2133331");
		// map.put("USERNAME", "2133331");
		// ObjectMapper mapper = new ObjectMapper();
		// mapper.writeValue(sw, map);
		// System.out.println(baseDataServiceImpl.modifyUserByJson(sw.toString().toString(),
		// "s_user", "1"));
		// sw.close();
		// baseDataServiceImpl.modifyOrgByTxt("s_group", "&;gname&;bz&;",
		// "888");
		// baseDataServiceImpl.modifyUserByTxt("s_user",
		// "USERCODE&;USERCODE&; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &;",
		// "1");
		// String xml = FileUtils.readFileToString(new
		// File("C:/XML/S_GROUP.XML") , "UTF-8");
		// System.out.println(baseDataServiceImpl.modifyOrgByXml("s_group", xml,
		// "888"));
		// String xml = FileUtils.readFileToString(new File("C:/XML/S_USER.XML")
		// , "UTF-8");
		// baseDataServiceImpl.modifyUserByXml("s_user", xml, "1");
		// System.out.println(baseDataServiceImpl.delOrg("11"));
		// baseDataServiceImpl.d;
		// URL url = new URL("http://localhost/LamsDataIF/cxf/BaseDataWS?wsdl");
		// QName qname = new QName("http://service.lams.cn/",
		// "BaseDataServiceImplService");
		// BaseDataWS bdsis = new BaseDataServiceImplService(url, qname)
		// .getBaseDataWSPort();

		// StringWriter sw = new StringWriter();
		// Map<String, String> map = new HashMap<String, String>();
		// map.put("USERCODE", "888");
		// map.put("USERNAME", "555");
		// ObjectMapper mapper = new ObjectMapper();
		// System.out.println(bdsis.delDeptByKey("123123"));

		try {
			// mapper.writeValue(sw, map);
			// bdsis.modifyQzhByJson(sw.toString().toString(), "9999");
			// bdsis.delQzhByJson("9999");
			// bdsis.modifyOrgByJson(sw.toString().toString(), "123123");
			// bdsis.delOrgByJson("123");
			// String xml = FileUtils.readFileToString(new File(
			// "C:/XML/S_QZH.XML"), "UTF-8");
			// bdsis.addUserByXml(xml, "123123", "1234");
			// bdsis.modifyUserByXml(xml, "1234");
			// bdsis.delUserByXml("1234");
			// bdsis.addOrgByXml(xml, "123123", "bb", "aa");
			// bdsis.modifyOrgByXml(xml, "123123");
			// bdsis.addOrgByXml(dataXml, deptZj, orgName, parentOrgNo)
			// bdsis.addQzhByXml(xml, "123123321");
			// bdsis.modifyQzhByXml(xml, "123123321");
			// bdsis.delQzhByXml("123123321");
			String  tableName = "e_file1";
			EFile eFile = new EFile();
			String title = "tttile";
			//DID,PID,EFILENAME,TITLE,EXT,PZM,PATHNAME,STATUS,ATTR,ATTREX,CREATOR,CREATETIME,FILESIZE,MD5,CONVERTSTATUS
			eFile.setDid(baseService.getMaxDid(tableName));
			eFile.setPid(1);
			eFile.setEfilename("/dj测试");
			eFile.setTitle(title);
			eFile.setExt("doc");
			eFile.setPzm("pzm");
			eFile.setPathname("//ssss/sdfsdfs/sdf/");
			eFile.setStatus(baseService.getStatus());
			eFile.setAttr(baseService.getAttr());
			eFile.setAttrex(baseService.getAttrex());
			eFile.setCreator( "ROOT");
			eFile.setCreatetime(new Date());
			eFile.setFilesize(9999);
			eFile.setMd5( "MD5Test");
			System.out.println(baseService.insertEfile(tableName , eFile));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Autowired
	private BaseService baseService;
	// @Autowired
	// private SingleService arcDataService;
}
