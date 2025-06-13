package com.meego.demo.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebhookListeningCreateVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 日志ID */
    @JsonProperty("log_id")
    private String logId;

    /** 插件版本 */
    @JsonProperty("source_plugin_version")
    private String sourcePluginVersion;

    /** 当前请求的毫秒级时间戳 */
    @JsonProperty("request_time")
    private Long requestTime;

    /** 请求签名，具体见验签说明 */
    @JsonProperty("signature")
    private String signature;

    /** 区分来源：normal/openapi/system/automation */
    @JsonProperty("source")
    private String source;

    /** 插件ID */
    @JsonProperty("source_plugin_id")
    private String sourcePluginId;

    /** 插件名称 */
    @JsonProperty("source_plugin_name")
    private String sourcePluginName;

    /** 事件具体数据 */
    @JsonProperty("data")
    private Data data;

    // Getter 和 Setter 方法
    public String getLogId() { return logId; }
    public void setLogId(String logId) { this.logId = logId; }
    public String getSourcePluginVersion() { return sourcePluginVersion; }
    public void setSourcePluginVersion(String sourcePluginVersion) { this.sourcePluginVersion = sourcePluginVersion; }
    public Long getRequestTime() { return requestTime; }
    public void setRequestTime(Long requestTime) { this.requestTime = requestTime; }
    public String getSignature() { return signature; }
    public void setSignature(String signature) { this.signature = signature; }
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
    public String getSourcePluginId() { return sourcePluginId; }
    public void setSourcePluginId(String sourcePluginId) { this.sourcePluginId = sourcePluginId; }
    public String getSourcePluginName() { return sourcePluginName; }
    public void setSourcePluginName(String sourcePluginName) { this.sourcePluginName = sourcePluginName; }
    public Data getData() { return data; }
    public void setData(Data data) { this.data = data; }

    /** 事件数据内部类 */
    public static class Data implements Serializable {
        private static final long serialVersionUID = 1L;

        /** 事件类型，枚举值 */
        @JsonProperty("event_type")
        private Integer eventType;

        /** 请求签名，具体见验签说明 */
        @JsonProperty("idempotent_key")
        private String idempotentKey;

        /** 是否为WBS流程 */
        @JsonProperty("is_batch_check")
        private Boolean isBatchCheck;

        /** 空间名称 */
        @JsonProperty("project_name")
        private String projectName;

        /** 空间Key */
        @JsonProperty("project_key")
        private String projectKey;

        /** 租户ID */
        @JsonProperty("tenant_id")
        private String tenantId;

        /** 工作流类型，0代表节点流，1代表状态流 */
        @JsonProperty("flow_type")
        private Integer flowType;

        /** 字段信息，字段与属性解析格式 */
        @JsonProperty("field_info")
        private List<FieldInfo> fieldInfo;

        /** 用户信息 */
        @JsonProperty("user_info")
        private UserInfo userInfo;

        /** 工作项信息 */
        @JsonProperty("work_item_info")
        private List<WorkItemInfo> workItemInfo;

        /** 工作项类型Key */
        @JsonProperty("work_item_type_key")
        private String workItemTypeKey;

        // Getter 和 Setter 方法
        public Integer getEventType() { return eventType; }
        public void setEventType(Integer eventType) { this.eventType = eventType; }
        public String getIdempotentKey() { return idempotentKey; }
        public void setIdempotentKey(String idempotentKey) { this.idempotentKey = idempotentKey; }
        public Boolean getIsBatchCheck() { return isBatchCheck; }
        public void setIsBatchCheck(Boolean isBatchCheck) { this.isBatchCheck = isBatchCheck; }
        public String getProjectName() { return projectName; }
        public void setProjectName(String projectName) { this.projectName = projectName; }
        public String getProjectKey() { return projectKey; }
        public void setProjectKey(String projectKey) { this.projectKey = projectKey; }
        public String getTenantId() { return tenantId; }
        public void setTenantId(String tenantId) { this.tenantId = tenantId; }
        public Integer getFlowType() { return flowType; }
        public void setFlowType(Integer flowType) { this.flowType = flowType; }
        public List<FieldInfo> getFieldInfo() { return fieldInfo; }
        public void setFieldInfo(List<FieldInfo> fieldInfo) { this.fieldInfo = fieldInfo; }
        public UserInfo getUserInfo() { return userInfo; }
        public void setUserInfo(UserInfo userInfo) { this.userInfo = userInfo; }
        public List<WorkItemInfo> getWorkItemInfo() { return workItemInfo; }
        public void setWorkItemInfo(List<WorkItemInfo> workItemInfo) { this.workItemInfo = workItemInfo; }
        public String getWorkItemTypeKey() { return workItemTypeKey; }
        public void setWorkItemTypeKey(String workItemTypeKey) { this.workItemTypeKey = workItemTypeKey; }

        /** 用户信息内部类 */
        public static class UserInfo implements Serializable {
            private static final long serialVersionUID = 1L;

            /** 用户唯一Key */
            @JsonProperty("user_key")
            private String userKey;

            /** 用户中文名 */
            @JsonProperty("name_cn")
            private String nameCn;

            /** 用户英文名 */
            @JsonProperty("name_en")
            private String nameEn;

            /** 用户邮箱 */
            @JsonProperty("email")
            private String email;

            // Getter 和 Setter 方法
            public String getUserKey() { return userKey; }
            public void setUserKey(String userKey) { this.userKey = userKey; }
            public String getNameCn() { return nameCn; }
            public void setNameCn(String nameCn) { this.nameCn = nameCn; }
            public String getNameEn() { return nameEn; }
            public void setNameEn(String nameEn) { this.nameEn = nameEn; }
            public String getEmail() { return email; }
            public void setEmail(String email) { this.email = email; }
        }

        /** 工作项信息内部类 */
        public static class WorkItemInfo implements Serializable {
            private static final long serialVersionUID = 1L;

            /** 工作项ID */
            @JsonProperty("work_item_id")
            private Long workItemId;

            /** 工作项名称 */
            @JsonProperty("work_item_name")
            private String workItemName;

            /** 工作项类型Key */
            @JsonProperty("work_item_type_key")
            private String workItemTypeKey;

            // Getter 和 Setter 方法
            public Long getWorkItemId() { return workItemId; }
            public void setWorkItemId(Long workItemId) { this.workItemId = workItemId; }
            public String getWorkItemName() { return workItemName; }
            public void setWorkItemName(String workItemName) { this.workItemName = workItemName; }
            public String getWorkItemTypeKey() { return workItemTypeKey; }
            public void setWorkItemTypeKey(String workItemTypeKey) { this.workItemTypeKey = workItemTypeKey; }
        }
    }
}