package com.banmenit.libcore.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

public class BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一主键id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    /**
     * 创建时间 (时间戳13位)
     */
    private Long creationTime;

    /**
     * 创建人用户id
     */
    private String creatorUserId;

    /**
     * 更新时间 (时间戳13位)
     */
    private Long lastModificationTime;

    /**
     * 更新人用户id
     */
    private String lastModifierUserId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }

    public String getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(String creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    public Long getLastModificationTime() {
        return lastModificationTime;
    }

    public void setLastModificationTime(Long lastModificationTime) {
        this.lastModificationTime = lastModificationTime;
    }

    public String getLastModifierUserId() {
        return lastModifierUserId;
    }

    public void setLastModifierUserId(String lastModifierUserId) {
        this.lastModifierUserId = lastModifierUserId;
    }


}
