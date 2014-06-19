package com.bwzk.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.ws.Holder;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.tempuri.MrBaseService;
import org.tempuri.MrBaseServiceSoap;

import ch.qos.logback.classic.Logger;

import com.bwzk.service.i.ArcService;
import com.bwzk.service.i.GepsService;
import com.bwzk.service.i.NoticeService;
import com.bwzk.util.GlobalFinalAttr;

@Controller
public class CommonCtler {
	/**
	 * 主页跳转
	 */
	@RequestMapping(value={"/index.html","/"})
	public String gotoIndex() {
		return "index.jsp";
	}
	/**
	 * 同步用户和部门
	 */
	@RequestMapping(value="/syncUserGroup")
	public ModelAndView syncUserGroup() {
		String message = arcServcieImpl.initUserGroup();
		return new ModelAndView("syncResult.jsp", "message", message);
	}
	
	/**
	 * 列出所有日志 
	 */
	@RequestMapping(value="/viewLogList")
	public ModelAndView viewLogList() {
		try {
			File[] listFile = new File(logHomeAdd).listFiles();
			return new ModelAndView("listLog.jsp", "listFile", listFile);
		} catch (Exception e) {
			log.error("获取日志列表错误." , e);
			return null;
		}
	} 
	
	/**
	 * 查看日志
	 */
	@RequestMapping("/viewLog")
	public void viewLog(HttpServletRequest request , HttpServletResponse response) {
		PrintWriter out = null;
		try {
			response.setContentType( "text/html;charset=GBK "); 
			out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("<BODY>");
			String filePath = request.getParameter("logFilePath");
			if(StringUtils.isEmpty(filePath)){
				out.print("获取日志错误!");
			}else{
				filePath = new String(filePath.getBytes("ISO-8859-1") , "UTF-8");
				File tempFile = new File(logHomeAdd+File.separatorChar+filePath);
				if(null != tempFile){
					List<String> stList = FileUtils.readLines(tempFile);
					for (String str : stList) {
						out.println(str+"<br/>");
					}
				}else{
					out.print("获取日志错误!");
				}
			}
			out.println("</BODY>");
			out.println("</HTML>");
		} catch (Exception e) {
			out.println("读取日志错误"+ e.getMessage());
			log.error("读取日志错误"+ e.getMessage());
		}finally{
			out.flush();
			out.close();
		}
	}
	
	/**
	 *初始化代码表修改
	 */
	@RequestMapping(value={"/initMapping"})
	public String initMapping() {
		arcServcieImpl.initMlFieldMapping();
		return "index.jsp";
	}
	/**
	 *接收梦龙项目管理中间库数据
	 */
	@RequestMapping(value={"/gepsSync"})
	public String gepsSync() {
		gepsService.projectList();
		return "index.jsp";
	}

	/**
	 * 列出所有用户 测试方法
	 */
	@RequestMapping(value="/getUsers" , method = RequestMethod.GET)
	public String userList(Model model){
		model.addAttribute("userlist", arcServcieImpl.listAllUser());
		return "userlist.jsp";
	}
	
	/**
	 * 单点登录
	 */
	@RequestMapping(value="/sso" , method = RequestMethod.GET)
	public String sso(@RequestParam String  usercode , @RequestParam String token ){
		String lamsUrl = "http://"+lamsIP+"/Lams/directLogin?usercode=";
		Boolean result = judgeSSO(usercode, token);
		if(result){//返回0 表示成功
			lamsUrl = lamsUrl + usercode;
			log.error("验证成功可以登录档案系统!");			
		}else{
			log.error("验证失败!");			
		}
		return "redirect:" + lamsUrl;   
	}
	
	/**
	 * 待办入口 会首先判断是否登录成功
	 */
	@RequestMapping(value="/toDoList")
	public void toDoList(HttpServletRequest request, HttpServletResponse response,@RequestParam String  usercode , @RequestParam String token ){
		String result = GlobalFinalAttr.RE_ML_ERR_MSG;
		if(StringUtils.isNotEmpty(usercode) && StringUtils.isNotEmpty(token)){
			Boolean isSSO = judgeSSO(usercode, token);
			if(isSSO){//返回0 表示成功
				result = noticeServiceImpl.toDoList(usercode);		
			}
			
		}
		System.out.println("-------------------------------------------------------------");
		System.out.println(result);
		System.out.println("-------------------------------------------------------------");
		response.setContentType("text/xml; charset=UTF-8");  
	    PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(result);   
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally{
			out.close();
		}
	}
	
	/**
	 * 发送消息到 即时通讯软件
	 */
	@RequestMapping(value="/sendMsg" , method = RequestMethod.POST)
	public void sendMsg(@RequestParam String  userCodes , @RequestParam String varsJson){
		if(StringUtils.isNotEmpty(varsJson)){
			noticeServiceImpl.sendMsg(userCodes, varsJson);
		}
	}
	
	/**
	 * 内部调用 传入token判断是否是ml平台的用户 ture是的
	 */
	private Boolean judgeSSO(String usercode , String token){
		Boolean result = false;
		log.error(usercode+" : " +token);
		Holder<Integer> resultInt = new Holder<Integer>(-1);
		Holder<String> resultMsg = new Holder<String>("--info--");
		try {
			MrBaseServiceSoap service = new MrBaseService(new URL(mlSSOWSDL) ,
					new QName("http://tempuri.org/", "MrBaseService")).getMrBaseServiceSoap();
			
			service.wmCheckUserByToken(usercode, token, resultMsg , resultInt);
			log.error(resultInt.value+" : "+resultMsg.value);
		} catch (MalformedURLException e) {
			log.error(e.getMessage()+" : "+resultInt.value+" : "+resultMsg.value);
		}
		System.out.println("===="+resultInt.value.equals(0)+"=====");
		System.out.println("===="+resultInt.value+"=====");
		return  (resultInt.value.equals(0) ? true : false);
	}
	
	@Autowired
	private ArcService arcServcieImpl;
	@Autowired
	private NoticeService noticeServiceImpl;
	@Autowired
	private GepsService gepsService;
	//日志地址
	@Autowired
	@Value("${interface.log.home.address}")
	private String logHomeAdd;
	//梦龙单点的wsdl
	@Autowired
	@Value("${morrowsoft.sso.wsdl}")
	private String mlSSOWSDL;
	@Autowired
	@Value("${lams.ip}")
	private String lamsIP;//档案服务器ip
	private Logger log =  (Logger) LoggerFactory.getLogger(this.getClass());
}
