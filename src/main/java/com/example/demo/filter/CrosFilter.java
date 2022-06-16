package com.example.demo.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * 当客户端发出Web资源的请求时，Web服务器根据应用程序配置文件设置的过滤规则进行检
 * 查，若客户请求满足过滤规则，则对客户请求／响应进行拦截，对请求头和请求数据进行检
 * 查或改动，并依次通过过滤器链，最后把请求／响应交给请求的Web资源处理。请求信息在
 * 过滤器链中可以被修改，也可以根据条件让请求不发往资源处理器，并直接向客户机发回一
 * 个响应。当资源处理器完成了对资源的处理后，响应信息将逐级逆向返回。同样在这个过程
 * 中，用户可以修改响应信息，从而完成一定的任务
 */

@Component
@Slf4j
public class CrosFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("模拟CROS过滤器");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
