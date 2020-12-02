package com.github.yooryan.primarysecondary;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.github.yooryan.primarysecondary.mapper")
public class PrimarySecondaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimarySecondaryApplication.class, args);
	}

}
