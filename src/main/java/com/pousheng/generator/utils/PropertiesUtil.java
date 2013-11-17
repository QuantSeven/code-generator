package com.pousheng.generator.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.pousheng.generator.core.model.ModelVO;
import com.pousheng.generator.database.model.Table;

public class PropertiesUtil {

	private static Properties props = null;
	// 数据库连接
	public static String DIVER_NAME = "com.mysql.jdbc.Driver";
	public static String URL = "jdbc:mysql://localhost:3306/t_generator?useUnicode=true&characterEncoding=UTF-8";
	public static String USERNAME = "root";
	public static String PASSWORD = "root";
	public static String TABLE_NAME = "t_generator";

	// 文件目录
	public static String JAVA_ROOT_PATH = "src/main/java";// Java源代码跟目录
	public static String RESOURCES_ROOT_PATH = "src/main/resources";// 资源文件根目录
	public static String WEB_ROOT_PATH = "src/main/webapp";// web文件根目录
	public static String JSP_PATH = "WEB-INF/views";//
	public static String JAVASCRIPT_PATH = "js/module";//
	public static String I18N_PATH = "i18n";//
	public static List<File> I18N_FILES = new ArrayList<File>(0);

	// 包名
	public static String BASE_PACKAGE = "test";
	public static String DAO_PACKAGE = "dao";
	public static String XML_PACKAGE = "mappings";
	public static String SERVICE_PACKAGE = "service";
	public static String SERVICEIMPL_PACKAGE = "service";
	public static String CONTROLLER_PACKAGE = "controller";
	public static String MODEL_PACKAGE = "model";

	// 是否生成指定的文件
	public static Boolean DAO_FLAG = true;
	public static Boolean XML_FLAG = true;
	public static Boolean SERVICE_FLAG = true;
	public static Boolean SERVICEIMPL_FLAG = true;
	public static Boolean CONTROLLER_FLAG = true;
	public static Boolean MODEL_FLAG = true;
	public static Boolean JSP_FLAG = true;
	public static Boolean JAVASCRIPT_FLAG = true;
	public static Boolean I18N_FLAG = true;

	public static Map<Object, Object> templateModelData = new HashMap<Object, Object>(); // Freemark模板数据

	public PropertiesUtil() {
	}

	static {
		props = loadAllProperties("generator/generator.properties");

		DIVER_NAME = getProperty("jdbc.driver", DIVER_NAME);
		URL = getProperty("jdbc.url", URL);
		USERNAME = getProperty("jdbc.username", USERNAME);
		PASSWORD = getProperty("jdbc.password", PASSWORD);

		JAVA_ROOT_PATH = System.getProperty("user.dir") + "/" + getProperty("java_root_path", JAVA_ROOT_PATH);
		RESOURCES_ROOT_PATH = System.getProperty("user.dir") + "/" + getProperty("resources_root_path", RESOURCES_ROOT_PATH);
		WEB_ROOT_PATH = System.getProperty("user.dir") + "/" + getProperty("web_root_path", WEB_ROOT_PATH);
		JSP_PATH = WEB_ROOT_PATH + "/" + getProperty("jsp_path", JSP_PATH);
		JAVASCRIPT_PATH = WEB_ROOT_PATH + "/" + getProperty("js_path", JAVASCRIPT_PATH);
		I18N_PATH = RESOURCES_ROOT_PATH + "/" + getProperty("i18n_path", I18N_PATH);

		BASE_PACKAGE = getProperty("basepackage", BASE_PACKAGE);
		DAO_PACKAGE = BASE_PACKAGE + "." + getProperty("dao_package", DAO_PACKAGE);
		XML_PACKAGE = getProperty("xmlbasepackage", XML_PACKAGE);
		SERVICE_PACKAGE = BASE_PACKAGE + "." + getProperty("service_package", SERVICE_PACKAGE);
		SERVICEIMPL_PACKAGE = BASE_PACKAGE + "." + getProperty("serviceimpl_package", SERVICEIMPL_PACKAGE);
		CONTROLLER_PACKAGE = BASE_PACKAGE + "." + getProperty("controller_package", CONTROLLER_PACKAGE);
		MODEL_PACKAGE = BASE_PACKAGE + "." + getProperty("model_package", MODEL_PACKAGE);

		try {
			FileUtil.listFiles(new File(I18N_PATH), I18N_FILES);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static Map<Object, Object> initTemplateModelData(Table table, ModelVO model) {
		String subPackageName = model.getPackageName();
		if (!StringUtil.isNullOrEmpty(subPackageName)) {
			DAO_PACKAGE = DAO_PACKAGE + "." + subPackageName;
			XML_PACKAGE = XML_PACKAGE + "." + subPackageName;
			SERVICE_PACKAGE = SERVICE_PACKAGE + "." + subPackageName;
			SERVICEIMPL_PACKAGE = SERVICEIMPL_PACKAGE + "." + subPackageName;
			CONTROLLER_PACKAGE = CONTROLLER_PACKAGE + "." + subPackageName;
			MODEL_PACKAGE = MODEL_PACKAGE + "." + subPackageName;
			JSP_PATH = JSP_PATH + "/" + subPackageName;
			JAVASCRIPT_PATH = JAVASCRIPT_PATH + "/" + subPackageName;
		}
		// TemplateModel templateModel = new
		// TemplateModel(tableVO.getTableName(), tableVO.getClassName(),
		// BASE_PACKAGE, subPackageName, DAO_PACKAGE, XML_PACKAGE,
		// SERVICE_PACKAGE, SERVICEIMPL_PACKAGE, MODEL_PACKAGE,
		// CONTROLLER_PACKAGE, JSP_PATH, JAVASCRIPT_PATH,
		// tableVO.getTemplateType());
		templateModelData.put("className", model.getClassName());
		templateModelData.put("template_type", model.getTemplateType());
		templateModelData.put("base_package", BASE_PACKAGE);
		templateModelData.put("sub_package_path", StringUtil.replace(model.getPackageName(), ".", "/"));
		templateModelData.put("xmlbasepackage", XML_PACKAGE);
		templateModelData.put("dao_package", DAO_PACKAGE);
		templateModelData.put("service_package", SERVICE_PACKAGE);
		templateModelData.put("serviceimpl_package", SERVICEIMPL_PACKAGE);
		templateModelData.put("model_package", MODEL_PACKAGE);
		templateModelData.put("controller_package", CONTROLLER_PACKAGE);
		templateModelData.put("jsp_path", JSP_PATH);
		templateModelData.put("js_path", JAVASCRIPT_PATH);
		templateModelData.put("table", table);
		return templateModelData;
	}

	public static String getProperty(String key) {
		return props.getProperty(key);
	}

	public static String getProperty(String key, String defaultValue) {
		return props.getProperty(key, defaultValue);
	}

	// 加载所有的资源文件
	public static Properties loadAllProperties(String resourceName) {
		Properties properties = new Properties();
		try {
			Enumeration<URL> urls = PropertiesUtil.class.getClassLoader().getResources(resourceName);
			while (urls.hasMoreElements()) {
				URL url = urls.nextElement();
				InputStream is = null;
				try {
					URLConnection con = url.openConnection();
					con.setUseCaches(false);
					is = con.getInputStream();
					properties.load(is);
				} finally {
					if (is != null) {
						is.close();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
}
