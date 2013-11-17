package com.pousheng.generator.database.model;

import com.pousheng.generator.utils.DatabaseDataTypesUtils;
import com.pousheng.generator.utils.StringUtil;

/**
 * 字段列 实体类
 * 
 * @author yongan.quan
 */
public class Column {
	/**
	 * 表名
	 */
	private final Table _table;

	/**
	 * SQl的类型
	 */
	private final int _sqlType;

	/**
	 * JDBC的类型
	 */
	private final String _sqlTypeName;

	/**
	 * 列名
	 */
	private final String _sqlName;

	/**
	 * 是否是主键
	 */
	private boolean _isPk;

	/**
	 * 是否是外键
	 */
	private boolean _isFk;

	/**
	 * 列长度大小
	 */
	private final int _size;

	private final int _decimalDigits;

	/**
	 * 是否允许为空
	 */
	private final boolean _isNullable;

	/**
	 * 是否索引列
	 */
	private final boolean _isIndexed;

	/**
	 * 是否唯一
	 */
	private final boolean _isUnique;

	/**
	 * 默认值
	 */
	private final String _defaultValue;

	/**
	 * 注释
	 */
	private String _comment;
	

	public Column(Table table, int sqlType, String sqlTypeName, String sqlName, int size, int decimalDigits, boolean isPk, boolean isFk, boolean isNullable, boolean isIndexed, boolean isUnique, String defaultValue, String comment) {
		_table = table;
		_sqlType = sqlType;
		_sqlName = sqlName;
		_sqlTypeName = sqlTypeName;
		_size = size;
		_decimalDigits = decimalDigits;
		_isPk = isPk;
		_isFk = isFk;
		_isNullable = isNullable;
		_isIndexed = isIndexed;
		_isUnique = isUnique;
		_defaultValue = defaultValue;
		_comment = comment;
	}

	public int getSqlType() {
		return _sqlType;
	}

	public Table getTable() {
		return _table;
	}

	public int getSize() {
		return _size;
	}

	public int getDecimalDigits() {
		return _decimalDigits;
	}

	public String getSqlTypeName() {
		return _sqlTypeName;
	}

	public String getSqlName() {
		return _sqlName;
	}

	public boolean isPk() {
		return _isPk;
	}

	public boolean isFk() {
		return _isFk;
	}

	public final boolean isNullable() {
		return _isNullable;
	}

	public final boolean isIndexed() {
		return _isIndexed;
	}

	public boolean isUnique() {
		return _isUnique;
	}

	public final String getDefaultValue() {
		return _defaultValue;
	}

	public String getComment() {
		return _comment;
	}

	public int hashCode() {
		return (getTable().getTableName() + "#" + getSqlName()).hashCode();
	}

	public boolean equals(Object o) {
		return this == o;
	}

	public String toString() {
		return getSqlName();
	}

	protected final String prefsPrefix() {
		return "tables/" + getTable().getTableName() + "/columns/" + getSqlName();
	}

	void setFk(boolean flag) {
		_isFk = flag;
	}

	//此处获取Java类的列名如 USER_ID 转换为 UserId 
	public String getColumnName() {
		return StringUtil.makeAllWordFirstLetterUpperCase(getSqlName());
	}
	//此处获取Java类的列名如 USER_ID 转换为 userId 
	public String getColumnNameLower() {
		return StringUtil.uncapitalize(getColumnName());
	}

	public boolean getIsNotIdOrVersionField() {
		return !isPk();
	}

	public String getValidateString() {
		String result = getNoRequiredValidateString();
		if (!isNullable()) {
			result = "required " + result;
		}
		return result;
	}

	public String getNoRequiredValidateString() {
		String result = "";
		if (getSqlName().indexOf("mail") >= 0) {
			result += "validate-email ";
		}
		if (DatabaseDataTypesUtils.isFloatNumber(getSqlType(), getSize(), getDecimalDigits())) {
			result += "validate-number ";
		}
		if (DatabaseDataTypesUtils.isIntegerNumber(getSqlType(), getSize(), getDecimalDigits())) {
			result += "validate-integer ";
		}
		return result;
	}

	public boolean getIsDateTimeColumn() {
		return DatabaseDataTypesUtils.isDate(getSqlType(), getSize(), getDecimalDigits());
	}

	public boolean isHtmlHidden() {
		return isPk() && _table.isSingleId();
	}

	public String getJavaType() {
		return DatabaseDataTypesUtils.getPreferredJavaType(getSqlType(), getSize(), getDecimalDigits());
	}
}
