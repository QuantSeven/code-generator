<#include "common.ftl"> 
package ${controller_package};


import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ${model_package}.${className};
import ${service_package}.${className}Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ${base_package}.web.controller.base.AbstractController;
import ${base_package}.web.ui.DataGrid;
import ${base_package}.web.ui.Json;
import ${base_package}.web.ui.PageRequest;

import framework.generic.utils.string.StringUtil;
import framework.generic.utils.webutils.ServletUtil;

@Controller
@RequestMapping("${classNameLower}/*")
public class ${className}Controller extends AbstractController<${className}, ${pk.javaType}> {

	private ${className}Service ${classNameLower}Service;

	@Resource
	public void set${className}Service(${className}Service ${classNameLower}Service) {
		this.${classNameLower}Service = ${classNameLower}Service;
	}

	/*-------------------------------列表显示页面---------------------------------*/
	@Override
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("${sub_package_path}/${classNameLower}_list");
	}

	@Override
	public DataGrid dataGrid(PageRequest pageRequest, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> paramMap = ServletUtil.getParametersMapStartingWith(request, "filter_");
		pageRequest.setParameter(paramMap);
		DataGrid dataGrid = ${classNameLower}Service.getDatagrid(pageRequest);
		return dataGrid;
	}

	/*--------------------------------添加操作-----------------------------------*/
	@Override
	public ModelAndView addFrom(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("action", "${classNameLower}/insert");
		return new ModelAndView("${sub_package_path}/${classNameLower}_edit");
	}

	@Override
	public Json insert(${className} ${classNameLower}, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			int insertRecords = ${classNameLower}Service.create(${classNameLower});
			if (insertRecords <= 0) {
				return error(getMessage("msg.error.add"));
			}
			<#if template_type == 'model'> 
			return success(getMessage("msg.success.add"));
			<#else>
			return success("${classNameLower}/index",getMessage("msg.success.add"));
			</#if>
		} catch (Exception e) {
			e.printStackTrace();
			return error(getMessage("msg.error.add"));
		}
	}

	/*--------------------------------编辑操作-----------------------------------*/
	@Override
	public ModelAndView editForm(${pk.javaType} ${pk.columnNameLower}, HttpServletRequest request, HttpServletResponse response) throws Exception {
		${className} ${classNameLower} = ${classNameLower}Service.get(${pk.columnNameLower});
		request.setAttribute("${classNameLower}", ${classNameLower});
		request.setAttribute("action", "${classNameLower}/update");
		return new ModelAndView("${sub_package_path}/${classNameLower}_edit");
	}

	@Override
	public Json update(${className} ${classNameLower}, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			int updatedRecords = ${classNameLower}Service.modify(${classNameLower});
			if (updatedRecords <= 0) {
				return error(getMessage("msg.error.add"));
			}
			<#if template_type == 'model'> 
			return success(getMessage("msg.success.update"));
			<#else>
			return success("${classNameLower}/index",getMessage("msg.success.update"));
			</#if>
		} catch (Exception e) {
			e.printStackTrace();
			return error(getMessage("msg.error.update"));
		}
	}

	/*--------------------------------删除操作-----------------------------------*/
	<#if template_type == 'model'> 
	@Override
	public Json deleteAll(${pk.javaType}[] ${classNameLower}Ids, HttpServletRequest request, HttpServletResponse response) {
		try {
			int deletedRecords = ${classNameLower}Service.removeAll(${classNameLower}Ids);
			if (deletedRecords <= 0) {
				return error(getMessage("msg.error.delete"));
			} 
			return success(getMessage("msg.success.delete"));
		} catch (Exception e) {
			e.printStackTrace();
			return error(getMessage("msg.error.delete"));
		}
	}
	<#else>
	@Override
	public Json delete(${pk.javaType} ${pk.columnNameLower}, HttpServletRequest request, HttpServletResponse response) {
		try {
			int deletedRecords = ${classNameLower}Service.removeAll(${pk.columnNameLower});
			if (deletedRecords <= 0) {
				return error(getMessage("msg.error.delete"));
			} 
			return success("${classNameLower}/index",getMessage("msg.success.delete"));
		} catch (Exception e) {
			e.printStackTrace();
			return error(getMessage("msg.error.delete"));
		}
	}
	</#if>
	/*--------------------------------查看操作-----------------------------------*/
	@Override
	public ModelAndView view(${pk.javaType} ${pk.columnNameLower}, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("${classNameLower}", ${classNameLower}Service.get(${pk.columnNameLower}));
		<#if template_type == 'inner'> 
		request.setAttribute("hideBtnSave", true);
		</#if>
		return new ModelAndView("${sub_package_path}/${classNameLower}_edit");
	}
	
	/*--------------------------------验证操作-----------------------------------*/
	@Override
	public boolean validatePk(${pk.javaType} ${pk.columnNameLower}, HttpServletRequest request, HttpServletResponse response) {
		${className} ${classNameLower} = ${classNameLower}Service.get(${pk.columnNameLower});
		if (StringUtil.isNullOrEmpty(${classNameLower})) {
			return true;
		}
		return false;
	}
}




