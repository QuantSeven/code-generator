package com.pousheng.generator.core;

import java.io.File;
import java.io.IOException;

import com.pousheng.generator.utils.PropertiesUtil;
import com.pousheng.generator.utils.StringUtil;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;

public class Generator {

	public static String TEMPLATE_ROOT_DIR = "";

	public static Boolean IS_FORM_JAR_PACKAGE = true;

	// 获取要生成的文件路径和名称
	public File getTargetFile(String templateRelativePath) {
		String filePath = "";
		File targetFile = null;
		String targetFileName = geTargetFileName(templateRelativePath);
		if (PropertiesUtil.DAO_FLAG && targetFileName.endsWith("Dao.java")) {
			filePath = PropertiesUtil.JAVA_ROOT_PATH + "/" + PropertiesUtil.DAO_PACKAGE;
		} else if (PropertiesUtil.XML_FLAG && targetFileName.endsWith("Dao.xml")) {
			filePath = PropertiesUtil.RESOURCES_ROOT_PATH + "/" + PropertiesUtil.XML_PACKAGE;
		} else if (PropertiesUtil.SERVICE_FLAG && targetFileName.endsWith("Service.java")) {
			filePath = PropertiesUtil.JAVA_ROOT_PATH + "/" + PropertiesUtil.SERVICE_PACKAGE;
		} else if (PropertiesUtil.SERVICEIMPL_FLAG && targetFileName.endsWith("ServiceImpl.java")) {
			filePath = PropertiesUtil.JAVA_ROOT_PATH + "/" + PropertiesUtil.SERVICEIMPL_PACKAGE;
		} else if (PropertiesUtil.CONTROLLER_FLAG && targetFileName.endsWith("Controller.java")) {
			filePath = PropertiesUtil.JAVA_ROOT_PATH + "/" + PropertiesUtil.CONTROLLER_PACKAGE;
		} else if (PropertiesUtil.MODEL_FLAG && targetFileName.endsWith(PropertiesUtil.templateModelData.get("className") + ".java")) {
			filePath = PropertiesUtil.JAVA_ROOT_PATH + "/" + PropertiesUtil.MODEL_PACKAGE;
		} else if (PropertiesUtil.JSP_FLAG && targetFileName.endsWith(".jsp")) {
			filePath = PropertiesUtil.JSP_PATH;
		} else if (PropertiesUtil.JAVASCRIPT_FLAG && targetFileName.endsWith(".js")) {
			filePath = PropertiesUtil.JAVASCRIPT_PATH;
		} else if (PropertiesUtil.I18N_FLAG && targetFileName.endsWith(".properties")) {
			filePath = PropertiesUtil.I18N_PATH;
			Boolean flag = false;
			for (File i18nFile : PropertiesUtil.I18N_FILES) {
				String i18nFileName = i18nFile.getName();
				if (i18nFileName.endsWith(targetFileName)) {
					targetFileName = i18nFile.getName();
					flag = true;
					break;
				}
			}
			if (!flag) {
				return null;
			}

		}
		if (!StringUtil.isNullOrEmpty(filePath)) {
			filePath = getTargetFilePath(filePath);
			targetFile = new File(filePath, targetFileName);
			return targetFile;
		}
		return targetFile;
	}

	public String geTargetFileName(String templateRelativePath) {
		String targetFileName = StringUtil.replaceFileName(PropertiesUtil.templateModelData, templateRelativePath);
		targetFileName = StringUtil.replace(targetFileName, ".ftl", ".java");
		return targetFileName;
	}

	public String getTargetFilePath(String path) {
		return StringUtil.replace(path, ".", "/");
	}

	/**
	 * Freemark的根文件夹配置
	 * 
	 * @return
	 */
	public Configuration buildConfiguration() {
		Configuration config = new Configuration();
		try {
			if (IS_FORM_JAR_PACKAGE) {
				config.setTemplateLoader(new ClassTemplateLoader(this.getClass(), TEMPLATE_ROOT_DIR));
			} else {
				config.setDirectoryForTemplateLoading(new File(TEMPLATE_ROOT_DIR).getAbsoluteFile());
			}
			config.setNumberFormat("###############");
			config.setBooleanFormat("true,false");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return config;
	}

}
