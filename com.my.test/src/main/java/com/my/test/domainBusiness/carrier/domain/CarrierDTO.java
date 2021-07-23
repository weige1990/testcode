package com.my.test.domainBusiness.carrier.domain;



import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 承运商
 *
 *
 * @since 1.0.0 2020-12-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CarrierDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    private Long id;


    private String name;

    private String code;

    private String abbreviation;

    private Integer type;

    private Integer status;

    private String contact;

    private String contactMobile;

    private String contactEmail;

    private String address;

    private String creditCode;

    private String businessLicenseImage;

    private String createUserName;

    private LocalDateTime createTime;

    private String updateUserName;

    private LocalDateTime updateTime;

    private Integer active;


}
