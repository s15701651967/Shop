package com.bistu.store.service;

import com.bistu.store.entity.Address;
import com.bistu.store.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

//@SpringBootTest:表示标注当前测试类为一个测试类，不会随同项目一块打包
@SpringBootTest
//@RunWith:表示启动该单元测试类（单元测试类不能够运行的），要传递一个参数，必须为SpringRunner的实例类型
// @RunWith(SpringRunner.class)注解是一个测试启动器，可以加载Springboot测试注解
@RunWith(SpringRunner.class)
@Service//标记当前类是一个service类，加上该注解会将当前类自动注入到spring容器中,将当前类的对象交给Spring来管理，自动创建对象以及对象的维护

public class OrderServiceTests {
    //idea有检测功能，接口不能直接创建Bean的(动态代理技术解决),
    @Autowired
    private IOrderService orderService;
    /**
     * 单元测试方法：可单独运行，不必启动整个项目，可以做单元测试，
     * 单元测试类：
     * 1.必须被@Test注解修饰
     * 2.返回值类型必须为void
     * 3.方法的参数列表不指定任何类型
     * 4.方法的访问修饰符必须为publ
     */

    @Test
    public void create(){
        //create(Integer aid, Integer[] cids, Integer uid, String username)
        Integer[] cids={1,2,3,4};
        Order order= orderService.create(1,cids,8,"宋");
        System.out.println(order);
    }

    @Test
    public void updateStatus(){
        orderService.updateStatus(2,new Date());
    }

    @Test
    public void findVOByOid(){
        System.out.println(orderService.findVOByOid(7));
    }

    @Test
    public void findOrderByOid(){
        System.out.println(orderService.findOrderByOid(2));
    }

    @Test
    public void findVOByUid(){
        System.out.println(orderService.findVOByUid(15));
    }

    @Test
    public void findBVOByUid(){
        System.out.println(orderService.findBVOByUid(34));
    }


    @Test
    public void findVOByOUid(){
        System.out.println(orderService.findVOByOUid(1,2));
    }

    @Test
    public void findVOByOUPid(){
        System.out.println(orderService.findVOByOUPid(9,10000009));
    }

    @Test
    public void paySearch(){
        System.out.println(orderService.paySearch(10000022));
    }

    @Test
    public void updatePStatus(){
        orderService.updatePStatus(1,10000022,2);
    }

    @Test
    public void updatePriority(){
        orderService.updatePriority(1,10000022,2);
    }

    @Test
    public void businessOrder(){
        System.out.println(orderService.businessOrder(35));
    }
}
