package com.my.test.maths;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DecimalTest {
    @Test
    public void testAdd(){

        BigDecimal zero= BigDecimal.ZERO;
        BigDecimal result = zero.add(new BigDecimal("1"));

        System.out.println(result.hashCode());
        System.out.println(result);
    }


    @Test
    public void testScale(){

        BigDecimal num1= new BigDecimal("6.351").setScale(2, RoundingMode.UP);
        int num2 = num1.multiply(new BigDecimal(100)).intValue();

        System.out.println(num1);

    }
}
