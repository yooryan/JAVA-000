package com.github.yooryan.shardingspherejdbc.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author linyunrui
 */
@Data
public class User {

    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String phone;
    private Integer gender;
    private LocalDateTime registerTime;
    private Integer status;
    private LocalDateTime createTime;
}
