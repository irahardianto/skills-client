package skills.example.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(SecurityProperties.DEFAULT_FILTER_ORDER-1)
public class VueEntryPointFilter implements Filter {

    @Autowired
    private VueEntryPointFilterUtils vueEntryPointFilterUtils;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String requestUri = httpServletRequest.getRequestURI();
        if (vueEntryPointFilterUtils.isFrontendResource(requestUri)) {
            // frontend resource, forward to the UI for vue-js to handle

            if (isNativeJSEntryPoint(requestUri)) {
//                httpServletResponse.sendRedirect("/native/index.html");
                filterChain.doFilter(request, response);
            } else {
                String targetUri = requestUri;
                if (isVueEntryPoint(requestUri)) {
                    targetUri = "/vuejs/index.html";
                } else if(isAppEntryPoint(requestUri)) {
                    targetUri = "/index.html";
                }
                httpServletRequest.getRequestDispatcher(targetUri).forward(request, response);
            }
        } else {
            // backend resource, continue with the filter chain
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void destroy() { }

    private boolean isVueEntryPoint(String requestUri) {
        return requestUri.replaceAll("\\/", "").equals("vuejs");
    }

    private boolean isNativeJSEntryPoint(String requestUri) {
        return requestUri.equals("/native");
    }

    private boolean isAppEntryPoint(String requestUri) {
        return requestUri.equals("/") || requestUri.equals("/index.html");
    }
}
