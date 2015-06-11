package tools.mybatisGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ParameterMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.mysql.jdbc.DatabaseMetaData;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;

/**
 * 数据库表信息
 * @author lugz
 *
 */
public class TableInfo {
	//数据库类型和java类型的映射关系。
	static private Map<String, String>javaDbTypeMap = new HashMap<String, String>();
	
	/**
	 * 数据库的表名字。
	 */
	private String tableName;
	
	/**
	 * modeljava对象名字。
	 */
	private String modelName;

	/**
	 * dao java对象名字
	 */
	private String daoName;
	
	/**
	 * mapping文件的名字
	 */
	private String mappingName;
	
	/**
	 * 基本数据字段列表，中间使用逗号分割。
	 */
	private String basicColumnList;
	
	/**
	 * 表的字段信息
	 */
	private List<ColumnInfo> columnInfoList = new LinkedList<ColumnInfo>();
	
	/**
	 * 表的主键信息
	 */
	private List<ColumnInfo> keysList = new LinkedList<ColumnInfo>();
	
	/**
	 * 来自全局配置信息
	 */
	private ConfigInfo configInfo;

	static {
		//一个很小的整数。有符号的范围是-128到127，无符号的范围是0到255。
		javaDbTypeMap.put("TINYINT", "java.lang.Integer");

		//一个小整数。有符号的范围是-32768到32767，无符号的范围是0到65535。
		javaDbTypeMap.put("SMALLINT", "java.lang.Integer");

		//一个中等大小整数。有符号的范围是-8388608到8388607，无符号的范围是0到16777215。
		javaDbTypeMap.put("MEDIUMINT", "java.lang.Integer");

		//一个正常大小整数。有符号的范围是-2147483648到2147483647，无符号的范围是0到4294967295。
		javaDbTypeMap.put("INT", "java.lang.Integer");

		//这是INT的一个同义词。
		javaDbTypeMap.put("INTEGER", "java.lang.Integer");

		//一个大整数。有符号的范围是-9223372036854775808到9223372036854775807，无符号的范围是0到
		javaDbTypeMap.put("BIGINT", "java.lang.Long");

		//一个小(单精密)浮点数字。不能无符号。允许的值是-3.402823466E+38到-1.175494351E-38，0 和1.175494351E-38到3.402823466E+38。M是显示宽度而D是小数的位数。没有参数的FLOAT或有<24 的一个参数表示一个单精密浮点数字。
		javaDbTypeMap.put("FLOAT", "java.lang.Float");

		//一个正常大小(双精密)浮点数字。不能无符号。允许的值是-1.7976931348623157E+308到-2.2250738585072014E-308、 0和2.2250738585072014E-308到1.7976931348623157E+308。M是显示宽度而D是小数位数。没有一个参数的 DOUBLE或FLOAT(X)(25 < = X < = 53)代表一个双精密浮点数字。
		javaDbTypeMap.put("DOUBLE", "java.lang.Double");

		//这些是DOUBLE同义词。
		javaDbTypeMap.put("REAL", "java.lang.Double");

		//一个未压缩(unpack)的浮点数字。不能无符号。行为如同一个CHAR列：“未压缩”意味着数字作为一个字符串被存储，值的每一位使用一个字符。小数点，并且对于负数，“-”符号不在M中计算。如果D是0，值将没有小数点或小数部分。DECIMAL值的最大范围与DOUBLE相同，但是对一个给定的 DECIMAL列，实际的范围可以通过M和D的选择被限制。如果D被省略，它被设置为0。如果M被省掉，它被设置为10。注意，在MySQL3.22 里，M参数包括符号和小数点。
		javaDbTypeMap.put("DECIMAL", "java.lang.Double");

		//这是DECIMAL的一个同义词。
		javaDbTypeMap.put("NUMERIC", "java.lang.Double");

		//一个日期。支持的范围是'1000-01-01'到'9999-12-31'。MySQL以'YYYY-MM-DD'格式来显示DATE值，但是允许你使用字符串或数字把值赋给DATE列。
		javaDbTypeMap.put("DATE", "java.lang.Date");

		//一个日期和时间组合。支持的范围是'1000-01-01 00:00:00'到'9999-12-31 23:59:59'。MySQL以'YYYY-MM-DD HH:MM:SS'格式来显示DATETIME值，但是允许你使用字符串或数字把值赋给DATETIME的列。
		javaDbTypeMap.put("DATETIME", "java.lang.Date");

		//一个时间戳记。范围是'1970-01-01 00:00:00'到2037年的某时。MySQL以YYYYMMDDHHMMSS、YYMMDDHHMMSS、YYYYMMDD或YYMMDD 格式来显示TIMESTAMP值，取决于是否M是14(或省略)、12、8或6，但是允许你使用字符串或数字把值赋给TIMESTAMP列。一个 TIMESTAMP列对于记录一个INSERT或UPDATE操作的日期和时间是有用的，因为如果你不自己给它赋值，它自动地被设置为最近操作的日期和时间。你以可以通过赋给它一个NULL值设置它为当前的日期和时间。
		javaDbTypeMap.put("TIMESTAMP", "java.lang.Date");

		//一个时间。范围是'-838:59:59'到'838:59:59'。MySQL以'HH:MM:SS'格式来显示TIME值，但是允许你使用字符串或数字把值赋给TIME列。
		javaDbTypeMap.put("TIME", "java.lang.Time");

		//一个2或4位数字格式的年(缺省是4位)。允许的值是1901到2155，和0000(4位年格式)，如果你使用2位，1970-2069( 70-69)。MySQL以YYYY格式来显示YEAR值，但是允许你把使用字符串或数字值赋给YEAR列。(YEAR类型在MySQL3.22中是新类型。)
		javaDbTypeMap.put("YEAR", "java.lang.Integer");

		//一个定长字符串，当存储时，总是是用空格填满右边到指定的长度。M的范围是1 ～ 255个字符。当值被检索时，空格尾部被删除。CHAR值根据缺省字符集以大小写不区分的方式排序和比较，除非给出BINARY关键词。NATIONAL CHAR(短形式NCHAR)是ANSI SQL的方式来定义CHAR列应该使用缺省字符集。这是MySQL的缺省。CHAR是CHARACTER的一个缩写。
		javaDbTypeMap.put("CHAR", "java.lang.String");

		//一个变长字符串。注意：当值被存储时，尾部的空格被删除(这不同于ANSI SQL规范)。M的范围是1 ～ 255个字符。 VARCHAR值根据缺省字符集以大小写不区分的方式排序和比较，除非给出BINARY关键词值。 VARCHAR是CHARACTER VARYING一个缩写。
		javaDbTypeMap.put("VARCHAR", "java.lang.String");

		//一个BLOB或TEXT列，最大长度为255(2^8-1)个字符。
		javaDbTypeMap.put("TINYBLOB", "java.sql.Blob");

		//一个BLOB或TEXT列，最大长度为255(2^8-1)个字符。
		javaDbTypeMap.put("TINYTEXT", "java.sql.Clob");

		//一个BLOB或TEXT列，最大长度为255(2^8-1)个字符。
		javaDbTypeMap.put("BLOB", "java.sql.Blob");

		//一个BLOB或TEXT列，最大长度为255(2^8-1)个字符。
		javaDbTypeMap.put("TEXT", "java.sql.Clob");

		//一个BLOB或TEXT列，最大长度为255(2^8-1)个字符。
		javaDbTypeMap.put("MEDIUMBLOB", "java.sql.Blob");

		//一个BLOB或TEXT列，最大长度为255(2^8-1)个字符。
		javaDbTypeMap.put("MEDIUMTEXT", "java.sql.Clob");

		//一个BLOB或TEXT列，最大长度为255(2^8-1)个字符。
		javaDbTypeMap.put("LONGBLOB", "java.sql.Blob");

		//一个BLOB或TEXT列，最大长度为255(2^8-1)个字符。
		javaDbTypeMap.put("LONGTEXT", "java.sql.Clob");

		//枚举。一个仅有一个值的字符串对象，这个值式选自与值列表'value1'、'value2', ...,或NULL。一个ENUM最多能有65535不同的值。
		//javaDbTypeMap.put("ENUM", "");	//不支持。

		//一个集合。能有零个或多个值的一个字符串对象，其中每一个必须从值列表'value1', 'value2', ...选出。一个SET最多能有64个成员。
		//javaDbTypeMap.put("SET", "");		//不支持。
	}
	
