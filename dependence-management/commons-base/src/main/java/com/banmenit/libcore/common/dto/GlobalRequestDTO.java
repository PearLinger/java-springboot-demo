package com.banmenit.libcore.common.dto;

import cn.hutool.core.util.StrUtil;
import com.banmenit.libcore.common.api.ResultCode;
import com.banmenit.libcore.common.exception.BizException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 全局请求参数 param
 *
 * @author tangsq
 */
@Builder
@Data
@AllArgsConstructor
@Slf4j
public class GlobalRequestDTO implements Cloneable {
    /**
     * 租户 来自jwt, 一般不为空
     */
    private String tenant;

    /**
     * 当前人（用户id、appid）、来自jwt, 一般不为空, 全局唯一
     */
    private String sub;

    /**
     * 用户名
     */
    private String name;

    /**
     * 是否服务, 来自jwt, 一般不为空
     */
    private Boolean server;

    /**
     * 区分用户类型，app那边要知道是员工，还是客户, 来自jwt
     */
    private Integer flag;


    /**
     * 实例Id, 来自url参数, 可空
     */
    private String instance;

    /**
     * 项目, 来自url, 可空
     */
    private String project;

    /**
     * 分布式链路追踪的traceid, 可以为null, 来自请求头 TraceId
     */
    private String traceId;

    /**
     * 时区, 可以为null, 来自请求头Timezone
     */
    private String timezone;

    /**
     * 请求ip, 可以为null, 来自请求头X-Real-IP
     */
    private String ip;

    /**
     * 存储jwt
     */
    private String jwt;


    public GlobalRequestDTO() {

    }

    /**
     * 非空获取,保证必须有
     *
     * @return {@link String}
     */
    public String getTenant() {
        if (StrUtil.isEmpty(tenant)) {
            throw BizException.buildException(ResultCode.VALIDATE_FAILED);
        }
        return tenant;
    }

    public String getSub() {
        if (StrUtil.isEmpty(sub)) {
            throw BizException.buildException(ResultCode.VALIDATE_FAILED);
        }
        return sub;
    }

    public String getName() {
        if (StrUtil.isEmpty(name)) {
            throw BizException.buildException(ResultCode.VALIDATE_FAILED);
        }
        return name;
    }

    public Boolean getServer() {
        if (server == null) {
            throw BizException.buildException(ResultCode.VALIDATE_FAILED);
        }
        return server;
    }

    public String getInstance() {
        if (StrUtil.isEmpty(instance)) {
            throw BizException.buildException(ResultCode.VALIDATE_FAILED);
        }
        return instance;
    }

    public String getProject() {
        if (StrUtil.isEmpty(project)) {
            throw BizException.buildException(ResultCode.VALIDATE_FAILED);
        }
        return project;
    }

    public String getTraceId() {
        if (StrUtil.isEmpty(traceId)) {
            throw BizException.buildException(ResultCode.VALIDATE_FAILED);
        }
        return traceId;
    }

    public String getTimezone() {
        if (StrUtil.isEmpty(timezone)) {
            throw BizException.buildException(ResultCode.VALIDATE_FAILED);
        }
        return timezone;
    }


    public String getJwt() {
        if (StrUtil.isEmpty(jwt)) {
            throw BizException.buildException(ResultCode.VALIDATE_FAILED);
        }
        return jwt;
    }

    public Integer getFlag() {
        if (flag == null) {
            throw BizException.buildException(ResultCode.VALIDATE_FAILED);
        }
        return flag;
    }

    public String getIp() {
        if (StrUtil.isEmpty(ip)) {
            throw BizException.buildException(ResultCode.VALIDATE_FAILED);
        }
        return ip;
    }

    /**
     * 可以为空获取
     *
     * @return {@link String}
     */
    public String getTenantCanNull() {
        return tenant;
    }

    public String getSubCanNull() {
        return sub;
    }

    public String getNameCanNull() {
        return name;
    }

    public Boolean getServerCanNull() {
        return server;
    }

    public String getInstanceCanNull() {
        return instance;
    }

    public String getProjectCanNull() {
        return project;
    }

    public String getTraceIdCanNull() {
        return traceId;
    }

    public String getTimezoneCanNull() {
        return timezone;
    }

    public String getJwtCanNull() {
        return jwt;
    }

    public Integer getFlagCanNull() {
        return flag;
    }

    public String getIpCanNull() {
        return ip;
    }

    @Override
    public String toString() {
        return "GlobalRequestDTO{" +
                "tenant='" + tenant + '\'' +
                ", sub='" + sub + '\'' +
                ", name='" + name + '\'' +
                ", server=" + server +
                ", flag=" + flag +
                ", instance='" + instance + '\'' +
                ", project='" + project + '\'' +
                ", traceId='" + traceId + '\'' +
                ", timezone='" + timezone + '\'' +
                ", ip='" + ip + '\'' +
                ", jwt='" + jwt + '\'' +
                '}';
    }

    /**
     * GlobalRequestDTO 拷贝
     *
     * @return
     */
    @Override
    public GlobalRequestDTO clone() {
        try {
            // 没有用二进制码流拷贝是因为二进制码流耗时10毫秒效率比较差
            // 字段中如果存在对象，则需要补充拷贝逻辑
            // 例如，如果有一个 List 类型的字段，需要创建一个新的list字段并将原始列表的元素逐个复制到其中
            return (GlobalRequestDTO) super.clone();
        } catch (CloneNotSupportedException e) {
            log.error("拷贝对象失败", e);
            throw BizException.buildException("CLONE_NOT_SUPPORTED_EXCEPTION", "拷贝对象失败");
        }
    }

}
