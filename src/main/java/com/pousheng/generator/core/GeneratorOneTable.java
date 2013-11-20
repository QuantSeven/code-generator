package com.pousheng.generator.core;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pousheng.generator.constant.TemplateFiles;
import com.pousheng.generator.core.model.SubTableVO;
import com.pousheng.generator.core.model.TableVO;
import com.pousheng.generator.database.DbUtils;
import com.pousheng.generator.database.model.Table;
import com.pousheng.generator.utils.TemplateModelUtil;

import freemarker.template.Configuration;

public class GeneratorOneTable extends Generator {

	public GeneratorOneTable() {
		TEMPLATE_ROOT_DIR = "/codetemplate/onetable";
		IS_FORM_JAR_PACKAGE = true;
	}

	public void generatorOneTable(TableVO tableVO) {
		Configuration config = buildConfiguration();
		try {
			Table table = DbUtils.getInstance().getTable(tableVO.getTableName());
			table.setClassName(tableVO.getClassName());
			table.setTemplateModel(TemplateModelUtil.getTemplateModel(tableVO));
			List<File> templateFiles = getTemplateFiles(TemplateFiles.TEMLPATE_FILE);
			generateFile(config, table, templateFiles); // 生成主表
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
