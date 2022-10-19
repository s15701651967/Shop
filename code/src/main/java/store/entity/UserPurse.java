package com.bistu.store.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/** 用户的实体类*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)

public class UserPurse extends BaseEntity implements Serializable {
    private Integer id;//id
    private Integer uid;//id
    private String username;//姓名
    private Integer purse;//性别
    private Integer addAmount;
    private Integer subAmount;
    private Integer pid;
    private String createdUser;
    private Date createdTime ;
    private String  modifiedUser;
    private Date modifiedTime;
   }
