package com.banmenit.libcore.common.context;

import com.banmenit.libcore.common.api.UpdateResultAware;
import lombok.Data;

/**
 * 用来存储全局唯一实例！多个线程可共享, 主要用来解耦Spring
 * 比如：全球化相关功能，项目启动时候设置updateResult到此处
 *
 * @author tangsq
 * @date 2023/01/17
 */
@Data
public class GlobalInstanceContext {

    /**
     * 单例
     */
    private static GlobalInstanceContext instance = new GlobalInstanceContext();


    /**
     * 更新结果, 需要在项目启动完成设置进来
     */
    private UpdateResultAware updateResultAware;


    private GlobalInstanceContext() {

    }

    /**
     * 返回单例
     *
     * @return {@link GlobalInstanceContext}
     */
    public static GlobalInstanceContext getInstance() {
        return instance;
    }

    public UpdateResultAware getUpdateResultAware() {
        return updateResultAware;
    }

    public void setUpdateResultAware(UpdateResultAware updateResultAware) {
        this.updateResultAware = updateResultAware;
    }

}
