<#include "common.ftl">  
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript" src="<#noparse>${</#noparse>jsPath<#noparse>}</#noparse>/module/${sub_package_path}/${classNameLower}_list.js">
</script> 
<div id="${classNameLower}ListGrid" data-datagrid="<#if template_type == 'model'>{singleSelect:false}</#if>" url="${classNameLower}/dataGrid" >
	<div class="datagrid-toolbar" id="${classNameLower}-toolbar">
		<div class="btn-group toolButton">
			<a class="btn" id="add" name="add" <#if template_type == 'inner'>href="${classNameLower}/addForm" data-rel='btn'</#if>>
				<i class="icon-plus"></i><spring:message code="common.btn.add"/>
			</a>
			<a class="btn" id="edit" name="edit" <#if template_type == 'inner'>href="${classNameLower}/editForm" data-rel='btn'</#if>>
				<i class="icon-edit"></i><spring:message code="common.btn.edit"/>
			</a> 			
			<a class="btn" id="view" name="view" <#if template_type == 'inner'>href="${classNameLower}/view" data-rel='btn'</#if>>
				<i class="icon-zoom-in"></i><spring:message code="common.btn.view"/>
			</a> 
			<a class="btn" id="delete" name="delete" <#if template_type == 'inner'>href="${classNameLower}/delete" data-rel='btn'</#if>>
				<i class="icon-trash"></i><spring:message code="common.btn.delete"/>
			</a> 
			<a class="btn" id="search" name="search" <#if template_type == 'inner'>data-rel='btn'</#if>> 
				<i class="icon-search"></i><spring:message code="common.btn.search"/>
			</a>
		</div>
	</div>
	<div class="datagrid-search">
	   <form class="form-search" style="width:100%"  >
		   	<ul>
		   		<li>
					<label><spring:message code="${classNameLower}.txt.${table.columns[0].columnNameLower}" />：</label>
					<input type="text" placeholder='<spring:message code="${classNameLower}.txt.${table.columns[0].columnNameLower}" />' name="filter_${table.columns[0].columnNameLower}" /></li>
				</li>
				<li >
					<label><spring:message code="${classNameLower}.txt.${table.columns[1].columnNameLower}" />：</label>
					<input type="text" placeholder='<spring:message code="${classNameLower}.txt.${table.columns[1].columnNameLower}" />' name="filter_${table.columns[1].columnNameLower}" /> 
		   		</li>
		  
		   	</ul>
		</form>
	</div>
	<table class="table">
		<thead>
		    <tr>
				<th width="25"><spring:message code="common.txt.seq"/></th>
				<th width="13"><input id="checkbox" type="checkbox" class="datagrid-header-check"/></th>
				<#list table.columns as column>
				<th width="100" class="sort-header" data-code="${column}"><spring:message code="${classNameLower}.txt.${column.columnNameLower}" /></th> 
				</#list>
			</tr>
		</thead>
		<tbody style="display:none" >
	   		<tr>
           		<td>{{:<#noparse>#</#noparse>index+1}}</td>
           		<td><input type="checkbox" class="datagrid-cell-check" value="{{:${pk.columnNameLower}}}"/></td>
				<#list table.columns as column>
				<td>{{:${column.columnNameLower} }}</td>
				</#list>
			</tr>
		</tbody>
	</table>
</div>
