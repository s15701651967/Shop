package com.bistu.store.entity;

//import javax.xml.crypto.Data;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/** 作为实体类的基类*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

public class BaseEntity implements Serializable {
    private String createdUser;
    private Date createdTime ;
    private String  modifiedUser;
    private Date modifiedTime;


}
