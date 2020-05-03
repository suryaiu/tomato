package me.alphar.user.vo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import me.alphar.user.entity.InnerUser;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
public class InnerUserVO implements Serializable {

    private static final long serialVersionUID = 99L;

    private static String MALE = "男";   // 1
    private static String FEMALE = "女"; // 0

    private static Map<Integer, String> map = Maps.newHashMap();

    static {
        map.put(0, "有效");
        map.put(1, "已禁用");
        map.put(2, "已删除");
    }

    private Long tid;

    private String loginName;

    private String realName;

    private String genderText;

    private Integer gender;

    private LocalDate birthday;

    private Integer state;
    // 0 有效用户，1 已禁用，2 已删除
    private String stateText;

    private String phone;

    public static InnerUserVO transferVO(InnerUser info) {
        if (info == null) {
            return null;
        }
        InnerUserVO vo = new InnerUserVO();
        if (info.getGender() != null) {
            if (info.getGender() == 1) {
                vo.setGenderText(MALE);
            } else {
                vo.setGenderText(FEMALE);
            }
        }
        if (info.getState() != null) {
            vo.setStateText(map.get(info.getState()));
        }
        BeanUtils.copyProperties(info, vo);
        return vo;
    }

    public static List<InnerUserVO> transferVoList(List<InnerUser> infoList) {
        List<InnerUserVO> voList = Lists.newArrayList();
        infoList.forEach(info -> voList.add(transferVO(info)));
        return voList;
    }
}
