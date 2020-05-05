package me.alphar.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.alphar.core.entity.InnerUser;;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface InnerUserMapper extends BaseMapper<InnerUser> {
}
