package com.bistu.store.service;

import com.bistu.store.entity.Product;

import java.util.Date;
import java.util.List;
/* 处理商品数据的业务层接口 */
public interface IProductService  {
    /**
     * 查询热销商品的前四名
     * @return 热销商品前四名的集合
     */
    List<Product> findHotList();

    /**
     * 查询评分最高商品前四名
     * @return 评分最高商品前四名
     */
    List<Product> findMarkList();

    /**
     * 查询最贵的前四件商品
     * @return 最贵的前四件商品
     */
    List<Product> findDescList();

    /**
     * 查询最便宜的前四件商品
     * @return 最便宜的前四件商品
     */
    List<Product> findAscList();

    /**
     * 根据商品id查询商品详情
     * @param id 商品id
     * @return
     */
    Product findById(Integer id);

    /**
     * 根据商品名称查询商品详情
     * @param title 商品名称
     * @return 商品详情
     */
    List<Product> findByTitle(String title);

    /**
     * 查询所有商品
     * @return 商品详情
     */
    List<Product> findAllProducts();
    /**
     * 查询所有在售商品
     * @return 商品详情
     */
    List<Product> findAllPassProducts();

    /**
     * 查询商家所有商品
     * @return
     */
    List<Product> findAllProductsByBid(Integer bid);

    /**
     * 查询商家所有在售商品
     * @return
     */
    List<Product> findPassProductsByBid(Integer bid);

    /**
     * 新增商品
     * @param product
     */
    void insert(Product product);

    /**
     * 根据商品id让商家发布的商品审核通过
     * @param id
     */
    void passProjectById(Integer id);

    /**
     * 根据商品id让商家发布的商品审核不通过
     * @param id
     */
    void rejectProjectById(Integer id);

    /**
     * 根据商品id让商家发布的商品上架
     * @param id
     */
    void passProjectByBusiness(Integer id);

    /**
     * 根据商品id让商家发布的商品下架
     * @param id
     */
    void rejectProjectByBusiness(Integer id);

}
