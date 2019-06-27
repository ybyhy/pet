package cn.edu.cdu.yhy.controller;

import cn.edu.cdu.yhy.bean.Income;
import cn.edu.cdu.yhy.bean.Pet;
import cn.edu.cdu.yhy.bean.User;
import cn.edu.cdu.yhy.mapper.IncomeMapper;
import cn.edu.cdu.yhy.mapper.PetMapper;
import cn.edu.cdu.yhy.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: 羊泓运
 * @Date: 2019/5/8 18:34
 * @Version 1.0
 */
@Controller
public class UserController {

    @Autowired
    IncomeMapper incomeMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PetMapper petMapper;

    @GetMapping("/shopkeeper/index")
    public String selectIncomeByLastSeven(HttpSession session){
        List<Income> list=incomeMapper.selectIncomeByLastSeven();
        List<String> dateList=new ArrayList<>();
        List<Float> incomeList=new ArrayList<>();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        for(int i=0;i<list.size();i++){
            dateList.add(df.format(list.get(i).getDate()));
            incomeList.add(list.get(i).getIncome());
        }
        Collections.reverse(dateList);
        Collections.reverse(incomeList);
        session.setAttribute("date",dateList);
        session.setAttribute("income",incomeList);
        return "shopkeeper/index";
    }

    @GetMapping("/shopkeeper/users")
    public String list(Model model){
        int pageNum=0;
        PageHelper.startPage(pageNum,10);
        List<User> users=userMapper.selectAll();
        long total=new PageInfo<>(users).getTotal();
        int numPages;
        if(total%10==0)
            numPages=(new Long(total).intValue()/10);
        else
            numPages=(new Long(total).intValue()/10)+1;
        model.addAttribute("users",users);
        model.addAttribute("numPages",numPages);
        model.addAttribute("pageNum",pageNum);
        return "shopkeeper/userManagement/userManagement";
    }

    @GetMapping("/shopkeeper/users/{pageNum}")
    public String list(Model model,@PathVariable("pageNum") int pageNum){
        PageHelper.startPage(pageNum,10);
        List<User> users=userMapper.selectAll();
        long total=new PageInfo<>(users).getTotal();
        int numPages;
        if(total%10==0)
            numPages=(new Long(total).intValue()/10);
        else
            numPages=(new Long(total).intValue()/10)+1;
        model.addAttribute("users",users);
        model.addAttribute("numPages",numPages);
        model.addAttribute("pageNum",pageNum);
        return "shopkeeper/userManagement/userManagement";
    }

    @GetMapping("/shopkeeper/users/insertUserHtml")
    public String InsertUserHtml(){
        return "shopkeeper/userManagement/userOperation/insertUser";
    }

    @PostMapping("/shopkeeper/users/insertUser")
    public String insertUser(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("gender") int gender){
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setGender(gender);
        user.setBalance(0.0);
        userMapper.insertUser(user);
        return "redirect:/shopkeeper/users";
    }

    @GetMapping("/shopkeeper/user/{id}")
    public String selectUserById(@PathVariable("id") int id,Model model){
        User user=userMapper.selectUserById(id);
        model.addAttribute("user",user);
        return "shopkeeper/userManagement/userOperation/updateUser";
    }

    @PostMapping("/shopkeeper/user/updateUser")
    public String updateUser(@RequestParam("id") Integer id,
                             @RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("name") String name,
                             @RequestParam("gender") Integer gender,
                             @RequestParam("age") Integer age,
                             @RequestParam("phone") String phone,
                             @RequestParam("email") String email){
        if(name.equals(""))
            name=null;
        if(phone.equals(""))
            phone=null;
        if(email.equals(""))
            email=null;
        User user=new User(id,username,password,name,gender,age,phone,email);
        userMapper.updateUser(user);
        return "redirect:/shopkeeper/users";
    }

    @GetMapping("/shopkeeper/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        userMapper.deleteUserById(id);
        return "redirect:/shopkeeper/users";
    }

    @GetMapping("/user/adopt/{id}")
    public String adopt(@PathVariable("id") Integer id){
        petMapper.deletePetById(id);
        return "redirect:/user/pets";
    }

    @GetMapping("/user/myInfo/{id}")
    public String myInfo(Model model,@PathVariable("id") Integer id){
        User user=userMapper.selectUserById(id);
        model.addAttribute("user",user);
        return "user/myInfo";
    }

    @PostMapping("/user/update/{id}")
    public String update(@PathVariable("id") Integer id,
                             @RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("name") String name,
                             @RequestParam("gender") Integer gender,
                             @RequestParam("age") Integer age,
                             @RequestParam("phone") String phone,
                             @RequestParam("email") String email){
        User user=new User(id,username,password,name,gender,age,phone,email);
        userMapper.updateUser(user);
        return "redirect:/user/myInfo/"+id;
    }

    @GetMapping("/user/myAccount/{id}")
    public String myAccount(Model model,@PathVariable("id") Integer id){
        User user=userMapper.selectUserById(id);
        model.addAttribute("user",user);
        return "user/myAccount";
    }

    @PostMapping("/user/recharge/{id}")
    public String recharge(Model model,@PathVariable("id") Integer id,
                            @RequestParam("balance") double balance){
        User user=userMapper.selectUserById(id);
        User update=new User();
        update.setId(id);
        update.setBalance(balance+user.getBalance());
        userMapper.updateBalance(update);
        user=userMapper.selectUserById(id);
        model.addAttribute("user",user);
        return "user/myAccount";
    }

    @GetMapping("/user/index/{id}")
    public String index(@PathVariable("id") Integer id,
                        Model model){
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
        model.addAttribute("id",id);
        return "user/index";
    }
}
