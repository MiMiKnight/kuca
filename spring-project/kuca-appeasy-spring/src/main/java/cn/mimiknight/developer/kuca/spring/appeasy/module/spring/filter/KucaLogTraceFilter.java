package cn.mimiknight.developer.kuca.spring.appeasy.module.spring.filter;

import cn.mimiknight.developer.kuca.spring.appeasy.utils.KucaAppEasyUtils;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

/**
 * 日志跟踪过滤器
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2023-09-10 21:36:42
 */
public class KucaLogTraceFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            // 设置当前请求线程中的跟踪ID
            KucaAppEasyUtils.setThreadTraceId();
            // 执行被跟踪的代码逻辑
            chain.doFilter(request, response);
        } finally {
            // 清除当前请求线程中的跟踪ID
            KucaAppEasyUtils.clearThreadTraceId();
        }
    }

}
