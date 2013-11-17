package com.pousheng.generator.template.model;

import java.io.Serializable;

public class TemplateModel implements Serializable {

	private static final long serialVersionUID = 6825627833324412857L;
	private String tableName;// 表名称
	private String className;// 类名称
	private String basePackage; // 父集包名
	private String subPackage; // 子包
	private String daoPackage; // dao
	private String xmlPackage; // mapper文件包
	private String servicePackage;
	private String serviceImplPackage;
	private String modelPackage;
	private String controllerPackage;
	private String jspPackage;
	private String javaScriptPackage;
	private String templateType;

	public TemplateModel() {

	}

	public TemplateModel(String tableName, String className, String basePackage, String subPackage, String daoPackage, String xmlPackage, String servicePackage, String serviceImplPackage, String modelPackage, String controllerPackage, String jspPackage, String javaScriptPackage, String templateType) {
		this.tableName = tableName;
		this.className = className;
		this.basePackage = basePackage;
		this.subPackage = subPackage;
		this.daoPackage = daoPackage;
		this.xmlPackage = xmlPackage;
		this.servicePackage = servicePackage;
		this.serviceImplPackage = serviceImplPackage;
		this.modelPackage = modelPackage;
		this.controllerPackage = controllerPackage;
		this.jspPackage = jspPackage;
		this.javaScriptPackage = javaScriptPackage;
		this.templateType = templateType;
	}

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

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	public String getSubPackage() {
		return subPackage;
	}

	public void setSubPackage(String subPackage) {
		this.subPackage = subPackage;
	}

	public String getDaoPackage() {
		return daoPackage;
	}

	public void setDaoPackage(String daoPackage) {
		this.daoPackage = daoPackage;
	}

	public String getXmlPackage() {
		return xmlPackage;
	}

	public void setXmlPackage(String xmlPackage) {
		this.xmlPackage = xmlPackage;
	}

	public String getServicePackage() {
		return servicePackage;
	}

	public void setServicePackage(String servicePackage) {
		this.servicePackage = servicePackage;
	}

	public String getServiceImplPackage() {
		return serviceImplPackage;
	}

	public void setServiceImplPackage(String serviceImplPackage) {
		this.serviceImplPackage = serviceImplPackage;
	}

	public String getModelPackage() {
		return modelPackage;
	}

	public void setModelPackage(String modelPackage) {
		this.modelPackage = modelPackage;
	}

	public String getControllerPackage() {
		return controllerPackage;
	}

	public void setControllerPackage(String controllerPackage) {
		this.controllerPackage = controllerPackage;
	}

	public String getJspPackage() {
		return jspPackage;
	}

	public void setJspPackage(String jspPackage) {
		this.jspPackage = jspPackage;
	}

	public String getJavaScriptPackage() {
		return javaScriptPackage;
	}

	public void setJavaScriptPackage(String javaScriptPackage) {
		this.javaScriptPackage = javaScriptPackage;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

}
