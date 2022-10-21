package com.bistu.store.controller;

import com.bistu.store.entity.Address;
import com.bistu.store.entity.BaseEntity;
import com.bistu.store.entity.User;
import com.bistu.store.service.IAddressService;
import com.bistu.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.util.List;

@RequestMapping("address")//通过它来指定控制器可以处理哪些URL请求
@RestController
public class AddressController extends BaseController {
    @Autowired
    private IAddressService addressService;//依赖于底层的业务接口

    @RequestMapping("add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session){
        Integer uid=getuidFromSession(session);
        String username=getUsernameFromSession(session);
        addressService.addNewAddress(uid,username,address);
        return new JsonResult<>(OK);
    }

    @RequestMapping({"","/"})
    public JsonResult<List<Address>> getByUid(HttpSession session){
        Integer uid=getuidFromSession(session);
        List<Address> data=addressService.getByUid(uid);
        return new JsonResult<>(OK,data);
    }

    @RequestMapping({"{uid}/admin"})
    public JsonResult<List<Address>> getByUidAdmin(@PathVariable("uid") Integer uid,HttpSession session){
//        Integer uid=getuidFromSession(session);
        List<Address> data=addressService.getByUid(uid);
        return new JsonResult<>(OK,data);
    }

    //Restful风格请求编写
    @RequestMapping("{aid}/set_default")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid,HttpSession session){
        addressService.setDefault(aid,getuidFromSession(session),getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }

//    @RequestMapping("{uid}/set_default")
//    public JsonResult<Void> setDefaultAdmin(@PathVariable("uid") Integer uid,HttpSession session){
//        addressService.setDefault(uid,getuidFromSession(session),getUsernameFromSession(session));
//        return new JsonResult<>(OK);
//    }

    @RequestMapping("{aid}/delete")
    public JsonResult<Void> delete(@PathVariable("aid") Integer aid,HttpSession session){
        addressService.delete(aid,getuidFromSession(session),getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }

   @RequestMapping("{aid}/change_info")
    public JsonResult<Void> updateInfoByAid(@PathVariable("aid") Integer aid,Address address,HttpSession session){
        String username=getUsernameFromSession(session);
        addressService.updateInfoByAid(address.getAid(),username,address);
        return new JsonResult<>(OK);
    }
}
