package com.bistu.store.entity;

import lombok.*;

import java.io.Serializable;
/** 用户的实体类*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)

public class User extends BaseEntity implements Serializable {
    private Integer uid;//id
    private String username;//姓名
    private String password;//密码
    private String salt;
    private String phone;
    private String email;//邮箱
    private Integer gender;//性别
    private String avatar;//头像
    private Integer isDelete;
    private String city;
    private String bankAccount;
    private String userType;
    private Integer isPass;
    private Integer purse;
    private Integer level;
    private Integer point;
}
