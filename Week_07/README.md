## 1、作业
 ### 1# （必做）按自己设计的表结构，插入100万订单模拟数据，测试不同方式的插入效率。
         com.github.yooryan.jdbc
            1.使用preparedStatement预编译,耗时:1939s
            2.使用statement静态SQL,耗时:2456s
            3.使用Hikari多线程插入, 耗时65s
                #需要注意连接参数要带上rewriteBatchedStatements=true , 否则单条线程最多只支持1w插入
 ### 2# （必做）读写分离-动态切换数据源版本1.0
         com.github.yooryan.primarysecondary
          基于Spring AOP 动态拦截
 ### 2# （必做）读写分离-数据库框架版本2.0
         com.github.yooryan.shardingspherejdbc

