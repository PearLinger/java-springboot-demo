package com.banmenit.libcore.common.enums;

/**
 * 应用类型枚举
 *
 * @author guohao
 * @date
 */
@Deprecated
public enum ApplicationEnum {

    ALARM("alarm", "告警中心"),
    APP_ENGINE("AppEngine", "APP引擎"),
    APPLICATION_MANAGEMENT("applicationManagement", "应用管理"),
    BEMS("bems", "能效管理"),
    BUILDING_APP("BuildingApp", "APP楼控"),
    BUSINESS_MODE("businessmode", "业务建模"),
    CUSTOM_DATA_SOURCE("customDataSource", "数据源"),
    DATA_MANAGE("dataManage", "数据归档"),
    DEVICE_MANAGEMENT("deviceManagement", "设备中心"),
    DICTIONARY_MANAGE("dictionaryManage", "物模型管理"),
    DU_TY_MANAGE("dutyManage", "值班管理"),
    ENVIRONMENT_SPACE("EnvironmentSpace", "环境空间"),
    EQUIPMENT_MANAGEMENT("EquipmentManagement", "设备管理"),
    INTELLIGENT_COLD_SOURCE("IntelligentColdSource", "智慧冷源"),
    INTELLIGENT_SAFETY("IntelligentSafety", "智慧安全"),
    IOC("Ioc", "IOC可视化"),
    IOC_VISUAL("iocvisual", "图形引擎"),
    IOT("iot", "设备接入"),
    IPP("ipp", "通行管理"),
    ISM("ism", "智慧会议"),
    ISS("iss", "综合安防"),
    IWM("iwm", "智慧工位"),
    KNOWLEDGE_BASE("knowledgeBase", "知识中心"),
    LOG("log", "日志管理"),
    MAIN_DATA("maindata", "统一业务服务"),
    MEETING("Meeting", "智慧会议"),
    MESSAGE_CENTER("messagecenter", "消息中心"),
    MESSAGE_MANGER("MessageManger", "消息管理"),
    ORCHESTRATION_ENGINE("orchestrationengine", "编排引擎"),
    PERSON_CENTER("personCenter", "个人中心"),
    PLATFORM_MANAGE("platformManage", "基础资料"),
    PORTAL_MANAGE("portalManage", "门户管理"),
    PROCESS_ENGINE("processEngine", "流程引擎"),
    RIGHT_MANAGEMENT("rightManagement", "权限中心"),
    SERVICE_HALL("ServiceHall", "服务大厅"),
    SERVICE_PROVIDER("ServiceProvider", "商家信息"),
    SMART_WORK("smartwork", "智慧运行"),
    STRATEGY("strategy", "场景中心"),
    SUPPLY_CHAIN_MANAGE("supplychainManage", "供应链管理"),
    SYSTEM_CONFIG("systemconfig", "系统配置"),
    SYSTEM_MANAGE("systemManage", "系统管理"),
    LIGHT_ANALYSIS("lightAnalysis", "轻分析"),
    FORM_CENTER("formCenter", "报表中心"),
    DATA_ANALYSE("dataAnalyse", "数据分析"),
    REPORT_CENTER("ReportCenter", "报表中心"),
    SPATIAL_INTELLIGENCE("spatialIntelligence", "空间场景"),
    CONTENT_MANAGE("cms","内容运营")
    ;


    private String code;
    private String name;

    ApplicationEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    /**
     * 根据应用编码获取应用名称
     *
     * @param code 应用编码
     * @return 应用名称
     */
    public static String getNameByCode(String code) {
        for (ApplicationEnum applicationEnum : ApplicationEnum.values()) {
            if (applicationEnum.getCode().equals(code)) {
                return applicationEnum.getName();
            }
        }
        return "UNKNOWN_CODE";
    }


    /**
     * 根据应用名称获取应用编码
     *
     * @param name 应用名称
     * @return 应用编码
     */
    public static String getCodeByName(String name) {
        for (ApplicationEnum applicationEnum : ApplicationEnum.values()) {
            if (applicationEnum.getName().equals(name)) {
                return applicationEnum.getCode();
            }
        }
        return "UNKNOWN_NAME";
    }

    /**
     * 根据应用名称获取应用编码（模糊匹配）
     *
     * @param name 应用名称
     * @return 应用编码
     */
    public static String searchCodeByName(String name) {
        for (ApplicationEnum applicationEnum : ApplicationEnum.values()) {
            if (applicationEnum.getName().contains(name)) {
                return applicationEnum.getCode();
            }
        }
        return "NOT_FOUND";
    }
}
