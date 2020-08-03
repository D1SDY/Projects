package cz.xentalib.interceptors;

import cz.xentalib.interceptors.UserServiceInterceptors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class UserServiceInterceporsAppConfig extends WebMvcConfigurerAdapter {
    @Autowired
    UserServiceInterceptors userServiceInterceptors;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userServiceInterceptors);
    }
}
