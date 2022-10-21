package com.bistu.store.service.impl;

import com.bistu.store.entity.Cart;
import com.bistu.store.entity.Product;
import com.bistu.store.mapper.CartMapper;
import com.bistu.store.mapper.ProductMapper;
import com.bistu.store.service.ICartService;
import com.bistu.store.service.ex.AccessDeniedException;
import com.bistu.store.service.ex.CartNotFoundException;
import com.bistu.store.service.ex.InsertException;
import com.bistu.store.service.ex.UpdateException;
import com.bistu.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {
    /**购物车业务层依赖于购物车持久层及商品的持久层*/
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;//因为购物车里也是商品，Cart部分数据可以通过商品拿到


    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount, String username) {
        //查询当前要添加的该物品是否在表中已经存在
        Cart result=cartMapper.findByUidAndPid(uid,pid);
        Date date = new Date();
        if(result==null){//表示该商品从未被添加到购物车中，进行新增插入
            //创建一个cart对象
            Cart cart=new Cart();
            //补全数据：参数传递的数据即uid，pid，amount
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);//amount为商品的总数
            //价格来自于商品中的数据
            Product product= productMapper.findById(pid);
            cart.setPrice(product.getPrice());
            //补全四个日志
            cart.setCreatedUser(username);
            cart.setCreatedTime(date);
            cart.setModifiedUser(username);
            cart.setModifiedTime(date);

            //数据插入
            Integer rows= cartMapper.insert(cart);
            if (rows!=1){
                throw new InsertException("插入数据时产生位置异常");
            }
        }else {//表示当前商品在购物车中已经存在，则更新该条数据的num值
            //进行原有数据和现有传递的数据进行相加操作
            Integer num= result.getNum()+amount;
            Integer rows= cartMapper.updateNumByCid(
                    result.getCid(),num,username,date);
            if(rows!=1){
                throw new UpdateException("更新数据时产生未知异常");
            }
        }
    }

    @Override
    public List<CartVO> getVOByUid(Integer uid) {
        return cartMapper.findVOByUid(uid);
    }

    @Override
    public void delCartItem(Integer cid) {
        Cart result=cartMapper.findByCid(cid);
        Integer rows =cartMapper.delCartItem(cid,"root",new Date());
        if(rows!=1){
            System.out.println("删除错误！");
        }
    }

    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
    // 调用findByCid(cid)根据参数cid查询购物车数据
        Cart result = cartMapper.findByCid(cid);
        // 判断查询结果是否为null
        if(result==null){
            throw new CartNotFoundException("数据不存在");
        }
        // 判断查询结果中的uid与参数uid是否不一致
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("数据非法访问");
        }
        // 根据查询结果中的原数量增加1得到新的数量num
        Integer num= result.getNum()+1;
        // 调用updateNumByCid(cid, num, modifiedUser, modifiedTime)执行修改数量
        Integer rows = cartMapper.updateNumByCid(cid,num,username,new Date());
        if(rows!=1){
            throw new UpdateException("更新数据失败");
        }
        return num;
    }

    @Override
    public Integer reduceNum(Integer cid, Integer uid, String username) {
        // 调用findByCid(cid)根据参数cid查询购物车数据
        Cart result = cartMapper.findByCid(cid);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：抛出CartNotFoundException
            throw new CartNotFoundException("尝试访问的购物车数据不存在");
        }

        // 判断查询结果中的uid与参数uid是否不一致
        if (!result.getUid().equals(uid)) {
            // 是：抛出AccessDeniedException
            throw new AccessDeniedException("非法访问");
        }

        // 可选：检查商品的数量是否大于多少(适用于增加数量)或小于多少(适用于减少数量)
        // 根据查询结果中的原数量增加1得到新的数量num
        Integer num = result.getNum() - 1;

        // 创建当前时间对象，作为modifiedTime
        Date now = new Date();
        // 调用updateNumByCid(cid, num, modifiedUser, modifiedTime)执行修改数量
        Integer rows = cartMapper.updateNumByCid(cid, num, username, now);
        if (rows != 1) {
            throw new InsertException("修改商品数量时出现未知错误，请联系系统管理员");
        }

        // 返回新的数量
        return num;
    }

    @Override
    public List<CartVO> getVOByCid(Integer uid, Integer[] cids) {
        List<CartVO> list= cartMapper.findVOByCids(cids);
        Iterator<CartVO> it=list.iterator();
        while (it.hasNext()){
            CartVO cartVO=it.next();
            if (!cartVO.getUid().equals(uid)) {//表示当前的数据不属于当前的用户
                //从集合中移除该元素
                it.remove();
            }
        }
        return list;
    }


}
