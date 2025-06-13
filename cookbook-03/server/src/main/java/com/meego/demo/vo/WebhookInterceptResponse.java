package com.meego.demo.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebhookInterceptResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 是否需要拦截 */
    private boolean needIntercept;

    /** 多语言消息 */
    private Msg msg;

    /** 自定义数据 */
    @JsonProperty("custom_data")
    private String customData;

    /** 拦截选项 */
    @JsonProperty("intercept_options")
    private InterceptOptions interceptOptions;

    // Getter 和 Setter 方法
    public boolean isNeedIntercept() { return needIntercept; }
    public void setNeedIntercept(boolean needIntercept) { this.needIntercept = needIntercept; }
    public Msg getMsg() { return msg; }
    public void setMsg(Msg msg) { this.msg = msg; }
    public String getCustomData() { return customData; }
    public void setCustomData(String customData) { this.customData = customData; }
    public InterceptOptions getInterceptOptions() { return interceptOptions; }
    public void setInterceptOptions(InterceptOptions interceptOptions) { this.interceptOptions = interceptOptions; }

    /** 多语言消息内部类 */
    public static class Msg implements Serializable {
        private static final long serialVersionUID = 1L;
        private String zh;
        private String en;
        private String ja;

        // Getter 和 Setter 方法
        public String getZh() { return zh; }
        public void setZh(String zh) { this.zh = zh; }
        public String getEn() { return en; }
        public void setEn(String en) { this.en = en; }
        public String getJa() { return ja; }
        public void setJa(String ja) { this.ja = ja; }
    }

    /** 拦截选项内部类 */
    public static class InterceptOptions implements Serializable {
        private static final long serialVersionUID = 1L;
        /** 是否禁用显示插件 */
        @JsonProperty("disable_show_plugin")
        private boolean disableShowPlugin;

        // Getter 和 Setter 方法
        public boolean isDisableShowPlugin() { return disableShowPlugin; }
        public void setDisableShowPlugin(boolean disableShowPlugin) { this.disableShowPlugin = disableShowPlugin; }
    }
}