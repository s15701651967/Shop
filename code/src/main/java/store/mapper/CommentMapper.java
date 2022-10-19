package com.bistu.store.mapper;
import com.bistu.store.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/** 用户模块的持久层接口 也就是dao层 与数据库进行交互的操作封装在该层*/
//接口依赖于实现类具体的实现
//impl存放相关实现类
    //service下放接口直接放在最外层
public interface CommentMapper {
    /**
     * 插入评论相关数据
     * @param comment
     * @return
     */
    Integer insert(Comment comment);

    /**
     * 商家根据商品id和用户id为用户提交评价
     * @param uid
     * @param bid
     * @param pid
     * @param oid
     * @param reply
     * @return
     */
    Integer updateByBid(Integer uid,Integer bid,Integer oid,Integer pid,String reply);

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

