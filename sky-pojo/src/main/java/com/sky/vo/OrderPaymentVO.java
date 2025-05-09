package com.sky.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

//@Builder
public class OrderPaymentVO implements Serializable {

    private String nonceStr; //随机字符串
    private String paySign; //签名
    private String timeStamp; //时间戳
    private String signType; //签名算法
    private String packageStr; //统一下单接口返回的 prepay_id 参数值

    @Override
    public String toString() {
        return "OrderPaymentVO{" +
                "nonceStr='" + nonceStr + '\'' +
                ", paySign='" + paySign + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", signType='" + signType + '\'' +
                ", packageStr='" + packageStr + '\'' +
                '}';
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getPackageStr() {
        return packageStr;
    }

    public void setPackageStr(String packageStr) {
        this.packageStr = packageStr;
    }

    public OrderPaymentVO() {
    }

    public OrderPaymentVO(String nonceStr, String paySign, String timeStamp, String signType, String packageStr) {
        this.nonceStr = nonceStr;
        this.paySign = paySign;
        this.timeStamp = timeStamp;
        this.signType = signType;
        this.packageStr = packageStr;
    }
}
