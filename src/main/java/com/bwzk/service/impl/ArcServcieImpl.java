package com.bwzk.service.impl;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.message.Attachment;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tempuri.DataSvc;
import org.tempuri.DataSvcSoap;
import org.tempuri.ReturnData;

import ch.qos.logback.classic.Logger;
import cn.lams.pojo.jaxb.Field;

import com.bwzk.dao.JdbcDao;
import com.bwzk.dao.i.SGroupMapper;
import com.bwzk.dao.i.SUserMapper;
import com.bwzk.dao.i.SUserroleMapper;
import com.bwzk.pojo.SGroup;
import com.bwzk.pojo.SUser;
import com.bwzk.pojo.SUserWithBLOBs;
import com.bwzk.pojo.SUserrole;
import com.bwzk.pojo.SUserroleExample;
import com.bwzk.service.BaseService;
import com.bwzk.service.i.ArcService;
import com.bwzk.util.CommonUtil;

@WebService
@Service("arcServcieImpl")
public class ArcServcieImpl  extends BaseService implements ArcService{
	@Transactional("txManager")
	public String fileRecive(String xml) {
		String resultStr = "";
		if(StringUtils.isNotEmpty(xml)){
			log.error("====================================================");//临时记录
			log.error(xml);//临时记录
			resultStr = this.add2Archive(xml);
			log.error("====================================================");//临时记录
			//throw new RuntimeException("test TX");
		}else{
			resultStr = "OA发送xml为空.";
			log.error(resultStr);
		}
		return resultStr;
	}
	
	/**
	 * @param xml 将xml解析并且添加到档案
	 */
	private String add2Archive(String xml){
		String resultStr = "1";
		return resultStr;
	}
	
	@Transactional("txManager")
	public List<SUser> listAllUser() {
		return sUserMapper.getAllUserList();
	}
	
	public String getBmidStrByDepCode(String depCode){
		return super.getBmidByDepCode(depCode);
	}
	
	public String getBmidStrByUserCode(String userCode){
		return super.getBmidByuserCode(userCode);
	}
	
	
	
	
	@Autowired
	private SGroupMapper sGroupMapper;
	@Autowired
	private SUserMapper sUserMapper;
	@Autowired
	private SUserroleMapper sUserroleMapper;
	@Autowired
	private JdbcDao jdbcDao;

	@Autowired
	@Value("${lams.dfile.status}")
	private String status;//状态
	@Autowired
	@Value("${lams.dfile.attr}")
	private String attr;//归档前后  1未归档  0已归档
	@Autowired
	@Value("${lams.dfile.attrex}")
	private String attrex;//移交
	@Autowired
	@Value("${lams.ip}")
	private String lamsIP;
	
	private Logger log =  (Logger) LoggerFactory.getLogger(this.getClass());
}
