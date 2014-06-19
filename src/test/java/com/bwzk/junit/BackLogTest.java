package com.bwzk.junit;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bwzk.pojo.MlToDoTask;
import com.bwzk.pojo.MlToDoTask.Task;
import com.bwzk.pojo.MlToDoTaskObjectFactory;
import com.bwzk.pojo.SBacklog;
import com.bwzk.service.i.NoticeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/test*.xml" })
public class BackLogTest extends AbstractJUnit4SpringContextTests {
	@Autowired
	private NoticeService noticeServiceImpl;

	@Test
	public void test01() {
		List<SBacklog> backLoList = noticeServiceImpl.allBacklog("lihong");
		for (SBacklog bakLog : backLoList) {
			System.out.println(bakLog.getDetail());
		}
	}

	@Test
	public void test02() throws JAXBException {
		Marshaller mashaller = JAXBContext.newInstance(MlToDoTask.class).createMarshaller();
		mashaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
		MlToDoTaskObjectFactory factory = new MlToDoTaskObjectFactory();
		MlToDoTask tasks = factory.createToDoTasks();
		Task tk = factory.createToDoTasksTask();
		tk.setFBeginDate("begin");
		tk.setFCreateDate("create");
		tk.setFCreatorUsrName("createUsername");
		tk.setFNetModuleID(1);
		tk.setFTaskID(22);
		tk.setFTaskLinkUrl("tasklinkUrl");
		tk.setFTaskName("测试nanme");
		Task tk1 = factory.createToDoTasksTask();
		tk1.setFBeginDate("beg1in");
		tk1.setFCreateDate("cre1ate");
		tk1.setFCreatorUsrName("crea1teUsername");
		tk1.setFNetModuleID(11);
		tk1.setFTaskID(221);
		tk1.setFTaskLinkUrl("tas1klinkUrl");
		tk1.setFTaskName("测试n1anme");
		tasks.getTask().add(tk);
		tasks.getTask().add(tk1);
		mashaller.marshal(tasks, new File("c:/outPut.xml"));
		// MLXmlResult toxmlResult = factory.createResult();
		// //可选 开始
		// com.bwzk.pojo.MLXmlResult.DocInfo.Field theField =
		// factory.createResultDocInfoField();
		// theField.setColName("中文桑德菲杰三闾大夫");
		// com.bwzk.pojo.MLXmlResult.DocInfo dii =
		// factory.createResultDocInfo();
		// dii.getField().add(theField);
		// toxmlResult.setDocInfo(dii);
		// //可选 结束
		// mashaller.marshal(toxmlResult, new File("c:/outPut.xml"));
	}
	
	@Test
	public void test03(){
		
		String[] ass = StringUtils.split(",luyu,ab,ccc,de","[,]");
		System.out.println(ass.length);
		System.out.println(ass[0]);
	}
}
