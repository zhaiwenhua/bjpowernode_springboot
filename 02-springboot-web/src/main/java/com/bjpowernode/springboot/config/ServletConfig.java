package com.bjpowernode.springboot.config;

import com.bjpowernode.springboot.filter.HeFilter;
import com.bjpowernode.springboot.servlet.HeServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
//配置类

/**
 * spring boot没有xml，@Configuration可以表示一个spring的xml配置文件
 * 比如：applicationContext.xml
 * 这是 Spring 3.0 添加的一个注解，
 * 用来代替 applicationContext.xml 配置文件，
 * 所有这个配置文件里面能做到的事情都可以通过这个注解所在类来进行注册。
 */
@Configuration
public class ServletConfig {
    //@Bean是一个方法级别上的注解，
    // 主要用在@Configuration注解的类里，
    // 也可以用在@Component注解的类里。
    // 添加的bean的id为方法名

    /**
     * <bean id="heServletRegistrationBean"
     * class="org.springframework.boot.web.servlet.ServletRegistrationBean">
     * </bean>
     * @return
     */
    @Bean
    public ServletRegistrationBean heServletRegistrationBean(){
        ServletRegistrationBean registration  = new ServletRegistrationBean(new HeServlet(),"/heServlet");
        return registration;
    }

    @Bean
    public FilterRegistrationBean heFilterRegistration(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new HeFilter());
        registrationBean.addUrlPatterns("/*");
        return  registrationBean;
    }

    /**
     * 等价于web.xml配置characterEncodingFilter
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding("UTF-8");
        registrationBean.setFilter(characterEncodingFilter);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

}
