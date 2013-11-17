<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form id="teamForm" class="pageForm"  title='<spring:message code="team.txt.title.info"/>' action="${action}" method="post"  modelAttribute="team">
    <div class="page-content">
		<div class="pageFormContent form-area" title='<spring:message code="team.txt.title.info"/>'>
			<ul>
				<li>
					<label class="red"><spring:message code="team.txt.teamId"/>:</label>
					<input type="text" name="teamId" id="teamId" value="${team.teamId}"  validate="{required:true<c:if test="${empty team}"> ,remote:'team/validatePk',messages:{remote:'必须唯一'}</c:if>}" <c:if test="${not empty team}"> readonly="readonly" class="readonly"</c:if>  />
				</li>
				<li>
					<label><spring:message code="team.txt.teamName"/>:</label>
					<input type="text" name="teamName" id="teamName" value="${team.teamName}" validate="{required:true}" />
				</li>
				<li>
					<label><spring:message code="team.txt.createDate"/>:</label>
					<input type="text" name="createDate" id="createDate" value="${team.createDate}" validate="{required:true}" />
				</li>
				
			</ul>
		</div>
	</div>
</form>