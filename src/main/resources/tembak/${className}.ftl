<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${model_package};

import framework.generic.annotation.Column;
import framework.generic.annotation.Table;
import framework.generic.model.PersistentModel;

<#if table.tableRemark!="">
/**
 * ${table.tableRemark}
 */
</#if>
@Table(name = "${table.sqlName}")
public class ${className} implements PersistentModel {
	<@generateFields/>
}
<#macro generateFields>
	
	<#list table.columns as column>
	<#if column.pk>
	<#if column.comment!="">
	/**
	 * ${column.comment}
	 */
	</#if>
	@Column(name = "${column}", pk = true, order = ${column_index})
	private ${column.javaType} ${column.columnNameLower};
	<#else>
	<#if column.comment!="">
	/**
	 * ${column.comment}
	 */
	</#if>
	@Column(name = "${column}")
	private ${column.javaType} ${column.columnNameLower};
	</#if>
	</#list>
	
	<#list table.columns as column>
	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	
	public void set${column.columnName}(${column.javaType} ${column.columnNameLower}) {
		this.${column.columnNameLower} = ${column.columnNameLower};
	}
	
	</#list>
</#macro>