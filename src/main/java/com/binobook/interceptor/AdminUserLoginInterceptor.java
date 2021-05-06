package com.binobook.interceptor;

import com.binobook.exceptions.NoLoginException;
import com.binobook.service.AdminUserService;
import com.binobook.utils.UserIDBase64;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Admin Side Interceptor
 *
 * @author 13
 * XXXXXX
 * @email XXXXXX@qq.com
 * @link
 */
@Component
public class AdminUserLoginInterceptor implements HandlerInterceptor {


    @Resource
    private AdminUserService adminUserService;
    private Object Session_Content;
    private Object content;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String uri = request.getRequestURI();
        System.out.println("AdminLoginInterceptor accessed");
        Session_Content =request.getSession().getAttribute("loginAdminUser");
        System.out.println("AdminLoginInterceptor says:Session Content loginUser:"+request.getSession().getAttribute("loginAdminUser"));//uri.startsWith("/admin")
        content =request.getSession().getAttribute("loginAdminUserIdStr");

        System.out.println("AdminLoginInterceptor says: Session Content loginAdminUserIdStr:"+ UserIDBase64.decoderUserID((String) content));//uri.startsWith("/admin")
        if (null == request.getSession().getAttribute("loginAdminUser")) {
            System.out.println("AdminLoginInterceptor says NO!");
            request.getSession().setAttribute("errorMsg", "Please Login");
            response.sendRedirect(request.getContextPath() + "/adminLogin");
            throw new NoLoginException();
//            return false;
        } else {
            System.out.println("AdminLoginInterceptor says pass");
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
