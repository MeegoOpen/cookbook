package com.meego.demo.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebhookInterceptVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 事件具体数据 */
    @JsonProperty("data")
    private Data data;

    /** 当前请求的秒级时间戳 */
    @JsonProperty("request_time")
    private Long requestTime;

    /** 请求签名（具体见验签说明） */
    @JsonProperty("signature")
    private String signature;

    /** 操作来源（normal/open_api） */
    @JsonProperty("source")
    private String source;

    /** 来源插件ID（仅当"source":"open_api"时有值） */
    @JsonProperty("source_plugin_id")
    private String sourcePluginId;

    /** 来源插件名称（仅当"source":"open_api"时有值） */
    @JsonProperty("source_plugin_name")
    private String sourcePluginName;

    // Getter 和 Setter 方法
    public Data getData() { return data; }
    public void setData(Data data) { this.data = data; }
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

    /** 内部数据类 */
    public static class Data implements Serializable {
        private static final long serialVersionUID = 1L;

        /** 事件类型（枚举值见事件列表） */
        @JsonProperty("event_type")
        private Integer eventType;

        /** 幂等key（对于同一个事件的多次操作，幂等key相同） */
        @JsonProperty("idempotent_key")
        private String idempotentKey;

        /** 是否为WBS批量校验 */
        @JsonProperty("is_batch_check")
        private Boolean isBatchCheck;

        /** 空间key */
        @JsonProperty("project_key")
        private String projectKey;

        /** 字段信息（可参考字段与属性解析格式） */
        @JsonProperty("field_info")
        private List<FieldInfo> fieldInfo;

        /** 当前用户信息 */
        @JsonProperty("user_info")
        private UserInfo userInfo;

        /** 当前工作项基础信息 */
        @JsonProperty("work_item_info")
        private List<WorkItemInfo> workItemInfo;

        /** 工作项类型key */
        @JsonProperty("work_item_type_key")
        private String workItemTypeKey;

        // Getter 和 Setter 方法
        public Integer getEventType() { return eventType; }
        public void setEventType(Integer eventType) { this.eventType = eventType; }
        public String getIdempotentKey() { return idempotentKey; }
        public void setIdempotentKey(String idempotentKey) { this.idempotentKey = idempotentKey; }
        public Boolean getIsBatchCheck() { return isBatchCheck; }
        public void setIsBatchCheck(Boolean isBatchCheck) { this.isBatchCheck = isBatchCheck; }
        public String getProjectKey() { return projectKey; }
        public void setProjectKey(String projectKey) { this.projectKey = projectKey; }
        public List<FieldInfo> getFieldInfo() { return fieldInfo; }
        public void setFieldInfo(List<FieldInfo> fieldInfo) { this.fieldInfo = fieldInfo; }
        public UserInfo getUserInfo() { return userInfo; }
        public void setUserInfo(UserInfo userInfo) { this.userInfo = userInfo; }
        public List<WorkItemInfo> getWorkItemInfo() { return workItemInfo; }
        public void setWorkItemInfo(List<WorkItemInfo> workItemInfo) { this.workItemInfo = workItemInfo; }
        public String getWorkItemTypeKey() { return workItemTypeKey; }
        public void setWorkItemTypeKey(String workItemTypeKey) { this.workItemTypeKey = workItemTypeKey; }

        /** 用户信息类 */
        public static class UserInfo implements Serializable {
            private static final long serialVersionUID = 1L;

            /** 用户邮箱 */
            @JsonProperty("email")
            private String email;

            /** 用户中文名 */
            @JsonProperty("name_cn")
            private String nameCn;

            /** 用户英文名（optional属性，不保证有值） */
            @JsonProperty("name_en")
            private String nameEn;

            /** 用户唯一key */
            @JsonProperty("user_key")
            private String userKey;

            // Getter 和 Setter 方法
            public String getEmail() { return email; }
            public void setEmail(String email) { this.email = email; }
            public String getNameCn() { return nameCn; }
            public void setNameCn(String nameCn) { this.nameCn = nameCn; }
            public String getNameEn() { return nameEn; }
            public void setNameEn(String nameEn) { this.nameEn = nameEn; }
            public String getUserKey() { return userKey; }
            public void setUserKey(String userKey) { this.userKey = userKey; }
        }

        /** 工作项信息类 */
        public static class WorkItemInfo implements Serializable {
            private static final long serialVersionUID = 1L;

            /** 工作项ID（新建为0） */
            @JsonProperty("work_item_id")
            private Long workItemId;

            /** 工作项名称 */
            @JsonProperty("work_item_name")
            private String workItemName;

            /** 工作项类型key */
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