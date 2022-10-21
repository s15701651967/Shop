package com.bistu.store.service.impl;

import com.bistu.store.entity.User;
import com.bistu.store.mapper.UserMapper;
import com.bistu.store.service.IUserService;
import com.bistu.store.service.ex.*;
import com.bistu.store.vo.PriorityVO;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/** 处理用户数据的业务层实现类即用户模块业务层实现类 */
@Service//标记当前类是一个service类，加上该注解会将当前类自动注入到spring容器中,将当前类的对象交给Spring来管理，自动创建对象以及对象的维护

public class UserServiceImpl implements IUserService {
    //调用mapper层方法再将User对象传递下去
    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {//用户注册
        //通过user参数来获取传递过来的username
        String username=user.getUsername();
        //调用findByUsername(username)判断用户是否被注册过
        User result=userMapper.findByUsername(username);//做一个底层的查询
        //判断结果集是否为null,若不为null则抛出用户名被占用的异常
        if (result !=null){
            //抛出异常
            throw new UsernameDuplicateException("用户名被占用");
        }

        //密码加密处理的实现：md5算法
        //串+password+串 交给md5算法进行加密，连续加载3次
        //串成为盐值  盐值+password+盐值   盐值为一个随机的字符串
        String oldPassword=user.getPassword();
        //获取盐值（随机生成一个盐值）
        String salt=UUID.randomUUID().toString().toUpperCase();//全大写盐值
        user.setSalt(salt);//补全数据库中盐值的记录
        //将密码和盐值作为一个整体进行加密处理
        String md5Password=getMD5Password(oldPassword,salt);
        //将加密之后的密码重新补全设置到user对象中
        user.setPassword(md5Password);

        //补全数据：is_delete设置为0
        user.setIsDelete(0);

        //补全数据：4个日志字段信息
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date =new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        //执行注册业务功能的实现(rows==1)
        Integer rows=userMapper.insert(user);
        if(rows!=1){
            throw new InsertException("在用户注册过程中产生未知的异常");
        }
    }

    @Override
    public User login(String username, String password) {
        //根据用户名称查询用户数据是否存在，不存在则抛出异常
        //调用mapper层中的findByUsername方法
        User result=userMapper.findByUsername(username);
        if (result==null){
            throw new UserNotFoundException("用户数据不存在");
        }
        //检测用户密码是否匹配
        //1.先获取数据库中加密后的密码
        String oldPassword=result.getPassword();
        //2.与用户传递过来的密码进行比较
        //2.1先获取盐值：上一次在注册中自动生成的盐值
        String salt=result.getSalt();
        //2.2将用户密码按相同md5算法的规则进行加密
        String newMd5Password=getMD5Password(password,salt);
        //将密码进行比较
        if(!newMd5Password.equals(oldPassword)){
            throw new PassWordNotMatchException("用户密码错误");
        }
        //判断is_delete字段的值是否为1表示被标记为删除
//        if(result.getIsDelete()==1){
//            throw new UserNotFoundException("用户数据不存在");
//        }

        //调用mapper层的findByUsername来查询用户数据,new一个user对象，相当于对要传输的数据进行了压缩提升了系统性能
        User user=new User();
        user.setUid(result.getUid());
        //返回有用户的头像
        user.setAvatar(result.getAvatar());
        user.setUsername(result.getUsername());
        user.setUserType(result.getUserType());
        user.setIsDelete(result.getIsDelete());
        user.setIsPass(result.getIsPass());
        //将当前用户数据返回，返回的数据是为了辅助其他页面做数据展示（user
        return user;
    }

    @Override
    public void changePassword(Integer uid, String username,
                               String oldPassword, String newPassword) {
        User result=userMapper.findByUid(uid);
        if(result==null||result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }

        //原始密码与数据库中密码比较
        String oldMd5Password=getMD5Password(oldPassword,result.getSalt());
        if(!result.getPassword().equals(oldMd5Password)){
            throw new PassWordNotMatchException("密码错误");
        }

        //将新密码设置到数据库中，将新的密码先加密再更新
        String newMd5Password= getMD5Password(newPassword,result.getSalt());
        Integer rows= userMapper.updatePasswordByYid(uid,newMd5Password,username,new Date());
        if(rows!=1){
            throw new UpdateException("更新数据时产生未知的异常");
        }
    }

