package com.my.test.lamda;

import com.my.test.domainBusiness.sale.domain.OrderDetailExcel;
import com.my.test.lamda.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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
        int result = 0;
        LinkedList<User> users = new LinkedList<>();
        while (count > 0) {
            count--;
            User userTemp = userBuilders.get(count % 3).build();
            result += userTemp.getAge();
            users.add(userTemp);
        }


        long result2 = users.stream().mapToInt(o -> o.getAge()).sum();

        System.out.println(result2);
        System.out.println(result);

    }

    private List<User> createUsers() {
        User user1 = User.builder().age(6).cardNo("12").deptNo("3").build();
        User user2 = User.builder().age(2).cardNo("13").deptNo("4").build();
        User user3 = User.builder().age(5).cardNo("14").deptNo("5").build();
        User user4 = User.builder().age(1).cardNo("14").deptNo("5").build();
        User userBuilder5 = User.builder().age(15).cardNo("14").deptNo("5").build();
        User userBuilder6 = User.builder().age(10).cardNo("14").deptNo("5").build();
        User userBuilder7 = User.builder().age(16).cardNo("14").deptNo("5").build();
        User userBuilder8 = User.builder().age(18).cardNo("14").deptNo("5").build();
        User userBuilder9 = User.builder().age(3).cardNo("14").deptNo("5").build();
        User userBuilder10 = User.builder().age(4).cardNo("14").deptNo("5").build();
        List<User> users = Lists.newArrayList(user1, user2, user3
                , user4, userBuilder5, userBuilder6
                , userBuilder7, userBuilder8, userBuilder9, userBuilder10
        );
        return users;
    }

    @Test
    public void testSorted() {

        List<User> users = createUsers();
        System.out.println(users);
        System.out.println("-------------------------排序后----------------");
        users = users.stream().sorted(Comparator.comparing(User::getAge)).collect(Collectors.toList());


        System.out.println(users);

    }

    @Test
    public void testCreateApplyNo2() {

        String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        System.out.println(String.format("YGPFP" + today + "%04d", (3)));
        System.out.println(String.format("YGPFP" + today + "%04d", (0)));
        System.out.println(String.format("YGPFP" + today + "%05d", (3)));
        System.out.println(String.format("YGPFP" + today + "%05d", (12)));
        System.out.println(String.format("YGPFP" + today + "%05d", (111)));
        System.out.println(String.format("YGPFP" + today + "%05d", (1113)));
        System.out.println(String.format("YGPFP" + today + "%05d", (11134)));
        System.out.println(String.format("YGPFP" + today + "%05d", (111345)));


    }


    @Test
    public void testEmptyListForEach() {

        List<User> users = createUsers();

        List<Integer> ages = users.stream().filter(o -> o.getAge() > 100).map(o -> o.getAge()).collect(Collectors.toList());
        Integer ageSum = users.stream().filter(o -> o.getAge() > 100).map(o -> o.getAge()).reduce(Integer::sum).orElse(null);

        System.out.println(ages);
        System.out.println(ageSum);


    }

    @Test
    public void testTime() {

        log.info("{}", System.currentTimeMillis());

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowPlus30Min = now.plusMinutes(30);

        Long miliSec = nowPlus30Min.toInstant(ZoneOffset.of("+8")).toEpochMilli() - now.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Long mins = (miliSec / (1000 * 60));
        System.out.println(miliSec);
        System.out.println(mins);


    }


    @Test
    public void testTime2() {

        log.info("{}", System.currentTimeMillis());

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowPlus30Min = now.plusDays(30);


        Duration duration1 = Duration.between(now, nowPlus30Min);
        Duration duration2 = Duration.between(nowPlus30Min, now);


        System.out.println(duration1.toDays());
        System.out.println(duration2.toDays());


    }

    @Test
    public void testTime3() {

        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));


    }

    @Test
    public void testTime5() {

        User.UserBuilder userBuilder1 = User.builder().age(2).deptNo("aa");
        User.UserBuilder userBuilder2 = User.builder().age(3).deptNo("bb");
        User.UserBuilder userBuilder3 = User.builder().age(4).deptNo("cc");
        List<User> lists = new LinkedList<>();

        lists.add(userBuilder1.build());
        lists.add(userBuilder2.build());
        lists.add(userBuilder3.build());
        lists.add(userBuilder2.build());
        lists.add(userBuilder3.build());
        lists.add(userBuilder1.build());
        lists.add(userBuilder2.build());
        lists.add(userBuilder3.build());
        lists.add(userBuilder1.build());
        lists.add(userBuilder2.build());
        lists.add(userBuilder3.build());
        lists.add(userBuilder1.build());
        lists.add(userBuilder2.build());
        lists.add(userBuilder3.build());
        lists.add(userBuilder1.build());

        List<User> targets = lists.stream().sorted(Comparator.comparing(User::getAge).reversed()).collect(Collectors.toList());

        System.out.println(targets);

    }

    @Test
    public void testTime4() {

        ArrayList<LocalDateTime> localDateTimes = Lists.newArrayList(LocalDateTime.now(), LocalDateTime.of(2021, 3, 25, 15, 0));

        LocalDateTime maxTime = localDateTimes.stream().max(Comparator.comparing(o -> o)).orElse(null);
        System.out.println(maxTime);


    }
 @Test
    public void testTime6() {

     User.UserBuilder userBuilder1 = User.builder().age(2).deptNo("aa");
     User.UserBuilder userBuilder2 = User.builder().age(3).deptNo("bb");
     User.UserBuilder userBuilder3 = User.builder().age(4).deptNo("cc");
     List<User> lists = new LinkedList<>();

     lists.add(userBuilder1.build());
     lists.add(userBuilder2.build());
     lists.add(userBuilder3.build());
     lists.add(userBuilder2.build());
     lists.add(userBuilder3.build());
     lists.add(userBuilder1.build());
     lists.add(userBuilder2.build());
     lists.add(userBuilder3.build());
     lists.add(userBuilder1.build());
     lists.add(userBuilder2.build());
     lists.add(userBuilder3.build());
     lists.add(userBuilder1.build());
     lists.add(userBuilder2.build());
     lists.add(userBuilder3.build());
     lists.add(userBuilder1.build());

     List<User> targets = lists.stream().sorted(Comparator.comparing(o->o.getAge())).collect(Collectors.toList());

     System.out.println(targets);


    }


}
