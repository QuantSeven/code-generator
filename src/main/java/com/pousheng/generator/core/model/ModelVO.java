package com.pousheng.generator.core.model;

import java.io.Serializable;

public interface ModelVO extends Serializable {

	public String getClassName();

	public String getPackageName();

	public String getTemplateType();

	public String getTableName();

}
