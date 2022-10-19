package com.bistu.store.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 * 定义一个拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 检测全局session对象中是否有uid数据，若有则放行，若无则重定向到登录页面
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 处理器（url + controller映射到一起）
     * @return 若返回值为true 表示放行当前的请求  若返回值为false 表示拦截当前的请求
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        //HttpServletRequest对象来获取session对象
        Object obj=request.getSession().getAttribute("uid");
        if(obj==null){//说明用户为登录过系统，则重定向到login.html页面
            response.sendRedirect("/web/login.html");
            //结束后续调用
            return false;
        }
        //请求可以通过，放行请求
        return true;
    }
}
