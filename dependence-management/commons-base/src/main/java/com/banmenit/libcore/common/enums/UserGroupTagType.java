package com.banmenit.libcore.common.enums;

public enum UserGroupTagType {
    ORG("ORG", "组织"),
    POS("POS", "岗位"),
    JOB("JOB", "职务"),
    POSITION_LEVEL("POSITION_LEVEL", "职务等级"),

    JOB_BIND_ORG("JOB_BIND_ORG", "职务绑定组织"),
    POS_BIND_JOB("POS_BIND_JOB", "岗位绑定职务"),
    POS_BIND_ORG("POS_BIND_ORG", "岗位绑定组织"),

    ORG_TYPE("ORG_TYPE", "组织类型"),
    ORG_PROJECT_ID("ORG_PROJECT_ID", "项目组织类型属性 项目id"),
    ORG_PARAM("ORG_PARAM_", "组织机构参数tag 前缀"),

    ORG_MANAGER("ORG_MANAGER", "组织负责人tag"),
    ORG_MAIN_MANAGER("ORG_MAIN_MANAGER","主负责人"),

    ;

    UserGroupTagType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    private String key;

    private String value;

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}