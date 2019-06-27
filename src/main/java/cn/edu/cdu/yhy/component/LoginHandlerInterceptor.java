package cn.edu.cdu.yhy.component;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 羊泓运
 * @Date: 2019/5/18 14:18
 * @Version 1.0
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception{
        Object user=request.getSession().getAttribute("loginUser");
        if(user==null){
            request.setAttribute("msg","请先登陆");
            request.getRequestDispatcher("/").forward(request,response);
            return false;
        }else {
            return true;
        }
    }
}
