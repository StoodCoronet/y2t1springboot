package com.stu_course.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
@Configuration
public class MyWebConfig extends WebMvcConfigurationSupport {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MVCInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/show_login_web")
                .excludePathPatterns("/register")
                .excludePathPatterns("/js/*")
                .excludePathPatterns("/css/*")
                .excludePathPatterns("/image/*")
                .excludePathPatterns("/css/img/*")
                .excludePathPatterns("/error")
                .excludePathPatterns("/css/font/*")
                .excludePathPatterns("/*.js")
                .excludePathPatterns("/*.html")
                .excludePathPatterns("register_student_info")
                .excludePathPatterns("/check_login_web");
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/static/**").addResourceLocations("/static/");
        //registry.addResourceHandler("/templates/**").addResourceLocations("/templates/");
        //super.addResourceHandlers(registry);
    }
}
