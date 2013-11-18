<#include "common.ftl"> 
package ${serviceimpl_package};

import javax.annotation.Resource;

import ${dao_package}.${className}Dao;
import ${model_package}.${className};
import ${service_package}.${className}Service;
import org.springframework.stereotype.Service;

import ${base_package}.web.ui.DataGrid;
import ${base_package}.web.ui.PageRequest;

import framework.generic.paginator.domain.PageList;

@Service("${classNameLower}Service")
public class ${className}ServiceImpl implements ${className}Service {

	private ${className}Dao ${classNameLower}Dao;
	
	@Resource
	public void set${className}Dao(${className}Dao ${classNameLower}Dao) {
		this.${classNameLower}Dao = ${classNameLower}Dao;
	}
	
	/*
	 * (non-Javadoc)
	 * @see ${service_package}.${className}Service#getDatagrid(${base_package}.web.ui.PageRequest)
	 */
	@Override
	public DataGrid getDatagrid(PageRequest pageRequest) {
		PageList<${className}> ${classNameLower}s = ${classNameLower}Dao.findByPage(pageRequest.getParameter(), pageRequest.getPageBounds());
		return new DataGrid(${classNameLower}s.getPaginator().getTotalCount(), ${classNameLower}s);
	}
	
	/*
	 * (non-Javadoc)
	 * @see ${service_package}.${className}Service#create(${model_package}.${className})
	 */
	@Override
	public int create(${className} ${classNameLower}) {
		return ${classNameLower}Dao.insert(${classNameLower});
	}
	
	/*
	 * (non-Javadoc)
	 * @see ${service_package}.${className}Service#modify(${model_package}.${className})
	 */
	@Override
	public int modify(${className} ${classNameLower}) {
		return ${classNameLower}Dao.update(${classNameLower});
	}
	
	/*
	 * (non-Javadoc)
	 * @see ${service_package}.${className}Service#remove(<#list table.primaryKeyColumns as pkColumn>${pkColumn.javaType}<#if pkColumn_has_next>, </#if></#list>)
	 */
	@Override
	public int remove(<#list table.primaryKeyColumns as pkColumn>${pkColumn.javaType} ${pkColumn.columnNameLower}<#if pkColumn_has_next>, </#if></#list>) {
		return ${classNameLower}Dao.deleteByPk(<#list table.primaryKeyColumns as pkColumn>${pkColumn.columnNameLower}<#if pkColumn_has_next>, </#if></#list>);
	}
	
	/*
	 * (non-Javadoc)
	 * @see ${service_package}.${className}Service#removeAll(${pk.javaType}[])
	 */
	@Override
	public int removeAll(${pk.javaType}... ${classNameLower}Ids) {
		return ${classNameLower}Dao.delete(${classNameLower}Ids);
	}

	/*
	 * (non-Javadoc)
	 * @see ${service_package}.${className}Service#getByPk(<#list table.primaryKeyColumns as pkColumn>${pkColumn.javaType}<#if pkColumn_has_next>, </#if></#list>)
	 */
	@Override
	public ${className} getByPk(<#list table.primaryKeyColumns as pkColumn>${pkColumn.javaType} ${pkColumn.columnNameLower}<#if pkColumn_has_next>, </#if></#list>) {
		return ${classNameLower}Dao.findByPk(<#list table.primaryKeyColumns as pkColumn>${pkColumn.columnNameLower}<#if pkColumn_has_next>, </#if></#list>);
	}
	
	/*
	 * (non-Javadoc)
	 * @see ${service_package}.${className}Service#get(${pk.javaType})
	 */
	@Override
	public ${className} get(${pk.javaType} ${pk.columnNameLower}) {
		return ${classNameLower}Dao.find(${pk.columnNameLower});
	}
}
