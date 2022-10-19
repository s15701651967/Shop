package com.bistu.store.service.impl;

import com.bistu.store.entity.Address;
import com.bistu.store.mapper.AddressMapper;
import com.bistu.store.service.IAddressService;
import com.bistu.store.service.IDistrictService;
import com.bistu.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service//标记当前类是一个service类，加上该注解会将当前类自动注入到spring容器中,将当前类的对象交给Spring来管理，自动创建对象以及对象的维护

/**新增收货地址实现类*/
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;

    //在添加用户的收获地址的业务层依赖于IDistrictService的业务层接口
    @Autowired
    private IDistrictService districtService;

    @Value("${user.address.max-account}")
    private Integer maxCount;

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        //调用收货地址统计的方法
        Integer count=addressMapper.countByUid(uid);
        if(count>=maxCount){
            throw new AddressCountLimitException("用户收货地址超出上限");
        }

        //对address对象中的数据进行补全：省市区
        String provinceName= districtService.getNameByCode(address.getProvinceCode());
        String cityName= districtService.getNameByCode(address.getCityCode());
        String areaName= districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);

        //uid、isDelete
        address.setUid(uid);
        Integer isDefault= count==0?1:0;//当count是0说明数据库中无地址数据，将插入的数据设置为默认地址数据1，否则非默认0
        address.setIsDefault(isDefault);

        //补全四项日志
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());

        //插入收货地址的方法
        Integer rows= addressMapper.insert(address);
        if(rows!=1){
            throw new InsertException("插入用户的收货地址产生未知异常");
        }
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list=addressMapper.findByUid(uid);
        for(Address address:list){
//            address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setCreatedUser(null);
            address.setCreatedTime(null);
            address.setModifiedUser(null);
            address.setModifiedTime(null);

        }
        return list;
    }

    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
         Address result= addressMapper.findByAid(aid);
        if(result==null){
            throw new AddressNotFoundException("收货地址不存在");
        }
        //检测当前获取到的收货地址数据归属
//        if(!result.getUid().equals(uid)){
//            throw new AccessDeniedException("非法数据访问");
//        }
        //先将所有收货地址设置为非默认
        Integer rows= addressMapper.updateNonDefault(uid);
            if(rows<1){
                throw new UpdateException("更新数据时产生未知异常");
            }
        rows=addressMapper.updateDefaultByAid(aid,username,new Date());
        if(rows!=1){
            throw new UpdateException("更新数据时产生未知异常");
        }

    }

    @Override
    public void delete(Integer aid, Integer uid, String username) {
        Address result= addressMapper.findByAid(aid);
        if (result == null) {
            // 是：抛出AddressNotFoundException
            throw new AddressNotFoundException("尝试访问的收货地址数据不存在");
        }

        // 判断查询结果中的uid与参数uid是否不一致(使用equals()判断)
//        if (!result.getUid().equals(uid)) {
//            // 是：抛出AccessDeniedException：非法访问
//            throw new AccessDeniedException("非常访问");
//        }

        // 根据参数aid，调用deleteByAid()执行删除
        Integer rows1 = addressMapper.deleteByAid(aid);
        if (rows1 != 1) {
            throw new DeleteException("删除收货地址数据时出现未知错误");
        }

        // 判断查询结果中的isDefault是否为0
        if (result.getIsDefault() == 0) {
            return;
        }

        // 调用持久层的countByUid()统计目前还有多少收货地址
        Integer count = addressMapper.countByUid(uid);
        // 判断目前的收货地址的数量是否为0
        if (count == 0) {
            //直接终止程序
            return;
        }

        /*   //将该条数据中的id_default字符值设置为1  调用findLastModified()找出用户最近修改的收货地址数据
        Address address=addressMapper.findLastModified(uid);
        addressMapper.updateDefaultByAid(address.getAid(),username,new Date());*/

        // 调用findLastModified()找出用户最近修改的收货地址数据
        Address lastModified = addressMapper.findLastModified(uid);
        // 从以上查询结果中找出aid属性值
        Integer lastModifiedAid = lastModified.getAid();
        // 调用持久层的updateDefaultByAid()方法执行设置默认收货地址，并获取返回的受影响的行数
        Integer rows2 = addressMapper.updateDefaultByAid(lastModifiedAid, username, new Date());
        // 判断受影响的行数是否不为1
        if (rows2 != 1) {
            // 是：抛出UpdateException
            throw new UpdateException("更新收货地址数据时出现未知错误");
        }
    }

    @Override
    public void updateInfoByAid(Integer aid, String username, Address address) {
        Address result=addressMapper.findByAid(aid);
        if(result==null){
            throw new AddressNotFoundException("收货地址不存在");
        }
        address.setAid(aid);
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());
        Integer rows=addressMapper.updateInfoByAid(address);
        if(rows!=1){
            throw new UpdateException("更新时数据产生位置异常");
        }
    }

    @Override
    public Address getByAid(Integer aid,Integer uid) {
        Address address= addressMapper.findByAid(aid);
        if(address==null){
            throw new AddressNotFoundException("收货地址数据不存在");
        }
//        if(!address.getUid().equals(uid)){
//            throw new AccessDeniedException("非法数据访问");
//        }
        address.setProvinceCode(null);
        address.setCityCode(null);
        address.setAreaCode(null);
        address.setCreatedUser(null);
        address.setCreatedTime(null);
        address.setModifiedUser(null);
        address.setModifiedTime(null);

        return address;
    }


}
