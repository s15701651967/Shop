package com.bistu.store.controller;

import com.bistu.store.controller.ex.*;
import com.bistu.store.service.ex.*;
import com.bistu.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**控制层类的基类*/
public class BaseController {//异常的捕获处理，相当于拦截器

    /**操作成功的状态码*/
    public static final int OK=200;

    //请求处理方法，方法返回值是需要传递给前端的数据,数据包装类型为JsonResult
    //自动将异常对象传递给此方法的参数列表上
    //当前项目中产生了异常，被统一拦截到此方法中，该方法此时充当的为请求处理方法，方法返回值直接给到前端
    @ExceptionHandler({ServiceException.class,FileUploadException.class})//用于统一处理抛出的异常,业务层所有异常的基类
    //凡是抛出的异常是ServiceException均会被拦截到该方法，
    public JsonResult<Void> handlerException(Throwable e){
        JsonResult<Void> result=new JsonResult<>(e);
        if(e instanceof UsernameDuplicateException){//4000开头的为细节的异常
            result.setState(4000);
            result.setMessage("用户名被占用");
        }else if(e instanceof UsernameDuplicateException){
            result.setState(4001);
            result.setMessage("用户名被占用");
        }else if(e instanceof UsernameDuplicateException){
            result.setState(4002);
            result.setMessage("用户名被占用");
        }else if(e instanceof AddressCountLimitException){
            result.setState(4003);
            result.setMessage("用户名收货地址超出上限");
        }else if(e instanceof AddressNotFoundException) {
            result.setState(4004);
            result.setMessage("用户名收货地址数据不存在异常");
        }else if(e instanceof AccessDeniedException){
            result.setState(4005);
            result.setMessage("收货地址数据非法访问的异常");
        }else if(e instanceof ProductNotFoundException){
            result.setState(4006);
            result.setMessage("商品数据不存在的异常");
        }else if(e instanceof ProductNotFoundException){
            result.setState(4007);
            result.setMessage("购物车数据不存在的异常");
        }
        else if(e instanceof AddMoneyException){
            result.setState(4008);
            result.setMessage("充值失败");
        }else if(e instanceof InsertException){//5000开始的为比较笼统的异常
            result.setState(5000);
            result.setMessage("注册时产生未知的异常");
        }else if(e instanceof UserNotFoundException){//instanceof测试它左边的对象是否是它右边的类的实例
            result.setState(5001);
            result.setMessage("用户数据不存在异常或用户未审核");
        }else if(e instanceof PassWordNotMatchException){//instanceof测试它左边的对象是否是它右边的类的实例
            result.setState(5002);
            result.setMessage("用户名的密码错误的异常");
        }else if(e instanceof InsertException){//instanceof测试它左边的对象是否是它右边的类的实例
            result.setState(5003);
            result.setMessage("更新数据时产生未知的异常");
        }else if(e instanceof DeleteException){//instanceof测试它左边的对象是否是它右边的类的实例
            result.setState(5004);
            result.setMessage("删除数据时产生未知的异常");
        }else if (e instanceof FileEmptyException) {//6000主要是文件 头像方面的异常
            result.setState(6000);
            result.setMessage("FileEmptyException");
        } else if (e instanceof FileSizeException) {
            result.setState(6001);
            result.setMessage("FileSizeException");
        } else if (e instanceof FileTypeException) {
            result.setState(6002);
            result.setMessage("FileTypeException");
        } else if (e instanceof FileStateException) {
            result.setState(6003);
            result.setMessage("FileStateException");
        } else if (e instanceof FileUploadIOException) {
            result.setState(6004);
            result.setMessage("FileUploadIOException");
        }
        return result;
    }

    /**
     * 获取session对象中的uid
     * @param session session对象
     * @return 当前登录的用户uid的值
     */
    protected final Integer getuidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 获取当前登录的username
     * @param session session对象
     * @return 当前登录用户的用户名
     *
     * 实现类中重写父类中的toString（）,非句柄信息输出
     */
    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }


}
