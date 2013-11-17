package com.pousheng.generator.utils;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMarkerUtil {

	/**
	 * 渲染模板字符串。
	 * 
	 * @param templateString
	 *            模板字符串
	 * @param model
	 *            数据源
	 * @return 渲染后的字符串
	 */
	public static String rendereString(String templateString, Map<String, ?> model) {
		try {
			StringWriter result = new StringWriter();
			Template t = new Template("name", new StringReader(templateString), new Configuration());
			t.process(model, result);
			return result.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 渲染Template文件.
	 * 
	 * @param template
	 *            Template文件
	 * @param model
	 * @return 渲染后的Template文件
	 * @since 1.0
	 */
	public static String renderTemplate(Template template, Object model) {
		try {
			StringWriter result = new StringWriter();
			template.process(model, result);
			return result.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
