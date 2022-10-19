package com.bistu.store.mapper;

import com.bistu.store.entity.Order;
import com.bistu.store.entity.OrderItem;
import com.bistu.store.vo.CartVO;
import com.bistu.store.vo.OrderVO;
import com.bistu.store.vo.PayVO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

/**订单持久层接口*/
public interface OrderMapper {

    /**
     * 插入订单数据
     * @param order 订单数据
     * @return 受影响行数
     */
    Integer insertOrder(Order order);

    Integer updateStatus(Integer oid,Date payTime);

    /**
     * 插入订单项的数据
     * @param orderItem 订单项的数据
     * @return 受影响行数
     */
    Integer insertOrderItem(OrderItem orderItem);

    /**
     * 根据订单id查询详情的列表
     * @param oid
     * @return
     */
    List<OrderVO> findVOByOid(Integer oid);

    Order findOrderByOid(Integer oid);

    /**
     * 根据用户id查询详情的列表
     * @param uid
     * @return
     */
    List<OrderVO> findVOByUid(Integer uid);


    List<OrderVO> findBVOByUid(Integer uid);


    /**
     *
     *根据用户id 订单id查询详情的列表
     *
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

    Integer updatePStatus(Integer oid,Integer pid,Integer pStatus);

    Integer updatePriority(Integer oid,Integer pid,Integer num);

    Integer updateSubPriority(Integer oid,Integer pid,Integer num);

    List<PayVO> businessOrder(Integer businessId);
//    List<PayVO> businessOrder(Integer uid);

}
