package yjr1100.ServletAPI;/*
 * @Auther:YJR-1100
 * @Date:2021/11/28 - 11 - 28 - 11:17
 * @Version:1.0
 * @Description:
 *
 */

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class pathFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        String url = httpServletRequest.getHeader("referer");
        //System.out.println(url);
        if(httpServletRequest.getRequestURI().equals("/webLogin_war_exploded/")||httpServletRequest.getRequestURI().equals("/webLogin_war_exploded/index.jsp")||(url!=null&&(url.contains("http://localhost:8080/webLogin_war_exploded/")||url.endsWith(".jsp")))){
            chain.doFilter(httpServletRequest, httpServletResponse);
        }else{
            System.out.println("被执行了");
            if(url==null||!url.endsWith(".jsp")){
                String contextPath = httpServletRequest.getContextPath();
                httpServletResponse.sendRedirect(contextPath + "/index.jsp");
                return;
            }
            chain.doFilter(httpServletRequest, httpServletResponse);
        }

        //    放行
    }
}
