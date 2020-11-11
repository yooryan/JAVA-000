 ## 1、作业
 ### 1# 思考有多少种方式，在main函数启动一个新线程，运行一个方法，拿到这 个方法的返回值后，退出主线程？
       十种方法:
            SleepCase:主线程直接sleep
            CompletableFutureCase:CompletableFuture接口返回结果
            CountDownLatchCase:CountDownLatch类等待执行结果
            CyclicBarrierCase:CyclicBarrier 回调获取
            FutureCase：Future接口get()方法获取结果
            FutureTaskCase ：FutureTask类
            SynchronizedCase: 同步方法阻塞等待结果
            ThreadJoinCase:线程join方法优先执行
            ReentrantReadWriteLockCase:读写锁互斥,获取结果后释放写锁
            
            
   
 ![binaryTree](JAVA并发编程.png "serialGC")
