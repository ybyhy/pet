package cn.edu.cdu.yhy.mapper;

import cn.edu.cdu.yhy.bean.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: 羊泓运
 * @Date: 2019/5/8 22:46
 * @Version 1.0
 */
@Mapper
public interface GoodsMapper {

    List<Goods> selectAll();

    void insertGoods(Goods goods);

    Goods selectGoodsById(Integer id);

    void updateGoodsById(Goods goods);

    void deleteGoodsById(Integer id);

    List<Goods> selectGoodsByType(Integer id);
}
