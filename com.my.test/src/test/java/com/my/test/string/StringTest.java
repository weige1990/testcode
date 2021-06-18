package com.my.test.string;

import org.junit.Test;

import java.util.StringJoiner;

public class StringTest {

    @Test
    public void testRegex() {


        String tar1 = " 1 1 1";
        String tar2 = "贵州省 毕节市 威宁彝族回族苗族自治县 威宁县龙城花园 101号房";


        String formattLength="\\S{1,50}[ ]\\S{1,50}[ ]\\S{1,50}[ ]\\S{1,50})";
        String formatt2="(\\S{1,10}[ ]\\S{1,15}[ ]\\S{1,10}[ ]\\S{1,50})||(\\S{1,10}[ ]\\S{1,15}[ ]\\S{1,10}[ ]\\S{1,50})";
        String formatt3="(\\S{1,50}[ ]\\S{1,50}[ ]\\S{1,50}[ ]\\S{1,50})||(\\S{1,50}[ ]\\S{1,50}[ ]\\S{1,50}[ ]\\S{1,50}[ ]\\S{1,50})";
        System.out.println(tar2.matches(formatt3));

    }  @Test
    public void testRege2() {


        String tar = "贵州省 毕节市 威宁彝族回族苗族自治县 威宁县龙城花园";
        System.out.println(tar.matches("\\S{1,10}[ ]\\S{1,15}[ ]\\S{1,10}[ ]\\S{1,50}"));

    }

    @Test
    public void testRegex1() {


        String tar1 = "12";
        String tar2 = "34";
        StringJoiner stringJoiner1 = new StringJoiner("").add(tar1);
        StringJoiner stringJoiner2 = new StringJoiner("").add(tar2);

        System.out.println(stringJoiner1);
        System.out.println(stringJoiner2);
        System.out.println(stringJoiner1.merge(stringJoiner2));

    }
    @Test
    public void testStringFormat() {


        System.out.println(String.format("%s的订单待发货，请及时处理，订单编号：%s", null, null));

    }
    @Test
    public void testRegex2() {


//        System.out.println(0b010-8);
        System.out.println(0b11111&0b01);

    }



}
