package com.bistu.store.controller;

import com.bistu.store.controller.ex.*;
import com.bistu.store.entity.Comment;
import com.bistu.store.entity.User;
import com.bistu.store.entity.UserPurse;
import com.bistu.store.service.ICommentService;
import com.bistu.store.service.IUserPurseService;
import com.bistu.store.service.IUserService;
import com.bistu.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController//等价于@Controller+@ResponseBody
//@Controller//当前类交给Spring管理
@RequestMapping("/comment")//什么样的请求可以被拦截到此类当中

public class CommentController extends BaseController {//在控制层抽离一个父类在父类中统一处理关于异常的相关操作，编写一个BaseController类，统一处理异常
    @Autowired//spring可以自动帮你把bean里面引用的对象的setter/getter方法省略
    private ICommentService commentService;

    /**
     *1.接收数据方式：请求处理方法的参数列表设置为pojo类型来接收前端数据，
     * SpringBoot会将前端的URL地址中的参数名和pojo类的属性名进行比较，若两个名称相同，将值注入到pojo类中对应的属性上
     */

    @RequestMapping("insert")//什么样的请求可以被拦截到此类当中/user/reg
    //@ResponseBody//将JSON数据写入响应包中    此方法的响应结果以JSON格式进行数据的相应给到前端
    public JsonResult<Void> insert(Comment comment){
        //创建响应结果对象
        commentService.insert(comment);
        return new JsonResult<>(OK);
    }

    @RequestMapping("updateByBid")
    public JsonResult<Void> updateByBid(Integer uid,Integer bid,Integer oid,Integer pid,String reply){
        commentService.updateByBid(uid, bid, oid, pid, reply);
        return new JsonResult<>(OK);
    }

    @RequestMapping("find_comment")
    public JsonResult<List<Comment>> findByUid(Integer uid){
        List<Comment> data=commentService.findByUid(uid);
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("find_reply")
    public JsonResult<List<Comment>> findByBid(Integer bid){
        List<Comment> data=commentService.findByBid(bid);
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("find_product")
    public JsonResult<List<Comment>> findByPid(Integer pid){
        List<Comment> data=commentService.findByPid(pid);
        return new JsonResult<>(OK,data);
    }

}

