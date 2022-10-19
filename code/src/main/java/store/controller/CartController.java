package com.bistu.store.controller;

import com.bistu.store.service.ICartService;
import com.bistu.store.util.JsonResult;
import com.bistu.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("cart")
@RestController
public class CartController extends BaseController {
    @Autowired
    private ICartService cartService;

    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid, Integer amount, HttpSession session){
        cartService.addToCart(getuidFromSession(session),pid,amount,getUsernameFromSession(session));
    return new JsonResult<>(OK);
    }

    @RequestMapping({"","/"})
    public JsonResult<List<CartVO>> getVOByUid(HttpSession session){
        List<CartVO> data= cartService.getVOByUid(getuidFromSession(session));
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("{cid}/del")
    public JsonResult<Void> delCartItem(@PathVariable("cid")Integer cid, HttpSession session){
        cartService.delCartItem(cid);
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("{cid}/num/add")
    public JsonResult<Integer> addNum(@PathVariable("cid")Integer cid, HttpSession session){
        Integer data=  cartService.addNum(cid,getuidFromSession(session),getUsernameFromSession(session));
        return new JsonResult<Integer>(OK,data);
    }

    @RequestMapping("{cid}/num/reduce")
    public JsonResult<Integer> reduceNum(@PathVariable("cid") Integer cid, HttpSession session) {
        // 从Session中获取uid和username
        Integer data = cartService.reduceNum(cid,getuidFromSession(session),getUsernameFromSession(session));
        // 返回成功
        return new JsonResult<Integer>(OK, data);
    }

    @RequestMapping("list")
    public JsonResult<List<CartVO>> getVOByCid(Integer[] cids, HttpSession session){
        List<CartVO> data= cartService.getVOByCid(getuidFromSession(session),cids);
        return new JsonResult<>(OK,data);
    }
}
