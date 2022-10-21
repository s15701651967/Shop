package com.bistu.store.mapper;

import com.bistu.store.entity.Address;
import com.bistu.store.entity.Order;
import com.bistu.store.entity.OrderItem;
import com.bistu.store.vo.PayVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

//@SpringBootTest:表示标注当前测试类为一个测试类，不会随同项目一块打包
@SpringBootTest
//@RunWith:表示启动该单元测试类（单元测试类不能够运行的），要传递一个参数，必须为SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class OrderMapperTests {
    //idea有检测功能，接口不能直接创建Bean的(动态代理技术解决),
    @Autowired//spring可以自动帮你把bean里面引用的对象的setter/getter方法省略
    private OrderMapper orderMapper;
    /**
     * 单元测试方法：可单独运行，不必启动整个项目，可以做单元测试，
     * 单元测试类：
     * 1.必须被@Test注解修饰
     * 2.返回值类型必须为void
     * 3.方法的参数列表不指定任何类型
     * 4.方法的访问修饰符必须为publ
     */

    @Test
    public void insertOrder(){
        Order order=new Order();
        order.setUid(15);
        order.setRecvName("SSS");
        order.setRecvPhone("15701765123");
        orderMapper.insertOrder(order);
    }

    @Test
    public void insertOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setOid(1);
        orderItem.setPid(10000005);
        orderItem.setTitle("施耐德（Schneider） K15 经典款圆珠笔 (5支混色装)");
        Integer rows = orderMapper.insertOrderItem(orderItem);
        System.out.println("rows=" + rows);
    }

    @Test
    public void updateStatus(){
        System.out.println(orderMapper.updateStatus(2,new Date()));
    }

    @Test
    public void findVOByOid(){
        System.out.println(orderMapper.findVOByOid(2));
    }

    @Test
    public void findOrderByOid(){
        System.out.println(orderMapper.findOrderByOid(2));
    }


    @Test
    public void findVOByUid(){
        System.out.println(orderMapper.findVOByUid(15));
    }

    @Test
    public void findBVOByUid(){
        System.out.println(orderMapper.findBVOByUid(34));
    }

    @Test
    public void findVOByOUid(){
        System.out.println(orderMapper.findVOByOUid(9,15));
    }

    @Test
    public void findVOByOUPid(){
        System.out.println(orderMapper.findVOByOUPid(9,10000009));
    }

    @Test
    public void paySearch(){
        System.out.println(orderMapper.paySearch(10000022));
    }

    @Test
    public void  updatePStatus(){
        Integer rows=orderMapper.updatePStatus(1,10000022,2);
    }

    @Test
    public void  updatePriority(){
        Integer rows=orderMapper.updatePriority(1,10000022,2);
    }


    @Test
    public void businessOrder(){
        System.out.println(orderMapper.businessOrder(24));
//        System.out.println(orderMapper.businessOrder(35));
    }

}
