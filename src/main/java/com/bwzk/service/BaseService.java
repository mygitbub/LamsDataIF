package com.bwzk.service;

import java.io.Reader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.RuntimeSqlException;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;

import com.bwzk.dao.JdbcDao;
import com.bwzk.dao.i.SGroupMapper;
import com.bwzk.dao.i.SUserMapper;
import com.bwzk.dao.i.SUserroleMapper;
import com.bwzk.pojo.SGroup;
import com.bwzk.pojo.SUser;
import com.bwzk.util.DateUtil;
import com.bwzk.util.GlobalFinalAttr.DatabaseType;

@Service
public class BaseService {
	/**
	 * 得到数据库信息 databaseType 和 databaseTime
	 */
	protected Map<String, Object> getDBInfo() throws RuntimeSqlException {
		Date dataTime = null;
		Map<String, Object> infos = new LinkedHashMap<String, Object>();
		TimeZone.setDefault(TimeZone.getTimeZone("ETC/GMT-8"));  //设置时区 中国/北京/香港
		String typeStr = getDBTyeStr();
		if(StringUtils.isNotEmpty(typeStr)){
			if(typeStr!=null&&typeStr.equals("Microsoft SQL Server")){
				dataTime = sUserMapper.selectDateTimeForMSSQL();
			}else if(typeStr!=null&&typeStr.equals("Oracle")){
				dataTime = sUserMapper.selectDateTimeForOra();
			}else if(typeStr!=null&&typeStr.equals("Db2")){
				dataTime = sUserMapper.selectDateTimeForDB2();
			}else if(typeStr!=null&&typeStr.equals("MySQL")){
				dataTime = sUserMapper.selectDateTimeForMySQL();
			}else if(typeStr!=null&&typeStr.equals("H2")){
				dataTime = sUserMapper.selectDateTimeForH2();
			}else{
				dataTime = new Date();
				log.error("DB Type not funder!");
			}
		}else{
			dataTime = new Date();
			log.error("get database time is error!");
		}
		infos.put("databaseType", typeStr);
		infos.put("databaseTime", dataTime);
		return infos;
	}
	
	
	protected String generateTimeToSQLDate(Object date){
		String datevalue = null;
		String typeStr = getDBTyeStr();
		TimeZone.setDefault(TimeZone.getTimeZone("ETC/GMT-8"));  //设置时区 中国/北京/香港
		if(date instanceof Date){
			datevalue = DateUtil.getDateTimeFormat().format(date);
		}else if(date instanceof String){
			datevalue = (String)date;
		}
		if(StringUtils.isNotEmpty(typeStr)){
			if(typeStr!=null&&typeStr.equals("Microsoft SQL Server")){
				datevalue = "cast('" + datevalue + "' as datetime)";
			}else if(typeStr!=null&&typeStr.equals("Oracle")){
				if(datevalue.indexOf(".")>-1){//防止出现 2056-12-25 00:00:00.0 而无法导入
					datevalue = datevalue.substring(0, datevalue.lastIndexOf("."));
				}
				datevalue = "TO_DATE('"+datevalue+"', 'yyyy-MM-dd HH24:mi:ss')";
			}else if(typeStr!=null&&typeStr.equals("Db2")){
				datevalue = "TIMESTAMP('" + datevalue + "' )";
			}else if(typeStr!=null&&typeStr.equals("MySQL")){
				datevalue =  "DATE_FORMAT('"+datevalue+"', '%Y-%m-%d %H:%i:%s')";
			}else if(typeStr!=null&&typeStr.equals("H2")){
				datevalue = "PARSEDATETIME('" + datevalue + "'，'dd-MM-yyyy hh:mm:ss.SS' )";
			}else{
				datevalue = "";
				log.error("DB Type not funder!");
			}
		}else{
			datevalue = "";
			log.error("get database time is error!");
		}
		return datevalue;
	}
	
	/**
	 * 得到数据库的时间 如果错误返回new的时间
	 * 
	 */
	protected Date getDBDateTime() throws RuntimeSqlException{
		Date dbDate = null;
		TimeZone.setDefault(TimeZone.getTimeZone("ETC/GMT-8"));  //设置时区 中国/北京/香港
		String typeStr = getDBTyeStr();
		if(StringUtils.isNotEmpty(typeStr)){
			if(typeStr.equals("Microsoft SQL Server")){
				dbDate = sUserMapper.selectDateTimeForMSSQL();
			}else if(typeStr.equals("Oracle")){
				dbDate = sUserMapper.selectDateTimeForOra();
			}else if(typeStr.equals("Db2")){
				dbDate = sUserMapper.selectDateTimeForDB2();
			}else if(typeStr.equals("MySQL")){
				dbDate = sUserMapper.selectDateTimeForMySQL();
			}else if(typeStr.equals("H2")){
				dbDate = sUserMapper.selectDateTimeForH2();
			}else{
				dbDate = new Date();
				log.error("DB is no look!");
			}
		}else{
			dbDate = new Date();
			log.error("get database time is error!");
		}
		return dbDate;
	}	
	
