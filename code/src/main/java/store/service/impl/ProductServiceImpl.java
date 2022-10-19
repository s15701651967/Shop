package com.bistu.store.service.impl;

import com.bistu.store.entity.Product;
import com.bistu.store.entity.User;
import com.bistu.store.mapper.ProductMapper;
import com.bistu.store.service.IProductService;
import com.bistu.store.service.ex.InsertException;
import com.bistu.store.service.ex.ProductExistedException;
import com.bistu.store.service.ex.ProductNotFoundException;
import com.bistu.store.service.ex.UsernameDuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
/** 处理商品数据的业务层实现类 */
@Service//标记当前类是一个service类，加上该注解会将当前类自动注入到spring容器中,将当前类的对象交给Spring来管理，自动创建对象以及对象的维护

public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findHotList() {
        List<Product> list = productMapper.findHotList();
        for (Product product : list) {
            product.setPriority(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        }
        return list;
    }

    @Override
    public List<Product> findMarkList() {
        List<Product> list = productMapper.findMarkList();
        for (Product product : list) {
            product.setPriority(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        }
        return list;
    }

    @Override
    public List<Product> findDescList() {
        List<Product> list = productMapper.findDescList();
        for (Product product : list) {
            product.setPriority(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        }
        return list;
    }

    @Override
    public List<Product> findAscList() {
        List<Product> list = productMapper.findAscList();
        for (Product product : list) {
            product.setPriority(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        }
        return list;
    }

    @Override
    public Product findById(Integer id) {
            // 根据参数id调用私有方法执行查询，获取商品数据
            Product product = productMapper.findById(id);
            // 判断查询结果是否为null
            if (product == null) {
                // 是：抛出ProductNotFoundException
                throw new ProductNotFoundException("尝试访问的商品数据不存在");
            }
            // 将查询结果中的部分属性设置为null
//            product.setPriority(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
            // 返回查询结果
            return product;
        }

    @Override
    public List<Product> findByTitle(String title) {
        List<Product> list = productMapper.findByTitle(title);
        for (Product product : list) {
            if (product == null) {
                // 是：抛出ProductNotFoundException
                throw new ProductNotFoundException("尝试访问的商品数据不存在");
            }
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        }
        return list;
    }

    @Override
    public List<Product> findAllProducts() {
        List<Product> list = productMapper.findAllProducts();
        for (Product product : list) {
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        }
        return list;
    }

    @Override
    public List<Product> findAllPassProducts() {
        List<Product> list = productMapper.findAllPassProducts();
        for (Product product : list) {
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        }
        return list;
    }

    @Override
    public List<Product> findAllProductsByBid(Integer bid) {
        List<Product> list = productMapper.findAllProductsByBid(bid);
        for (Product product : list) {
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        }
        return list;
    }

    @Override
    public List<Product> findPassProductsByBid(Integer bid) {
        List<Product> list = productMapper.findPassProductsByBid(bid);
        for (Product product : list) {
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        }
        return list;
    }

    @Override
    public void insert(Product product) {
        Integer pid=product.getId();
        Product result=productMapper.findById(pid);
        if (result != null) {
            throw new ProductExistedException("商品已经存在");
        }
        Date date =new Date();
        product.setCreatedUser("商家");
        product.setModifiedUser("管理员");
        product.setCreatedTime(date);
        product.setModifiedTime(date);

        Integer rows= productMapper.insert(product);
        if(rows!=1){
            throw new InsertException("插入商品产生未知异常");
        }
    }

    @Override
    public void passProjectById(Integer id) {
    Product result=productMapper.findById(id);
    productMapper.passByAdmin(result.getId(),1,"root",new Date());
    }

    @Override
    public void rejectProjectById(Integer id) {
        Product result=productMapper.findById(id);
        productMapper.passByAdmin(result.getId(),0,"root",new Date());
    }

    @Override
    public void passProjectByBusiness(Integer id) {
        Product result=productMapper.findById(id);
        productMapper.passByBusiness(result.getId(),1,"商家",new Date());
    }

    @Override
    public void rejectProjectByBusiness(Integer id) {
        Product result=productMapper.findById(id);
        productMapper.rejectByBusiness(result.getId(),2,"商家",new Date());
    }
}
