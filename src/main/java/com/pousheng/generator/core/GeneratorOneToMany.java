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

public class GeneratorOneToMany extends Generator {

	public GeneratorOneToMany() {
		TEMPLATE_ROOT_DIR = "/codetemplate/onetomany";
		IS_FORM_JAR_PACKAGE = true;
	}

	public void generatorOneToMany(TableVO tableVO) {
		Configuration config = buildConfiguration();
		try {
			Table table = DbUtils.getInstance().getTable(tableVO.getTableName());
			table.setClassName(tableVO.getClassName());
			table.setTemplateModel(TemplateModelUtil.getTemplateModel(tableVO));
			List<Table> subTables = new ArrayList<Table>();
			Map<String, Object> subMap = new HashMap<String, Object>();
			for (SubTableVO sub : tableVO.getChildrens()) {
				Table subTable = DbUtils.getInstance().getTable(sub.getTableName());
				subTable.setClassName(sub.getClassName());
				subTable.setForeignKeys(sub.getRelationKeys());
				subTable.setTemplateModel(TemplateModelUtil.getTemplateModel(sub));
				subMap.put(sub.getTableName(), subTable);
				subTables.add(subTable);
			}
			table.setChildrens(subTables);
			List<File> templateFiles = getTemplateFiles(TemplateFiles.TEMLPATE_FILE);
			generateFile(config, table, templateFiles); // 生成主表

			// 生成子表
			templateFiles = getTemplateFiles(TemplateFiles.SUB_TEMLPATE_FILE);
			for (SubTableVO subTableVo : tableVO.getChildrens()) {
				Table t = (Table) subMap.get(subTableVo.getTableName());
				t.setClassName(subTableVo.getClassName());
				t.isSubTableFlag = true;
				t.setTemplateModel(TemplateModelUtil.getTemplateModel(subTableVo));
				generateFile(config, t, templateFiles);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