	/**
	 * 得到数据库的类型str
	 */
	protected String getDBTyeStr() throws RuntimeSqlException {
		String typeStr = null;
		TimeZone.setDefault(TimeZone.getTimeZone("ETC/GMT-8"));  //设置时区 中国/北京/香港
		Connection conn = null;
		DatabaseMetaData dbmd = null;
		try {
			conn = jdbcDao.getConn();
			dbmd = conn.getMetaData();
			typeStr = dbmd.getDatabaseProductName();
		} catch (Exception e) {
			log.error("get database type is error!" , e);
		}finally{
			try {
				dbmd = null;
				conn.close();
			} catch (SQLException exx) {
				log.error(exx.getMessage());
			}
		}
		return typeStr;
	}
	
	/**
	 * 得到数据库类型的 DatabaseType
	 */
	protected DatabaseType getDatabaseType(){
		DatabaseType databaseType = null;
		try {
			databaseType = DatabaseType.getDatabaseType(getDBTyeStr());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return databaseType;
	}
	
	/**
	 * 根据表名判断数据表是否存在
	 */
	protected Boolean existTable(String tablename){
		boolean result = false;
		Connection conn = null;
		DatabaseMetaData dbmd = null;
		ResultSet rs = null;
		try {
			conn = jdbcDao.getConn();
			dbmd = conn.getMetaData();
			String schemaName = getSchemaName(dbmd);
			rs = dbmd.getTables(null , schemaName ,  tablename, new String[]{"TABLE"});
			if(rs.next()){
				result = true;
			}
		}catch(Exception ex){
			log.error(ex.getMessage());
		}finally{
			try {
				dbmd = null;
				rs.close();
				conn.close();
			} catch (SQLException e) {
				log.error("获取ConnectionMetaData关闭链接错误!");
			}
		}
		return result;
	}
	
	/**
	 * 判断表的字段是否存在
	 */
	protected boolean existColumn(String tablename,String columnName){
		return existColumnOrIndex(tablename, columnName, true);
	}
	/**
	 * 判断字段的索引是否存在
	 */
	protected boolean existIndex(String tablename,String indexName){
		
		return existColumnOrIndex(tablename, indexName, false);
	}
	
	protected Map<String, Object> queryForMap(String sql){
		return jdbcDao.queryForMap(sql);
	}
	protected List<Map<String, Object>> quertListMap(String sql){
		return jdbcDao.quertListMap(sql);
	}
	protected String queryForString(String sql){
		return jdbcDao.query4String(sql);
	}
	/**
	 * 查新表2列 第一列是key第二列是value的一个map
	 */
	protected Map<String , String> quert2Colum4Map(String sql , String col1 , String col2){
		return jdbcDao.quert2Colum4Map(sql , col1 , col2);
	}
	/**
	 * 判断表的字段或者索引是否存在
	 * @param tablename 表名
	 * @param columnOrIndexName 字段名, 或者索引名
	 * @param isColumn true字段 false索引
	 * @return boolean true存在 false 不存在
	 */
	protected boolean existColumnOrIndex(String tablename,String columnOrIndexName,boolean isColumn){
		boolean result = false;
		Connection conn = null;
		DatabaseMetaData dbmd = null;
		ResultSet rs = null;
		try {
			conn = jdbcDao.getConn();
			dbmd = conn.getMetaData();
			String schemaName = getSchemaName(dbmd);
			if(isColumn){
				rs = dbmd.getColumns(null, schemaName, tablename, columnOrIndexName);
				if(rs.next()){
					result = true;
				}
			}else{
				rs = dbmd.getIndexInfo(null, schemaName, tablename, false, false);
				while (rs.next()) {
					String indexName = rs.getString(6);
					if(indexName!=null&&indexName.equals(columnOrIndexName)){
						result = true;
						break;
					}
				}
			}
		}catch(Exception ex){
			log.error(ex.getMessage());
		}finally{
			try {
				dbmd = null;
				rs.close();
				conn.close();
			} catch (SQLException e) {
				log.error("获取ConnectionMetaData关闭链接错误!");
			}
		}
		return result;
	}
	
	/**
	 * 根据表字段是否可以为空
	 */
	protected boolean validateColumnIsNULL(String tablename,String columnName){
		boolean result = false;
		Connection conn = null;
		DatabaseMetaData dbmd = null;
		ResultSet rs = null;
		try {
			conn = jdbcDao.getConn();
			dbmd = conn.getMetaData();
			String schemaName = getSchemaName(dbmd); 
			rs = dbmd.getColumns(null, schemaName , tablename, columnName);
			if(rs.next()){
				String notnull = rs.getString(11);
				result = notnull!=null&&notnull.equals("1");
			}
		}catch(Exception ex){
			log.error(ex.getMessage());
		}finally{
			try {
				dbmd = null;
				rs.close();
				conn.close();
			} catch (SQLException e) {
				log.error("获取ConnectionMetaData关闭链接错误!");
			}
		}
		return result;
	}
	
	/**
	 * 执行sql文件
	 */
	protected boolean runScript(Reader reader){
		boolean result = false;
		Connection conn = null;
		try {
			conn = jdbcDao.getConn();
			ScriptRunner runner = new ScriptRunner(conn);  
			runner.setErrorLogWriter(null);  
			runner.setLogWriter(null);  
			runner.runScript(reader);  
			result = true;
		}catch(Exception ex){
			log.error(ex.getMessage() + "执行sql文件错误" , ex);
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				log.error(e.getMessage() + "获取ConnectionMetaData关闭链接错误!" , e);
			}
		}
		return result;
	}
	
