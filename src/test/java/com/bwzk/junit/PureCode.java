package com.bwzk.junit;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tempuri.DataSvc;
import org.tempuri.DataSvcSoap;
import org.tempuri.ReturnData;

import com.bwzk.pojo.MLDepNewDataSet;
import com.bwzk.pojo.MLUsrDataSet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring/test*.xml"})
public class PureCode {
	//2个系统id公用的补偿
	@Autowired
	@Value("${morrowsoft.2.unis.step}")
	private Integer stepNum;
//	@Test
//	public void test01(){
//		String filename="关于.任命的收文.docx";
//		String pathname="/gui/2013/2013082248572345.docx";
//		final String efileTitle = filename.substring(0 , filename.lastIndexOf("."));
//		final String efileKzm = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
//		System.out.println(efileTitle);
//		System.out.println(efileKzm);
//		
//		String[] fileNameArray =  StringUtils.split(pathname  , "/");
//		final String efileName = fileNameArray[fileNameArray.length-1];
//		System.out.println(efileName);
//	}	
//	@Test
//	public void test02(){
//		Long start = System.currentTimeMillis();
//		DynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance(); 
//	    Client client = dcf.createClient("http://tlk.glodon.com:8877/WebService/LK6SyncService/MrBaseService/MrBaseService.asmx?wsdl"); 
//	    try {
//			Object[] result = client.invoke("wmCheckUserByToken", "luyu","111","sss");
//			for (Object oobbj : result) {
//				System.out.println(oobbj);
//				
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	    System.out.println(System.currentTimeMillis() - start);
//	}
//	@Test
//	public void test03(){
//		String urlStr= "http://tlk.glodon.com:8877/WebService/LK6SyncService/MrBaseService/MrBaseService.asmx?wsdl";
//		URL url = null;
//		try {
//			url = new URL(urlStr);
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		QName qName = new QName("http://tempuri.org/", "MrBaseService");
//		
//		Long start = System.currentTimeMillis();
//		String userCode = "luyu";
//		String token = "111";
//		MrBaseServiceSoap service = new MrBaseService(url , qName).getMrBaseServiceSoap();
//		Holder<String> h = new Holder<String>("errror info");
//		Holder<Integer> resultInt = new Holder<Integer>(1);
//		service.wmCheckUserByToken(userCode, token, h, resultInt);
//		System.out.println(resultInt.value);
//		System.out.println(h.value);
//		System.out.println(System.currentTimeMillis() - start);
//	}
	//dep或者usr 
//	@Test
//	public void test05(){
//		String token="!@#$asdf$#@!";
//		QName qName = new QName("http://tempuri.org/", "DataSvc");
//		URL url = null;
//		try {
//			String urlStr= "http://211.98.128.170:8888/WebService/LK6SyncService/MrMMSSvc/DataSvc.asmx?wsdl";
//			url = new URL(urlStr);
////			 new URL("http://211.98.128.170:8888/WebService/LK6SyncService/MrMMSSvc/DataSvc.asmx?wsdl");
//			DataSvcSoap dataSvcSop = new DataSvc(url , qName).getDataSvcSoap();
//			ReturnData returnData = dataSvcSop.getAllData(token, "usr");
//			System.out.println(returnData.getXML());
//			ReturnData depDate = dataSvcSop.getAllData(token, "dep");
//			FileUtils.writeStringToFile(new File("c:/test.xml"), depDate.getXML());
//			System.out.println(depDate.getXML());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		DataSvcSoap dataSvcSop = new DataSvc().getDataSvcSoap();
//	}
	@Test
	public void test06(){
		System.out.println("=======================================");
		try {
			File file = new File("c:/_mltest/usr_sj.xml");
			String xmlStr;
			xmlStr = FileUtils.readFileToString(file , "GBK");
			Reader reader = new StringReader(xmlStr);  
			Unmarshaller unmarshaller =  JAXBContext.newInstance(MLUsrDataSet.class).createUnmarshaller();       
			MLUsrDataSet result = (MLUsrDataSet) unmarshaller.unmarshal(reader);   
			List<MLUsrDataSet.Table> list = result.getTable();
			for (MLUsrDataSet.Table table : list) {
				System.out.println(table.getUSRCODE()+": PID: "+table.getUSRDEPID()+": name: "+table.getUSRNAME()
						+": PID: "+table.getUSRID());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		System.out.println("=======================================");
	}
	@Test
	public void test07(){
		System.out.println("=======================================");
		try {
			File file = new File("c:/_mltest/dep_sj.xml");
			String xmlStr;
			xmlStr = FileUtils.readFileToString(file , "GBK");
			Reader reader = new StringReader(xmlStr);  
			Unmarshaller unmarshaller =  JAXBContext.newInstance(MLDepNewDataSet.class).createUnmarshaller();       
			MLDepNewDataSet result = (MLDepNewDataSet) unmarshaller.unmarshal(reader);   
			List<MLDepNewDataSet.Table> list = result.getTable();
			for (MLDepNewDataSet.Table table : list) {
				System.out.println(table.getDEPNAME()+": ID: " + table.getDEPID()+": PID: "+table.getDEPPARENTID()+": code: " +table.getDEPCODE()
						+": name: " +table.getDEPNAME());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}  
		System.out.println(stepNum + "=======================================");
	}
//    档案系统全宗名称               全宗号      梦龙单位名称     梦龙id
//山东三箭置业集团有限公司            1000         集团机关       1101(ML部门ID)
//山东三箭建设工程股份有限公司        1001     三箭建设股份公司   1152(ML部门ID)
//山东三箭建设工程管理有限公司        1002     三箭建设管理公司   1154(ML部门ID)


}
