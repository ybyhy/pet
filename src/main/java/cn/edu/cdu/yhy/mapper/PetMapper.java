package cn.edu.cdu.yhy.mapper;

import cn.edu.cdu.yhy.bean.Pet;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PetMapper {
    List<Pet> selectAll();

    void insertPet(Pet pet);

    Pet selectPetById(Integer id);

    void updatePetById(Pet pet);

    void deletePetById(Integer id);

    List<Pet> selectDogByType(String type);
}