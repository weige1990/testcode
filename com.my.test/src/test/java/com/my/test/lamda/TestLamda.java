package com.my.test.lamda;

import com.my.test.domainBusiness.sale.domain.OrderDetailExcel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@SpringBootTest("com.my.test.TestStarter")
@Slf4j
public class TestLamda {


    @Test
    public void testFilter() {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        List<Integer> arrList = Arrays.asList(arr);

        List<Integer> leisure = arrList.stream()
                .filter(o -> {
                    return ((Integer) o).compareTo(3) > 0;
                }).collect(Collectors.toList());


        log.info("生成List{}",leisure);
        log.debug("生成List{}",leisure);
        log.error("生成List{}",leisure);
        log.trace("生成List{}",leisure);

    }


    @Test
    public void testGetAllFields() {

        Class<OrderDetailExcel> orderDetailExcelClass = OrderDetailExcel.class;

        List<Field> fields = Arrays.asList(orderDetailExcelClass.getDeclaredFields());


        List<String> fieldStrs = fields.stream().map(field -> {
            return "\""+field.getName()+"\"";
        }).collect(Collectors.toList());



        System.out.println(fieldStrs);

    }
}
