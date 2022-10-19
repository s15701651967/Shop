package com.bistu.store.service;

import com.bistu.store.entity.Comment;
import com.bistu.store.entity.User;

import java.util.Date;
import java.util.List;

/** 处理用户数据的业务层接口 */
//创建接口，在impl包下实现接口
public interface ICommentService {
    /**
     * 插入评论相关数据
     * @param comment
     * @return
     */
    void insert(Comment comment);

    /**
     * 商家根据商品id和用户id为用户提交评价
     * @param uid
     * @param bid
     * @param pid
     * @param oid
     * @param reply
     * @return
     */
    void updateByBid(Integer uid,Integer bid,Integer oid,Integer pid,String reply);

    /**
     * 用户根据uid查看所有评价信息
     * @param uid
     * @return
     */
    List<Comment> findByUid(Integer uid);

    /**
     * 商家根据bid也就是商家的uid查看所有对买家的回复
     * @param bid
     * @return
     */
    List<Comment> findByBid(Integer bid);

    /**
     * 根据商品号查看商品评价
     * @param pid
     * @return
     */
    List<Comment> findByPid(Integer pid);
}
