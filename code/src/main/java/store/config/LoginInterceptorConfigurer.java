package com.bistu.store.config;

import com.bistu.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 完成处理器拦截器的注册，若不注册则功能无法实现
 */
@Configuration//加载当前的拦截器并进行注册
public class LoginInterceptorConfigurer implements WebMvcConfigurer {

    /**
     * 配置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //创建自定义拦截器对象
        HandlerInterceptor interceptor=new LoginInterceptor();

        //配置白名单（可以在不登录状态下查看信息）,存放在一个List集合中
        List<String> patterns=new ArrayList<>();
        patterns.add("/bootstrap3/**");//放行静态资源
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register.html");
        patterns.add("/web/login.html");
        patterns.add("/web/product.html");
        patterns.add("/web/index.html");
        patterns.add("/index.html");
        patterns.add("/user/reg");
        patterns.add("/user/login");
        patterns.add("/district/**");
        patterns.add("/address");
        patterns.add("/product/**");
        patterns.add("/web/index_products.html");


        //完成拦截器注册
        // 添加拦截器   添加路径参数表示要拦截的url是什么
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**").excludePathPatterns(patterns); //添加路径参数表示要拦截的url是什么,除了什么
    }
}
