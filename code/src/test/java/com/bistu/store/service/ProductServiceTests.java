package com.bistu.store.service;

import com.bistu.store.entity.Address;
import com.bistu.store.entity.Product;
import com.bistu.store.entity.User;
import com.bistu.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

//@SpringBootTest:表示标注当前测试类为一个测试类，不会随同项目一块打包
@SpringBootTest
//@RunWith:表示启动该单元测试类（单元测试类不能够运行的），要传递一个参数，必须为SpringRunner的实例类型
// @RunWith(SpringRunner.class)注解是一个测试启动器，可以加载Springboot测试注解
@RunWith(SpringRunner.class)
@Service//标记当前类是一个service类，加上该注解会将当前类自动注入到spring容器中,将当前类的对象交给Spring来管理，自动创建对象以及对象的维护

public class ProductServiceTests {
    //idea有检测功能，接口不能直接创建Bean的(动态代理技术解决),
    @Autowired
    private IProductService productService;
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
        try {
            List<Product> list = productService.findHotList();
            System.out.println("count=" + list.size());
            for (Product item : list) {
                System.out.println(item);
            }
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void findMarkList() {
        try {
            List<Product> list = productService.findMarkList();
            System.out.println("count=" + list.size());
            for (Product item : list) {
                System.out.println(item);
            }
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void findDescList() {
        try {
            List<Product> list = productService.findDescList();
            System.out.println("count=" + list.size());
            for (Product item : list) {
                System.out.println(item);
            }
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void findAscList() {
        try {
            List<Product> list = productService.findAscList();
            System.out.println("count=" + list.size());
            for (Product item : list) {
                System.out.println(item);
            }
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void findByTitle() {
        try {
            List<Product> list = productService.findByTitle("Dell");
            System.out.println("count=" + list.size());
            for (Product item : list) {
                System.out.println(item);
            }
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void findAllProducts() {
        try {
            List<Product> list = productService.findAllProducts();
            System.out.println("count=" + list.size());
            for (Product item : list) {
                System.out.println(item);
            }
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void findAllPassProducts() {
        try {
            List<Product> list = productService.findAllPassProducts();
            System.out.println("count=" + list.size());
            for (Product item : list) {
                System.out.println(item);
            }
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void findAllProductsByBid() {
        try {
            List<Product> list = productService.findAllProductsByBid(24);
            System.out.println("count=" + list.size());
            for (Product item : list) {
                System.out.println(item);
            }
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void findPassProductsByBid() {
        try {
            List<Product> list = productService.findPassProductsByBid(24);
            System.out.println("count=" + list.size());
            for (Product item : list) {
                System.out.println(item);
            }
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void insert(){
        Product product=new Product();
        product.setId(10000016);
        product.setCategoryId(241);
        product.setItemType("test圆珠笔");
        product.setTitle("test晨光a2圆珠笔中油笔水感顺滑黑色40支按动式0.7mm 40支黑色");
        product.setSellPoint("test圆珠笔中油笔水感顺滑黑色");
        product.setPrice((long) 30);
        product.setNum(99999);
        product.setImage("/images/portal/24_test/");
        product.setStatus(1);
        product.setPriority(53);
        product.setNowSellNum(41);
        product.setHistorySellNum(101);
        product.setSellPersons(101);
        product.setJudgeMark(10);
        product.setSellMethod("网上支付");
        product.setBusinessId(99999);

        productService.insert(product);
        try {
            System.out.println("添加商品成功，等待管理员审核");
        } catch (ServiceException e) {
            //获取类的对象，再获取类的名称
            System.out.println(e.getClass().getSimpleName());
            //获取异常具体描述信息
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void passProjectById(){
        productService.passProjectById(10000016);
    }

    @Test
    public void rejectProjectById(){
        productService.rejectProjectById(100000262);
    }

    @Test
    public void passProjectByBusiness(){
        productService.passProjectByBusiness(10000015);
    }

    @Test
    public void rejectProjectByBusiness(){
        productService.rejectProjectByBusiness(10000023);
    }
}