	/**
	 * 生成model对象流。
	 */
	private StringBuffer generateModelBuffer(StringBuffer buf) {
		
		
		//第一步：清空内存信息。
		buf.setLength(0);
		
		//第二步：增加包名
		buf.append("package ").append(configInfo.getModelTableTargetPackage()).append(";\n\n");

		//第三步：增加import信息。
		Iterator<ColumnInfo> it = columnInfoList.iterator();
		ColumnInfo columnInfo = null;
		
		boolean hasDate=false, hasBlob=false, hasClob=false, hasTime=false;
		while(it.hasNext()) {
			columnInfo = it.next();
			if (columnInfo.getColumnClassName().indexOf("Date") != -1) {
				if (!hasDate) {
					buf.append("import java.util.Date;\n");
					hasDate = true;
				}
			} else if (columnInfo.getColumnClassName().indexOf("Blob") != -1) {
				if (!hasBlob) {
					buf.append("import java.sql.Blob;\n");
					hasBlob = true;
				}
			} else if (columnInfo.getColumnClassName().indexOf("Clob") != -1) {
				if (!hasClob) {
					buf.append("import java.sql.Clob;\n");
					hasClob = true;
				}
			} else if (columnInfo.getColumnClassName().indexOf("Time") != -1) {
				if (!hasTime) {
					buf.append("import java.lang.Time;\n");
					hasTime = true;
				}
			}
		}
		
		//第四步：增加类名字
		buf.append("public class ").append(modelName).append(" {\n");

		//第五步：增加字段信息
		it = columnInfoList.iterator();
		while(it.hasNext()) {
			columnInfo = it.next();
			buf.append("\tprivate ").append(columnInfo.getColumnShortClassName())
			.append(" ").append(columnInfo.getColumnJavaName()).append(";\n\n");
		}

		//第六步：增加setter和getter
	    String firstUpperColumnName;//首字母大写的字段名字
		it = columnInfoList.iterator();
		while(it.hasNext()) {
			columnInfo = it.next();
			firstUpperColumnName = columnInfo.getColumnJavaName().substring(0,1).toUpperCase() + columnInfo.getColumnJavaName().substring(1);
			buf.append("\tpublic ").append(columnInfo.getColumnShortClassName()).append(" get").append(firstUpperColumnName)
			.append("() {\n").append("\t\treturn ").append(columnInfo.getColumnJavaName()).append(";\n\t}\n\n")
			.append("\tpublic void set").append(firstUpperColumnName).append("(").append(columnInfo.getColumnShortClassName())
			.append(" ").append(columnInfo.getColumnJavaName()).append(") {\n\t\tthis.")
			.append(columnInfo.getColumnJavaName()).append(" = ").append(columnInfo.getColumnJavaName())
			.append(";\n\t}\n\n");
		}

		//最后一步：增加结束符号
		buf.append("}\n");
		
		return buf;
	}

