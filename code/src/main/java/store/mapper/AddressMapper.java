package com.bistu.store.mapper;

import com.bistu.store.entity.Address;
import com.bistu.store.entity.District;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**收货地址持久层的接口*/
public interface AddressMapper {

    /**
     * 插入用户的收货地址数据
     * @param address 收货地址数据
     * @return 受影响的行数
     */
    Integer insert(Address address);

    /**
     * 根据用户id统计收货地址数量
     * @param uid 用户id
     * @return 当前用户的收货地址总数
     */
    Integer countByUid(Integer uid);

    /**
     * 根据用户id查询用户收获地址数据
     * @param uid 用户id
     * @return 收货地址数据
     */
    List<Address> findByUid(Integer uid);

    /**
     * 根据aid查询收货地址数据
     * @param aid 收货地址id
     * @return 收货地址数据，若没有找到则返回null值
     */
    Address findByAid(Integer aid);

    /**
     * 根据用户的uid值来修改用户的收货地址设置为非默认
     * @param uid 用户的id值
     * @return 受影响行数
     */
    Integer updateNonDefault(Integer uid);

    Integer updateDefaultByAid(@Param("aid") Integer aid,
                               @Param("modifiedUser") String modifiedUser,
                               @Param("modifiedTime") Date modifiedTime);

    /**
     * 根据收货地址id删除收货地址数据
     * @param aid 收货地址id
     * @return 受影响行数
     */
    Integer deleteByAid(Integer aid);

    /**
     * 根据用户uid查询当前用户最后一次被修改的收货地址数据
     * @param uid 用户id
     * @return 收货地址
     */
    Address findLastModified(Integer uid);

    /**
     *更新用户地址信息
     * @param address 用户地址数据
     * @return 返回值为受影响行
     */
    Integer updateInfoByAid(Address address);
}
