package com.bwzk.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.classic.Logger;
import com.bwzk.service.i.GepsService;

@Controller
@RequestMapping("/gepsCtl")
public class GepsCtler {
	/**
	 * <p>Title: 测试Geps接口是否通</p>
	 * <p>Description: 测试geps的JDBC是否可以正常使用</p>
	 * @param request
	 * @param response
	 * 
	 * @date 2014年1月15日
	*/
	@RequestMapping("/testGeps")
	public void viewLog(HttpServletRequest request , HttpServletResponse response) {
		PrintWriter out = null;
		try {
			response.setContentType( "text/html;charset=UTF-8 "); 
			out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("<BODY>");
			out.println("测试中文" + gepsService.projectList().size());
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
	
	@Autowired
	private GepsService gepsService;
	private Logger log =  (Logger) LoggerFactory.getLogger(this.getClass());
}
