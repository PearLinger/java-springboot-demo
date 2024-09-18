package com.banmenit.libcore.common.pulsar;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lizrd
 * @date 2022/12/1 15:16
 */
@Data
public class ProfileData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 业务模块分类的编码
     */
    private String poiCode;

    /**
     * 业务场景的编码
     */
    private String appType;

    /**
     * 对象模型ID
     */
    private Long modelId;
}
