package com.my.test.leetcode.straightPic;

import org.apache.commons.lang3.ArrayUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Stream;


class Solution {


    public static void main(String[] args) {
        System.out.println(new Solution().trap(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1));
//        System.out.println(new Solution().trap(0));
    }

    public int trap(int... height) {
        if (height.length == 0) {
            return 0;
        }


        //先查出最大高度?或者是一边上升一边查出谁是最大高度(第一行就知道谁是最大高度了,可以孤立第一行的处理逻辑),选择这个吧
        WholePic wholePic = new WholePic();
        wholePic.setRows(new LinkedList<>());
        //1迭代出第一行数据


        Integer startAbscissa = null;//这一行的起始横坐标
        Integer endAbscissa = null;//这一行的末尾横坐标
        int maxHeight = 0;//图的最大高度
        int heightRecordTemp = 0;//高度记录
        int waterSquareTemp = 0;
        int willBeWaterSquare = 0;
        RowPic firstRowPic = new RowPic();
        for (int i = 0; i <= height.length - 1; i++) {

            if (maxHeight <= height[i]) {
                maxHeight = height[i];
            }
            if (height[i] - heightRecordTemp > 0 && startAbscissa == null) {

                startAbscissa = i;
                endAbscissa = i;

            }
            //已有起始坐标最左是实体
            if (startAbscissa != null) {
                //当前位置也是实体
                if (height[i] - heightRecordTemp > 0) {
                    endAbscissa = i;

                    waterSquareTemp += willBeWaterSquare;

                    willBeWaterSquare = 0;


                } else {
                    //当前位置是水

                    willBeWaterSquare += (1 * RowPic.rowHeight);


                }

            }


        }

        firstRowPic.setStartAbscissa(startAbscissa);
        firstRowPic.setEndAbscissa(endAbscissa);
        firstRowPic.setWaterSquare(waterSquareTemp);
        wholePic.getRows().add(firstRowPic);

        //2迭代出以后的n行数据
        for (int j = 1; j < maxHeight; j++) {
            RowPic rowPic = generateRowWhenKnewMaxHeight(j * RowPic.rowHeight, height);
            wholePic.getRows().add(rowPic);
        }


        return wholePic.getRows().stream().mapToInt(RowPic::getWaterSquare).sum();


    }

    private RowPic generateRowWhenKnewMaxHeight(int heightRecord, int[] height) {

        Integer startAbscissa = null;//这一行的起始横坐标
        Integer endAbscissa = null;//这一行的末尾横坐标

        int heightRecordTemp = heightRecord;//高度记录
        int waterSquareTemp = 0;
        int willBeWaterSquare = 0;
        RowPic rowPic = new RowPic();
        for (int i = 0; i <= height.length - 1; i++) {


            if (height[i] - heightRecordTemp > 0 && startAbscissa == null) {

                startAbscissa = i;
                endAbscissa = i;

            }
            //已有起始坐标最左是实体
            if (startAbscissa != null) {
                //当前位置也是实体
                if (height[i] - heightRecordTemp > 0) {
                    endAbscissa = i;

                    waterSquareTemp += willBeWaterSquare;
                    willBeWaterSquare = 0;


                } else {
                    //当前位置是水

                    willBeWaterSquare += (1 * RowPic.rowHeight);


                }

            }

        }

        rowPic.setStartAbscissa(startAbscissa);
        rowPic.setEndAbscissa(endAbscissa);
        rowPic.setWaterSquare(waterSquareTemp);

        return rowPic;

    }
}


/**
 * 一个行图片
 */
class RowPic {
    private Integer startAbscissa;//每一行的起始横坐标
    private Integer endAbscissa;//每一行的结尾横坐标

    public int getWaterSquare() {
        return waterSquare;
    }

    public void setWaterSquare(int waterSquare) {
        this.waterSquare = waterSquare;
    }

    private int waterSquare;//有储水的图片的面积
    public static final int rowHeight = 1;

    public Integer getStartAbscissa() {
        return startAbscissa;
    }

    public void setStartAbscissa(Integer startAbscissa) {
        this.startAbscissa = startAbscissa;
    }

    public Integer getEndAbscissa() {
        return endAbscissa;
    }

    public void setEndAbscissa(Integer endAbscissa) {
        this.endAbscissa = endAbscissa;
    }

    public static int getRowHeight() {
        return rowHeight;
    }
}

/**
 * 整个图片
 */
class WholePic {
    private List<RowPic> rows;//一个图片其实是由多个行构成

    public List<RowPic> getRows() {
        return rows;
    }

    public void setRows(List<RowPic> rows) {
        this.rows = rows;
    }
}
