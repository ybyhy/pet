package cn.edu.cdu.yhy.mapper;

import cn.edu.cdu.yhy.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> selectAll();

    void insertUser(User user);

    User selectUserById(Integer id);

    void updateUser(User user);

    void deleteUserById(Integer id);

    User selectUserByUsername(String username);

    void  updateBalance(User user);
}