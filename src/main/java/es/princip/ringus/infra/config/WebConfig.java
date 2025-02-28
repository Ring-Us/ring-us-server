package es.princip.ringus.infra.config;

import es.princip.ringus.global.interceptor.SessionInterceptor;
import es.princip.ringus.global.resolver.SessionMemberIdArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final SessionInterceptor sessionInterceptor;
    private final SessionMemberIdArgumentResolver sessionMemberIdArgumentResolver;

    @Autowired
    public WebConfig(SessionInterceptor sessionInterceptor, SessionMemberIdArgumentResolver sessionMemberIdArgumentResolver) {
        this.sessionInterceptor = sessionInterceptor;
        this.sessionMemberIdArgumentResolver = sessionMemberIdArgumentResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor)
                .excludePathPatterns("/auth/**")
                .excludePathPatterns("/service-terms")
                .excludePathPatterns("/swagger-ui/**")
                .excludePathPatterns("/v3/api-docs/**")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/webjars/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(sessionMemberIdArgumentResolver);
    }
}
