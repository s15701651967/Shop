package com.bistu.store.controller;

import com.bistu.store.entity.Product;
import com.bistu.store.entity.User;
import com.bistu.store.service.IProductService;
import com.bistu.store.service.ex.ProductNotFoundException;
import com.bistu.store.util.JsonResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController extends BaseController {
    @Autowired
    private IProductService productService;

    @RequestMapping("hot_list")
    public JsonResult<List<Product>> getHotList() {
        List<Product> data = productService.findHotList();
        return new JsonResult<List<Product>>(OK, data);
    }

    @RequestMapping("mark_list")
    public JsonResult<List<Product>> getMarkList() {
        List<Product> data = productService.findMarkList();
        return new JsonResult<List<Product>>(OK, data);
    }

    @RequestMapping("desc_list")
    public JsonResult<List<Product>> getDescList() {
        List<Product> data = productService.findDescList();
        return new JsonResult<List<Product>>(OK, data);
    }

    @RequestMapping("asc_list")
    public JsonResult<List<Product>> getAscList() {
        List<Product> data = productService.findAscList();
        return new JsonResult<List<Product>>(OK, data);
    }

    @GetMapping("{id}/details")
    public JsonResult<Product> getById(@PathVariable("id") Integer id) {
        // 调用业务对象执行获取数据
        Product data = productService.findById(id);
        // 返回成功和数据
        return new JsonResult<Product>(OK, data);
    }

    @RequestMapping("title")
    public JsonResult<List<Product>> getByTitle(String title) {
        try {
            List<Product> data = productService.findByTitle(title);
            System.out.println(data);
            // 返回成功和数据
            return new JsonResult<List<Product>>(OK, data);

        } catch (Exception e) {
            throw new ProductNotFoundException("功能失败");
        }
    }

    @RequestMapping("all")
    public JsonResult<List<Product>> findAllProducts() {
        List<Product> data = productService.findAllProducts();
        return new JsonResult<List<Product>>(OK, data);
    }

    @RequestMapping("all_pass_products")
    public JsonResult<List<Product>> findAllPassProducts() {
        List<Product> data = productService.findAllPassProducts();
        return new JsonResult<List<Product>>(OK, data);
    }

    @RequestMapping("{bid}/all_bid")
    public JsonResult<List<Product>> findAllProductsByBid(@PathVariable("bid") Integer bid) {
        List<Product> data = productService.findAllProductsByBid(bid);
        return new JsonResult<List<Product>>(OK, data);
    }

    @RequestMapping("{bid}/all_pass_bid")
    public JsonResult<List<Product>> findPassProductsByBid(@PathVariable("bid") Integer bid) {
        List<Product> data = productService.findPassProductsByBid(bid);
        return new JsonResult<List<Product>>(OK, data);
    }

    @RequestMapping("insert")
    public JsonResult<Void> insert(Product product, HttpSession session) {
        JsonResult<Void> result = new JsonResult<>();
        Integer uid = getuidFromSession(session);
        product.setBusinessId(uid);
        productService.insert(product);
        return new JsonResult<>(OK);
    }

    @RequestMapping("{id}/pass_product")
    public JsonResult<Void> passUserByUid(@PathVariable("id") Integer id) {
        productService.passProjectById(id);
        return new JsonResult<>(OK);
    }

    @RequestMapping("{id}/reject_product")
    public JsonResult<Void> rejectUserByUid(@PathVariable("id") Integer id) {
        productService.rejectProjectById(id);
        return new JsonResult<>(OK);
    }

    @RequestMapping("{id}/upByBusiness")
    public JsonResult<Void> passProjectByBusiness(@PathVariable("id") Integer id) {
        productService.passProjectByBusiness(id);
        return new JsonResult<>(OK);
    }

    @RequestMapping("{id}/underByBusiness")
    public JsonResult<Void> rejectProjectByBusiness(@PathVariable("id") Integer id) {
        productService.rejectProjectByBusiness(id);
        return new JsonResult<>(OK);
    }


}
