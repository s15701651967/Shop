package com.bistu.store.entity;

import lombok.*;

import java.io.Serializable;

/** 评论的实体类*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)

public class Comment extends BaseEntity implements Serializable {
    private Integer id;// '主键id',
    private Integer uid;//'用户id',
    private Integer bid;//'商家id',
    private Integer oid; // '订单id',
    private Integer pid;// '商品id',
    private String pComment; //'商品的评价',
    private String bComment; // '商家的评价',
    private Integer judgeMark; //'商品的评分',
    private String reply;//'商家的回复'

}
