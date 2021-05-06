package com.binobook.interceptor;

import com.binobook.exceptions.NoLoginException;
import com.binobook.service.MallUserService;
import com.binobook.utils.UserIDBase64;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台系统身份验证拦截器
 *
 * @author 13
 * XXXXXX
 * @email XXXXXX@qq.com
 * @link
 */
@Component
public class MallUserLoginInterceptor implements HandlerInterceptor {


    @Resource
    private MallUserService mallUserService;
    private Object Session_Content;
    private Object content;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String uri = request.getRequestURI();
        System.out.println("MallUserLoginInterceptor accessed");
        Session_Content =request.getSession().getAttribute("loginMallUser");
        System.out.println("MallUserLoginInterceptor says:Session Content loginUser:"+request.getSession().getAttribute("loginMallUser"));//uri.startsWith("/admin")
        content =request.getSession().getAttribute("loginMallUserIdStr");

        System.out.println("MallUserLoginInterceptor says: Session Content loginMallUserIdStr:"+ UserIDBase64.decoderUserID((String) content));//uri.startsWith("/admin")
        if (null == request.getSession().getAttribute("loginMallUser")) {
            System.out.println("MallUserLoginInterceptor says NO!");
            request.getSession().setAttribute("errorMsg", "Please Login");
            response.sendRedirect(request.getContextPath() + "/mallUserLogin");
            throw new NoLoginException();
//            return false;
        } else {
            System.out.println("MallUserLoginInterceptor says pass");
            request.getSession().removeAttribute("errorMsg");
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
