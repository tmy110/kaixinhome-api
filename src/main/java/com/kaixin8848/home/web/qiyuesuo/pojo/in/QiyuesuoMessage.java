package com.kaixin8848.home.web.qiyuesuo.pojo.in;

import lombok.Data;

@Data
public class QiyuesuoMessage {
    /**
     *  当前文件的ID
     */
    private String contractId;
    /**
     * 当前回调通知的类型
     *
     *      通知类型	说明
     *      SEND_SIGNING	发送签署
     *      SEND_FILLING	发送填参
     *      FILLED	完成参数
     *      CONFIG_FLOW	配置流程
     *      PERSONAL	个人签字
     *      OPERATOR	经办人签名
     *      LEGAL_PERSON	法定代表人签字
     *      SEAL	企业签章
     *      AUDIT_SIGN	审批并签署
     *      AUDIT	文件审批
     *      RECALLED	撤回文件
     *      REJECTED	退回文件
     *      EXPIRED	文件过期
     *      COMPLETE	文件完成
     *      SEND_INVALID	发送作废
     *      INVALIDING	签署作废
     *      INVALIDED	作废完成
     *      INVALID_REJECTED	拒绝作废
     *      FAILED	签署失败
     *      UNKNOWN	未知
     */
    private String callbackType;
    /**
     * 文件当前的状态
     *
     * 文件状态	描述
     * DRAFT	草稿
     * RECALLED	已撤回
     * SIGNING	签署中
     * REJECTED	已退回
     * COMPLETE	已完成
     * EXPIRED	已过期
     * FILLING	拟定中
     * FAILED	签署失败
     * INVALIDING	作废中
     * INVALIDED	已作废
     */
    private String contractStatus;
}
