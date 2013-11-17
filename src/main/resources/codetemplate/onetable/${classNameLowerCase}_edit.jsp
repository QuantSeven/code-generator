<#assign classNameLower = className?uncap_first>   
<#assign pk = table.primaryKeyColumns[0]> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form id="${classNameLower}Form" class="pageForm"  title='<spring:message code="${classNameLower}.txt.title.info"/>' action="<#noparse>${</#noparse>action<#noparse>}</#noparse>" method="post"  modelAttribute="${classNameLower}">
    <div class="page-content">
		<div class="pageFormContent form-area" title='<spring:message code="${classNameLower}.txt.title.info"/>'>
			<ul>
				<#list table.columns as column>
				<#if !column.pk>
				<li>
					<label><spring:message code="${classNameLower}.txt.${column.columnNameLower}"/>:</label>
					<input type="text" name="${column.columnNameLower}" id="${column.columnNameLower}" value="<#noparse>${</#noparse>${classNameLower}.${column.columnNameLower}<#noparse>}</#noparse>" validate="{required:true}" />
				</li>
				<#else>
				<li>
					<label class="red"><spring:message code="${classNameLower}.txt.${column.columnNameLower}"/>:</label>
					<input type="text" name="${column.columnNameLower}" id="${column.columnNameLower}" value="<#noparse>${</#noparse>${classNameLower}.${column.columnNameLower}<#noparse>}</#noparse>"  validate="{required:true<c:if test="<#noparse>${</#noparse>empty ${classNameLower}<#noparse>}</#noparse>"> ,remote:'${classNameLower}/validatePk',messages:{remote:'必须唯一'}</c:if>}" <c:if test="<#noparse>${</#noparse>not empty ${classNameLower}<#noparse>}</#noparse>"> readonly="readonly" class="readonly"</c:if>  />
				</li>
				</#if>
				</#list>
				
			</ul>
		</div>
	</div>
	<#if template_type == 'inner'> 
	<div class="formBar">
		<ul>
			<c:choose>
				<c:when test="<#noparse>${</#noparse>not empty hideBtnSave <#noparse>}</#noparse>">
					<li><a href="${classNameLower}/index" data-rel="ajax" class="btn btn-primary" > <spring:message code="common.btn.cancel"/> </a></li>
				</c:when>
				<c:otherwise>
					<li><button class="btn btn-primary" type="submit"><spring:message code="common.btn.save"/></button></li> 
					<li><a href="${classNameLower}/index" data-rel="ajax" class="btn" > <spring:message code="common.btn.cancel"/> </a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
	</#if>
</form>