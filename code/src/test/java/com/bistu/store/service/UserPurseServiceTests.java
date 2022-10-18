package com.bistu.store.service;

import com.bistu.store.entity.User;
import com.bistu.store.entity.UserPurse;
import com.bistu.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

//@SpringBootTest:表示标注当前测试类为一个测试类，不会随同项目一块打包
@SpringBootTest
//@RunWith:表示启动该单元测试类（单元测试类不能够运行的），要传递一个参数，必须为SpringRunner的实例类型
// @RunWith(SpringRunner.class)注解是一个测试启动器，可以加载Springboot测试注解
@RunWith(SpringRunner.class)
@Service//标记当前类是一个service类，加上该注解会将当前类自动注入到spring容器中,将当前类的对象交给Spring来管理，自动创建对象以及对象的维护

public class UserPurseServiceTests {
    //idea有检测功能，接口不能直接创建Bean的(动态代理技术解决),
    @Autowired
    private IUserPurseService userPurseService;
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
        UserPurse user=new UserPurse();
        user.setUid(15);
        user.setUsername("root");
        user.setPurse(87120);
        user.setAddAmount(0);
        user.setSubAmount(0);
//        user.setModifiedUser("root");
//        user.setModifiedTime(new Date());
        userPurseService.insert(user);
        try {
            System.out.println("成功");
        } catch (ServiceException e) {
            //获取类的对象，再获取类的名称
            System.out.println(e.getClass().getSimpleName());
            //获取异常具体描述信息
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void inserts(){
        UserPurse user=new UserPurse();
        user.setUid(34);
        user.setUsername("root");
        user.setPurse(5438);
        user.setAddAmount(0);
        user.setSubAmount(100);
//        user.setModifiedUser("root");
//        user.setModifiedTime(new Date());
        userPurseService.inserts(user);
        try {
            System.out.println("成功");
        } catch (ServiceException e) {
            //获取类的对象，再获取类的名称
            System.out.println(e.getClass().getSimpleName());
            //获取异常具体描述信息
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getByUid(){
        List<UserPurse> result = userPurseService.getByUid(15);
        System.err.println(result);
    }

}
