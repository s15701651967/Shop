package com.bistu.store.mapper;
import com.bistu.store.entity.User;
import com.bistu.store.entity.UserPurse;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/** 用户模块的持久层接口 也就是dao层 与数据库进行交互的操作封装在该层*/
//接口依赖于实现类具体的实现
//impl存放相关实现类
    //service下放接口直接放在最外层
public interface UserPurseMapper {
    /**
     * 插入用户充值记录的数据
     *
     * @param user 用户充值记录
     * @return 受影响的行数（增删改均有有影响的行数作为返回值，根据返回值判断是否执行成功
     */
    Integer insert(UserPurse user);

    Integer inserts(UserPurse user);
    /**
     * 根据用户id查询用户的数据
     * @param uid 用户id
     * @return 若找到则返回对象，反正返回null
     */
    List<UserPurse> findByUid(Integer uid);

}