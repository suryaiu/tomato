package me.alphar.user.core;

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

    public static <T> Res<T> success(String msg, T data) {
        return new Res<>(Wrapper.SUCCESS_CODE, msg, data);
    }

    public static <T> Res<T> success(T data) {
        return new Res<>(Wrapper.SUCCESS_CODE, "操作成功", data);
    }

    public static <T> Res<T> success(String msg) {
        return new Res<>(Wrapper.SUCCESS_CODE, msg);
    }

    public static <T> Res<T> success() {
        return new Res<>(Wrapper.SUCCESS_CODE, "操作成功");
    }

    public static <T> Res<T> error(String msg, T data) {
        return new Res<>(Wrapper.ERROR_CODE, msg, data);
    }

    public static <T> Res<T> error(T data) {
        return new Res<>(Wrapper.ERROR_CODE, "操作失败", data);
    }

    public static <T> Res<T> error(String msg) {
        return new Res<>(Wrapper.ERROR_CODE, msg);
    }

    public static <T> Res<T> error() {
        return new Res<>(Wrapper.ERROR_CODE, "操作失败");
    }
}
