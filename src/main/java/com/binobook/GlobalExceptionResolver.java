package com.binobook;

import com.alibaba.fastjson.JSON;
import com.binobook.base.ResultInfo;
import com.binobook.exceptions.AuthException;
import com.binobook.exceptions.NoLoginException;
import com.binobook.exceptions.ParamsException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    /**
     * Exception Handler
     *  Return F：
     *      1. View
     *      2. JSON
     *
     *  How to decide returned format？
     *      Verify if @ResponseBody exists, if yes, return JSON, else return veiw
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        /**
         */
        if (ex instanceof NoLoginException) {
            // redirect to login page
            ModelAndView mv = new ModelAndView("redirect:/login");
            return mv;
        }


        /**
         * default handler（return view）
         */
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("code",500);
        modelAndView.addObject("msg","System Error，Try AGain...");


        // Verify HandlerMethod
        if (handler instanceof HandlerMethod) {
            // Type Conversion
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // Obtain Response Body Object
            ResponseBody responseBody = handlerMethod.getMethod().getDeclaredAnnotation(ResponseBody.class);

            // Determine if ResponseBody is empty （if it is，return view；else，return view data）
            if (responseBody == null) {
                /**
                 * return view
                 */
                // Verify Error Exception Type
                if (ex instanceof ParamsException) {
                    ParamsException p = (ParamsException) ex;
                    // Set Exception msg
                    modelAndView.addObject("code",p.getCode());
                    modelAndView.addObject("msg",p.getMsg());

                } else if (ex instanceof AuthException) {
                    AuthException a  = (AuthException) ex;
                    // Set Exception msg
                    modelAndView.addObject("code",a.getCode());
                    modelAndView.addObject("msg",a.getMsg());
                }

                return modelAndView;

            } else {
                /**
                 * return JSON
                 */
                // set default handing method
                ResultInfo resultInfo = new ResultInfo();
                resultInfo.setCode(500);
                resultInfo.setMsg("Error, please try again！");

                // Verify if it is self defined exception
                if (ex instanceof ParamsException) {
                    ParamsException p = (ParamsException) ex;
                    resultInfo.setCode(p.getCode());
                    resultInfo.setMsg(p.getMsg());

                } else if (ex instanceof AuthException) { //
                    AuthException a = (AuthException) ex;
                    resultInfo.setCode(a.getCode());
                    resultInfo.setMsg(a.getMsg());
                }

                // set response body and format of code
                response.setContentType("application/json;charset=UTF-8");

                PrintWriter out = null;
                try {
                    out = response.getWriter();
                    String json = JSON.toJSONString(resultInfo);
                    out.write(json);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (out != null) {
                        out.close();
                    }
                }
                return null;
            }
        }
        return modelAndView;
    }
}
