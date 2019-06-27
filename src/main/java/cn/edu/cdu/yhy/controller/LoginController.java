package cn.edu.cdu.yhy.controller;

import cn.edu.cdu.yhy.bean.Income;
import cn.edu.cdu.yhy.bean.Pet;
import cn.edu.cdu.yhy.bean.Shopkeeper;
import cn.edu.cdu.yhy.bean.User;
import cn.edu.cdu.yhy.mapper.IncomeMapper;
import cn.edu.cdu.yhy.mapper.PetMapper;
import cn.edu.cdu.yhy.mapper.ShopkeeperMapper;
import cn.edu.cdu.yhy.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Author: 羊泓运
 * @Date: 2019/5/7 23:19
 * @Version 1.0
 */
@Controller
public class LoginController {

    @Autowired
    ShopkeeperMapper shopkeeperMapper;

    @Autowired
    IncomeMapper incomeMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PetMapper petMapper;

    @PostMapping(value = "/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("visitor") Integer visitor,
                        @RequestParam("checkbox") Integer checkbox,
                        Map<String,Object> map, HttpSession session, Model model, HttpServletResponse response){
        Cookie cookie=null;
        String user=null;
        if(visitor==0){
            Shopkeeper shopkeeper=shopkeeperMapper.selectShopkeeperByUsername(username);
            if(shopkeeper!=null&&shopkeeper.getPassword().equals(password)) {
                //登陆成功,放置表单重复提交，可以重定向到主页
                session.setAttribute("loginUser",shopkeeper.getName());
                if(checkbox==1){
                    user=shopkeeper.getUsername()+"#"+shopkeeper.getPassword();
                    cookie=new Cookie("user",user);
                    cookie.setMaxAge(1*24*60*60);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
                else if(checkbox==0){
                    cookie=new Cookie("user",null);
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
                return "redirect:/shopkeeper/index";
            }else {
                //登陆失败
                map.put("msg","用户名密码错误");
                return "login";
            }
        }
        else if(visitor==1){
            User users=userMapper.selectUserByUsername(username);
            if(users!=null&&users.getPassword().equals(password)){
                int pageNum=0;
                PageHelper.startPage(pageNum,8);
                List<Pet> pets=petMapper.selectAll();
                long total=new PageInfo<>(pets).getTotal();
                int numPages;
                if(total%8==0)
                    numPages=(new Long(total).intValue()/8);
                else
                    numPages=(new Long(total).intValue()/8)+1;
                model.addAttribute("pet",pets);
                model.addAttribute("pets","pets");
                model.addAttribute("numPages",numPages);
                model.addAttribute("pageNum",pageNum);
                session.setAttribute("id",users.getId());
                session.setAttribute("loginUser",users.getName());
                if(checkbox==1){
                    user=users.getUsername()+"#"+users.getPassword();
                    cookie=new Cookie("user",user);
                    cookie.setMaxAge(1*24*60*60);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
                else if(checkbox==0){
                    cookie=new Cookie("user",null);
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
                return "user/index";
            }else {
                map.put("msg","用户名密码错误");
                return "login";
            }
        }
        else {
            map.put("msg","用户名密码错误");
            return "login";
        }
    }

    @GetMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }
}
