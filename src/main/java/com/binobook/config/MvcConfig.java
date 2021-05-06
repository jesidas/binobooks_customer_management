package com.binobook.config;

import com.binobook.interceptor.AdminUserLoginInterceptor;
import com.binobook.interceptor.MallUserLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Configuration//Reference:https://blog.51cto.com/12066352/2093750
public class MvcConfig implements WebMvcConfigurer { //public class MvcConfig extends WebMvcConfigurerAdapter

    @Autowired
    private MallUserLoginInterceptor mallUserLoginInterceptor;
    @Autowired
    private AdminUserLoginInterceptor adminUserLoginInterceptor;

    /**
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        String[] swaggerExcludes = new String[]{"/swagger-ui.html","/swagger-resources/**","/webjars/**"};
        String[] frontEndResources = new String[]{"/css/**","/images/**","/js/**","/lib/**","/views"};
        registry.addInterceptor(adminUserLoginInterceptor)
                .addPathPatterns("/**").excludePathPatterns("/mallUser/**")
                .excludePathPatterns("/mallUser/login").excludePathPatterns(swaggerExcludes)
                .excludePathPatterns("/mallUser/dist/**").excludePathPatterns("/mallUser/plugins/**")
                .excludePathPatterns("/role/**").excludePathPatterns("/definitions/**")
                .excludePathPatterns("/order/**").excludePathPatterns("/userRole/**").excludePathPatterns("/module/**")
                .excludePathPatterns("/mallMain").excludePathPatterns(frontEndResources)
                .excludePathPatterns("/adminLogin","/mallUserLogin","/mallUserRegister","/login","/register","/welcome/**", "/adminUser/login","/mallMain","/sgu");//
        // Mall Interception
        registry.addInterceptor(mallUserLoginInterceptor)
                .addPathPatterns("/**").excludePathPatterns("/adminUser/**")
                .excludePathPatterns("/adminMain/**").excludePathPatterns("/customer/**")
                .excludePathPatterns("/order/**").excludePathPatterns(swaggerExcludes)
                .excludePathPatterns("/role/**").excludePathPatterns("/userRole/**")
                .excludePathPatterns("/module/**").excludePathPatterns("/adminUser/login")
                .excludePathPatterns("/adminUser/dist/**").excludePathPatterns("/adminUser/plugins/**")
                .excludePathPatterns(frontEndResources)
                .excludePathPatterns("/adminLogin","/mallUserLogin","/mallUserRegister","/login","/register","/welcome/**","/mallUser/login","/mallUser/register", "/adminMain");//

    }
}
