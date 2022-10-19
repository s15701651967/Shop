package com.bistu.store.service;

import com.bistu.store.vo.CartVO;

import java.util.Date;
import java.util.List;

public interface ICartService {
    /**
     * 将商品添加至购物车中
     * @param uid 用户id
     * @param pid 商品id
     * @param amount 新增数量
     * @param username 修改者用户名
     */
    void addToCart(Integer uid,Integer pid,Integer amount,String username);

    /**
     * 根据用户uid查询VO类用户加入购物车的商品列表
     * @param uid
     * @return
     */
    List<CartVO> getVOByUid(Integer uid);

    /**
     * 根据购物车id逻辑删除某项购物车物品
     * @param cid
     * @return
     */
    void delCartItem(Integer cid);

    /**
     *  更新用户购物车数据的数量
     *  结合Mapper层中的findByCid和updateNumByCid增加购物车商品数目
     * @param cid
     * @param uid
     * @param username
     * @return
     */
    Integer addNum(Integer cid,Integer uid,String username);

    /**
     *  更新用户购物车数据的数量
     *  结合Mapper层中的findByCid和updateNumByCid增加购物车商品数目
     * @param cid
     * @param uid
     * @param username
     * @return
     */
    Integer reduceNum(Integer cid,Integer uid,String username);

    /**
     * 获取购物车数据
     * @param uid
     * @param cids
     * @return
     */
    List<CartVO> getVOByCid(Integer uid, Integer[] cids);
}
