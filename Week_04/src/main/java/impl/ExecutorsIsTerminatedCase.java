package impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author linyunrui
 */
public class ExecutorsIsTerminatedCase {

    public static void main(String[] args) throws InterruptedException {
        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        int[] result = new int[1];
         // 异步执行 下面方法
        executorService.execute(() ->{
            result[0] = sum();
        });

        executorService.shutdown();
        //等待关闭后所有任务都已完成
        while(!executorService.isTerminated()){
          //否则继续等待
          System.out.println("等待异步任务完成");
          Thread.sleep(10);
        }
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+ result[0]);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        // 然后退出main线程
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }
}
