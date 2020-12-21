package com.my.test.redis;

import com.my.test.TestStarter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = TestStarter.class)
@Slf4j
public class PanicBuyingTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private static String NUM_KEY = ":num";
    private static String ONSALE_KEY = ":onsale";
    private static String ORIGIN_STORE_NUM = ":origin_store_num";

    @Test(groups = "testPanicBuying")
    /**
     * 上货
     */
    public void testPanicBuyingUpload() {
        //提前把货物的sku+数量+活动批次号+(给一个订单号就好了) 加redis里面

        //1获取一个订单号
        //2货物的数量
        //3统一批次号就是一个常量吧
        //4上传一把状态锁标记这个商品是不是还能抢,1就是还能抢,假如是已经消失了,就不能抢了
        redisTemplate.opsForValue().set(getBatchNo() + ":" + getOrderNo() + NUM_KEY, 0);
        redisTemplate.opsForValue().set(getBatchNo() + ":" + getOrderNo() + ORIGIN_STORE_NUM, geNumOfStore());
//        redisTemplate.opsForValue().set(getBatchNo() + ":" + getOrderNo()+ONSALE_KEY,1);


    }

    private String getOrderNo() {

        return "ABC202012191";

    }

    private Long geNumOfStore() {
        return 1000L;

    }

    private String getBatchNo() {
        return "hotSale123";
    }

    @Test(threadPoolSize = 2000, invocationCount = 2000, groups = "testPaincBuying")
    public void testPanicBuyingBegin() {
       /* if (redisTemplate.opsForValue().get(getBatchNo() + ":" + getOrderNo()+ONSALE_KEY)!=null)
        {
            log.info("没有抢够到商品,直接退出");
            return;
        } */

        Integer orderPredict = (Integer) redisTemplate.opsForValue().get(getBatchNo() + ":" + getOrderNo() + NUM_KEY);

        if (orderPredict < 0) {
            log.info("没有抢够到商品,直接退出,号码为{}", orderPredict);
            return;
        }
        Long orderNum = redisTemplate.opsForValue().increment(getBatchNo() + ":" + getOrderNo() + NUM_KEY);
        Integer  originStoreNum= (Integer)redisTemplate.opsForValue().get(getBatchNo() + ":" + getOrderNo() + ORIGIN_STORE_NUM);
        if ((originStoreNum-orderNum) >=0) {
            log.info("抢购到一件商品单号为:{}", getBatchNo() + "-" + getOrderNo() + "-" + orderNum);
        } else {
//            redisTemplate.delete(getBatchNo() + ":" + getOrderNo()+ONSALE_KEY);
            boolean lock = redisTemplate.opsForValue().setIfAbsent(getBatchNo() + ":" + getOrderNo() + ONSALE_KEY, 1, 300, TimeUnit.SECONDS);
            log.info("并没有抢购到商品,商品数目为:{},上锁为{}", orderNum, lock);
        }
    }

//    @AfterMethod(groups = "testPaincBuying")
    public void testPanicBuyingAgain() {
        testPanicBuyingBegin();
    }

    @Test
    public void deletePanicBuyingKey()
    {


        Set<String> keys = redisTemplate.keys(getBatchNo() + ":" + getOrderNo() + ":*");
        Long deleteSuccess = redisTemplate.delete(keys);
        if (deleteSuccess>0)
        {
            log.info("deletePanicBuyingKey successfully!");
        }
        else
        {

            log.info("fail to deletePanicBuyingKey!");
        }
    }

}
