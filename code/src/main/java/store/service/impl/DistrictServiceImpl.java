package com.bistu.store.service.impl;

import com.bistu.store.entity.District;
import com.bistu.store.mapper.DistrictMapper;
import com.bistu.store.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements IDistrictService {
    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public List<District> getByParent(String parent) {
        List<District> list= districtMapper.findByParent(parent);
        //进行网络数据传输时，为尽量避免无效数据的传递，将无效数据设置为null
        //可节省流量同时提示效率
        for(District d:list){
            d.setId(null);
            d.setParent(null);
        }
        return list;
    }

    @Override
    public String getNameByCode(String code) {
        return districtMapper.findNameByCode(code);
    }
}
