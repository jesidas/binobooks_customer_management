package com.binobook.aspect;

import com.binobook.annoation.RequiredPermission;
import com.binobook.exceptions.AuthException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Component
@Aspect
public class PermissionProxy {

    @Resource
    private HttpSession session;

    /**
     *  Intercept com.binobook.annoation the RequiredPermission Annotation
     * @return java.lang.Object
     */
    @Around(value = "@annotation(com.binobook.annoation.RequiredPermission)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null;
        System.out.println("Here is the permission proxies!");
        //  Get the permission of the current login user  （by session）
        List<String> permissions = (List<String>) session.getAttribute("permissions");
        System.out.println("Permissions="+permissions.toString());
        //Verify whether the user has permission or not
        if (null == permissions || permissions.size() < 1) {
            // 抛出认证异常
            System.out.println("Permission proxy says: The User is not authorized!");
            throw  new AuthException();
        }

        // Get the corresponding goal
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        System.out.println("methodSignature="+methodSignature.toString());
        //  Get annotation on the method
        RequiredPermission requiredPermission = methodSignature.getMethod().getDeclaredAnnotation(RequiredPermission.class);
        System.out.println("requiredPermission="+requiredPermission);
        // Determine the corresponding status code on the annotation
        if (!(permissions.contains(requiredPermission.code()))) {
            //If the permission does not contain the permission code
            // specified by the annotation on the current method,
            // an exception is thrown
            System.out.println("The User has no auhorization ");
            throw new AuthException();
        }
        System.out.println("Allowed!");
        result = pjp.proceed();
        return result;
    }

}
