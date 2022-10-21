package com.bistu.store.mapper;

import com.bistu.store.entity.Comment;
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
public class CommentMapperTests {
    //idea有检测功能，接口不能直接创建Bean的(动态代理技术解决),
    @Autowired//spring可以自动帮你把bean里面引用的对象的setter/getter方法省略
    private CommentMapper commentMapper;

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
        Comment comment=new Comment();
        comment.setUid(34);
        comment.setBid(35);
        comment.setOid(1);
        comment.setPid(10000022);
        comment.setPComment("商品很好用");
        comment.setBComment("卖家态度不错");
        comment.setJudgeMark(10);
        comment.setReply("感谢购物");

        Integer rows = commentMapper.insert(comment);
        System.out.println(rows);
    }

    @Test
    public void updateByBid(){
        commentMapper.updateByBid(34,24,4,10000005,"感谢您的购买");
    }

    @Test
    public void findByUid(){
        List<Comment> comments=commentMapper.findByUid(34);
        System.out.println(comments);
    }

    @Test
    public void findByBid(){
        List<Comment> comments=commentMapper.findByBid(24);
        System.out.println(comments);
    }

}
