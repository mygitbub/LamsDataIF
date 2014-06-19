package com.bwzk.service.impl;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.tempuri.ImMessageService;
import org.tempuri.ImMessageServiceSoap;

import ch.qos.logback.classic.Logger;

import com.bwzk.dao.i.SBacklogMapper;
import com.bwzk.dao.i.SUserMapper;
import com.bwzk.pojo.MlToDoTask;
import com.bwzk.pojo.MlToDoTask.Task;
import com.bwzk.pojo.MlToDoTaskObjectFactory;
import com.bwzk.pojo.SBacklog;
import com.bwzk.pojo.SBacklogExample;
import com.bwzk.pojo.SUser;
import com.bwzk.service.BaseService;
import com.bwzk.service.i.NoticeService;
import com.bwzk.util.DateUtil;
import com.bwzk.util.GlobalFinalAttr;
import com.bwzk.util.SeriKeyOper;
import com.bwzk.util.XmlObjUtil;
@Service("noticeServiceImpl")
public class NoticeServiceImpl extends BaseService implements NoticeService {

	public void sendMsg( String  userCodes , String varsJson) {
		System.out.println("=============================================");
		System.out.println(userCodes);
		System.out.println(varsJson);
		System.out.println("=============================================");
		
		String sqrbm = "";
		String sqrxm = "";
		String sqyy = "";
		String mj = "";
		SUser user = null;
		short nOnlineOnly = 0;
		short nReserveDays = 2;
		
		if(StringUtils.isNotEmpty(varsJson)){
			ObjectMapper mapper = new ObjectMapper();  
			Map<String,Object> vars = null;
			try {
				Long nSenderPlatID = Long.parseLong(nSenderPlatIDStr);
				String[] userCodeList = StringUtils.split(userCodes,"[,]");
				vars = mapper.readValue(varsJson, Map.class); 
				sqrxm = (vars.get("sqrxm") == null ? "" : vars.get("sqrxm").toString());
				sqrbm = (vars.get("sqrbm") == null ? "" : vars.get("sqrbm").toString());
				sqyy = (vars.get("sqyy") == null ? "" : vars.get("sqyy").toString());
				mj = (vars.get("mj") == null ? "" : vars.get("mj").toString());
				String content = sendVM.replace("@usergroupname", sqrbm);
				content = content.replace("@username", sqrxm);
				content = content.replace("@sqyy", sqyy);
				content = content.replace("@mj", mj);
				for (String userCode : userCodeList) {
					user = sUserMapper.getUserByUsercode(userCode);
					if(user != null){
						// @usergroupname  @username @gotolamsurl @sqyy
						content = content.replace("@gotolamsurl", generatUrl(user,  "任务列表", "izerui"));
						System.out.println(content);
						log.error(this.sendMsgSoap().sendCommonMessageByUserCode(nSenderPlatID,
								userCode , nSenderPlatIDStr+"@"+userCode , content, nOnlineOnly 
								, nReserveDays , "GXT.IM", null, strFromUserName, null));
					}
				}
				
				
				//tk.setFTaskLinkUrl(generatUrl(user, "任务列表", "izerui"));
//				System.out.println(this.sendMsgSoap().sendCommonMessageByUserCode(2121L, "luyu", "2121@luyu", 
//						"测试看看是http://lams.canycan.com否这是消息可以", nOnlineOnly , nReserveDays , "GXT.IM", null, "陆宇", null));
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
	}

	@Override
	public String toDoList(String usercode) {
		String result = GlobalFinalAttr.RE_ML_ERR_MSG;
		Integer todoNum = sBacklogMapper.getBackLogNum(" OPERMODULEOWNER='izerui' AND OPERMODULEZH='任务列表'"
				+ " AND ISOPER=0 AND USERCODE ='" + usercode + "'");
		if(todoNum > 0){
			try {
				SUser user = sUserMapper.getUserByUsercode(usercode);
				if(user != null){
					MlToDoTaskObjectFactory factory = new MlToDoTaskObjectFactory();
					MlToDoTask tasks = factory.createToDoTasks();
					Task tk = factory.createToDoTasksTask();
					tk.setFTaskID(user.getDid());//这个不重要 所以用userdid代替
//					tk.setFNetModuleID(2);
					String title = (StringUtils.isEmpty(todoTitle) || !todoTitle.contains(UM_FLAG) ? "档案系统审批: 待处理"+todoNum+"条数据" :
							todoTitle.replace(UM_FLAG, todoNum.toString()));
					tk.setFTaskName(title);
					Date curDate = super.getDBDateTime();
					Date limitDate = DateUtil.addDate(curDate, 3);//有效时间为创建时间+3
					tk.setFCreateDate(DateUtil.getDateFormat().format(curDate));
					tk.setFBeginDate(DateUtil.getDateFormat().format(limitDate));
					tk.setFCreatorUsrName(user.getUsername());
					tk.setFTaskLinkUrl(generatUrl(user, "任务列表", "izerui"));
					tasks.getTask().add(tk);
					result = XmlObjUtil.obj2Xml(tasks);
				}
			} catch (Exception e) {
				result = GlobalFinalAttr.RE_ML_ERR_MSG;
			}
		}
		return result;
	}

	@Override
	public List<SBacklog> allBacklog(String usercode) {
		SBacklogExample ex = new SBacklogExample();
		ex.createCriteria().andUsercodeEqualTo("chenli");
		List<SBacklog> backLoList = sBacklogMapper.selectByExample(ex);
		return backLoList;
	}
	
	/**
	 * 得到登录Lams并且进入相应模块的URL
	 * @param user 用户
	 * @param modName 模块中文名称
	 * @param modOwner 模块所属者
	 */
	private String generatUrl(SUser user , String modName , String modOwner){
		StringBuffer sb = new StringBuffer();
		sb.append("http://").append(lamsIP).append("/Lams/autoLogin?card=").append(SeriKeyOper.encrypt(user.getUsercode()));
		sb.append("&serikey=").append(SeriKeyOper.encrypt(user.getPasswd())).append("&moduleName=");
		sb.append(SeriKeyOper.encrypt(modName)).append("&moduleOwner=").append(SeriKeyOper.encrypt(modOwner));
		sb.append("&random=").append(Math.random());
		return sb.toString();
	}
	
	/**
	 * 得到广梦 消息服务soap
	 */
	private ImMessageServiceSoap sendMsgSoap(){//mlSncUserPasswd
		URL url = null;
		QName qName = null;
		ImMessageServiceSoap  msgSoap = null;
		try {
			qName = new QName("http://tempuri.org/", "ImMessageService");
			url = new URL(gxtWSDL);
			msgSoap = new ImMessageService(url , qName).getImMessageServiceSoap();
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return msgSoap;
	}
	@Autowired
	private SUserMapper sUserMapper;
	@Autowired
	private SBacklogMapper sBacklogMapper;
	@Autowired
	@Value("${lams.ip}")
	private String lamsIP;
	@Autowired
	@Value("${morrowsoft.todo.title}")
	private String todoTitle;//档案系统审批: 共计unis-morrow条!
	@Autowired
	@Value("${morrowsoft.send2gxt.wsdl}")
	private String gxtWSDL;//gxt wsdl
	@Autowired
	@Value("${morrowsoft.send2gxt.nSenderPlatID}")
	private String nSenderPlatIDStr;//gxt wsdl
	@Autowired
	@Value("${morrowsoft.send2gxt.strFromUserName}")
	private String strFromUserName;//产生业务消息的账号的用户名。 可以在广梦系统中增加一个“系统消息”账号，发送者默认为 系统消息.
	@Autowired
	@Value("${morrowsoft.send2gxt.vm}")
	private String sendVM;//简单模板必须包含 @usergroupname  @username @gotolamsurl @sqyy @mj

	private Logger log =  (Logger) LoggerFactory.getLogger(this.getClass());
	private static final String UM_FLAG = "unis-morrow";//替换变量的关键字
}
