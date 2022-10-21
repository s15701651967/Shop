package com.bistu.store.entity;

import lombok.*;

import java.util.Objects;

@Data//添加get set方法
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
/** 收货地址数据的实体类*/
public class Address extends BaseEntity{
    private Integer aid;//收货地址id
    private Integer uid;//归属的用户名
    private String name;//收货人姓名
    private String provinceName;//省名称
    private String provinceCode;//省-行政代号
    private String cityName;//市-名称
    private String cityCode;//市-行政代号
    private String areaName;//区-名称
    private String areaCode;//区-行政代号
    private String zip;//邮政编码
    private String address;//详细地址
    private String phone;//手机
    private String tel;//固话
    private String tag;//标签
    private Integer isDefault;//是否默认：0-不默认，1-默认
}
