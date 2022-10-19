package com.bistu.store.service;

import com.bistu.store.entity.Address;
import com.bistu.store.entity.User;

import java.util.List;

/**收货地址业务层接口*/
public interface IAddressService {
    void addNewAddress(Integer uid,String username,Address address);

    List<Address> getByUid(Integer uid);

    /**
     * 修改某个用户收货地址数据为默认收货地址
     * @param aid 收货地址id
     * @param uid 用户的id
     * @param username 表示修改执行的人
     */
    void setDefault(Integer aid,Integer uid,String username);


    /**
     * 删除用户选中的收货地址数据
     * @param aid 收货地址id
     * @param uid 用户id
     * @param username 用户名
     */
    void delete(Integer aid, Integer uid,String username);

    /**
     *
     /**
     * 根据用户aid修改用户收货地址数据
     * @param aid 用户aid
     * @param username 用户名称
     * @param address 用户地址对象数据
     * @return 用户的收货地址数据
     */
    void updateInfoByAid(Integer aid,String username,Address address);

    /**
     * 根据收货地址id获取收货地址数据
     * @param aid
     * @param uid
     * @return
     */
    Address getByAid(Integer aid,Integer uid);
}
