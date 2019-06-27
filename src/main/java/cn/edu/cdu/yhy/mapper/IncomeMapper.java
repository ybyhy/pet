package cn.edu.cdu.yhy.mapper;

import cn.edu.cdu.yhy.bean.Income;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IncomeMapper {
    List<Income> selectIncomeByLastSeven();
}