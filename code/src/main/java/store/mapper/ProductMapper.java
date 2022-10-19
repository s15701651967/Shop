package com.bistu.store.mapper;

import com.bistu.store.entity.Cart;
import com.bistu.store.entity.Product;
import com.bistu.store.entity.User;

import java.util.Date;
import java.util.List;
/** 处理商品数据的持久层接口 */
public interface ProductMapper  {

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

    /* 根据商品id查询商品详情*/
    Product findById(Integer id);

    /**
     * 根据商品名查询商品详情
     * @return
     */
    List<Product> findByTitle(String title);

    /**
     * 查询所有商品
     * @return
     */
    List<Product> findAllProducts();

    /**
     * 查询所有在售商品
     * @return
     */
    List<Product> findAllPassProducts();

    /**
     * 查询所有商家商品
     * @return
     */
    List<Product> findAllProductsByBid(Integer bid);

    /**
     * 查询所有商家在售商品
     * @return
     */
    List<Product> findPassProductsByBid(Integer bid);


    /**
     * 发布商品
     * @param product
     * @return
     */
    Integer insert(Product product);



    /**
     * 根据商品id让商家发布的商品审核通过
     * @param id 用户id
     * @param status 是否通过
     * @param modifiedUser 修改的执行者
     * @param modifiedTime 修改数据时间
     * @return 返回值为受影响行
     */
    Integer passByAdmin(Integer id, Integer status,
                        String modifiedUser, Date modifiedTime);

    /**
     * 根据商品id让商家发布的商品审核不通过
     * @param id 用户id
     * @param status 是否通过
     * @param modifiedUser 修改的执行者
     * @param modifiedTime 修改数据时间
     * @return 返回值为受影响行
     */
    Integer rejectByAdmin(Integer id, Integer status,
                          String modifiedUser, Date modifiedTime);

    /**
     * 根据商品id让商家发布的商品审核通过
     * @param id 用户id
     * @param status 是否通过
     * @param modifiedUser 修改的执行者
     * @param modifiedTime 修改数据时间
     * @return 返回值为受影响行
     */
    Integer passByBusiness(Integer id, Integer status,
                        String modifiedUser, Date modifiedTime);

    /**
     * 根据商品id让商家发布的商品审核不通过
     * @param id 用户id
     * @param status 是否通过
     * @param modifiedUser 修改的执行者
     * @param modifiedTime 修改数据时间
     * @return 返回值为受影响行
     */
    Integer rejectByBusiness(Integer id, Integer status,
                          String modifiedUser, Date modifiedTime);

}
