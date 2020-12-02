package com.github.yooryan.shardingspherejdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.github.yooryan.shardingspherejdbc.mapper")
public class ShardingphereJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShardingphereJdbcApplication.class, args);
	}

}
