package com.liu.ob.ob_backend.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {

    private Integer code;
    private String msg;

    public Message(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