	/**
	 * 生成model文件。
	 * @throws IOException 
	 */
	private void generateModelFile(StringBuffer buf) throws IOException {
		generateModelBuffer(buf);
		
		String modelPath = configInfo.getModelTargetProject()+"/" + configInfo.getModelTableTargetPackage().replaceAll("[.]","/")+"/" ;
		File ModelPathFile = new File( modelPath);
		ModelPathFile.mkdirs();
		FileOutputStream fos = new FileOutputStream(modelPath+"/" +  modelName +".java");
		fos.write(buf.toString().getBytes());  
		fos.close();
	}
	
	/**
	 * 生成mapping文件。
	 * @throws IOException 
	 */
	private void generateMappingFile(StringBuffer buf) throws IOException {		
		String mappingPath = configInfo.getMappingTargetProject()+"/" + configInfo.getMappingTableTargetPackage().replaceAll("[.]","/")+"/" ;
		File ModelPathFile = new File( mappingPath);
		ModelPathFile.mkdirs();
		FileOutputStream fos = new FileOutputStream(mappingPath+"/" +  mappingName +".xml");
		fos.write(buf.toString().getBytes());  
		fos.close();
	}

	/**
	 * 得到指定SQL语句的列和参数的信息。
	 * @param tableInfo
	 * @throws Exception 
	 */
	private void getTableColumnsAndKeys() throws Exception {
		DatabaseMetaData  dbMetaData = (DatabaseMetaData) configInfo.getConn().getMetaData();
		ResultSet  tableMetaData= dbMetaData.getColumns(null, null, tableName, "%");

		ColumnInfo columnInfo = null;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; tableMetaData.next(); i ++) {
			columnInfo = new ColumnInfo();
			columnInfo.setColumnIndex(i);
			columnInfo.setColumnName(tableMetaData.getString("COLUMN_NAME").toLowerCase());
			columnInfo.setColumnLabel(tableMetaData.getString("COLUMN_NAME").toLowerCase());
			if (javaDbTypeMap.get(tableMetaData.getString("TYPE_NAME")) == null) {
				throw new Exception("不支持的数据库字段类型" + tableMetaData.getString("TYPE_NAME"));
			}
			columnInfo.setColumnClassName(javaDbTypeMap.get(tableMetaData.getString("TYPE_NAME")));
			columnInfo.setColumnTypeName(tableMetaData.getString("TYPE_NAME"));
			columnInfo.setColumnType(tableMetaData.getInt("SQL_DATA_TYPE")); 
			columnInfo.setColumnDisplaySize(tableMetaData.getInt("COLUMN_SIZE"));
			columnInfo.setPrecision(tableMetaData.getInt("COLUMN_SIZE"));
			columnInfo.setScale(tableMetaData.getInt("DECIMAL_DIGITS"));
			columnInfo.setNullable(tableMetaData.getInt("NULLABLE")==DatabaseMetaData.typeNullable);
			columnInfoList.add(columnInfo);
			sb.append(columnInfo.getColumnLabel()).append(",");
		}
		
