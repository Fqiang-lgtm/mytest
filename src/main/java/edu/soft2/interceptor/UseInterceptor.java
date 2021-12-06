package edu.soft2.interceptor;

import edu.soft2.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UseInterceptor implements HandlerInterceptor {
    /**
     *相当于aop的前置增强方法
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
       //从session获取user对象
       o = request.getSession().getAttribute("user");
       if (o != null &&o instanceof User){
         return true;//返回true，通过拦截器
       }else {
           //跳转至登录页面
           System.out.println("被拦截");
           request.getRequestDispatcher("index").forward(request, response);
           return false;//返回false,被拦截器阻断
       }
    }

    /**
     *相当于aop的后置增强方法
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

    }
}
