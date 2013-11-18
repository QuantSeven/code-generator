package com.pousheng.generator.core;

import java.io.File;
import java.util.List;

import com.pousheng.generator.constant.TemplateFiles;
import com.pousheng.generator.core.model.TableVO;
import com.pousheng.generator.database.DbUtils;
import com.pousheng.generator.database.model.Table;
import com.pousheng.generator.utils.StringUtil;

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
			List<File> templateFiles = getTemplateFiles(TemplateFiles.TEMLPATE_FILE);
			if (!StringUtil.isNullOrEmpty(templateFiles)) {
				generateFile(config, table, templateFiles);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
