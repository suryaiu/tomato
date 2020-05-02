package me.alphar.user.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {

    @JsonProperty("List")
    private List<T> list;

    @JsonProperty("Page")
    private PagePara page;
}
