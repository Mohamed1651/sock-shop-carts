package works.weave.socks.cart.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import works.weave.socks.cart.middleware.HTTPMonitoringInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public HTTPMonitoringInterceptor httpMonitoringInterceptor() {
        return new HTTPMonitoringInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Call the bean method directly here
        registry.addInterceptor(httpMonitoringInterceptor())
                .addPathPatterns("/**");
    }
}
