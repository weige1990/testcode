package com.my.test.string;

import org.junit.Test;

import java.util.StringJoiner;

public class StringTest {

    @Test
    public void testRegex() {


        String tar = " 1 1 1";
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
}
