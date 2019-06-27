package cn.edu.cdu.yhy.controller;

import cn.edu.cdu.yhy.bean.Goods;
import cn.edu.cdu.yhy.bean.Pet;
import cn.edu.cdu.yhy.bean.User;
import cn.edu.cdu.yhy.mapper.GoodsMapper;
import cn.edu.cdu.yhy.mapper.PetMapper;
import cn.edu.cdu.yhy.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @Author: 羊泓运
 * @Date: 2019/5/10 17:18
 * @Version 1.0
 */
@Controller
public class ShopController {

    @Autowired
    PetMapper petMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    UserMapper userMapper;

    @GetMapping("/user/pets/{pageNum}")
    public String list(Model model, @PathVariable("pageNum") int pageNum){
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
        return "user/index";
    }

    @GetMapping("/user/pets")
    public String list(Model model){
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
        return "user/index";
    }

    @GetMapping("/user/dogs")
    public String dogList(Model model){
        int pageNum=0;
        PageHelper.startPage(pageNum,8);
        List<Pet> pets=petMapper.selectDogByType("狗");
        long total=new PageInfo<>(pets).getTotal();
        int numPages;
        if(total%8==0)
            numPages=(new Long(total).intValue()/8);
        else
            numPages=(new Long(total).intValue()/8)+1;
        model.addAttribute("pet",pets);
        model.addAttribute("pets","dogs");
        model.addAttribute("numPages",numPages);
        model.addAttribute("pageNum",pageNum);
        return "user/index";
    }

    @GetMapping("/user/dogs/{pageNum}")
    public String dogList(Model model,@PathVariable("pageNum") int pageNum){
        PageHelper.startPage(pageNum,8);
        List<Pet> pets=petMapper.selectDogByType("狗");
        long total=new PageInfo<>(pets).getTotal();
        int numPages;
        if(total%8==0)
            numPages=(new Long(total).intValue()/8);
        else
            numPages=(new Long(total).intValue()/8)+1;
        model.addAttribute("pet",pets);
        model.addAttribute("pets","dogs");
        model.addAttribute("numPages",numPages);
        model.addAttribute("pageNum",pageNum);
        return "user/index";
    }

    @GetMapping("/user/cats")
    public String catList(Model model){
        int pageNum=0;
        PageHelper.startPage(pageNum,8);
        List<Pet> pets=petMapper.selectDogByType("猫");
        long total=new PageInfo<>(pets).getTotal();
        int numPages;
        if(total%8==0)
            numPages=(new Long(total).intValue()/8);
        else
            numPages=(new Long(total).intValue()/8)+1;
        model.addAttribute("pet",pets);
        model.addAttribute("pets","cats");
        model.addAttribute("numPages",numPages);
        model.addAttribute("pageNum",pageNum);
        return "user/index";
    }

    @GetMapping("/user/cats/{pageNum}")
    public String catList(Model model,@PathVariable("pageNum") int pageNum){
        PageHelper.startPage(pageNum,8);
        List<Pet> pets=petMapper.selectDogByType("猫");
        long total=new PageInfo<>(pets).getTotal();
        int numPages;
        if(total%8==0)
            numPages=(new Long(total).intValue()/8);
        else
            numPages=(new Long(total).intValue()/8)+1;
        model.addAttribute("pet",pets);
        model.addAttribute("pets","cats");
        model.addAttribute("numPages",numPages);
        model.addAttribute("pageNum",pageNum);
        return "user/index";
    }

    @GetMapping("/user/goods")
    public String goodsList(Model model){
        int pageNum=0;
        PageHelper.startPage(pageNum,10);
        List<Goods> goods=goodsMapper.selectAll();
        long total=new PageInfo<>(goods).getTotal();
        int numPages;
        if(total%10==0)
            numPages=(new Long(total).intValue()/10);
        else
            numPages=(new Long(total).intValue()/10)+1;
        model.addAttribute("Goods",goods);
        model.addAttribute("goods","goods");
        model.addAttribute("numPages",numPages);
        model.addAttribute("pageNum",pageNum);
        return "user/goods";
    }

