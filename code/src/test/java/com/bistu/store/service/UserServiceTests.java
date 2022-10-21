package com.bistu.store.service;

import com.bistu.store.entity.User;
import com.bistu.store.mapper.UserMapper;
import com.bistu.store.service.ex.ServiceException;
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

public class UserServiceTests{
    //idea有检测功能，接口不能直接创建Bean的(动态代理技术解决),
    @Autowired
    private IUserService userService;
    /**
     * 单元测试方法：可单独运行，不必启动整个项目，可以做单元测试，
     * 单元测试类：
     * 1.必须被@Test注解修饰
     * 2.返回值类型必须为void
     * 3.方法的参数列表不指定任何类型
     * 4.方法的访问修饰符必须为publ
     */

    @Test
    public void reg(){
        User user=new User();
        user.setUsername("root");
        user.setPassword("123");
        user.setBankAccount("1234567891234567");
        user.setUserType("123123");
        userService.reg(user);
        try {
            System.out.println("注册成功");
        } catch (ServiceException e) {
            //获取类的对象，再获取类的名称
            System.out.println(e.getClass().getSimpleName());
            //获取异常具体描述信息
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void login(){
       User user= userService.login("root","123");
        System.out.println(user);
    }

    @Test
    public void changePassword(){

        userService.changePassword(2,"test0","321","123");
    }

    @Test
    public void changePasswordByAdmin(){

        userService.changePassword(15,"root","123","321");
    }

    @Test
    public void getByUid(){
        System.err.println(userService.getByUid(15));
    }

    @Test
    public void changeInfo(){
        User user=new User();
        user.setPhone("15700000000");
        user.setEmail("1123000000@qq.com");
        user.setGender(0);
        userService.changeInfo(15,"root",user);
    }

    @Test
    public void changeAvatar(){
        userService.changeAvatar(15,"/upload/test.png","aaadmin");
    }

    @Test
    public void checkAllUsers(){
        System.out.println(userService.checkAllUsers());
    }

    @Test
    public void passUserByUid(){
        userService.passUserByUid(34);
    }

    @Test
    public void rejectUserByUid(){
        userService.rejectUserByUid(2);
    }

    @Test
    public void addMoney(){
        userService.addMoney(2,14000,"root",new Date());
    }

    @Test
    public void subMoney(){
        userService.subMoney(15,200,"root",new Date());
    }

    @Test
    public void getMoney(){
        System.out.println(userService.getMoney(15));
    }

    @Test
    public void addLevel(){
        userService.addLevel(17,"root",new Date());
    }

    @Test
    public void subLevel(){
        userService.subLevel(17,"root",new Date());
    }

    @Test
    public void getLevel(){
        System.out.println(userService.getLevel());
    }

    @Test
    public void getLevelByBid(){
        System.out.println(userService.getLevelByBid(35));
    }

    @Test
    public void addPoint(){
        userService.addPoint(15,14000,"root",new Date());
    }

    @Test
    public void subPoint(){
        userService.subPoint(15,200,"root",new Date());
    }

    @Test
    public void getPoint(){
        System.out.println(userService.getPoint(15));
    }

}
