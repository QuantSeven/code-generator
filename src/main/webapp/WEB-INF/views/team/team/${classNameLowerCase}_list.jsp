<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript" src="${jsPath}/module/team/team_list.js">
</script> 
<div id="teamListGrid" data-datagrid="{singleSelect:false}" url="team/dataGrid" >
	<div class="datagrid-toolbar" id="team-toolbar">
		<div class="btn-group toolButton">
			<a class="btn" id="add" name="add" >
				<i class="icon-plus"></i><spring:message code="common.btn.add"/>
			</a>
			<a class="btn" id="edit" name="edit" >
				<i class="icon-edit"></i><spring:message code="common.btn.edit"/>
			</a> 			
			<a class="btn" id="view" name="view" >
				<i class="icon-zoom-in"></i><spring:message code="common.btn.view"/>
			</a> 
			<a class="btn" id="delete" name="delete" >
				<i class="icon-trash"></i><spring:message code="common.btn.delete"/>
			</a> 
			<a class="btn" id="search" name="search" > 
				<i class="icon-search"></i><spring:message code="common.btn.search"/>
			</a>
		</div>
	</div>
	<div class="datagrid-search">
	   <form class="form-search" style="width:100%"  >
		   	<ul>
		   		<li>
					<label><spring:message code="team.txt.teamId" />：</label>
					<input type="text" placeholder='<spring:message code="team.txt.teamId" />' name="filter_teamId" /></li>
				</li>
				<li >
					<label><spring:message code="team.txt.teamName" />：</label>
					<input type="text" placeholder='<spring:message code="team.txt.teamName" />' name="filter_teamName" /> 
		   		</li>
		  
		   	</ul>
		</form>
	</div>
	<table class="table">
		<thead>
		    <tr>
				<th width="25"><spring:message code="common.txt.seq"/></th>
				<th width="13"><input id="checkbox" type="checkbox" class="datagrid-header-check"/></th>
				<th width="100" class="sort-header" data-code="TEAM_ID"><spring:message code="team.txt.teamId" /></th> 
				<th width="100" class="sort-header" data-code="TEAM_NAME"><spring:message code="team.txt.teamName" /></th> 
				<th width="100" class="sort-header" data-code="CREATE_DATE"><spring:message code="team.txt.createDate" /></th> 
			</tr>
		</thead>
		<tbody style="display:none" >
	   		<tr>
           		<td>{{:#index+1}}</td>
           		<td><input type="checkbox" class="datagrid-cell-check" value="{{:teamId}}"/></td>
				<td>{{:teamId }}</td>
				<td>{{:teamName }}</td>
				<td>{{:createDate }}</td>
			</tr>
		</tbody>
	</table>
</div>