    @GetMapping("/user/goods/{pageNum}")
    public String goodsList(Model model,@PathVariable("pageNum") int pageNum){
        PageHelper.startPage(pageNum,10);
        List<Goods> goods=goodsMapper.selectAll();
        long total=new PageInfo<>(goods).getTotal();
        int numPages;
        if(total%10==0)
            numPages=(new Long(total).intValue()/10);
        else
            numPages=(new Long(total).intValue()/10)+1;
        model.addAttribute("Goods",goods);
        model.addAttribute("goods","goods");
        model.addAttribute("numPages",numPages);
        model.addAttribute("pageNum",pageNum);
        return "user/goods";
    }

    @GetMapping("/user/toys")
    public String toysList(Model model){
        int pageNum=0;
        PageHelper.startPage(pageNum,10);
        List<Goods> goods=goodsMapper.selectGoodsByType(0);
        long total=new PageInfo<>(goods).getTotal();
        int numPages;
        if(total%10==0)
            numPages=(new Long(total).intValue()/10);
        else
            numPages=(new Long(total).intValue()/10)+1;
        model.addAttribute("Goods",goods);
        model.addAttribute("goods","toys");
        model.addAttribute("numPages",numPages);
        model.addAttribute("pageNum",pageNum);
        return "user/goods";
    }

    @GetMapping("/user/toys/{pageNum}")
    public String toysList(Model model,@PathVariable("pageNum") int pageNum){
        PageHelper.startPage(pageNum,10);
        List<Goods> goods=goodsMapper.selectGoodsByType(0);
        long total=new PageInfo<>(goods).getTotal();
        int numPages;
        if(total%10==0)
            numPages=(new Long(total).intValue()/10);
        else
            numPages=(new Long(total).intValue()/10)+1;
        model.addAttribute("Goods",goods);
        model.addAttribute("goods","toys");
        model.addAttribute("numPages",numPages);
        model.addAttribute("pageNum",pageNum);
        return "user/goods";
    }

    @GetMapping("/user/foods")
    public String foodsList(Model model){
        int pageNum=0;
        PageHelper.startPage(pageNum,10);
        List<Goods> goods=goodsMapper.selectGoodsByType(1);
        long total=new PageInfo<>(goods).getTotal();
        int numPages;
        if(total%10==0)
            numPages=(new Long(total).intValue()/10);
        else
            numPages=(new Long(total).intValue()/10)+1;
        model.addAttribute("Goods",goods);
        model.addAttribute("goods","foods");
        model.addAttribute("numPages",numPages);
        model.addAttribute("pageNum",pageNum);
        return "user/goods";
    }

    @GetMapping("/user/foods/{pageNum}")
    public String foodsList(Model model,@PathVariable("pageNum") int pageNum){
        PageHelper.startPage(pageNum,10);
        List<Goods> goods=goodsMapper.selectGoodsByType(1);
        long total=new PageInfo<>(goods).getTotal();
        int numPages;
        if(total%10==0)
            numPages=(new Long(total).intValue()/10);
        else
            numPages=(new Long(total).intValue()/10)+1;
        model.addAttribute("Goods",goods);
        model.addAttribute("goods","foods");
        model.addAttribute("numPages",numPages);
        model.addAttribute("pageNum",pageNum);
        return "user/goods";
    }

    @GetMapping("/user/petGoods/{goodsId}&{price}&{stock}&{id}")
    public String buy(@PathVariable("goodsId") Integer goodsId,
                      @PathVariable("price") Integer price,
                      @PathVariable("stock") Integer stock,
                      @PathVariable("id") Integer id,
                      Model model){
        int pageNum=0;
        if(stock==0){
            model.addAttribute("msg","库存不足");
        }
        else{
          User user=userMapper.selectUserById(id);
          if(user.getBalance()<price){
              model.addAttribute("msg","你的余额不足");
          }
          else {
              user.setBalance(user.getBalance()-price);
              userMapper.updateBalance(user);
              Goods goods=goodsMapper.selectGoodsById(goodsId);
              goods.setStock(stock-1);
              goodsMapper.updateGoodsById(goods);
              model.addAttribute("msg","购买成功");
          }
        }
        PageHelper.startPage(pageNum,10);
        List<Goods> goods=goodsMapper.selectAll();
        long total=new PageInfo<>(goods).getTotal();
        int numPages;
        if(total%10==0)
            numPages=(new Long(total).intValue()/10);
        else
            numPages=(new Long(total).intValue()/10)+1;
        model.addAttribute("Goods",goods);
        model.addAttribute("goods","goods");
        model.addAttribute("numPages",numPages);
        model.addAttribute("pageNum",pageNum);
        return "user/goods";
    }
}