	/**
	 * 获取表模式 private
	 */
	private String getSchemaName(DatabaseMetaData dbmd) throws SQLException{
		String schemaName;
		switch (getDatabaseType().getValue()) {
		case 1://mssql
			schemaName = sqlserverSchemaName;
			break;
		case 4://h2
			schemaName = null;
			break;
		default:
			schemaName = dbmd.getUserName();
			break;
		}
		return schemaName;
	}
	
	protected void execSql(String sql){
		jdbcDao.excute(sql);
	}
	/**
	 * 通过groupdid得到bmid
	 * @author: LuYu
	 */
	protected String getBmidByDepCode(String depCode){
		StringBuffer bmid = new StringBuffer();
		SGroup firstGroup = this.getGroupByDepCode(depCode);
		try {
			List<SGroup> groupList = this.getGroupList(firstGroup.getDid() , null);
			bmid.append(groupList.get(0).getQzh());
			Collections.reverse(groupList);
			for (SGroup group : groupList) {
				bmid.append("_").append(group.getDid());
			}
		} catch (Exception e) {
			bmid.append("");
			log.error("getUserByUserCode类在通过groupDID得到group的时候的时错误,在 getGroupByDid is " + e.getMessage());
		}
		return bmid.toString();
	}
	/**
	 * 通过groupdid得到bmid
	 * @author: LuYu
	 */
	protected String getBmidByuserCode(String usercode){
		StringBuffer bmid = new StringBuffer();
		Integer groupDid = sUserMapper.getUserByUsercode(usercode).getPid();
		try {
			List<SGroup> groupList = this.getGroupList(groupDid , null);
			bmid.append(groupList.get(0).getQzh());
			Collections.reverse(groupList);
			for (SGroup group : groupList) {
				bmid.append("_").append(group.getDid());
			}
		} catch (Exception e) {
			bmid.append("");
			log.error("getUserByUserCode类在通过groupDID得到group的时候的时错误,在 getGroupByDid is " + e.getMessage());
		}
		return bmid.toString();
	}
	
	/**
	 * 获取数据库参数 数据库类型名称,时间
	 */
	protected String getSysdate(){
		if(sysdate!=null){
			return sysdate;
		}
		try {
			String databaseType = getDBTyeStr();
			if(databaseType!=null&&databaseType.equals("Microsoft SQL Server")){
				sysdate = "GETDATE()";
			}else if(databaseType!=null&&databaseType.equals("Oracle")){
				sysdate = "SYSDATE";
			}else if(databaseType!=null&&databaseType.equals("Db2")){
				sysdate = "CURRENT TIMESTAMP";
			}else if(databaseType!=null&&databaseType.equals("MySQL")){
				sysdate = "NOW()";
			}else if(databaseType!=null&&databaseType.equals("H2")){
				sysdate = "current_timestamp";
			}
		} catch (Exception e) {
			log.error("get database time is error!");
		}
		return sysdate;
	}
	
	protected SGroup getGroupByDid(Integer did){
		return sGroupMapper.selectByPrimaryKey(did);
	}
	protected SGroup getGroupByDepCode(String depCode){
		return sGroupMapper.getGroupByDepcode(depCode);
	}
	
	protected SUser getUserByUserCode(String usercode){
		return sUserMapper.getUserByUsercode(usercode);
	}
	
	/**
	 * 通过组的得到一个groupList 从小到大 从最底层到最高层
	 * @param 组的did
	 */
	private List<SGroup> getGroupList(Integer groupDid , List<SGroup> groupList){
		SGroup tempGroup = this.getGroupByDid(groupDid);
		if(groupList == null){
			groupList = new ArrayList<SGroup>();
		}
		groupList.add(tempGroup);
		if(tempGroup.getPid() != 0){
			this.getGroupList(tempGroup.getPid(), groupList);
		}
		return groupList;
		
	}
	
	protected Integer getMaxDid(String tableName){
		Integer returnMaxDid = sUserMapper.getMaxDid(tableName);
		if(returnMaxDid == null ){
			returnMaxDid = 1;
		}else{
			returnMaxDid = returnMaxDid + 1;
		}
		return returnMaxDid;
		
	}
	
	@Autowired
	private JdbcDao jdbcDao;
	@Autowired
	private SGroupMapper sGroupMapper;
	@Autowired
	private SUserMapper sUserMapper;
	@Autowired
	private SUserroleMapper sUserroleMapper;
	@Autowired
	@Value("${sqlserverSchemaName}")
	private String sqlserverSchemaName;
	private String sysdate = null;
	private Logger log =  (Logger) LoggerFactory.getLogger(this.getClass());
}
