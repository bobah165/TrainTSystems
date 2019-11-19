package com.trains.util.MyExeptions;

import org.springframework.stereotype.Component;

@Component
public class MyExeptionForTrain extends RuntimeException {
    private String errMsg;

    public MyExeptionForTrain() { }

    public MyExeptionForTrain(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
