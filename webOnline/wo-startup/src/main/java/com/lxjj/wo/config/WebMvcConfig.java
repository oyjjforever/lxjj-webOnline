package com.lxjj.wo.config;

import org.apache.tomcat.util.buf.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Autowired
    Interceptor interceptor;

    @Bean
    public HttpPutFormContentFilter httpPutFormContentFilter() {
        return new HttpPutFormContentFilter();
    }

    /**
     * 静态资源配置
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Spting Boot默认静态资源
        registry
                .addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/ ", "classpath:/resources/", "classpath:/static/ ", "classpath:/public/");
        // swagger静态资源配置
        registry
                .addResourceHandler("swagger-ui.html", "doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }

    /**
     * interceptor配置
     */
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(interceptor)
                .addPathPatterns("/api/**");
    }

    /**
     * 跨域设置
     */
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowCredentials(true);
    }
}

@Component
class Interceptor implements HandlerInterceptor {
    long start = System.currentTimeMillis();
    StringBuffer requestInfo = new StringBuffer();
    private static Logger log = LoggerFactory.getLogger(Interceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object o) {
        start = System.currentTimeMillis();
        requestInfo.append("\r\n========请求信息Begin========\r\n");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse resp, Object o, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object o, Exception e) {
        requestInfo.append("远程IP：" + req.getRemoteAddr() + System.getProperty("line.separator"));
        requestInfo.append("请求地址：" + req.getRequestURL() + System.getProperty("line.separator"));
        requestInfo.append("参数信息：");
        Map<String, String[]> params = req.getParameterMap();
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            requestInfo.append(entry.getKey() + "=");
            if (entry.getValue().length > 1) {
                requestInfo.append("[" + StringUtils.join(Arrays.asList(entry.getValue()), ',') + "]");
            } else {
                requestInfo.append(StringUtils.join(Arrays.asList(entry.getValue()), ','));
            }
            requestInfo.append("，");
        }
        if (params.size() == 0) {
            requestInfo.append("无");
        } else {
            requestInfo = new StringBuffer(requestInfo.substring(0, requestInfo.length() - 1));
        }
        requestInfo.append(System.getProperty("line.separator") + "耗时：" + (System.currentTimeMillis() - start) + "毫秒" + System.getProperty("line.separator"));
        requestInfo.append("========请求信息End========" + System.getProperty("line.separator"));
        log.info(requestInfo.toString());
        requestInfo.setLength(0);
    }
}
