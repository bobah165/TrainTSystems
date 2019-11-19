package com.trains.util.MyExeptions;

import org.springframework.stereotype.Component;

@Component
public class MyException extends RuntimeException {

    private String errMsg;

    public MyException() { }

    public MyException(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
