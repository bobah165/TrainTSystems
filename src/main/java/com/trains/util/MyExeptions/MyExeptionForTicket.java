package com.trains.util.MyExeptions;

import org.springframework.stereotype.Component;

@Component
public class MyExeptionForTicket extends RuntimeException {
    private String errMsg;

    public MyExeptionForTicket() { }

    public MyExeptionForTicket(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
