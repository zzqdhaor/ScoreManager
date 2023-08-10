package zq.scoremanager.registry;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import zq.scoremanager.interceptor.AuthInterceptor;
import zq.scoremanager.interceptor.UniversalInterceptor;

@Configuration
public class MyInterceptorRegistry implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UniversalInterceptor());
        registry.addInterceptor(new AuthInterceptor());
        WebMvcConfigurer.super.addInterceptors(registry);
    }

}
