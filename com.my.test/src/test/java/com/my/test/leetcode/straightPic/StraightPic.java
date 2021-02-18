package com.my.test.leetcode.straightPic;

import org.apache.commons.lang3.ArrayUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Stream;


class Solution {


    public static void main(String[] args) {
//        System.out.println(new Solution().trap(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1));
//        System.out.println(new Solution().trap(0));
        System.out.println(new Solution().trap(2, 0, 2));
//        System.out.println(new Solution().trap(1));
    }

    public int trap(int... height) {
        return getTrap1(height);
    }

    /**
     * @param heightRecord
     * @param height
     * @param startAbscissaMax
     * @param endAbscissaMax
     * @return 返回数组 0:这一列的数位平方数 1:这一行的起始索引值  2:这一行的末尾索引值
     */
    private int[] generateRowWhenKnewMaxHeight(int heightRecord, int[] height, int startAbscissaMax,
                                               int endAbscissaMax) {
        int[] result;


        int startAbscissa = -1;//这一行的起始横坐标
        int endAbscissa = -1;//这一行的末尾横坐标
        int block = 0;//阻塞的面积

        //第一次结果:执行用时：(1需要前后一起操作优化)
       /* 1837 ms
        , 在所有 Java 提交中击败了
        5.07%
        的用户
        内存消耗：
        38.8 MB
        , 在所有 Java 提交中击败了
        5.07%
        的用户*/

//        第二次结果(快了21%)
      /*  执行用时：
        1430 ms
                , 在所有 Java 提交中击败了
        5.07%
                的用户
        内存消耗：
        38.6 MB
                , 在所有 Java 提交中击败了
        5.07%
                的用户*/




     /* 第三次(1去掉了三元和大部分的if判断 2不使用装箱,使用基本数据模型) 效率提升为31% 执行用时：
        1257 ms
                , 在所有 Java 提交中击败了
        5.07%
                的用户
        内存消耗：
        38.2 MB
                , 在所有 Java 提交中击败了
        42.54%
                的用户*/


        for (int i = startAbscissaMax; i <= endAbscissaMax - 1; i++) {


            if (height[i] - heightRecord > 0) {
                if (startAbscissa >= 0)
                //当前位置也是实体
                {
                    //已有起始坐标最左是实体
                    endAbscissa = i + 1;
                    block += 1;

                } else {

                    startAbscissa = i;
                    endAbscissa = i + 1;
                    block += 1;

                }

            }


        }

        result = new int[]

                {
                        endAbscissa - startAbscissa - block, startAbscissa, endAbscissa
                }

        ;


        return result;

    }


    private int getTrap1(int... height) {


        if (height.length == 0) {
            return 0;
        }


        //先查出最大高度?或者是一边上升一边查出谁是最大高度(第一行就知道谁是最大高度了,可以孤立第一行的处理逻辑),选择这个吧
        //1迭代出第一行数据


        int startAbscissaMax = -1;//这一行的起始横坐标
        int endAbscissaMax = -1;//这一行的末尾横坐标
        int maxHeight = 0;//图的最大高度
        int heightRecordTemp = 0;//高度记录
        int waterSquareTemp = 0;
        int block = 0;//阻塞的面积


        for (int i = 0; i <= height.length - 1; i++) {

            if (maxHeight <= height[i]) {
                maxHeight = height[i];
            }
            //已有起始坐标最左是实体
            if (height[i] - heightRecordTemp > 0) {

                if (startAbscissaMax >= 0) {
                    //当前位置也是实体
                    endAbscissaMax = i + 1;
                    block += 1;

                } else {
                    startAbscissaMax = i;
                    endAbscissaMax = i + 1;
                    block += 1;
                }

            }
        }


        waterSquareTemp = endAbscissaMax - startAbscissaMax - block;


        //2迭代出以后的n行数据
        for (int j = 1; j < maxHeight; j++) {


            int[] results = generateRowWhenKnewMaxHeight(j, height, startAbscissaMax, endAbscissaMax);

            waterSquareTemp += results[0];
            startAbscissaMax = results[1];
            endAbscissaMax = results[2];


        }


        return waterSquareTemp;

    }

 /*   private int getTrap2(int... height) {



    }*/
}



//用负数解决


