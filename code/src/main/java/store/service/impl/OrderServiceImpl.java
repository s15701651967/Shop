package com.bistu.store.service.impl;

import com.bistu.store.entity.Address;
import com.bistu.store.entity.Order;
import com.bistu.store.entity.OrderItem;
import com.bistu.store.mapper.OrderMapper;
import com.bistu.store.service.IAddressService;
import com.bistu.store.service.ICartService;
import com.bistu.store.service.IOrderService;
import com.bistu.store.service.ex.InsertException;
import com.bistu.store.vo.CartVO;
import com.bistu.store.vo.OrderVO;
import com.bistu.store.vo.PayVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
/** 处理订单和订单数据的业务层实现类 */
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private ICartService cartService;

    @Override
    public Order create(Integer aid, Integer[] cids, Integer uid, String username) {
        // 根据cids查询所勾选的购物车列表中的数据 即将下单的列表
        List<CartVO> carts = cartService.getVOByCid(uid, cids);
        // 计算所有商品的总价
        long totalPrice = 0L;
        for (CartVO cart : carts) {
            totalPrice += cart.getRealPrice() * cart.getNum();
        }
        // 查询收货地址数据
        Address address = addressService.getByAid(aid, uid);

        Order order = new Order();
        order.setUid(uid);
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());
        //是否支付 总价
        order.setStatus(0);
        order.setTotalPrice(totalPrice);
        // 补全数据：下单时间
        order.setOrderTime(new Date());
        // 补全数据：日志
        order.setCreatedUser(username);
        order.setCreatedTime(new Date());
        order.setModifiedUser(username);
        order.setModifiedTime(new Date());

        Integer rows= orderMapper.insertOrder(order);
        if(rows!=1){
            throw new InsertException("插入数据异常");
        }

        //创建订单详细数据项 遍历carts，循环插入订单商品数据
        for(CartVO cart:carts){
            //创建订单详细数据项 订单商品数据
            OrderItem orderItem=new OrderItem();
            orderItem.setOid(order.getOid());
            // 补全数据：pid, title, image, price, num
            orderItem.setPid(cart.getPid());
            orderItem.setTitle(cart.getTitle());
            orderItem.setImage(cart.getImage());
            orderItem.setPrice(cart.getRealPrice());
            orderItem.setNum(cart.getNum());
            // 补全数据：4项日志
            orderItem.setCreatedUser(username);
            orderItem.setCreatedTime(new Date());
            orderItem.setModifiedUser(username);
            orderItem.setModifiedTime(new Date());
            //插入数据操作
            Integer  rows2= orderMapper.insertOrderItem(orderItem);
            if(rows2!=1){
                throw new InsertException("插入订单商品数据时出现未知错误，请联系系统管理员");
            }
        }
        return order;
    }

    @Override
    public Order create1(Integer aid, Integer[] cids, Integer uid, String username) {
        // 根据cids查询所勾选的购物车列表中的数据 即将下单的列表
        List<CartVO> carts = cartService.getVOByCid(uid, cids);
        // 计算所有商品的总价
        long totalPrice = 0L;
        for (CartVO cart : carts) {
            totalPrice += cart.getRealPrice() * cart.getNum();
        }
        // 查询收货地址数据
        Address address = addressService.getByAid(aid, uid);

        Order order = new Order();
        order.setUid(uid);
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());
        //是否支付 总价
        order.setStatus(1);
        order.setTotalPrice(totalPrice);
        // 补全数据：下单时间
        order.setOrderTime(new Date());
        order.setPayTime(new Date());
        // 补全数据：日志
        order.setCreatedUser(username);
        order.setCreatedTime(new Date());
        order.setModifiedUser(username);
        order.setModifiedTime(new Date());

        Integer rows= orderMapper.insertOrder(order);
        if(rows!=1){
            throw new InsertException("插入数据异常");
        }

        //创建订单详细数据项 遍历carts，循环插入订单商品数据
        for(CartVO cart:carts){
            //创建订单详细数据项 订单商品数据
            OrderItem orderItem=new OrderItem();
            orderItem.setOid(order.getOid());
            // 补全数据：pid, title, image, price, num
            orderItem.setPid(cart.getPid());
            orderItem.setTitle(cart.getTitle());
            orderItem.setImage(cart.getImage());
            orderItem.setPrice(cart.getRealPrice());
            orderItem.setNum(cart.getNum());
            // 补全数据：4项日志
            orderItem.setCreatedUser(username);
            orderItem.setCreatedTime(new Date());
            orderItem.setModifiedUser(username);
            orderItem.setModifiedTime(new Date());
            //插入数据操作
            Integer  rows2= orderMapper.insertOrderItem(orderItem);
            if(rows2!=1){
                throw new InsertException("插入订单商品数据时出现未知错误，请联系系统管理员");
            }
        }
        return order;
    }

    @Override
    public List<OrderVO> findVOByOid(Integer oid) {
        List<OrderVO> list=orderMapper.findVOByOid(oid);

        return list;
    }

    @Override
    public Order findOrderByOid(Integer oid) {
        Order order=orderMapper.findOrderByOid(oid);
        return order;
    }

    @Override
    public void updateStatus(Integer oid, Date payTime) {
        orderMapper.updateStatus(oid,new Date());
    }

    @Override
    public List<OrderVO> findVOByUid(Integer uid) {
        List<OrderVO> list=orderMapper.findVOByUid(uid);

        return list;
    }

    @Override
    public List<OrderVO> findBVOByUid(Integer uid) {
        List<OrderVO> list=orderMapper.findBVOByUid(uid);

        return list;
    }

    @Override
    public List<OrderVO> findVOByOUid(Integer oid, Integer uid) {
        List<OrderVO> list=orderMapper.findVOByOUid(oid,uid);

        return list;
    }

    @Override
    public OrderVO findVOByOUPid(Integer oid, Integer pid) {
        OrderVO order=orderMapper.findVOByOUPid(oid,pid);

        return order;
    }

    @Override
    public List<PayVO> paySearch(Integer pid) {

        List<PayVO> list=orderMapper.paySearch(pid);

        return list;
    }

    @Override
    public void updatePStatus(Integer oid, Integer pid, Integer pStatus) {
        Integer rows=orderMapper.updatePStatus(oid,pid,pStatus);
        if(rows!=1){
            System.out.println("货物状态异常！");
        }
    }

    @Override
    public void updatePriority(Integer oid, Integer pid, Integer num) {
        Integer rows=orderMapper.updatePriority(oid,pid,num);
        if(rows!=1){
            System.out.println("货物状态异常！");
        }
    }

    @Override
    public void updateSubPriority(Integer oid, Integer pid, Integer num) {
        Integer rows=orderMapper.updateSubPriority(oid,pid,num);
        if(rows!=1){
            System.out.println("货物状态异常！");
        }
    }

    @Override
    public List<PayVO> businessOrder(Integer businessId) {
        List<PayVO> list=orderMapper.businessOrder(businessId);

        return list;
    }
}
