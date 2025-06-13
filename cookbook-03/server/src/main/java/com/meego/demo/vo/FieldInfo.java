package com.meego.demo.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FieldInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 字段 Key */
	@JsonProperty("field_key")
	private String fieldKey;

	/** 字段对接标识 */
	@JsonProperty("alias")
	private String alias;

	/** 字段类型（JSON 中存在转义字符，建议存储为字符串） */
	@JsonProperty("field_type")
	private String fieldType;

	/** 事件触发前值 */
	@JsonProperty("before_field_value")
	private String beforeFieldValue;

	/** 事件触发后值 */
	@JsonProperty("after_field_value")
	private String afterFieldValue;

	// Getter 和 Setter 方法
	public String getFieldKey() {
		return fieldKey;
	}

	public void setFieldKey(String fieldKey) {
		this.fieldKey = fieldKey;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getBeforeFieldValue() {
		return beforeFieldValue;
	}

	public void setBeforeFieldValue(String beforeFieldValue) {
		this.beforeFieldValue = beforeFieldValue;
	}

	public String getAfterFieldValue() {
		return afterFieldValue;
	}

	public void setAfterFieldValue(String afterFieldValue) {
		this.afterFieldValue = afterFieldValue;
	}
}
