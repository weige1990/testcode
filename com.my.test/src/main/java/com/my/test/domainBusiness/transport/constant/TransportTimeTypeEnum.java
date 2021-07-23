package com.my.test.domainBusiness.transport.constant;

import lombok.Getter;

/**
 * 运单查询的时间类型枚举
 *
 *
 * @since 1.0.0 2020-12-31
 */

@Getter
public enum TransportTimeTypeEnum {
    PACKAGE_TIME_TYPE(1, "出库时间"),
    DISTRIBUTE_TIME_TYPE(2, "派单时间"),
    DISPATCH_TIME_TYPE(3, "出库时间");;

    TransportTimeTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;

    }

    /**
     * 代号
     */
    private Integer code;
    /**
     * 运单时间类型说明
     */
    private String name;

    public static TransportTimeTypeEnum getType(Integer code) {
        if (code==null)
        {
            return null;
        }
        switch (code) {
            case 1:return PACKAGE_TIME_TYPE;
            case 2:return DISTRIBUTE_TIME_TYPE;
            case 3:return DISPATCH_TIME_TYPE;
            default:return null;
        }
    }
}