    @Override
    public User getByUid(Integer uid) {
            User result = userMapper.findByUid(uid);
            if(result==null||result.getIsDelete()==1){
                throw new UserNotFoundException("用户数据不存在");
            }
            User user=new User();
            user.setUid(result.getUid());
            user.setUsername(result.getUsername());
            user.setPhone(result.getPhone());
            user.setEmail(result.getEmail());
            user.setGender(result.getGender());
            user.setLevel(result.getLevel());
            user.setPoint(result.getPoint());

            return user;
    }

    /**
     * User对象中的数据phone\email\gender,手动将用户 u i d和 user name封装到user对象中
     * @param uid 用户id
     * @param username 用户名称
     * @param user 用户对象的数据
     */
    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result = userMapper.findByUid(uid);
        if(result==null||result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        user.setUid(uid);
//        user.setUsername(username);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        Integer rows=userMapper.updateInfoByUid(user);
        if(rows!=1){
            throw new UpdateException("更新时数据产生位置异常");
        }
    }

    /**
     * 业务层更新用户头像方法
     * @param uid 用户id
     * @param avatar 用户头像路径
     * @param username 用户名称
     */
    @Override
    public void changeAvatar(Integer uid, String avatar, String username) {
        //查询当前用户数据是否存在
        User result=userMapper.findByUid(uid);
        if(result==null||result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        Integer rows=userMapper.updateAvatarByUid(uid,avatar,
                username,new Date());
        if(rows!=1){
            throw new UpdateException("更新用户头像时产生未知的异常");
        }
    }

    @Override
    public List<User> checkAllUsers() {
        List<User> user=userMapper.checkAllUsers();
        return user;
    }

    @Override
    public void passUserByUid(Integer uid) {
        User result=userMapper.findByUid(uid);
        userMapper.passByAdmin(result.getUid(),1,"root",new Date());
    }

    @Override
    public void rejectUserByUid(Integer uid) {
        User result=userMapper.findByUid(uid);
        userMapper.passByAdmin(result.getUid(),0,"root",new Date());
    }

    @Override
    public void addMoney(Integer uid, Integer purse, String modifiedUser, Date modifiedTime) {
        User result=userMapper.findByUid(uid);
        userMapper.addMoney(result.getUid(),purse,modifiedUser,new Date());
    }

    @Override
    public void subMoney(Integer uid, Integer purse, String modifiedUser, Date modifiedTime) {
        User result=userMapper.findByUid(uid);
        userMapper.subMoney(result.getUid(),purse,modifiedUser,new Date());
    }

    @Override
    public User getMoney(Integer uid) {
        User result=userMapper.findByUid(uid);
        return userMapper.getMoney(result.getUid());
    }

    @Override
    public void addLevel(Integer uid,String modifiedUser, Date modifiedTime) {
        User result=userMapper.findByUid(uid);
        userMapper.addLevel(result.getUid(),modifiedUser,new Date());
    }
    @Override
    public void subLevel(Integer uid,String modifiedUser, Date modifiedTime) {
        User result=userMapper.findByUid(uid);
        userMapper.subLevel(result.getUid(),modifiedUser,new Date());
    }

    @Override
    public List<User> getLevel() {
        List<User> user=userMapper.getLevel();
        return user;
    }

    @Override
    public User getBidLevel(Integer uid) {
        User user=userMapper.getBidLevel(uid);
        return user;
    }

    @Override
    public PriorityVO getLevelByBid(Integer uid) {
        PriorityVO user=userMapper.getLevelByBid(uid);
        return user;
    }

    @Override
    public void addPoint(Integer uid, Integer point, String modifiedUser, Date modifiedTime) {
        User result=userMapper.findByUid(uid);
        userMapper.addPoint(result.getUid(),point,modifiedUser,new Date());
    }

    @Override
    public void subPoint(Integer uid, Integer point, String modifiedUser, Date modifiedTime) {
        User result=userMapper.findByUid(uid);
        userMapper.subPoint(result.getUid(),point,modifiedUser,new Date());
    }

    @Override
    public User getPoint(Integer uid) {
        User result=userMapper.findByUid(uid);
        return userMapper.getPoint(result.getUid());
    }


    /**
     * 定义一个md5算法加密处理
     */
    private String getMD5Password(String password,String salt){
        for (int i=0;i<3;i++){
            //md5加密算法方法的调用（进行三次加密，用for循环）
            password=DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();//toUpperCase()转化为全大写
        }
        //返回加密之后的密码
        return  password;
    }
}
