package com.trains.util.MyExeptions;

import org.springframework.stereotype.Component;

@Component
public class MyExeptionForPassenger extends RuntimeException {

    private String errMsg;

    public MyExeptionForPassenger() { }

    public MyExeptionForPassenger(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
