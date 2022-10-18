package com.bistu.store.mapper;

import com.bistu.store.entity.Address;
import com.bistu.store.entity.Product;
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
public class ProductMapperTests {
    //idea有检测功能，接口不能直接创建Bean的(动态代理技术解决),
    @Autowired//spring可以自动帮你把bean里面引用的对象的setter/getter方法省略
    private ProductMapper productMapper;
    /**
     * 单元测试方法：可单独运行，不必启动整个项目，可以做单元测试，
     * 单元测试类：
     * 1.必须被@Test注解修饰
     * 2.返回值类型必须为void
     * 3.方法的参数列表不指定任何类型
     * 4.方法的访问修饰符必须为publ
     */

    @Test
    public void findHotList() {
        List<Product> list = productMapper.findHotList();
        System.out.println("count=" + list.size());
        for (Product item : list) {
            System.out.println(item);
        }
    }

    @Test
    public void findDescList() {
        List<Product> list = productMapper.findDescList();
        System.out.println("count=" + list.size());
        for (Product item : list) {
            System.out.println(item);
        }
    }

    @Test
    public void findAscList() {
        List<Product> list = productMapper.findAscList();
        System.out.println("count=" + list.size());
        for (Product item : list) {
            System.out.println(item);
        }
    }

    @Test
    public void findMarkList() {
        List<Product> list = productMapper.findMarkList();
        System.out.println("count=" + list.size());
        for (Product item : list) {
            System.out.println(item);
        }
    }

    @Test
    public void findByTitle() {
       /* Product product=productMapper.findByTitle("圆珠笔");
        System.out.println(product);*/
        List<Product> list = productMapper.findByTitle("Dell");
        System.out.println("count=" + list.size());
        for (Product item : list) {
            System.out.println(item);
        }
    }

    @Test
    public void findAllProducts() {
        List<Product> list = productMapper.findAllProducts();
        System.out.println("count=" + list.size());
        for (Product item : list) {
            System.out.println(item);
        }
    }

    @Test
    public void findAllPassProducts() {
        List<Product> list = productMapper.findAllPassProducts();
        System.out.println("count=" + list.size());
        for (Product item : list) {
            System.out.println(item);
        }
    }

    @Test
    public void findAllProductsByBid() {
        List<Product> list = productMapper.findAllProductsByBid(24);
        System.out.println("count=" + list.size());
        for (Product item : list) {
            System.out.println(item);
        }
    }

    @Test
    public void findPassProductsByBid() {
        List<Product> list = productMapper.findPassProductsByBid(24);
        System.out.println("count=" + list.size());
        for (Product item : list) {
            System.out.println(item);
        }
    }

    @Test
    public void insert() {
       Product product=new Product();
       product.setId(10000025);
       product.setCategoryId(241);
       product.setItemType("圆珠笔");
       product.setTitle("晨光a2圆珠笔中油笔水感顺滑黑色40支按动式0.7mm 40支黑色");
       product.setSellPoint("圆珠笔中油笔水感顺滑黑色");
       product.setPrice((long) 30);
       product.setNum(99999);
       product.setImage("/images/portal/26_test/");
       product.setStatus(0);
       product.setPriority(54);
//       product.setNowSellNum(40);
//       product.setHistorySellNum(100);
       product.setSellPersons(102);
       product.setJudgeMark(10);
       product.setSellMethod("网上支付");
       product.setBusinessId(35);
        Date date =new Date();
        product.setCreatedTime(date);
        product.setModifiedTime(date);
        product.setCreatedUser("商家");
        product.setModifiedUser("管理员");

       Integer rows=productMapper.insert(product);
        System.out.println(rows);
    }

    @Test
    public void passByAdmin(){
        productMapper.passByAdmin(100000262,1,"root",new Date());
    }
    @Test
    public void rejectByAdmin(){
        productMapper.passByAdmin(100000262,0,"root",new Date());
    }

    @Test
    public void passByBusiness(){
        productMapper.passByBusiness(10000023,2,"root",new Date());
    }
    @Test
    public void rejectByBusiness(){
        productMapper.rejectByBusiness(10000023,0,"root",new Date());
    }

}
