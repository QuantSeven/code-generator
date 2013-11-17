package com.pousheng.generator.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pousheng.generator.constant.TemplateFiles;
import com.pousheng.generator.core.model.SubTableVO;
import com.pousheng.generator.core.model.TableVO;
import com.pousheng.generator.database.DbUtils;
import com.pousheng.generator.database.model.Table;
import com.pousheng.generator.utils.FileUtil;
import com.pousheng.generator.utils.FreeMarkerUtil;
import com.pousheng.generator.utils.PropertiesUtil;
import com.pousheng.generator.utils.StringUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class GeneratorOneToMany extends Generator {

	public GeneratorOneToMany() {
		TEMPLATE_ROOT_DIR = "/codetemplate/onetomany";
		IS_FORM_JAR_PACKAGE = true;
	}

	public void generatorOneToMany(TableVO tableVO) {
		Configuration config = buildConfiguration();
		try {
			Table table = DbUtils.getInstance().getTable(tableVO.getTableName());
			List<Table> subTables = new ArrayList<Table>();
			Map<String, Object> subMap = new HashMap<String, Object>();
			for (SubTableVO sub : tableVO.getChildrens()) {
				Table subTable = DbUtils.getInstance().getTable(sub.getTableName());
				subMap.put(sub.getTableName(), subTable);
				subTables.add(subTable);
			}
			table.setClassName(tableVO.getClassName());
			table.setChildrens(subTables);
			PropertiesUtil.initTemplateModelData(table, tableVO);
			List<File> templateFiles = getTemplateFiles();
			generateFile(config, table, templateFiles); // 生成主表

			// 生成子表
			templateFiles = getSubTemplateFiles();
			for (SubTableVO subTableVo : tableVO.getChildrens()) {
				Table t = (Table) subMap.get(subTableVo.getTableName());
				t.setClassName(subTableVo.getClassName());
				PropertiesUtil.initTemplateModelData(t, subTableVo);
				generateFile(config, t, templateFiles);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 生成文件
	private void generateFile(Configuration config, Table table, List<File> templateFiles) {
		try {
			for (File templateFile : templateFiles) {
				String templateRelativePath = templateFile.getName();
				if (templateFile.isDirectory() || templateFile.isHidden())
					continue;
				if (templateRelativePath.equals(""))
					continue;
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

	/**
	 * 获取模板文件
	 * 
	 * @return
	 */
	private List<File> getSubTemplateFiles() {
		List<File> templateFiles = new ArrayList<File>();
		try {
			if (IS_FORM_JAR_PACKAGE) {
				for (String fileName : TemplateFiles.SUB_TEMLPATE_FILE) {
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
