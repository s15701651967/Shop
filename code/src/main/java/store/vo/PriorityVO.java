package com.bistu.store.vo;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class PriorityVO implements Serializable {
    private Integer uid;
    private String username;//姓名
    private String userType;
    private Integer priorities;
    private Integer level;
}
