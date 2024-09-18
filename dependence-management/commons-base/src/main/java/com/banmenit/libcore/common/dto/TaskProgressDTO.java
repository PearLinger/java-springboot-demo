package com.banmenit.libcore.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 任务实体类
 *
 * @author lizrd
 * @date 2022/12/8 11:19
 */
@Data
public class TaskProgressDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务开始时间，毫秒级时间戳
     */
    private Long startTime = System.currentTimeMillis();

    /**
     * 任务完成百分比
     */
    private Integer percentage;

    /**
     * 任务状态： 0. 进行中 1. 已完成 2. 全部失败 3. 部分失败
     */
    private Integer status = 0;

    /**
     * 文件存储服务的Host地址
     */
    private String fileHost;

    /**
     * 任务文件下载链接（相对路径，导出任务状态为已完成时可用）
     */
    private String filePath;

    /**
     * 前端传入的任务ID
     */
    private String taskId;

    /**
     * 任务消息
     */
    private String message;
}
