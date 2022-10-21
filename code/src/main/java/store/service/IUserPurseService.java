package com.bistu.store.service;

import com.bistu.store.entity.User;
import com.bistu.store.entity.UserPurse;

import java.util.Date;
import java.util.List;

/**
 * 处理用户数据的业务层接口
 */
//创建接口，在impl包下实现接口
public interface IUserPurseService {
    /**
     * 用户充值记录录入
     *
     * @param user 用户数据
     */
    void insert(UserPurse user);//定义一个user类型参数列表作为对象

    /**
     * 用户充值记录录入
     *
     * @param user 用户数据
     */
    void inserts(UserPurse user);//定义一个user类型参数列表作为对象

    /**
     * 根据用户的id查询用户数据
     *
     * @param uid 用户id
     * @return 用户的数据
     */
    List<UserPurse> getByUid(Integer uid);


}
