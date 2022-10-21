package com.bistu.store.mapper;

import com.bistu.store.entity.District;

import java.util.List;

public interface DistrictMapper {
    /**
     * 根据父代号查询区域信息
     * @param parent 父代号
     * @return 某个父区域下的所有区域列表
     */
    List<District> findByParent(String  parent);

    /**
     * 根据code查询区域名称
     * @param code 地区代号code
     * @return 地区名字
     */
    String findNameByCode(String code);

}
