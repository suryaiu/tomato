package me.alphar.common.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Res<T> {

    private Integer code;
    private String msg;
    private T data;

    public Res(Integer code, String msg) {
        this(code, msg, null);
    }
}
