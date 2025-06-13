package com.meego.demo.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebhookCreateVO implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 请求时间戳（毫秒级） */
	@JsonProperty("request_time")
	private Long requestTime;

	/** 请求签名（具体见验签说明） */
	@JsonProperty("signature")
	private String signature;

	/** 操作来源（normal/openapi/system/automation） */
	@JsonProperty("source")
	private String source;

	/** 当前插件 ID */
	@JsonProperty("source_plugin_id")
	private String sourcePluginId;

	/** 当前插件名称 */
	@JsonProperty("source_plugin_name")
	private String sourcePluginName;

	/** 事件具体数据 */
	@JsonProperty("data")
	private Data data;

	// Getter 和 Setter 方法
	public Long getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Long requestTime) {
		this.requestTime = requestTime;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourcePluginId() {
		return sourcePluginId;
	}

	public void setSourcePluginId(String sourcePluginId) {
		this.sourcePluginId = sourcePluginId;
	}

	public String getSourcePluginName() {
		return sourcePluginName;
	}

	public void setSourcePluginName(String sourcePluginName) {
		this.sourcePluginName = sourcePluginName;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	/** 内部数据类 */
	public static class Data implements Serializable {
		private static final long serialVersionUID = 1L;

		/** 幂等键 */
		@JsonProperty("idempotent_key")
		private String idempotentKey;

		/** 当前空间 ID */
		@JsonProperty("project_key")
		private String projectKey;

		/** 当前工作项类型 */
		@JsonProperty("work_item_type_key")
		private String workItemTypeKey;

		/** 事件类型（枚举值） */
		@JsonProperty("event_type")
		private Integer eventType;

		/** 当前用户信息 */
		@JsonProperty("user_info")
		private UserInfo userInfo;

		/** 当前创建的工作项基础信息 */
		@JsonProperty("work_item_info")
		private List<WorkItemInfo> workItemInfo;

		/** 是否为批量校验（WBS流程） */
		@JsonProperty("is_batch_check")
		private Boolean isBatchCheck;

		/** 表单中插件控件的字段信息 */
		@JsonProperty("field_info")
		private List<FieldInfo> fieldInfo;

		// Getter 和 Setter 方法
		public String getIdempotentKey() {
			return idempotentKey;
		}

		public void setIdempotentKey(String idempotentKey) {
			this.idempotentKey = idempotentKey;
		}

		public String getProjectKey() {
			return projectKey;
		}

		public void setProjectKey(String projectKey) {
			this.projectKey = projectKey;
		}

		public String getWorkItemTypeKey() {
			return workItemTypeKey;
		}

		public void setWorkItemTypeKey(String workItemTypeKey) {
			this.workItemTypeKey = workItemTypeKey;
		}

		public Integer getEventType() {
			return eventType;
		}

		public void setEventType(Integer eventType) {
			this.eventType = eventType;
		}

		public UserInfo getUserInfo() {
			return userInfo;
		}

		public void setUserInfo(UserInfo userInfo) {
			this.userInfo = userInfo;
		}

		public List<WorkItemInfo> getWorkItemInfo() {
			return workItemInfo;
		}

		public void setWorkItemInfo(List<WorkItemInfo> workItemInfo) {
			this.workItemInfo = workItemInfo;
		}

		public Boolean getIsBatchCheck() {
			return isBatchCheck;
		}

		public void setIsBatchCheck(Boolean isBatchCheck) {
			this.isBatchCheck = isBatchCheck;
		}

		public List<FieldInfo> getFieldInfo() {
			return fieldInfo;
		}

		public void setFieldInfo(List<FieldInfo> fieldInfo) {
			this.fieldInfo = fieldInfo;
		}

		/** 用户信息类 */
		public static class UserInfo implements Serializable {
			private static final long serialVersionUID = 1L;

			/** 用户唯一标识 */
			@JsonProperty("user_key")
			private String userKey;

			/** 中文姓名 */
			@JsonProperty("name_cn")
			private String nameCn;

			/** 英文姓名 */
			@JsonProperty("name_en")
			private String nameEn;

			/** 邮箱 */
			@JsonProperty("email")
			private String email;

			// Getter 和 Setter 方法
			public String getUserKey() {
				return userKey;
			}

			public void setUserKey(String userKey) {
				this.userKey = userKey;
			}

			public String getNameCn() {
				return nameCn;
			}

			public void setNameCn(String nameCn) {
				this.nameCn = nameCn;
			}

			public String getNameEn() {
				return nameEn;
			}

			public void setNameEn(String nameEn) {
				this.nameEn = nameEn;
			}

			public String getEmail() {
				return email;
			}

			public void setEmail(String email) {
				this.email = email;
			}
		}

		/** 工作项信息类 */
		public static class WorkItemInfo implements Serializable {
			private static final long serialVersionUID = 1L;

			/** 工作项 ID */
			@JsonProperty("work_item_id")
			private Long workItemId;

			/** 工作项名称 */
			@JsonProperty("work_item_name")
			private String workItemName;

			/** 工作项类型 Key */
			@JsonProperty("work_item_type_key")
			private String workItemTypeKey;

			// Getter 和 Setter 方法
			public Long getWorkItemId() {
				return workItemId;
			}

			public void setWorkItemId(Long workItemId) {
				this.workItemId = workItemId;
			}

			public String getWorkItemName() {
				return workItemName;
			}

			public void setWorkItemName(String workItemName) {
				this.workItemName = workItemName;
			}

			public String getWorkItemTypeKey() {
				return workItemTypeKey;
			}

			public void setWorkItemTypeKey(String workItemTypeKey) {
				this.workItemTypeKey = workItemTypeKey;
			}
		}
	}
}
