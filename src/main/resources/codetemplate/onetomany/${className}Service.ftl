<#assign classNameLower = className?uncap_first>   
<#assign pk = table.primaryKeyColumns[0]>   
package ${service_package};

import ${model_package}.${className};

import ${base_package}.web.ui.DataGrid;
import ${base_package}.web.ui.PageRequest;

public interface ${className}Service {
	
	/**
	 * 获取DataGrid列表数据和总数
	 * @param pageRequest datagrid页面请求数据
	 * @return DataGrid
	 */
	DataGrid getDatagrid(PageRequest pageRequest);
	
	/**
	 * 创建一条数据库记录
	 * @param ${classNameLower}
	 * @return 受影响的行数
	 */
	int create(${className} ${classNameLower});

	/**
	 * 修改一条数据库记录
	 * @param ${classNameLower}
	 * @return 受影响的行数
	 */
	int modify(${className} ${classNameLower});

	/**
	 * 根据主键删除一条数据库记录
	 * @return 受影响的行数
	 */
	int remove(<#list table.primaryKeyColumns as pkColumn>${pkColumn.javaType} ${pkColumn.columnNameLower}<#if pkColumn_has_next>, </#if></#list>);
	
	/**
	 * 批量删除数据库记录
	 * @return 受影响的行数
	 */
	int removeAll(${pk.javaType}... ${classNameLower}Ids);
	
	/**
	 * 根据主键查询一条数据库记录
	 * @return 实体类
	 */
	${className} getByPk(<#list table.primaryKeyColumns as pkColumn>${pkColumn.javaType} ${pkColumn.columnNameLower}<#if pkColumn_has_next>, </#if></#list>);
	
	/**
	 * 查询一条数据库记录
	 * @return 实体类
	 */
	${className} get(${pk.javaType} ${pk.columnNameLower});
	
}
