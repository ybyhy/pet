package cn.edu.cdu.yhy.controller;

import cn.edu.cdu.yhy.bean.Pet;
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
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Author: 羊泓运
 * @Date: 2019/5/8 21:34
 * @Version 1.0
 */
@Controller
public class PetController {

    @Autowired
    PetMapper petMapper;

    @GetMapping("/shopkeeper/pets")
    public String list(Model model){
        int pageNum=0;
        PageHelper.startPage(pageNum,10);
        List<Pet> pets=petMapper.selectAll();
        long total=new PageInfo<>(pets).getTotal();
        int numPages;
        if(total%10==0)
            numPages=(new Long(total).intValue()/10);
        else
            numPages=(new Long(total).intValue()/10)+1;



        model.addAttribute("pets",pets);
        model.addAttribute("numPages",numPages);
        model.addAttribute("pageNum",pageNum);
        return "shopkeeper/petManagement/petManagement";
    }

    @GetMapping("/shopkeeper/pets/{pageNum}")
    public String list(Model model,@PathVariable("pageNum") int pageNum){
        PageHelper.startPage(pageNum,10);
        List<Pet> pets=petMapper.selectAll();
        long total=new PageInfo<>(pets).getTotal();
        int numPages;
        if(total%10==0)
            numPages=(new Long(total).intValue()/10);
        else
            numPages=(new Long(total).intValue()/10)+1;
        model.addAttribute("pets",pets);
        model.addAttribute("numPages",numPages);
        model.addAttribute("pageNum",pageNum);
        return "shopkeeper/petManagement/petManagement";
    }

    @GetMapping("/shopkeeper/pets/insertPetHtml")
    public String InsertUserHtml(){
        return "shopkeeper/petManagement/petOperation/insertPet";
    }

    @PostMapping("/shopkeeper/pets/insertPet")
    public String insertUser(@RequestParam("type") String type,
                             @RequestParam("breed") String breed,
                             @RequestParam("age") int age,
                             @RequestParam("gender") int gender){
        Pet pet=new Pet();
        pet.setType(type);
        pet.setBreed(breed);
        pet.setAge(age);
        pet.setGender(gender);
        petMapper.insertPet(pet);
        return "redirect:/shopkeeper/pets";
    }

    @GetMapping("/shopkeeper/pet/{id}")
    public String selectUserById(@PathVariable("id") int id,Model model){
        Pet pet=petMapper.selectPetById(id);
        model.addAttribute("pet",pet);
        return "shopkeeper/petManagement/petOperation/updatePet";
    }

    @PostMapping("/shopkeeper/pet/updatePet")
    public String updateUser(@RequestParam("id") Integer id,
                             @RequestParam("name") String name,
                             @RequestParam("type") String type,
                             @RequestParam("breed") String breed,
                             @RequestParam("gender") Integer gender,
                             @RequestParam("age") Integer age,
                             @RequestParam("photo") MultipartFile photo){
        Pet pet=new Pet();
        pet.setId(id);
        pet.setName(name);
        pet.setType(type);
        pet.setBreed(breed);
        pet.setGender(gender);
        pet.setAge(age);
        if(!photo.isEmpty()){
            String path="http://localhost:8080/img";
            String fileName=photo.getOriginalFilename();
            File file=new File(path,fileName);
            String str=path+"/"+fileName;
            pet.setPhoto(str);
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            try {
                photo.transferTo(new File("E:/IDEA_project/pet/src/main/resources/static/img/"+fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        petMapper.updatePetById(pet);
        return "redirect:/shopkeeper/pets";
    }

    @GetMapping("/shopkeeper/deletePet/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        petMapper.deletePetById(id);
        return "redirect:/shopkeeper/pets";
    }
}
