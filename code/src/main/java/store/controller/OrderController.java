package com.bistu.store.controller;

import com.bistu.store.entity.Order;
import com.bistu.store.service.IOrderService;
import com.bistu.store.util.JsonResult;
import com.bistu.store.vo.CartVO;
import com.bistu.store.vo.OrderVO;
import com.bistu.store.vo.PayVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RequestMapping("order")
@RestController
public class OrderController extends BaseController {
    @Autowired
    private IOrderService orderService;
    @RequestMapping("create")
    public JsonResult<Order> create(Integer aid, Integer[] cids, HttpSession session){
        Integer uid=getuidFromSession(session);
        String username=getUsernameFromSession(session);
        Order data= orderService.create(aid,cids,uid,username);
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("create1")
    public JsonResult<Order> create1(Integer aid, Integer[] cids, HttpSession session){
        Integer uid=getuidFromSession(session);
        String username=getUsernameFromSession(session);
        Order data= orderService.create1(aid,cids,uid,username);
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("pay")
    public JsonResult<Order> pay(Integer oid,HttpSession session){
        orderService.updateStatus(oid,new Date());
        return new JsonResult<>(OK);
    }


    @RequestMapping("listo")
    public JsonResult<List<OrderVO>> findVOByOid(Integer oid){
        List<OrderVO> data= orderService.findVOByOid(oid);
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("listu")
    public JsonResult<List<OrderVO>> findVOByUid(HttpSession session){
        List<OrderVO> data= orderService.findVOByUid(getuidFromSession(session));
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("listbu")
    public JsonResult<List<OrderVO>> findBVOByUid(HttpSession session){
        List<OrderVO> data= orderService.findBVOByUid(getuidFromSession(session));
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("listou")
    public JsonResult<List<OrderVO>> findVOByOUid(Integer oid,Integer uid){
        List<OrderVO> data= orderService.findVOByOUid(oid,uid);
        return new JsonResult<>(OK,data);
    }
//    @RequestMapping({"","/"})
//    public JsonResult<List<OrderVO>> findVOByOid(Integer oid){
//        List<OrderVO> data= orderService.findVOByOid(oid);
//        return new JsonResult<>(OK,data);
//    }

    @RequestMapping("p_status")
    public JsonResult<OrderVO> findVOByOUPid(Integer oid,Integer pid,HttpSession session){
        Integer uid=getuidFromSession(session);
        OrderVO data= orderService.findVOByOUPid(oid,pid);
        System.out.println(data);
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("status")
    public JsonResult<Order> findOrderByOid(Integer oid){
        Order data= orderService.findOrderByOid(oid);
        System.out.println(data);
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("business_search")
    public JsonResult<List<PayVO>> paySearch(Integer pid){
        List<PayVO> data= orderService.paySearch(pid);
        System.out.println(data);
        return new JsonResult<List<PayVO>>(OK,data);
    }

    @RequestMapping("business_update")
    public JsonResult<Void> updatePStatus(Integer oid,Integer pid,Integer pStatus){
        orderService.updatePStatus(oid, pid, 1);
        System.out.println("货物状态号："+1);
        return new JsonResult<>(OK);
    }

    @RequestMapping("business_addPriority")
    public JsonResult<Void> updatePriority(Integer oid,Integer pid,Integer num){
        orderService.updatePriority(oid, pid, num);
        System.out.println("销量增长："+num);
        return new JsonResult<>(OK);
    }

    @RequestMapping("business_subPriority")
    public JsonResult<Void> updateSubPriority(Integer oid,Integer pid,Integer num){
        orderService.updateSubPriority(oid, pid, num);
        System.out.println("销量减少："+num);
        return new JsonResult<>(OK);
    }

    @RequestMapping("business_order")
    public JsonResult<List<PayVO>> businessOrder(Integer businessId){
        List<PayVO> data=  orderService.businessOrder(businessId);
        System.out.println("商家uid："+businessId);
        System.out.println("商家订单："+data);

        return new JsonResult<List<PayVO>>(OK,data);
    }

    @RequestMapping("receive_product")
    public JsonResult<Void> receivePStatus(Integer oid,Integer pid,Integer pStatus){
        orderService.updatePStatus(oid, pid, 3);
        System.out.println("货物状态号："+1);
        return new JsonResult<>(OK);
    }

    // 5申请退货 6退货完成 7退货驳回
    @RequestMapping("return_product")
    public JsonResult<Void> returnPStatus(Integer oid,Integer pid,Integer pStatus){
        orderService.updatePStatus(oid, pid, 5);
        System.out.println("货物状态号："+5);
        return new JsonResult<>(OK);
    }

    @RequestMapping("business_return")
    public JsonResult<Void> businessReturn(Integer oid,Integer pid,Integer pStatus){
        orderService.updatePStatus(oid, pid, 6);
        System.out.println("货物状态号："+6);
        return new JsonResult<>(OK);
    }

    @RequestMapping("business_refuse")
    public JsonResult<Void> businessRefuse(Integer oid,Integer pid,Integer pStatus){
        orderService.updatePStatus(oid, pid, 7);
        System.out.println("货物状态号："+7);
        return new JsonResult<>(OK);
    }
}