		if (!sb.toString().equals("") ) {
			sb.deleteCharAt(sb.length()-1);
		}
		basicColumnList = sb.toString();

		ColumnInfo keyInfo = null;
		ResultSet  keyMetaData= dbMetaData.getPrimaryKeys(null, null, tableName);
		Iterator<ColumnInfo> it = null;
		for(int i = 1; keyMetaData.next(); i ++) {
			keyInfo = new ColumnInfo();
			keyInfo.setColumnIndex(i);
			keyInfo.setColumnName(keyMetaData.getString("COLUMN_NAME").toLowerCase());
			keyInfo.setColumnLabel(keyMetaData.getString("COLUMN_NAME").toLowerCase());

			it = columnInfoList.iterator();
			while(it.hasNext()) {
				columnInfo = it.next();
				if (keyInfo.getColumnLabel().equals(columnInfo.getColumnLabel())) {
					keyInfo.setColumnClassName(columnInfo.getColumnClassName());
					keyInfo.setColumnTypeName(columnInfo.getColumnTypeName());
					keyInfo.setColumnType(columnInfo.getColumnType()); 
					keyInfo.setColumnDisplaySize(columnInfo.getColumnDisplaySize());
					keyInfo.setPrecision(columnInfo.getPrecision());
					keyInfo.setScale(columnInfo.getScale());
					keyInfo.setNullable(columnInfo.isNullable());
					keysList.add(keyInfo);
					break;
				}
			}
			
		}
	}

	/**
	 * 增加mapping流头数据，包括固定的按照主键生成的增加、修改、删除和查询功能。
	 * 如果没有主键则不生成修改、删除和查询功能，只生成增加功能。
	 */
	private void appendMappingHead(StringBuffer bufMapping){
		bufMapping.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n")
			.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\n")
			.append("<mapper namespace=\"").append(configInfo.getDaoTableTargetPackage()).append(".")
			.append(daoName).append("\" >\n");
		bufMapping.append("  <resultMap id=\"BaseResultMap\" type=\"").append(configInfo.getModelTableTargetPackage()).append(".").append(modelName).append("\" >\n");
		Iterator<ColumnInfo> it = columnInfoList.iterator();
		Iterator<ColumnInfo> itKey = null;
		ColumnInfo columnInfo = null;
		boolean isKey = false;
		while(it.hasNext()) {
			columnInfo = it.next();
			itKey = keysList.iterator();
			isKey = false;
			while (itKey.hasNext()) {
				if (columnInfo.getColumnJavaName().equals(itKey.next().getColumnJavaName())) {
					isKey = true;
					break;
				}
			}
			if (isKey) {
				bufMapping.append("    <id column=\"");
			} else {
				bufMapping.append("    <result column=\"");
			}
			bufMapping.append(columnInfo.getColumnLabel()).append("\" property=\"").append(columnInfo.getColumnJavaName()).append("\" jdbcType=\"").append(columnInfo.getColumnTypeName()).append("\" />\n");
		}
		
		bufMapping.append("  </resultMap>\n");

		//生成基本字段信息
		bufMapping.append("  <sql id=\"Base_Column_List\" >\n    ");
		bufMapping.append(basicColumnList);
		bufMapping.append("\n  </sql>\n");
		
		//生成标准insert语句
		bufMapping.append("  <insert id=\"insert\" parameterType=\"").append(configInfo.getModelTableTargetPackage()).append(".").append(modelName).append("\">\n");
		bufMapping.append("    insert into re_user_order (\n        ").append(basicColumnList).append("\n    values (\n        ");
		it = columnInfoList.iterator();
		int columnPos = 0;
		while(it.hasNext()) {
			columnInfo = it.next();
			if (columnPos != 0) {	//不是第一个字段，增加一个逗号。
				bufMapping.append(",");
				if (columnPos % 3 == 0) {	//3个字段换行。
					bufMapping.append("\n        ");
				}
			}
			bufMapping.append("#{").append(columnInfo.getColumnJavaName()).append(",jdbcType=").append(columnInfo.getColumnTypeName()).append("}");
			columnPos ++;
		}
		bufMapping.append("\n    )\n  </insert>\n");

		//如果有主键则生成修改、删除和查询功能。
		ColumnInfo keyInfo = null;
		int keyPos = 0;
		if (!keysList.isEmpty()) {
			//生成标准update语句
			bufMapping.append("  <update id=\"updateByPrimaryKeySelective\" parameterType=\"").append(configInfo.getModelTableTargetPackage()).append(".").append(modelName).append("\">\n");
			bufMapping.append("    update ").append(tableName).append("\n");
			bufMapping.append("    <set>\n");
			it = columnInfoList.iterator();
			while(it.hasNext()) {
				columnInfo = it.next();
				
				bufMapping.append("      <if test=\"").append(columnInfo.getColumnJavaName()).append(" != null\">\n        ");
				bufMapping.append(columnInfo.getColumnLabel()).append(" = #{").append(columnInfo.getColumnJavaName()).append(",jdbcType=").append(columnInfo.getColumnTypeName()).append("},\n");
				bufMapping.append("      </if>\n");
			}
			bufMapping.append("    </set>\n    where ");
			itKey = keysList.iterator();
			keyPos = 0;
			while (itKey.hasNext()) {
				keyInfo = itKey.next();
				if (keyPos != 0) {
					bufMapping.append("      and ");
				}
				bufMapping.append(keyInfo.getColumnLabel()).append(" = #{").append(keyInfo.getColumnJavaName()).append(",jdbcType=").append(keyInfo.getColumnTypeName()).append("},\n");
				keyPos ++;
			}
			bufMapping.append("  </update>\n");

			//生成标准delete语句
			itKey = keysList.iterator();
			keyInfo = itKey.next();
			if (keysList.size() == 1) {//单个字段为主键
				bufMapping.append("  <delete id=\"deleteByPrimaryKey\" parameterType=\"").append(keyInfo.getColumnClassName()).append("\">\n");
			} else {//联合主键
				bufMapping.append("  <delete id=\"deleteByPrimaryKey\" parameterType=\"java.util.Map\">\n");
			}
			bufMapping.append("    delete from ").append(tableName).append("\n");
			bufMapping.append("     where ");
			itKey = keysList.iterator();
			keyPos = 0;
			while (itKey.hasNext()) {
				keyInfo = itKey.next();
				if (keyPos != 0) {
					bufMapping.append("       and ");
				}
				bufMapping.append(keyInfo.getColumnLabel()).append(" = #{").append(keyInfo.getColumnJavaName()).append(",jdbcType=").append(keyInfo.getColumnTypeName()).append("}\n");
				keyPos ++;
			}
			bufMapping.append("  </delete>\n");

			//生成标准select语句
			itKey = keysList.iterator();
			keyInfo = itKey.next();
			if (keysList.size() == 1) {//单个字段为主键
				bufMapping.append("  <select id=\"selectByPrimaryKey\" resultMap=\"BaseResultMap\" parameterType=\"").append(keyInfo.getColumnClassName()).append("\">\n");
			} else {//联合主键
				bufMapping.append("  <select id=\"selectByPrimaryKey\" resultMap=\"BaseResultMap\" parameterType=\"java.util.Map\">\n");
			}
			bufMapping.append("    select \n    <include refid=\"Base_Column_List\" />\n");
			bufMapping.append("      from ").append(tableName);
			bufMapping.append("\n     where ");
			itKey = keysList.iterator();
			keyPos = 0;
			while (itKey.hasNext()) {
				keyInfo = itKey.next();
				if (keyPos != 0) {
					bufMapping.append("       and ");
				}
				bufMapping.append(keyInfo.getColumnLabel()).append(" = #{").append(keyInfo.getColumnJavaName()).append(",jdbcType=").append(keyInfo.getColumnTypeName()).append("}\n");
				keyPos ++;
			}
			bufMapping.append("  </select>\n");
		}
	}
	
	/**
	 * 增加mapping流头数据。
	 */
	private void appendMappingTail(StringBuffer bufMapping){
		bufMapping.append("</mapper>\n");
	}
	
	/**
	 * 处理table语句
	 * @param table
	 * @throws Exception 
	 */
	public void dealTable(Element table) throws Exception {
		getTableColumnsAndKeys();
		
		//第一步：生成model文件。
		StringBuffer bufModel = new StringBuffer();	//model buf
		generateModelFile(bufModel);

		StringBuffer bufDao = new StringBuffer();	//dao buf
		StringBuffer bufMapping = new StringBuffer();	//mapping buf
		
		appendMappingHead(bufMapping);	//增加mapping头数据。
		
		NodeList selectList = table.getElementsByTagName("select");
		Element sqlElement = null;
		for(int i = 0; i < selectList.getLength(); i ++) {
			sqlElement = (Element) selectList.item(i);
			dealSelectSql(sqlElement, bufDao, bufMapping);
		}
		NodeList insertList = table.getElementsByTagName("insert");
		for(int i = 0; i < insertList.getLength(); i ++) {
			sqlElement = (Element) insertList.item(i);
			dealInsertSql(sqlElement, bufDao, bufMapping);
		}
		NodeList updateList = table.getElementsByTagName("update");
		for(int i = 0; i < updateList.getLength(); i ++) {
			sqlElement = (Element) updateList.item(i);
			dealUpdateSql(sqlElement, bufDao, bufMapping);
		}
		NodeList deleteList = table.getElementsByTagName("delete");
		for(int i = 0; i < deleteList.getLength(); i ++) {
			sqlElement = (Element) deleteList.item(i);
			dealDeleteSql(sqlElement, bufDao, bufMapping);
		}
		
		appendMappingTail(bufMapping);	//增加mapping尾数据。
		
		//第二步：创建mapping文件
		generateMappingFile(bufMapping);
		
		//第三步：创建dao文件
	}


	/**
	 * 处理联合查询的表。
	 * @param table
	 */
	public void dealJoinTable(Element table) {
		
	}

	/**
	 *     处理单表SQL语句，SQL语句中可以包含“*”号，当出现“*”号时，工具内部会自动
	 * 替换所有的星号为具体的表字段列表。
	 * 
	 * @param sqlElement
	 * @param columnInfoList
	 * @throws DOMException
	 * @throws SQLException
	 */
	private void dealSelectSql(Element sqlElement, StringBuffer bufDao, StringBuffer bufMapping) throws DOMException, SQLException {
		List<ColumnInfo> parameterInfoList = new LinkedList<ColumnInfo>();
		String sqlRaw = sqlElement.getTextContent();
		sqlRaw = sqlRaw.replaceFirst("\\*", basicColumnList);	//替换所有的星号为具体的表字段列表。
		getSqlParameters(sqlRaw, parameterInfoList);
		
		//当有大于一个的条件时，需要使用对象传递参数。
		//1、当条件中的部分条件非model中的字段时，采用java.util.Map作为对象。
		if (parameterInfoList.size() > 1) {
			bufMapping.append("  <select id=\"").append(sqlElement.getAttribute("id")).append("\" resultMap=\"BaseResultMap\" parameterType=\"java.util.Map\">");
		} else if (parameterInfoList.size() == 1) { //一个参数
			bufMapping.append("  <select id=\"").append(sqlElement.getAttribute("id")).append("\" resultMap=\"BaseResultMap\" parameterType=\"").append(parameterInfoList.get(0).getColumnClassName()).append("\">");
		} else { //没有参数
			bufMapping.append("  <select id=\"").append(sqlElement.getAttribute("id")).append("\" resultMap=\"BaseResultMap\">");
		}
		sqlRaw = sqlElement.getTextContent();
		sqlRaw = sqlRaw.replaceFirst("\\*", "<include refid=\"Base_Column_List\" />");	//替换所有的星号为具体的表字段列表。
		Iterator<ColumnInfo> it= parameterInfoList.iterator();
		String newParameter;
		ColumnInfo parameterInfo = null;
		while (it.hasNext()) {
			parameterInfo = it.next();
			newParameter = "#{" + parameterInfo.getColumnJavaName() + ",jdbcType=" + parameterInfo.getColumnTypeName() + "}";    
			sqlRaw = sqlRaw.replaceFirst("\\#\\{\\S*\\}", newParameter);
		}
		bufMapping.append(sqlRaw);
		bufMapping.append("  </select>\n");
	}

	private void dealInsertSql(Element sqlElement, StringBuffer bufDao, StringBuffer bufMapping) {
		
	}
	
	private void dealUpdateSql(Element sqlElement, StringBuffer bufDao, StringBuffer bufMapping) {
		
	}

	private void dealDeleteSql(Element sqlElement, StringBuffer bufDao, StringBuffer bufMapping) {
		
	}

	/**
	 * 得到指定SQL语句的数据库字段列信息。
	 * @param sqlStmt
	 * @param columnInfoList
	 * @param parameterInfoList
	 * @throws SQLException
	 */
	private void getSqlColumns(String sqlStmt,
				List<ColumnInfo> columnInfoList) throws SQLException {
		PreparedStatement preparedStatement = (PreparedStatement) configInfo.getConn().prepareStatement(sqlStmt);
		
		ResultSetMetaData resultSetMetaData = (ResultSetMetaData) preparedStatement.getMetaData();
		ColumnInfo columnInfo = null;
		for(int i = 1; i <= resultSetMetaData.getColumnCount(); i ++) {
			columnInfo = new ColumnInfo();
			columnInfo.setColumnIndex(i);
			columnInfo.setColumnName(resultSetMetaData.getColumnName(i).toLowerCase());
			columnInfo.setColumnLabel(resultSetMetaData.getColumnLabel(i).toLowerCase());
			columnInfo.setColumnClassName(resultSetMetaData.getColumnClassName(i));
			columnInfo.setColumnTypeName(resultSetMetaData.getColumnTypeName(i));
			columnInfo.setColumnType(resultSetMetaData.getColumnType(i));
			columnInfo.setColumnDisplaySize(resultSetMetaData.getColumnDisplaySize(i));
			columnInfo.setPrecision(resultSetMetaData.getPrecision(i));
			columnInfo.setScale(resultSetMetaData.getScale(i));
			columnInfo.setNullable(resultSetMetaData.isNullable(i) == ResultSetMetaData.columnNullable);
			columnInfoList.add(columnInfo);
		}
	}

	/**
	 * 得到SQL语句中参数的名字列表。
	 * @param paramNameList
	 * @param sqlRaw
	 */
	private void getSqlParamNameList(List<String> paramNameList, String sqlRaw) {
		String paramName = "";
		int posBegin = sqlRaw.indexOf("#{");
		int posEnd = sqlRaw.indexOf("}", posBegin + 2);
		while ((posBegin != -1) && (posEnd != -1)) {
			paramName = sqlRaw.substring(posBegin + 2, posEnd);
			paramNameList.add(paramName);
			posBegin = sqlRaw.indexOf("#{", posEnd + 1);
			if (posBegin != -1) {
				posEnd = sqlRaw.indexOf("}", posBegin + 2);
			}
		}
	}
	
	/**
	 * 得到指定SQL语句的参数的信息。
	 * @param sqlStmt
	 * @param columnInfoList
	 * @param parameterInfoList
	 * @throws SQLException
	 */
	private void getSqlParameters(String sqlRaw,
				List<ColumnInfo> parameterInfoList) throws SQLException {
		String sqlStmt = sqlRaw.replaceAll("\\#\\{\\S*\\}", "?");		//替换变量为问号。
		PreparedStatement preparedStatement = (PreparedStatement) configInfo.getConn().prepareStatement(sqlStmt);
		
		List<String> paramNameList = new LinkedList<String>();
		getSqlParamNameList(paramNameList, sqlRaw);
		
		ColumnInfo columnInfo = null;
		ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
		for(int i = 1; i <= parameterMetaData.getParameterCount(); i ++) {
			columnInfo = new ColumnInfo();
			columnInfo.setColumnIndex(i);
			columnInfo.setColumnName("");
			columnInfo.setColumnLabel("");
			columnInfo.setColumnJavaName(paramNameList.get(i-1));
			columnInfo.setColumnClassName(parameterMetaData.getParameterClassName(i));
			columnInfo.setColumnTypeName(parameterMetaData.getParameterTypeName(i));
			columnInfo.setColumnType(parameterMetaData.getParameterType(i));
			columnInfo.setColumnDisplaySize(0);
			columnInfo.setPrecision(parameterMetaData.getPrecision(i));
			columnInfo.setScale(parameterMetaData.getScale(i));
			columnInfo.setNullable(false);
			parameterInfoList.add(columnInfo);
		}
	}

	public List<ColumnInfo> getColumnInfoList() {
		return columnInfoList;
	}

	public void setColumnInfoList(List<ColumnInfo> columnInfoList) {
		this.columnInfoList = columnInfoList;
	}

	public List<ColumnInfo> getKeysList() {
		return keysList;
	}

	public void setKeysList(List<ColumnInfo> keysList) {
		this.keysList = keysList;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getDaoName() {
		return daoName;
	}

	public void setDaoName(String daoName) {
		this.daoName = daoName;
	}

	public String getMappingName() {
		return mappingName;
	}

	public void setMappingName(String mappingName) {
		this.mappingName = mappingName;
	}

	public String getBasicColumnList() {
		return basicColumnList;
	}

	public void setBasicColumnList(String basicColumnList) {
		this.basicColumnList = basicColumnList;
	}

	public ConfigInfo getConfigInfo() {
		return configInfo;
	}

	public void setConfigInfo(ConfigInfo configInfo) {
		this.configInfo = configInfo;
	}	
}
