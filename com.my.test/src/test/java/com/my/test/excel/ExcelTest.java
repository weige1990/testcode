package com.my.test.excel;

import org.testng.annotations.Test;

public class ExcelTest {


        /*
    public class TemplateExcelManySheet {



        @Test
        public void manyMap() throws Exception {
            TemplateExportParams params = new TemplateExportParams(
                    "doc/exportTemp.xls", 0,1);
            params.setHeadingRows(2);
            params.setHeadingStartRow(2);
            params.setStyle(ExcelStyleType.BORDER.getClazz());
            Map<Integer,Map<String,Object>> sheetMap = new HashMap<Integer, Map<String,Object>>();
            Map<String, Object> map = new HashMap<String, Object>();
            //sheet 1
            map.put("year", "2013");
            map.put("sunCourses", list.size());
            Map<String, Object> obj = new HashMap<String, Object>();
            map.put("obj", obj);
            obj.put("n"
                    + "ame", list.size());

            // sheet 2
            map.put("month", 10);

            //第一个sheet Map的值put进去
            sheetMap.put(0, map);
            map = new HashMap<String, Object>();
            Map<String, Object> temp;
            for (int i = 1; i < 8; i++) {
                temp = new HashMap<String, Object>();
                temp.put("per", i * 10);
                temp.put("mon", i * 1000);
                temp.put("summon", i * 10000);
                map.put("i" + i, temp);
            }
            //第二个sheet Map的值put进去
            sheetMap.put(1, map);
            Workbook book = ExcelExportUtil.exportExcel(sheetMap,params);
            File savefile = new File("D:/home/excel/");
            if (!savefile.exists()) {
                savefile.mkdirs();
            }
            FileOutputStream fos = new FileOutputStream("D:/home/excel/exportTemp3.xls");
            book.write(fos);
            fos.close();

        }*/
}
