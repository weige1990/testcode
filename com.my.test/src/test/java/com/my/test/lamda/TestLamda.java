package com.my.test.lamda;

import com.my.test.domainBusiness.sale.domain.OrderDetailExcel;
import com.my.test.lamda.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.util.*;
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


        log.info("生成List{}", leisure);
        log.debug("生成List{}", leisure);
        log.error("生成List{}", leisure);
        log.trace("生成List{}", leisure);

    }


    @Test
    public void testGetAllFields() {

        Class<OrderDetailExcel> orderDetailExcelClass = OrderDetailExcel.class;

        List<Field> fields = Arrays.asList(orderDetailExcelClass.getDeclaredFields());


        List<String> fieldStrs = fields.stream().map(field -> {
            return "\"" + field.getName() + "\"";
        }).collect(Collectors.toList());


        System.out.println(fieldStrs);

    }


    @Test
    public void testMapInt() {
        User.UserBuilder userBuilder1 = User.builder().age(2).cardNo("12").deptNo("3");
        User.UserBuilder userBuilder2 = User.builder().age(5).cardNo("13").deptNo("4");
        User.UserBuilder userBuilder3 = User.builder().age(6).cardNo("14").deptNo("5");
        List<User.UserBuilder> userBuilders = new ArrayList<>();
        userBuilders.add(userBuilder1);
        userBuilders.add(userBuilder2);
        userBuilders.add(userBuilder3);
        int count = 50;
        int result=0;
        LinkedList<User> users = new LinkedList<>();
        while (count > 0) {
            count--;
            User userTemp = userBuilders.get(count % 3).build();
            result+=userTemp.getAge();
            users.add(userTemp);
        }


        long result2 = users.stream().mapToInt(o -> o.getAge()).sum();

        System.out.println(result2);
        System.out.println(result);

    }


    @Test
    public void testSorted() {
        User user1 = User.builder().age(6).cardNo("12").deptNo("3").build();
        User user2 = User.builder().age(2).cardNo("13").deptNo("4").build();
        User user3 = User.builder().age(5).cardNo("14").deptNo("5").build();
        User user4 = User.builder().age(1).cardNo("14").deptNo("5").build();
        User userBuilder5 = User.builder().age(15).cardNo("14").deptNo("5").build();
        User userBuilder6 = User.builder().age(10).cardNo("14").deptNo("5").build();
        User userBuilder7 = User.builder().age(16).cardNo("14").deptNo("5").build();
        User userBuilder8= User.builder().age(18).cardNo("14").deptNo("5").build();
        User userBuilder9 = User.builder().age(3).cardNo("14").deptNo("5").build();
        User userBuilder10 = User.builder().age(4).cardNo("14").deptNo("5").build();
        List<User> users = Lists.newArrayList(user1,user2,user3
                ,user4,userBuilder5,userBuilder6
                ,userBuilder7,userBuilder8,userBuilder9,userBuilder10
        );

        System.out.println(users);
        System.out.println("-------------------------排序后----------------");
        users=users.stream().sorted(Comparator.comparing(User::getAge)).collect(Collectors.toList());


        System.out.println(users);

    }
}
