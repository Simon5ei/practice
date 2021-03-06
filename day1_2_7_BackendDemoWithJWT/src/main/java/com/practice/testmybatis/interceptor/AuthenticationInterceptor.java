package com.practice.testmybatis.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.practice.testmybatis.auth.PassToken;
import com.practice.testmybatis.auth.UserLoginToken;
import com.practice.testmybatis.domain.User;
import com.practice.testmybatis.mapper.UserNameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private UserNameMapper userNameMapper;

    @Override
    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    getError(httpServletResponse,"No token Plz ReLogin");
                    return false;
                }
                String username = null;
                try {
                    username = JWT.decode(token).getClaims().get("name").asString();
                } catch (JWTDecodeException j) {
                    getError(httpServletResponse,"Wrong Json");
                    return false;
                }
                User user = userNameMapper.queryNameMapper(username);
                if (user == null) {
                    getError(httpServletResponse,"No user");
                    return false;
                }
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("1234")).build();

                try {
                    System.out.println(jwtVerifier.verify(token));
                    System.out.println("name:"+JWT.decode(token).getClaim("name").asString());
                } catch (JWTVerificationException e) {
                    getError(httpServletResponse,"Wrong Json");
                    return false;
                }
                return true;
            }
        }
        return true;
    }
    private void getError(HttpServletResponse httpServletResponse,String msg) throws IOException {
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        JSONObject res = new JSONObject();
        res.put("status","-1");
        res.put("msg",msg);
        PrintWriter out = null ;
        out = httpServletResponse.getWriter();
        out.write(res.toString());
        out.flush();
        out.close();
    }
    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}