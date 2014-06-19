package com.bwzk.junit;

import java.io.File;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FileUtils;
import org.tempuri.ImMessageService;
import org.tempuri.ImMessageServiceSoap;

import com.bwzk.pojo.MLObjectFactory;
import com.bwzk.pojo.MLXmlResult;

public class ParseXmlTest {
	public static void main(String[] args) throws Exception {
//		File file = new File("C:/ML_OA_DATA.xml");
//		String xmlStr = FileUtils.readFileToString(file , "UTF-8");
//		Reader reader = new StringReader(xmlStr);
//        Unmarshaller unmarshaller =  JAXBContext.newInstance(MLXmlResult.class).createUnmarshaller();     
//        MLXmlResult result = (MLXmlResult) unmarshaller.unmarshal(reader); 
//        List<com.bwzk.pojo.MLXmlResult.DocInfo.Field> list = result.getDocInfo().getField();
//        List<com.bwzk.pojo.MLXmlResult.Attachments.Attachment> eFileList = result.getAttachments().getAttachment();
//        for (com.bwzk.pojo.MLXmlResult.Attachments.Attachment efile : eFileList) {
//        	System.out.println(efile.getFtpdir());
//		}
////        for (Result.DocInfo.Field field : list) {
////        	System.out.println(field.getColName()+":"+field.getValue());
////		}
//  
//        Marshaller mashaller =  JAXBContext.newInstance(MLXmlResult.class).createMarshaller();
//        MLObjectFactory factory = new MLObjectFactory();
//        MLXmlResult toxmlResult = factory.createResult();
//        //可选 开始
//        com.bwzk.pojo.MLXmlResult.DocInfo.Field theField = factory.createResultDocInfoField();
//        theField.setColName("中文桑德菲杰三闾大夫");
//        com.bwzk.pojo.MLXmlResult.DocInfo dii = factory.createResultDocInfo();
//        dii.getField().add(theField);
//        toxmlResult.setDocInfo(dii);
//        //可选 结束
//        mashaller.marshal(toxmlResult, new File("c:/outPut.xml"));
//		
		ImMessageServiceSoap service = new ImMessageService().getImMessageServiceSoap();
		short nOnlineOnly = 0;
		short nReserveDays = 2;
		System.out.println(service.sendCommonMessageByUserCode(2121L, "luyu", "2121@luyu", 
				"测试看看是http://lams.canycan.com否这是消息可以", nOnlineOnly , nReserveDays , "GXT.IM", null, "陆宇", null));

//		service.sendCommonMessageByUserCode(nSenderPlatID, nSenderUserCode, sReceiverPlatUserIds,
//				strContent, nOnlineOnly, nReserveDays, strFromApp, strAppCode, strFromUserName, strCustomInfo)
		
		
		
	}
}
