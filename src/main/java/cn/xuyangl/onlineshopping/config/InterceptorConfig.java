package cn.xuyangl.onlineshopping.config;

import cn.xuyangl.onlineshopping.interceptor.AdminInterceptor;
import cn.xuyangl.onlineshopping.interceptor.BuyerInterceptor;
import cn.xuyangl.onlineshopping.interceptor.SellerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * @author xjin
 * created on 2018/12/8 3:46 PM
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private BuyerInterceptor buyerInterceptor;

    private SellerInterceptor sellerInterceptor;

    private AdminInterceptor adminInterceptor;

    @Autowired
    public InterceptorConfig(BuyerInterceptor buyerInterceptor,SellerInterceptor sellerInterceptor, AdminInterceptor adminInterceptor) {
        this.buyerInterceptor = buyerInterceptor;
        this.sellerInterceptor = sellerInterceptor;
        this.adminInterceptor = adminInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(buyerInterceptor).addPathPatterns("/buyer/**").excludePathPatterns("/buyer/register", "/buyer/login","/buyer/order/**","/buyer/findPassword","/buyer/orderByCart");
        registry.addInterceptor(sellerInterceptor).addPathPatterns("/seller/**").excludePathPatterns("/seller/register","/seller/login");
        registry.addInterceptor(adminInterceptor).addPathPatterns("/admin/personal/**");
    }
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {

    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

    }

    @Override
    public void addFormatters(FormatterRegistry registry) {

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {

    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {

    }

    @Override
    public Validator getValidator() {
        return null;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return null;
    }
}
