package com.bistu.store.mapper;
import com.bistu.store.entity.User;
import com.bistu.store.vo.PriorityVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/** 用户模块的持久层接口 也就是dao层 与数据库进行交互的操作封装在该层*/
//接口依赖于实现类具体的实现
//impl存放相关实现类
    //service下放接口直接放在最外层
public interface UserMapper {
    /**
     * 插入用户的数据
     *
     * @param user 用户的数据
     * @return 受影响的行数（增删改均有有影响的行数作为返回值，根据返回值判断是否执行成功
     */
    Integer insert(User user);

    /**
     * 根据用户名查询用户数据
     *
     * @param username 用户名
     * @return若找到相应用户返回用户数据，如未找到返回null值
     */
    User findByUsername(String username);

    /**
     * 根据用户uid来修改用户密码
     * @param uid 用户id
     * @param password 用户输入新密码
     * @param modifiedUser 修改的执行者
     * @param modifiedTime 修改数据时间
     * @return 返回值为受影响行
     */
    Integer updatePasswordByYid(Integer uid, String password,
                                String modifiedUser, Date modifiedTime);

    /**
     * 根据用户id查询用户的数据
     * @param uid 用户id
     * @return 若找到则返回对象，反正返回null
     */
    User findByUid(Integer uid);

    /**
     * 更新用户的数据信息
     * @param user 用户数据
     * @return 返回值为受影响行
     */
    Integer updateInfoByUid(User user);

    /**
     * 根据用户uid值修改用户头像
     * @param uid
     * @param avatar
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    //Integer uid要注入到UserMapper.xml中的sql语句中的uid中 ，相同可以省略，不同可以长型匹配
    // @Param("SQL映射文件中#{}井号里面占位符的变量名")，当SQL语句中的占位符与映射的接口方法参数名不一致时，
    // 要将某个参数强行注入到某个占位符变量上时，可以使用@Param注解标注映射关系
    // 解决数据库与服务端名称不一致问题
    Integer updateAvatarByUid(@Param("uid") Integer uid,
                              @Param("avatar") String avatar,
                              @Param("modifiedUser") String modifiedUser,
                              @Param("modifiedTime")  Date modifiedTime);

    /**
     * 管理员查看所有用户数据
     * @return
     */
    //@Select("select uid,username,password,salt,phone,email,gender,avatar,is_delete,city,bankAccount,userType,is_pass FROM t_user")
    List<User> checkAllUsers();

    /**
     * 根据用户uid让用户审核通过
     * @param uid 用户id
     * @param isPass 是否通过
     * @param modifiedUser 修改的执行者
     * @param modifiedTime 修改数据时间
     * @return 返回值为受影响行
     */
    Integer passByAdmin(Integer uid, Integer isPass,
                                String modifiedUser, Date modifiedTime);

    /**
     * 根据用户uid让用户审核不通过
     * @param uid 用户id
     * @param isPass 是否通过
     * @param modifiedUser 修改的执行者
     * @param modifiedTime 修改数据时间
     * @return 返回值为受影响行
     */
    Integer rejectByAdmin(Integer uid, Integer isPass,
                        String modifiedUser, Date modifiedTime);

    /**
     * 根据用户uid添加钱
     * @param uid
     * @param purse
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer addMoney(Integer uid,Integer purse, String modifiedUser, Date modifiedTime);

    /**
     * 根据用户uid减钱
     * @param uid
     * @param purse
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer subMoney(Integer uid,Integer purse, String modifiedUser, Date modifiedTime);

    /**
     * 根据用户id查询余额
     * @param uid
     * @return
     */
    User getMoney(Integer uid);

    /**
     * 根据用户id修改商家等级
     * @param uid
     * @return
     */
    Integer addLevel(Integer uid,
                        @Param("modifiedUser") String modifiedUser,
                        @Param("modifiedTime")  Date modifiedTime);
    /**
     * 根据用户id修改商家等级
     * @param uid
     * @return
     */
    Integer subLevel(Integer uid,
                     @Param("modifiedUser") String modifiedUser,
                     @Param("modifiedTime")  Date modifiedTime);
    /**
     * 根据用户id查询商家等级
     * @return
     */
    List<User> getLevel();

    User getBidLevel(Integer uid);

    /**
     * 根据商家id查询等级 扣费用
     * @param uid
     * @return
     */
    PriorityVO getLevelByBid(Integer uid);

    /**
     * 为用户加积分
     * @param uid
     * @param point
     * @return
     */
    Integer addPoint(Integer uid,Integer point,
                     @Param("modifiedUser") String modifiedUser,
                     @Param("modifiedTime")  Date modifiedTime);

    /**
     * 为用户减积分
     * @param uid
     * @param point
     * @return
     */
    Integer subPoint(Integer uid,Integer point,
                     @Param("modifiedUser") String modifiedUser,
                     @Param("modifiedTime")  Date modifiedTime);


    /**
     * 根据用户id查询商家等级
     * @param uid
     * @return
     */
    User getPoint(Integer uid);
}

