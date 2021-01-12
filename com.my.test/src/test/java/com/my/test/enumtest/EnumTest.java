package com.my.test.enumtest;

import com.my.test.domainBusiness.transport.constant.TransportTimeTypeEnum;
import org.junit.Test;

public class EnumTest {

@Test
    public void testGetEnum() {
        System.out.println(TransportTimeTypeEnum.PACKAGE_TIME_TYPE.equals(TransportTimeTypeEnum.getType(null)));
        System.out.println(TransportTimeTypeEnum.getType(1).equals(TransportTimeTypeEnum.PACKAGE_TIME_TYPE));
        System.out.println(TransportTimeTypeEnum.getType(2).equals(TransportTimeTypeEnum.PACKAGE_TIME_TYPE));
        System.out.println(TransportTimeTypeEnum.getType(3).equals(TransportTimeTypeEnum.PACKAGE_TIME_TYPE));

    }

    @Test
    public void testEqualNull() {
       Integer num=1;
        System.out.println(num.equals(null));
        System.out.println(null instanceof Integer);

    }
}
