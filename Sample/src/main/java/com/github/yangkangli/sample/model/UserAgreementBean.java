package com.github.yangkangli.sample.model;

/**
 * 用户服务协议&隐私政策
 */
public class UserAgreementBean {

    /**
     * 对话框标题文本
     */
    private String title;

    /**
     * 对话框内容文本
     */
    private String content;

    /**
     * 用户服务协议关键字
     */
    private String servicesAgreementText;

    /**
     * 隐私政策关键字
     */
    private String privacyPolicyText;

    /**
     * 服务协议访问地址
     */
    private String servicesAgreementUrl;

    /**
     * 隐私政策访问地址
     */
    private String privacyPolicyUrl;

    /**
     * 对话框不同意按钮文本
     */
    private String negativeText;

    /**
     * 对话框同意按钮文本
     */
    private String positiveText;

    /**
     * 标记是否读取的本地数据
     */
    private boolean fromLocal;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getServicesAgreementText() {
        return servicesAgreementText;
    }

    public void setServicesAgreementText(String servicesAgreementText) {
        this.servicesAgreementText = servicesAgreementText;
    }

    public String getPrivacyPolicyText() {
        return privacyPolicyText;
    }

    public void setPrivacyPolicyText(String privacyPolicyText) {
        this.privacyPolicyText = privacyPolicyText;
    }

    public String getServicesAgreementUrl() {
        return servicesAgreementUrl;
    }

    public void setServicesAgreementUrl(String servicesAgreementUrl) {
        this.servicesAgreementUrl = servicesAgreementUrl;
    }

    public String getPrivacyPolicyUrl() {
        return privacyPolicyUrl;
    }

    public void setPrivacyPolicyUrl(String privacyPolicyUrl) {
        this.privacyPolicyUrl = privacyPolicyUrl;
    }

    public String getNegativeText() {
        return negativeText;
    }

    public void setNegativeText(String negativeText) {
        this.negativeText = negativeText;
    }

    public String getPositiveText() {
        return positiveText;
    }

    public void setPositiveText(String positiveText) {
        this.positiveText = positiveText;
    }

    public boolean isFromLocal() {
        return fromLocal;
    }

    public void setFromLocal(boolean fromLocal) {
        this.fromLocal = fromLocal;
    }
}
