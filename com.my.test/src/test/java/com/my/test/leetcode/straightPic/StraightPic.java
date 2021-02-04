package com.my.test.leetcode.straightPic;

import org.apache.commons.lang3.ArrayUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Stream;


class Solution {


    public static void main(String[] args) {
//        System.out.println(new Solution().trap(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1));
       System.out.println(new Solution().trap(0));
//        System.out.println(new Solution().trap(1));
    }

    public int trap(int... height) {
        if (height.length == 0) {
            return 0;
        }


        //先查出最大高度?或者是一边上升一边查出谁是最大高度(第一行就知道谁是最大高度了,可以孤立第一行的处理逻辑),选择这个吧
        //1迭代出第一行数据


        Integer startAbscissa = null;//这一行的起始横坐标
        Integer endAbscissa = null;//这一行的末尾横坐标
        int maxHeight = 0;//图的最大高度
        int heightRecordTemp = 0;//高度记录
        int waterSquareTemp = 0;
        int block = 0;//阻塞的面积
        final int rowHeight = 1;

        for (int i = 0; i <= height.length - 1; i++) {

            if (maxHeight <= height[i]) {
                maxHeight = height[i];
            }
            if (height[i] - heightRecordTemp > 0 && startAbscissa == null) {

                startAbscissa = i;
                endAbscissa = startAbscissa + 1;

            }
            //已有起始坐标最左是实体
            if (startAbscissa != null) {
                //当前位置也是实体
                if (height[i] - heightRecordTemp > 0) {
                    endAbscissa = i + 1;
                    block += (1 * rowHeight);
                }

            }


        }


        waterSquareTemp = ((endAbscissa==null?0:endAbscissa) - (startAbscissa==null?0:startAbscissa)) * rowHeight - block;


        //2迭代出以后的n行数据
        for (int j = 1; j < maxHeight; j++) {
            waterSquareTemp += generateRowWhenKnewMaxHeight(j * rowHeight, height);

        }


        return waterSquareTemp;


    }

    private int generateRowWhenKnewMaxHeight(int heightRecord, int[] height) {
        final int rowHeight = 1;
        Integer startAbscissa = null;//这一行的起始横坐标
        Integer endAbscissa = null;//这一行的末尾横坐标
        int block = 0;//阻塞的面积

        for (int i = 0; i <= height.length - 1; i++) {


            if (height[i] - heightRecord > 0 && startAbscissa == null) {

                startAbscissa = i;
                endAbscissa = startAbscissa + 1;

            }
            //已有起始坐标最左是实体
            if (startAbscissa != null) {
                //当前位置也是实体
                if (height[i] - heightRecord > 0) {
                    endAbscissa = i + 1;
                    block += (1 * rowHeight);

                }

            }


        }
        return ((endAbscissa==null?0:endAbscissa) - (startAbscissa==null?0:startAbscissa)) * rowHeight - block;

    }
}


