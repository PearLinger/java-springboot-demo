package com.banmenit.libcore.common.pulsar;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lizrd
 * @date 2022/12/1 15:25
 */
@Data
public class PayloadData<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 消息唯一Id（建议UUID）（代表业务场景的编码）非必传
     */
    private String msgId;

    /**
     * 事件上报时间（13位时间戳）
     */
    private Long reportTs;

    /**
     * 事件的准确类别
     */
    private ProfileData profile;

    /**
     * 事件信息
     */
    private T events;
}
