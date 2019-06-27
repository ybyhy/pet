package cn.edu.cdu.yhy.mapper;

import cn.edu.cdu.yhy.bean.Shopkeeper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopkeeperMapper {
    Shopkeeper selectShopkeeperByUsername(String username);
}