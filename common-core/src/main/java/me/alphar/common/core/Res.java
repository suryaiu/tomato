package me.alphar.common.core;

import lombok.Data;

@Data
public class Res<T> {

    private Integer code;
    private String msg;
    private T data;

    public Res(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Res(Integer code, String msg) {
        this(code, msg, null);
    }
}
