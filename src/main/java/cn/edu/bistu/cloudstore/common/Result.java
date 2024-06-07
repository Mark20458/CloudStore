package cn.edu.bistu.cloudstore.common;

import lombok.Data;

@Data
public class Result {
    private String msg;
    private int code;
    private Object data;

    public Result(int code) {
        this.code = code;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result message(String msg) {
        this.msg = msg;
        return this;
    }

    public Result data(Object data) {
        this.data = data;
        return this;
    }

    public static Result OK() {
        return new Result(CODE.OK);
    }

    public static Result ERR() {
        return new Result(CODE.ERR);
    }
}
