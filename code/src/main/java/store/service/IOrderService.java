package com.bistu.store.service;

import com.bistu.store.entity.Order;
import com.bistu.store.vo.OrderVO;
import com.bistu.store.vo.PayVO;

import java.util.Date;
import java.util.List;

/** 处理订单和订单数据的业务层接口 */
public interface IOrderService {
    /*创建订单*/
    Order create(Integer aid,Integer[] cids, Integer uid, String username);

    /*创建订单*/
    Order create1(Integer aid,Integer[] cids, Integer uid, String username);


    /**
     * 根据订单id查询详情的列表
     * @param oid
     * @return
     */
    List<OrderVO> findVOByOid(Integer oid);

    Order findOrderByOid(Integer oid);

    void updateStatus(Integer oid, Date payTime);

    /**
     * 根据用户id 查询详情的列表
     * @param uid 用户id
     * @return
     */
    List<OrderVO> findVOByUid(Integer uid);

    /**
     * 根据用户id查询商家id
     * @param uid
     * @return
     */
    List<OrderVO> findBVOByUid(Integer uid);

    /**
     * 根据用户id 订单id查询详情的列表
     * @param oid 订单id
     * @param uid 用户id
     * @return
     */
    List<OrderVO> findVOByOUid(Integer oid,Integer uid);



    /**
     * 根据订单id 用户id 商品id查询用户下单的某个商品
     * @param oid
     * @param pid
     * @return
     */
    OrderVO findVOByOUPid(Integer oid,Integer pid);

    List<PayVO> paySearch(Integer pid);

    void updatePStatus(Integer oid,Integer pid,Integer pStatus);

    void updatePriority(Integer oid,Integer pid,Integer num);

    void updateSubPriority(Integer oid,Integer pid,Integer num);

    List<PayVO> businessOrder(Integer businessId);

}
