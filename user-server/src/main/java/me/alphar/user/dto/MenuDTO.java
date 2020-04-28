package me.alphar.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MenuDTO {

    private String name;
    private Integer id;
    private Integer order;
    private String path;

    private List<MenuDTO> children;
}
