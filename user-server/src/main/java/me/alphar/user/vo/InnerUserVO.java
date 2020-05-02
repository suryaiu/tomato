package me.alphar.user.vo;

import com.google.common.collect.Lists;
import lombok.Data;
import me.alphar.user.entity.InnerUser;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class InnerUserVO implements Serializable {

    private static final long serialVersionUID = 99L;

    private Long tid;

    private String loginName;

    private String realName;

    private String password;

    private Integer gender;

    private LocalDate birthday;

    private Integer state;

    private String phone;

    public static InnerUserVO transferVO(InnerUser info) {
        InnerUserVO vo = new InnerUserVO();
        BeanUtils.copyProperties(info, vo);
        return vo;
    }

    public static List<InnerUserVO> transferVoList(List<InnerUser> infoList) {
        List<InnerUserVO> voList = Lists.newArrayList();
        infoList.forEach(info -> voList.add(transferVO(info)));
        return voList;
    }
}
