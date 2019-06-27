package cn.edu.cdu.yhy.controller;

import cn.edu.cdu.yhy.bean.Goods;
import cn.edu.cdu.yhy.bean.Pet;
import cn.edu.cdu.yhy.mapper.GoodsMapper;
import cn.edu.cdu.yhy.mapper.PetMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: 羊泓运
 * @Date: 2019/5/9 10:59
 * @Version 1.0
 */
@Controller
public class PetGoodsController {

    @Autowired
    GoodsMapper goodsMapper;

    @GetMapping("/shopkeeper/petGoods")
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
        model.addAttribute("goods",goods);
        model.addAttribute("numPages",numPages);
        model.addAttribute("pageNum",pageNum);
        return "shopkeeper/goodsManagement/goodsManagement";
    }

    @GetMapping("/shopkeeper/goods/{pageNum}")
    public String goodsList(Model model,@PathVariable("pageNum") int pageNum){
        PageHelper.startPage(pageNum,10);
        List<Goods> goods=goodsMapper.selectAll();
        long total=new PageInfo<>(goods).getTotal();
        int numPages;
        if(total%10==0)
            numPages=(new Long(total).intValue()/10);
        else
            numPages=(new Long(total).intValue()/10)+1;
        model.addAttribute("goods",goods);
        model.addAttribute("numPages",numPages);
        model.addAttribute("pageNum",pageNum);
        return "shopkeeper/goodsManagement/goodsManagement";
    }

    @GetMapping("/shopkeeper/goods/insertGoodsHtml")
    public String InsertGoodsHtml(){
        return "shopkeeper/goodsManagement/goodsOperation/insertGoods";
    }

    @PostMapping("/shopkeeper/goods/insertGoods")
    public String insertGoods(@RequestParam("name") String name,
                             @RequestParam("introduce") String introduce,
                             @RequestParam("type") Integer type,
                             @RequestParam("price") Integer price,
                             @RequestParam("stock") Integer stock){
        Goods goods=new Goods(name,introduce,type,price,stock);
        goodsMapper.insertGoods(goods);
        return "redirect:/shopkeeper/petGoods";
    }

    @GetMapping("/shopkeeper/petGoods/{id}")
    public String selectGoodsById(@PathVariable("id") Integer id,Model model){
        Goods goods=goodsMapper.selectGoodsById(id);
        model.addAttribute("goods",goods);
        return "shopkeeper/goodsManagement/goodsOperation/updateGoods";
    }

    @PostMapping("/shopkeeper/goods/updateGoods")
    public String updateUser(@RequestParam("id") Integer id,
                             @RequestParam("name") String name,
                             @RequestParam("introduce") String introduce,
                             @RequestParam("type") Integer type,
                             @RequestParam("price") Integer price,
                             @RequestParam("stock") Integer stock){
        Goods goods=new Goods(id,name,introduce,type,price,stock);
        goodsMapper.updateGoodsById(goods);
        return "redirect:/shopkeeper/petGoods";
    }

    @GetMapping("/shopkeeper/deleteGoods/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        goodsMapper.deleteGoodsById(id);
        return "redirect:/shopkeeper/petGoods";
    }
}
