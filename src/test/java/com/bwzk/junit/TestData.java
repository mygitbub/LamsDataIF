//package com.bwzk.junit;
//
//import java.io.IOException;
//import java.io.StringWriter;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.xml.namespace.QName;
//
//import org.codehaus.jackson.JsonGenerationException;
//import org.codehaus.jackson.map.JsonMappingException;
//import org.codehaus.jackson.map.ObjectMapper;
//
//import cn.lams.BaseDataServiceImplService;
//import cn.lams.BaseDataWS;
//
//public class TestData {
//
//	public static void main(String[] args) {
//		try {
//			URL url = new URL("http://localhost/LamsDataIF/cxf/BaseDataWS?wsdl");
//			QName qname = new QName("http://service.lams.cn/", "BaseDataServiceImplService");
//			BaseDataWS bdsis = new BaseDataServiceImplService(url,qname).getBaseDataWSPort();
////			bdsis.addUserByTxt("USERCODE4&;USERCODE4&; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &; &;", "12312", "122332");
////			bdsis.delUserByTxt("12233");
////			#bdsis.addOrgByTxt("&;gname&;'b'&;", "11", "123", "123");
////			bdsis.modifyOrgByTxt("&;gname&;'b'&;", "11");
////			bdsis.delOrgByTxt("11");
//			StringWriter sw = new StringWriter();
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("USERCODE", "444");
//			map.put("USERNAME", "444");
//			ObjectMapper mapper = new ObjectMapper(); 
//			try {
//				mapper.writeValue(sw, map);
//			} catch (JsonGenerationException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (JsonMappingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			bdsis.addUserByJson(sw.toString().toString(), "999", "999");
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
