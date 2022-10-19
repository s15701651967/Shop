package com.bistu.store.controller;

import com.bistu.store.entity.District;
import com.bistu.store.service.IDistrictService;
import com.bistu.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("district")
@RestController
public class DistrictController extends BaseController{
    @Autowired
    private IDistrictService districtService;

    //以district请求均被拦截到getByParent()方法里
    @RequestMapping({"","/"})//所有以district请求均被拦截到getByParent()
    public JsonResult<List<District>> getByParent(String parent){
        List<District> data=districtService.getByParent(parent);
        return new JsonResult<>(OK,data);
    }
}
