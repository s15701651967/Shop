package com.bistu.store.mapper;

import com.bistu.store.entity.Address;
import com.bistu.store.entity.User;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

//@SpringBootTest:表示标注当前测试类为一个测试类，不会随同项目一块打包
@SpringBootTest
//@RunWith:表示启动该单元测试类（单元测试类不能够运行的），要传递一个参数，必须为SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class AddressMapperTests {
    //idea有检测功能，接口不能直接创建Bean的(动态代理技术解决),
    @Autowired//spring可以自动帮你把bean里面引用的对象的setter/getter方法省略
    private AddressMapper addressMapper;
    /**
     * 单元测试方法：可单独运行，不必启动整个项目，可以做单元测试，
     * 单元测试类：
     * 1.必须被@Test注解修饰
     * 2.返回值类型必须为void
     * 3.方法的参数列表不指定任何类型
     * 4.方法的访问修饰符必须为publ
     */

    @Test
    public void insert(){
        Address address=new Address();
        address.setUid(15);
        address.setPhone("15701651967");
        address.setName("Tom");
        addressMapper.insert(address);
    }

    @Test
    public void countByUid(){
    Integer count =addressMapper.countByUid(15);
        System.out.println(count);
    }

    @Test
    public void findByUid(){
        List<Address> list= addressMapper.findByUid(15);
        System.out.println(list);
    }

    @Test
    public void findByAid(){
        System.err.println( addressMapper.findByAid(9));
    }

    @Test
    public void  updateNonDefault(){
        addressMapper.updateNonDefault(15);
    }

    @Test
    public void  updateDefaultByAid(){
        addressMapper.updateDefaultByAid(6,"mingming",new Date());
    }

    @Test
    public void deleteByAid(){
        addressMapper.deleteByAid(1);
    }

    @Test
    public void findLastModified(){
        System.out.println(addressMapper.findLastModified(15));
    }

    @Test
    public void updateInfoByAid(){
        Address address=new Address();
        address.setAid(6);
        address.setTag("公司子公司");
        address.setAddress("1号楼2单元301");
        address.setPhone("15700009999");
address.setName("Sss");
addressMapper.updateInfoByAid(address);
    }
}
