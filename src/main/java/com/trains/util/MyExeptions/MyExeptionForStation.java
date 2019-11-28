package com.trains.util.MyExeptions;

import org.springframework.stereotype.Component;

@Component
public class MyExeptionForStation extends RuntimeException {
    private String errMsg;

    public MyExeptionForStation() { }

    public MyExeptionForStation(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
