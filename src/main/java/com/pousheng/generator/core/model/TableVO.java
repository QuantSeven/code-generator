package com.pousheng.generator.core.model;

import java.util.ArrayList;
import java.util.List;

public class TableVO implements ModelVO  {

	private static final long serialVersionUID = -4631397551699720526L;
	private String tableName;
	private String className;
	private String packageName;
	private String templateType;

	private List<SubTableVO> childrens = new ArrayList<SubTableVO>(0);

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public List<SubTableVO> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<SubTableVO> childrens) {
		this.childrens = childrens;
	}
	
	public void addSubTable(SubTableVO subTableVo) {
		this.childrens.add(subTableVo);
	}

}
