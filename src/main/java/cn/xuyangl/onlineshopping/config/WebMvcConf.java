package cn.xuyangl.onlineshopping.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.persistence.Entity;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2019/1/6 21:44
 */
@Configuration
public class WebMvcConf extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

        // 上传的图片保存在本地
        registry.addResourceHandler("/Users/mac/Downloads/images/**").addResourceLocations("file:///Users/mac/Downloads/images/");
        // windows
//        registry.addResourceHandler("/images/**").addResourceLocations("file:D:/images/");
    }
}
