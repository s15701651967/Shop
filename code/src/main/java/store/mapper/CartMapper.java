package com.bistu.store.mapper;

import com.bistu.store.entity.Cart;
import com.bistu.store.vo.CartVO;

import java.util.Date;
import java.util.List;

public interface CartMapper {
    /**
     * 插入购物车数据
     * @param cart 购物车数据
     * @return 受影响行数
     */
    Integer insert(Cart cart);

    /**
     * 更新购物车某件商品数量
     * @param cid 购物车数据id
     * @param num 更新数量
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updateNumByCid(Integer cid, Integer num, String modifiedUser, Date modifiedTime);

    /**
     * 根据用户id与商品id来查询购物车中数据
     * @param uid 用户id
     * @param pid 商品id
     * @return
     */
    Cart findByUidAndPid(Integer uid,Integer pid);

    /**
     * 根据用户uid查询VO类用户加入购物车的商品列表
     * @param uid
     * @return
     */
    List<CartVO> findVOByUid(Integer uid);

    /**
     * 根据购物车id逻辑删除某项购物车物品
     * @param cid
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer delCartItem(Integer cid, String modifiedUser, Date modifiedTime);

    /**
     * 根据购物车cid值查询商品
     * @param cid
     * @return
     */
    Cart findByCid(Integer cid);

    /**
     * 根据若干个购物车数据id查询详情的列表
     * @param cids
     * @return
     */
    List<CartVO> findVOByCids(Integer[] cids);
}
