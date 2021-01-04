package com.dropapp.app.filter;

import com.dropapp.app.util.Constant;
import com.dropapp.app.util.JwtUtil;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;


public class JwtFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        logger.info("################### In JwtFilter");
        JSONObject errorResponse = null;
        try {
            String path = httpRequest.getRequestURI().split("/")[0];
            if (!path.equals("register") && !path.equals("login")) {
                String token = httpRequest.getHeader("token");
                if (token != null && !token.isEmpty()) {
                    JwtUtil.validateToken(token);
                    chain.doFilter(request, response);

                } else {
                    errorResponse = new JSONObject();
                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    errorResponse.put("code", Constant.UNAUTHORIZED_CODE);
                    errorResponse.put("message", "Token not found.");
                    out.print(errorResponse);
                    out.flush();
                }
            } else {
                chain.doFilter(request, response);
            }
        } catch (Exception e) {
            errorResponse = new JSONObject();
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            errorResponse.put("code", Constant.UNAUTHORIZED_CODE);
            errorResponse.put("message", "Invalid token.");
            out.print(errorResponse);
            out.flush();
        }
    }
}
