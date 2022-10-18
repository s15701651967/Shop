package com.bistu.store.mapper;

import com.bistu.store.entity.User;
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
public class UserMapperTests {
    //idea有检测功能，接口不能直接创建Bean的(动态代理技术解决),
    @Autowired//spring可以自动帮你把bean里面引用的对象的setter/getter方法省略
    private UserMapper userMapper;

    /**
     * 单元测试方法：可单独运行，不必启动整个项目，可以做单元测试，
     * 单元测试类：
     * 1.必须被@Test注解修饰
     * 2.返回值类型必须为void
     * 3.方法的参数列表不指定任何类型
     * 4.方法的访问修饰符必须为publ
     */

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("root1");
        user.setPassword("123");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void findByUsername() {
        User user = userMapper.findByUsername("root");
        System.out.println(user);
    }

    @Test
    public void updatePasswordByYid() {
        userMapper.updatePasswordByYid(14, "321", "管理员", new Date());
    }

    @Test
    public void findByUid() {
        User user = userMapper.findByUid(15);
        if (user.getUserType().equals("1")) {
            System.err.println("管理员");
        }
        System.out.println(userMapper.findByUid(15));
    }

    @Test
    public void updateInfoByUid() {
        User user = new User();
        user.setUid(15);
        user.setPhone("15701651900");
        user.setEmail("1123868000@qq.com");
        user.setGender(1);
        userMapper.updateInfoByUid(user);
    }

    @Test
    public void updateAvatarByUid() {
        userMapper.updateAvatarByUid(15, "/upload/avatar.png", "admin", new Date());
    }

    @Test
    public void checkAllUsers() {
        System.out.println(userMapper.checkAllUsers());
    }

    @Test
    public void passByAdmin() {
        userMapper.passByAdmin(34, 1, "root", new Date());
    }

    @Test
    public void rejectByAdmin() {
        userMapper.passByAdmin(34, 0, "root", new Date());
    }

    @Test
    public void addMoney() {
        userMapper.addMoney(15, 10000, "root", new Date());
    }

    @Test
    public void subMoney() {
        userMapper.subMoney(15, 100, "root", new Date());
    }

    @Test
    public void getMoney() {

//       Integer money= userMapper.getMoney(15);
        System.out.println(userMapper.getMoney(15));
    }

    @Test
    public void addLevel() {
        userMapper.addLevel(17, "root", new Date());
    }

    @Test
    public void subLevel() {
        userMapper.subLevel(17, "root", new Date());
    }

    @Test
    public void getLevel() {

//       Integer money= userMapper.getMoney(15);
        System.out.println(userMapper.getLevel());
    }
    @Test
    public void getBidLevel(){
        System.out.println(userMapper.getBidLevel(3));

    }
    @Test
    public void getLevelByBid() {

//       Integer money= userMapper.getMoney(15);
        System.out.println(userMapper.getLevelByBid(24));
    }

    @Test
    public void addPoint() {
        userMapper.addPoint(15, 1000, "root", new Date());
    }

    @Test
    public void subPoint() {
        userMapper.subPoint(15, 1000, "root", new Date());
    }

    @Test
    public void getPoint() {

//       Integer money= userMapper.getMoney(15);
        System.out.println(userMapper.getPoint(15));
    }
}
