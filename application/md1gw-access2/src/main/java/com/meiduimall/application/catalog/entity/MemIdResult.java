package com.meiduimall.application.catalog.entity;

/**
 * Created by yangchangfu on 2017/4/12.
 */

public class MemIdResult {


    private String status;
    private String msg;

    /**
     * memId : 1234567891234654
     */

    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String memId;

        public String getMemId() {
            return memId;
        }

        public void setMemId(String memId) {
            this.memId = memId;
        }
    }
}
