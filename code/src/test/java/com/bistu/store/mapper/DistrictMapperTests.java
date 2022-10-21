package com.bistu.store.mapper;

import com.bistu.store.entity.Address;
import com.bistu.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

//@SpringBootTest:表示标注当前测试类为一个测试类，不会随同项目一块打包
@SpringBootTest
//@RunWith:表示启动该单元测试类（单元测试类不能够运行的），要传递一个参数，必须为SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class DistrictMapperTests {
    //idea有检测功能，接口不能直接创建Bean的(动态代理技术解决),
    @Autowired//spring可以自动帮你把bean里面引用的对象的setter/getter方法省略
    private DistrictMapper districtMapper;
    /**
     * 单元测试方法：可单独运行，不必启动整个项目，可以做单元测试，
     * 单元测试类：
     * 1.必须被@Test注解修饰
     * 2.返回值类型必须为void
     * 3.方法的参数列表不指定任何类型
     * 4.方法的访问修饰符必须为publ
     */

    @Test
    public void findByParent(){
        List<District> list= districtMapper.findByParent("110100");
        for(District d:list){
            System.out.println(d);
        }
    }

    @Test
    public void findNameByCode(){
        String name=districtMapper.findNameByCode("110000");
        System.out.println(name);
    }

}
