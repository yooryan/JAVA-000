package com.github.yooryan.jdbc;

import ch.qos.logback.core.pattern.ConverterUtil;
import com.github.yooryan.jdbc.util.ConnectionUtil;
import com.github.yooryan.jdbc.util.HikariUtil;
import org.apache.catalina.Executor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PutMapping;

import java.security.PublicKey;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author linyunrui
 */
public class InsertTest {



    public static void main(String[] args) throws Exception {
       InsertTest insertTest = new InsertTest();
       // insertTest.preparedStatementAddBatch();
       // insertTest.statementAddBatch();
        insertTest.threadPoolPreparedStatementAddBatch();
    }


    /**
     * 使用statement静态SQL
     * @throws Exception
     */
    public void statementAddBatch() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestampNow = Timestamp.from(now.toInstant(ZoneOffset.of("+8")));
        try (Connection connection = ConnectionUtil.getConnection();
             Statement statement = connection.createStatement();) {
            connection.setAutoCommit(false);

            for (int i = 0; i < 1000000; i++) {
                 String sql = "insert into" +
                         " `order` (" +
                         "user_id," +
                         "store_id," +
                         "order_sn," +
                         "pay_type," +
                         "payment_order_sn," +
                         "payment_time," +
                         "create_time," +
                         "status," +
                         "remark," +
                         "delivery_sn," +
                         "receiver_name," +
                         "receiver_phone," +
                         "receiver_province," +
                         "receiver_city," +
                         "receiver_region," +
                         "receiver_detail_address," +
                         "confirm_status) values (1,1,'"+UUID.randomUUID().toString()+"',1,'"+UUID.randomUUID().toString()+"','"+timestampNow+"','"+timestampNow+"'" +
                         ",1,'备注','运单号','收件人','号码','省份','城市','地区','地址',0)";
                statement.addBatch(sql);
            }
            long t1 = System.currentTimeMillis();
            System.out.println("=======开始插入=======  t1:"+ t1);
            statement.executeBatch();
            connection.commit();
            long t2 = System.currentTimeMillis();
            long l1 = t2 - t1;
            System.out.println("=======结束插入=======  耗时:"+ l1);
        }

    }

    /**
     * 使用preparedStatement预编译
     * @throws Exception
     */
    public void preparedStatementAddBatch() throws Exception {
        String sql = getSql();
        try (Connection connection = ConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            connection.setAutoCommit(false);

            for (int i = 0; i < 1000000; i++) {
                batchData(preparedStatement, i);
                preparedStatement.addBatch();
            }

            long t1 = System.currentTimeMillis();
            System.out.println("=======开始插入=======  t1:"+ t1);
            int[] ints = preparedStatement.executeBatch();
            connection.commit();
            long t2 = System.currentTimeMillis();
            long l1 = t2 - t1;
            System.out.println("=======结束插入=======  耗时:"+ l1);

        }
    }

    /**
     * statement多线程插入
     * @throws Exception
     */
    public void threadPoolPreparedStatementAddBatch() throws Exception {
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        final CountDownLatch countDownLatch = new CountDownLatch(100);
        long t1 = System.currentTimeMillis();
        System.out.println("=======开始插入=======  t1:"+ t1);
        for (int i = 0; i < 100; i++) {
            executorService.execute(() ->{
                threadInsert(countDownLatch);
            });
        }
        countDownLatch.await();
        long t2 = System.currentTimeMillis();
        long l1 = t2 - t1;
        System.out.println("=======结束插入=======  耗时:"+ l1);
        executorService.shutdown();
    }

    private void threadInsert(CountDownLatch countDownLatch) {
        try (final Connection connection = HikariUtil.getConn();
             PreparedStatement preparedStatement = connection.prepareStatement(getSql())
               ){
            connection.setAutoCommit(false);
            for (int i = 0; i < 10000; i++) {
                batchData(preparedStatement,i);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }finally {
            countDownLatch.countDown();
        }
    }

    private void batchData(PreparedStatement preparedStatement, int i) throws SQLException {
        preparedStatement.setLong(1, 1L);
        preparedStatement.setLong(2, 1L);
        preparedStatement.setString(3, UUID.randomUUID().toString());
        preparedStatement.setInt(4, 1);
        preparedStatement.setString(5, UUID.randomUUID().toString());
        preparedStatement.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
        preparedStatement.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
        preparedStatement.setInt(8, 1);
        preparedStatement.setString(9, "备注" + i);
        preparedStatement.setString(10, "运单号" + i);
        preparedStatement.setString(11, "收件人" + i);
        preparedStatement.setString(12, "号码" + i);
        preparedStatement.setString(13, "省份" + i);
        preparedStatement.setString(14, "城市" + i);
        preparedStatement.setString(15, "地区" + i);
        preparedStatement.setString(16, "地址" + i);
        preparedStatement.setInt(17, 0);
    }

    public String getSql(){
       return  "insert into" +
                " `order` (" +
                "user_id," +
                "store_id," +
                "order_sn," +
                "pay_type," +
                "payment_order_sn," +
                "payment_time," +
                "create_time," +
                "status," +
                "remark," +
                "delivery_sn," +
                "receiver_name," +
                "receiver_phone," +
                "receiver_province," +
                "receiver_city," +
                "receiver_region," +
                "receiver_detail_address," +
                "confirm_status) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    }
}
