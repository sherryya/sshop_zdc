package tools.mybatisGenerator;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.mysql.jdbc.Connection;


/**
 * 用于生成符合mybatis规范的dao、model和mapping。
 * @author lugz
 *
 */
public class MybatisGenerator {
	public MybatisGenerator() {
	}
	
	/**
	 * 连接数据库
	 * @param url
	 * @param name
	 * @param password
	 * @throws SQLException 
	 */
	private Connection connectDb(String url, String name, String password) throws SQLException {
		Connection con = (Connection) DriverManager.getConnection(url, name, password);
		return con;
	}
	
	/**
	 * 读取mybatis generator的配置文件。
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public void loadGeneratorConfig(String configFileName) throws ParserConfigurationException, SAXException, IOException, SQLException{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		InputStream  is = this.getClass().getResourceAsStream(configFileName);
		Document doc = builder.parse(is);
		Element root = doc.getDocumentElement();
		if (root == null)
		{
			System.err.println("generator root is null");
			return;
		}
		Element context = (Element) root.getElementsByTagName("context").item(0);
		Element jdbcConnection = (Element) context.getElementsByTagName("jdbcConnection").item(0);
		
		System.out.println("url=" + jdbcConnection.getAttribute("url")
				+"  user=" + jdbcConnection.getAttribute("user")
				+"  password=" + jdbcConnection.getAttribute("password"));
		Connection conn = connectDb(jdbcConnection.getAttribute("url"), jdbcConnection.getAttribute("user"), jdbcConnection.getAttribute("password"));
		configInfo = new ConfigInfo();
		configInfo.setConn(conn);
		
		Element model = (Element) context.getElementsByTagName("model").item(0);
		configInfo.setModelTargetProject(model.getAttribute("targetProject"));
		configInfo.setModelTableTargetPackage(model.getAttribute("tableTargetPackage"));
		configInfo.setModelJointableTargetProject(model.getAttribute("jointableTargetPackage"));

		Element mapping = (Element) context.getElementsByTagName("mapping").item(0);
		configInfo.setMappingTargetProject(mapping.getAttribute("targetProject"));
		configInfo.setMappingTableTargetPackage(mapping.getAttribute("tableTargetPackage"));
		configInfo.setMappingJointableTargetProject(mapping.getAttribute("jointableTargetPackage"));

		Element dao = (Element) context.getElementsByTagName("dao").item(0);
		configInfo.setDaoTargetProject(dao.getAttribute("targetProject"));
		configInfo.setDaoTableTargetPackage(dao.getAttribute("tableTargetPackage"));
		configInfo.setDaoJointableTargetProject(dao.getAttribute("jointableTargetPackage"));

		Element sqlMaps = (Element) root.getElementsByTagName("sqlMaps").item(0);
		List<String> sqlMapsList = new LinkedList<String>();
		NodeList sqlMapNodeList = sqlMaps.getElementsByTagName("sqlMap");
		int sqlMapCount = sqlMapNodeList.getLength();
		Element sqlMap;
		for(int i = 0; i < sqlMapCount; i ++) {
			sqlMap = (Element) sqlMapNodeList.item(i);
			sqlMapsList.add(sqlMap.getAttribute("resource"));
		}
		configInfo.setSqlMaps(sqlMapsList);
	}

	/**
	 * 处理SQL语句。
	 * @throws Exception 
	 */
	private void dealSqlConfig() throws Exception{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		
		List<String> sqlMapsList = configInfo.getSqlMaps();
		Iterator<String> it = sqlMapsList.iterator();
		String resource;
		while(it.hasNext()) {
			resource = it.next();
			InputStream  is = this.getClass().getResourceAsStream(resource);
			Document doc = builder.parse(is);
			Element root = doc.getDocumentElement();	//table元素或jointable元素。
			if (root == null)
			{
				System.err.println("sql root is null");
				return;
			}
			if (root.getTagName().equals("table")) {
				String lowerCaseTableName = root.getAttribute("name").toLowerCase();
				TableInfo tableInfo = new TableInfo();
				tableInfo.setConfigInfo(configInfo);
				tableInfo.setTableName(lowerCaseTableName);
				tableInfo.setModelName(underline2ObjectName(lowerCaseTableName));
				tableInfo.setDaoName(tableInfo.getModelName() + "Mapper");
				tableInfo.setMappingName(lowerCaseTableName);
				tableInfo.dealTable(root);
			}
	
			if (root.getTagName().equals("jointable")) {
				//TODO:
				String lowerCaseTableName = root.getAttribute("name").toLowerCase();
				TableInfo tableInfo = new TableInfo();
				tableInfo.setConfigInfo(configInfo);
				tableInfo.setTableName(lowerCaseTableName);
				tableInfo.setModelName(underline2ObjectName(lowerCaseTableName));
				tableInfo.setDaoName(tableInfo.getModelName() + "Mapper");
				tableInfo.setMappingName(lowerCaseTableName);
				tableInfo.dealJoinTable(root);
			}
		}
	}
	
	/**
	 * 把带下划线的字符串转换成java对象名字。
	 * @param s
	 * @return
	 */
	public static String underline2ObjectName(String s) {
		String sb = "";
		while (s.indexOf("_") != -1) {
			int pos = s.indexOf("_");
			sb = sb + s.substring(0, 1).toUpperCase() + s.substring(1, pos);
			s = s.substring(s.indexOf("_") + 1);
		}
		sb = sb + s.substring(0, 1).toUpperCase() + s.substring(1);

		return sb;
	}	
	
	private ConfigInfo configInfo;
	
	public static void main(String[] args) throws Exception{
		MybatisGenerator mybatisGenerator = new MybatisGenerator();
		try {
			mybatisGenerator.loadGeneratorConfig("myBatisGenerator.xml");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		try {
			mybatisGenerator.dealSqlConfig();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		String aa="ss#{aa}bb";
//		System.out.println("xxxx--:"+aa.replaceAll("\\#\\{\\S*\\}", "?"));
	}
}
