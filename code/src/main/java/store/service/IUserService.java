package com.bistu.store.service;

import com.bistu.store.entity.User;
import com.bistu.store.vo.PriorityVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/** 处理用户数据的业务层接口 */
//创建接口，在impl包下实现接口
public interface IUserService {
    /**
     * 用户注册方法
     * @param user 用户数据
     */
    void reg(User user);//定义一个user类型参数列表作为对象


    /**
     * 用户登录方法
     * @param username  用户名
     * @param password  用户密码
     * @return 当前匹配的用户数据 无则返回NULL
     */
     User login(String username,
                String password);

    /**
     * 修改密码
     * @param uid 用户id
     * @param username 用户名
     * @param oldPassword 老密码
     * @param newPassword 新密码
     */
     void changePassword(Integer uid,
                         String username,
                         String oldPassword,
                         String newPassword);

    /**
     * 根据用户的id查询用户数据
     * @param uid 用户id
     * @return 用户的数据
     */
     User getByUid(Integer uid);

    /**
     * 更新用户的数据
     * @param uid 用户id
     * @param username 用户名称
     * @param user 用户对象的数据
     */
     void changeInfo(Integer uid,
                     String username,
                     User user);

    /**
     * 修改用户头像
     * @param uid 用户id
     * @param avatar 用户头像路径
     * @param username 用户名称
     */
     void changeAvatar(Integer uid,
                       String avatar,
                       String username);

    /**
     * 管理员查看所有用户数据
     * @return
     */
    List<User> checkAllUsers();


    /**
     * 根据用户uid让用户审核通过
     * @param uid
     */
    void passUserByUid(Integer uid);

    /**
     * 根据用户uid让用户审核不通过
     * @param uid
     */
    void rejectUserByUid(Integer uid);

    /**
     * 根据用户uid添加钱
     * @param uid
     * @param purse
     * @param modifiedUser
     * @param modifiedTime
     */
    void addMoney(Integer uid,Integer purse, String modifiedUser, Date modifiedTime);

    /**
     * 根据用户uid减钱
     * @param uid
     * @param purse
     * @param modifiedUser
     * @param modifiedTime
     */
    void subMoney(Integer uid,Integer purse, String modifiedUser, Date modifiedTime);

    /**
     * 查询用户余额
     * @param uid
     * @return
     */
    User getMoney(Integer uid);

    /**
     * 根据用户id修改商家等级
     * @param uid
     * @return
     */
    void  addLevel(Integer uid,
                      String modifiedUser, Date modifiedTime);

    /**
     * 根据用户id修改商家等级
     * @param uid
     * @return
     */
    void  subLevel(Integer uid,
                   String modifiedUser, Date modifiedTime);

    /**
     * 根据用户id查询商家等级
     * @return
     */
    List<User> getLevel();

    User getBidLevel(Integer uid);

    PriorityVO getLevelByBid(Integer uid);

    /**
     * 为用户加积分
     * @param uid
     * @param point
     * @return
     */
    void addPoint(Integer uid,Integer point,
                     String modifiedUser, Date modifiedTime);

    /**
     * 为用户加积分
     * @param uid
     * @param point
     * @return
     */
    void subPoint(Integer uid,Integer point,
                     String modifiedUser, Date modifiedTime);

    /**
     * 根据用户id查询商家等级
     * @param uid
     * @return
     */
    User getPoint(Integer uid);
}
