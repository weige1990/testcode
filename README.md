# testcode
用于做为测试类的代码,可以用于制作testNG或jUNIT


# 20210722加入阿里com.alibaba.ttl.TransmittableThreadLocal测试

## 结论不适合:主线程 提交异步线程池任务场景(测试类 com.my.test.thread.TestThreadController)

一分析目标(TransmittableThreadLocal 是否适合 主线程 提交异步线程池任务场景?):
com.alibaba.ttl.TransmittableThreadLocal 这个ThreadLocal类的可用场景不多

2.12.1
版本


二.实现接口




三.主要研究的方法
com.alibaba.ttl.TransmittableThreadLocal#set






特殊-----------------------------------------------------------------------------------------------------------------------------------------------------
//特殊1:主线程执行了 set/get方法 都会邹到createMap->inhreitableThreadLocals就不会是空



//特殊2:子线程初始化时 java.lang.Thread#init(java.lang.ThreadGroup, java.lang.Runnable, java.lang.String, long, java.security.AccessControlContext)
集成,这个主要是把主线程的initheritableThreadLocal数据 赋值进去自己的initheritableThreadLocal=>所以假如是线程池下面的子线程ThreadLocal=>   推论:子线程获取的数据是 :初始化线程时候的线程的信息(用来做获取用户信息就会有问题)而且线程池的thread对象是复用的,根本就不会一个任务init一次!



--------------------------------------------------------------------------------------------------------------------------------------------
用的是inheritbleThreadLocals


holder其实可以看做一个ThreadLocal
holder


com.alibaba.ttl.TransmittableThreadLocal#get





![image](https://user-images.githubusercontent.com/35860913/126746470-41ed7c51-3aaa-43a7-a48f-8e6d387462fd.png)
