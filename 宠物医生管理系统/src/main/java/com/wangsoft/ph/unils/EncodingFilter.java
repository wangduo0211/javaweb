package com.wangsoft.ph.unils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//过滤器
@WebFilter(filterName ="EncodingFilter",urlPatterns = "/*")
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("创建编码过滤器");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        // 对所有经过的链接请求设置编码
        request.setCharacterEncoding("utf-8");
        //请求继续传递
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
        System.out.println("销毁编码过滤器");
    }
}
