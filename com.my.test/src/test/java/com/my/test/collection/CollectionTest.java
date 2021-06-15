package com.my.test.collection;

import com.google.common.collect.Sets;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CollectionTest {
    @Test
    public void testCollectionToArr(){

        Set<String> strings = Sets.newHashSet("小明", "晓东", "小红");

//        String[] strArr = (String[]) strings.toArray();
        String[] strArr = strings.toArray(new String[0]);


        System.out.println(strArr);
        System.out.println(strings);

    } @Test
    public void testCollectionMapDel(){

        Map<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put(null,"大哥");
        objectObjectHashMap.put("2","二哥");
        objectObjectHashMap.put("3","三哥");

        System.out.println(objectObjectHashMap);

        objectObjectHashMap.remove(null);

        System.out.println(objectObjectHashMap);



    }
}
