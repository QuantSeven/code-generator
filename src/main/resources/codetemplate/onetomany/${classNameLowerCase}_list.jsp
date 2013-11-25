<#include "common.ftl">  
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript" src="<#noparse>${</#noparse>jsPath<#noparse>}</#noparse>/module/${sub_package_path}/${classNameLowerCase}_list.js">
</script>
<#if (table.childrens?size <= 0)> 
<div id="${classNameLowerCase}ListGrid" data-datagrid="<#if template_type == 'model'>{singleSelect:false}</#if>" url="${classNameLowerCase}/dataGrid" >
	<div class="datagrid-toolbar" id="${classNameLowerCase}-toolbar">
		<div class="btn-group toolButton">
			<a class="btn" id="add" name="add" <#if template_type == 'inner'>href="${classNameLowerCase}/addForm" data-rel='btn'</#if>>
				<i class="icon-plus"></i><spring:message code="common.btn.add"/>
			</a>
			<a class="btn" id="edit" name="edit" <#if template_type == 'inner'>href="${classNameLowerCase}/editForm" data-rel='btn'</#if>>
				<i class="icon-edit"></i><spring:message code="common.btn.edit"/>
			</a> 			
			<a class="btn" id="view" name="view" <#if template_type == 'inner'>href="${classNameLowerCase}/view" data-rel='btn'</#if>>
				<i class="icon-zoom-in"></i><spring:message code="common.btn.view"/>
			</a> 
			<a class="btn" id="delete" name="delete" <#if template_type == 'inner'>href="${classNameLowerCase}/delete" data-rel='btn'</#if>>
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
					<label><spring:message code="${classNameAllLowerCase}.txt.${table.columns[0].columnNameAllLowerCase}" />：</label>
					<input type="text" placeholder='<spring:message code="${classNameAllLowerCase}.txt.${table.columns[0].columnNameAllLowerCase}" />' name="filter_${table.columns[0].columnNameLowerCase}" /></li>
				</li>
				<li >
					<label><spring:message code="${classNameAllLowerCase}.txt.${table.columns[1].columnNameAllLowerCase}" />：</label>
					<input type="text" placeholder='<spring:message code="${classNameAllLowerCase}.txt.${table.columns[1].columnNameAllLowerCase}" />' name="filter_${table.columns[1].columnNameLowerCase}" /> 
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
				<th width="100" class="sort-header" data-code="${column}"><spring:message code="${classNameAllLowerCase}.txt.${column.columnNameAllLowerCase}" /></th> 
				</#list>
			</tr>
		</thead>
		<tbody style="display:none" >
	   		<tr>
           		<td>{{:<#noparse>#</#noparse>index+1}}</td>
           		<td><input type="checkbox" class="datagrid-cell-check" value="{{:${pk.columnNameLowerCase}}}"/></td>
				<#list table.columns as column>
				<td>{{:${column.columnNameAllLowerCase} }}</td>
				</#list>
			</tr>
		</tbody>
	</table>
</div>
<#else>
<div data-layout >
	<div region="north" style="height:75px;">
		<div class="datagrid-toolbar" id="team-toolbar">
			<div class="btn-group toolButton">
				<a class="btn" name="add" id="add" href="#" data-rel='btn' >
					<i class="icon-plus"></i><spring:message code="common.btn.add"/>
				</a>
				<a class="btn" name="edit" id="edit" href="#" data-rel='btn'>
					<i class="icon-edit"></i><spring:message code="common.btn.edit"/>
				</a> 			
				<a class="btn" name="view" id="view" data-rel='btn' href="#">
					<i class="icon-zoom-in"></i><spring:message code="common.btn.view"/>
				</a> 
				<a class="btn" name="delete" id="delete" data-rel='btn' href="#">
					<i class="icon-trash"></i><spring:message code="common.btn.delete"/>
				</a> 
				<a class="btn" name="search" id="search" data-rel='btn'> 
					<i class="icon-search"></i><spring:message code="common.btn.search"/>
				</a>
				<#list table.childrens as child>
				<a class="btn" name="add${child.className }" id="add${child.className }" href="#" data-rel='btn' >
					<i class="icon-plus"></i><spring:message code="${classNameAllLowerCase}.txt.add${child.classNameAllLowerCase}"/>
				</a>
				<a class="btn" name="edit${child.className }" id="edit${child.className }" href="#" data-rel='btn' >
					<i class="icon-edit"></i><spring:message code="${classNameAllLowerCase}.txt.edit${child.classNameAllLowerCase}"/>
				</a>
				<a class="btn" name="delete${child.className }" id="delete${child.className }" data-rel='btn' href="#">
					<i class="icon-trash"></i><spring:message code="${classNameAllLowerCase}.txt.delete${child.classNameAllLowerCase}"/>
				</a> 
			 	</#list>
				
			</div>
		</div>
		<div class="datagrid-search">
		   <form class="form-search" style="width:100%" >
			   	<ul>
			   		<li>
						<label ><spring:message code="team.txt.teamname"/>：</label>
						<input type="text" placeholder='<spring:message code="team.txt.teamname" />' name="filter_teamName" /></li>
					</li>
			   	</ul>
			</form>
		</div>
	</div>
	<div region="center">
		<div id="${classNameLowerCase}ListGrid" data-datagrid="datagrid" url="${classNameLowerCase}/dataGrid">
			<table class="table">
				<thead>
				    <tr>
						<th width="25"><spring:message code="common.txt.seq"/></th>
						<th width="13"><input id="checkbox" type="checkbox" class="datagrid-header-check"/></th>
						<#list table.columns as column>
						<th width="100" class="sort-header" data-code="${column}"><spring:message code="${classNameAllLowerCase}.txt.${column.columnNameAllLowerCase}" /></th> 
						</#list>
					</tr>
				</thead>
				<tbody style="display:none" >
			   		<tr>
		           		<td>{{:<#noparse>#</#noparse>index+1}}</td>
		           		<td><input type="checkbox" class="datagrid-cell-check" value="{{:${pk.columnNameLowerCase}}}"/></td>
						<#list table.columns as column>
						<td>{{:${column.columnNameLowerCase} }}</td>
						</#list>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div region="east" title="  " split="true" style="width:450px;" >
		<div id="${classNameLowerCase}Tab" data-tabs>
			<#list table.childrens as child>
			<div title='<spring:message code="${classNameAllLowerCase}.txt.${child.classNameAllLowerCase}"/>'>
				<div id="${child.classNameLowerCase}ListGrid" data-datagrid="{pagination:false}" url="${classNameLowerCase}/${child.classNameLowerCase}DataGrid">
					<table class="table">
						<thead>
						    <tr>
								<th width="25"><spring:message code="common.txt.seq"/></th>
								<th width="13"><input id="checkbox" type="checkbox" class="datagrid-header-check"/></th>
								<#list child.columns as column>
								<th width="100" class="sort-header" data-code="${column}"><spring:message code="${child.classNameAllLowerCase}.txt.${column.columnNameAllLowerCase}" /></th> 
								</#list>
							</tr>
						</thead>
						<tbody style="display:none" >
					   		<tr>
				           		<td>{{:<#noparse>#</#noparse>index+1}}</td>
				           		<td><input type="checkbox" class="datagrid-cell-check" value="{{:${child.primaryKeyColumns[0].columnNameLowerCase}}}"/></td>
								<#list child.columns as column>
								<td>{{:${column.columnNameLowerCase} }}</td>
								</#list>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			</#list>
		</div>
	</div>
</div>
</#if>