package com.pousheng.generator.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.pousheng.generator.constant.TemplateFiles;
import com.pousheng.generator.core.model.TableVO;
import com.pousheng.generator.database.DbUtils;
import com.pousheng.generator.database.model.Table;
import com.pousheng.generator.utils.FileUtil;
import com.pousheng.generator.utils.FreeMarkerUtil;
import com.pousheng.generator.utils.PropertiesUtil;
import com.pousheng.generator.utils.StringUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class GeneratorOneTable extends Generator {

	public GeneratorOneTable() {
		TEMPLATE_ROOT_DIR = "/codetemplate/onetable";
		IS_FORM_JAR_PACKAGE = true;
	}

	public void generatorOneTable(TableVO tableVO) {
		Configuration config = buildConfiguration();
		try {
			Table table = DbUtils.getInstance().getTable(tableVO.getTableName());
			PropertiesUtil.initTemplateModelData(table, tableVO);//
			List<File> templateFiles = getTemplateFiles();
			for (File templateFile : templateFiles) {
				String templateRelativePath = templateFile.getName();
				if (templateFile.isDirectory() || templateFile.isHidden())
					continue;
				if (templateRelativePath.equals(""))
					continue;
				generateFile(config, table, templateRelativePath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 生成文件
	private void generateFile(Configuration config, Table table, String templateRelativePath) {
		try {
			Template template = config.getTemplate(templateRelativePath);
			File targetFile = getTargetFile(templateRelativePath);
			String result = FreeMarkerUtil.renderTemplate(template, PropertiesUtil.templateModelData);
			if (!StringUtil.isNullOrEmpty(targetFile)) {
				if (templateRelativePath.endsWith(".properties")) {
					FileUtil.writeStringToFile(targetFile, result, true);
				} else {
					FileUtil.writeStringToFile(targetFile, result, false);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取模板文件
	 * 
	 * @return
	 */
	private List<File> getTemplateFiles() {
		List<File> templateFiles = new ArrayList<File>();
		try {
			if (IS_FORM_JAR_PACKAGE) {
				for (String fileName : TemplateFiles.TEMLPATE_FILE) {
					templateFiles.add(new File(fileName));
				}
			} else {
				FileUtil.listFiles(new File(TEMPLATE_ROOT_DIR).getAbsoluteFile(), templateFiles);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return templateFiles;
	}
}
