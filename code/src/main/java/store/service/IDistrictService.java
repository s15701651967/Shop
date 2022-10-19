package com.bistu.store.service;

import com.bistu.store.entity.District;

import java.util.List;

public interface IDistrictService {
    /**
     * 根据父区域代号来查询区域信息 省市区
     * @param parent 父代码
     * @return 多个区域信息
     */
    List<District> getByParent(String parent);

    /**
     * 根据code查询区域名称
     * @param code 地区代号code
     * @return 地区名字
     */

    String getNameByCode(String code);
}
