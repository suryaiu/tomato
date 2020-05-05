package me.alphar.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import me.alphar.core.entity.InnerUser;
import me.alphar.user.core.PagePara;
import me.alphar.user.core.PageResult;
import me.alphar.user.vo.InnerUserVO;

public interface IInnerUserService extends IService<InnerUser> {

    int insert(InnerUser user);

    int update(InnerUser user);

    PageResult<InnerUserVO> getUserPage(PagePara pagePara);

    InnerUserVO getByTid(Long tid);

    InnerUser getByLoginName(String loginName);
}
