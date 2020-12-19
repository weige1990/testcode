package com.my.test.domainBusiness.sale.domain;


import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单明细表
 *
 * @author zengzhicong zengzhicong@sanyygp.com
 * @since 1.0.0 2020-07-12
 */
@Data
public class OrderDetailExcel {


    private String orderNo;
    private String platformTypeDesc;
    private String orderTypeDesc;
    private String statusDesc;
    private String customerName;
    /**
     * 实付金额 商品总计金额+运费-优惠价-折扣价
     */
    private BigDecimal payPrice;
    private LocalDateTime createTime;
    /**
     * 支付方式 线上：取字典表 线下：取账期描述
     */
    private String payMannerDesc;
    private String payPeriod;
    private String consignee;
    private String consigneeMobile;
    private String address;
    private String salesName;
    private String superiorInviterMobile;
    private String topInviterMobile;
    //    @Excel(name = "要求开发票", replace = {"是_true", "否_false"})
//    private Boolean needInvoice;
//    @Excel(name = "是否开票 0：未开票 1：已开票", replace = {"已开票_1", "未开票_0"})
//    private Integer invoiceStatus;
    private String userMobile;
    private String deliveryTemplateDesc;
    private String cancelReason;


    //明细信息
    private String skuNo;
    private String skuName;
    private String brand;
    private String modelCode;
    private String stockStatusDesc;
    private String goodsDate;
    private LocalDateTime goodsTime;
    private String itemPcs;
    private Integer num;
    private BigDecimal detailPayUnitPrice;
    private BigDecimal detailPayPrice;

    private String signStatusDesc;
    private Integer deliveryNum;
    private String warehouseName;
    private String logisticsCompany;
    private String logisticsNo;
    private Integer allowInvoice;

    private String outboundNo;
    private LocalDateTime transactionTime;


    private String businessLine;


    private LocalDateTime receiveTime;



    private LocalDateTime actualPaidTime;


    private BigDecimal couponPrice;


    private String customerIdentity;


}
