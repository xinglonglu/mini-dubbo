package org.mini.dubbo.support;

import java.io.Serializable;

public class Response implements Serializable{
    //结果
    private Object result;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

}
