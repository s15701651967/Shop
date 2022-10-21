package com.bistu.store.service.impl;

import com.bistu.store.entity.Product;
import com.bistu.store.entity.User;
import com.bistu.store.entity.UserPurse;
import com.bistu.store.mapper.UserMapper;
import com.bistu.store.mapper.UserPurseMapper;
import com.bistu.store.service.IUserPurseService;
import com.bistu.store.service.IUserService;
import com.bistu.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/** 处理用户数据的业务层实现类即用户模块业务层实现类 */
@Service//标记当前类是一个service类，加上该注解会将当前类自动注入到spring容器中,将当前类的对象交给Spring来管理，自动创建对象以及对象的维护

public class UserPurseServiceImpl implements IUserPurseService {
    //调用mapper层方法再将User对象传递下去
    @Autowired
    private UserPurseMapper userPurseMapper;


    @Override
    public void insert(UserPurse user) {
        user.setModifiedUser(user.getUsername());
        user.setModifiedTime(new Date());
        Integer rows=userPurseMapper.insert(user);
        if(rows!=1){
            throw new InsertException("用户充值记录录入产生未知的异常");
        }
    }

    @Override
    public void inserts(UserPurse user) {
        user.setModifiedUser(user.getUsername());
        user.setModifiedTime(new Date());
        Integer rows=userPurseMapper.inserts(user);
        if(rows!=1){
            throw new InsertException("用户充值记录录入产生未知的异常");
        }
    }

    @Override
    public List<UserPurse> getByUid(Integer uid) {
        List<UserPurse> result = userPurseMapper.findByUid(uid);
//        for (UserPurse user : result) {
////           user.setModifiedUser();
//           user.setModifiedTime(new Date());
//        }
//        if(result==null){
//            throw new UserRecordNotFoundException("数据不存在");
//        }
//        UserPurse user=new UserPurse();
//        user.setId(result.getId());
//        user.setUid(result.getUid());
//        user.setUsername(result.getUsername());
//        user.setPurse(result.getPurse());
//        user.setAddAmount(result.getAddAmount());
//        user.setSubAmount(result.getSubAmount());
//        user.setPid(result.getPid());
        return result;
    }
}
