package com.banmenit.libcore.web.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

public class BaseModel<T extends Model<?>> extends Model<T> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一主键id
     */
    @TableId("id")
    private Long id;

    /**
     * 创建时间 (时间戳13位)
     */
    @TableField(value = "creation_time", fill = FieldFill.INSERT)
    private Long creationTime;

    /**
     * 创建人用户id
     */
    @TableField(value = "creator_user_id", fill = FieldFill.INSERT)
    private String creatorUserId;

    /**
     * 更新时间 (时间戳13位)
     */
    @TableField(value = "last_modification_time", fill = FieldFill.INSERT_UPDATE)
    private Long lastModificationTime;

    /**
     * 更新人用户id
     */
    @TableField(value = "last_modifier_user_id", fill = FieldFill.INSERT_UPDATE)
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
