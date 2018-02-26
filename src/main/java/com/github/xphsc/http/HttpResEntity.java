package com.github.xphsc.http;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by ${huipei.x} on 2017-7-23.
 */
public class HttpResEntity {

    @JSONField(ordinal = 3)
    private Object data ;
    @JSONField(ordinal = 5)
    private Integer total;
    @JSONField(ordinal = 2)
    private Object msg;
    @JSONField(ordinal = 1)
    private int code;
    @JSONField(ordinal = 4)
    private JSONObject page;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public JSONObject getPage() {
        return page;
    }

    public void setPage(JSONObject page) {
        this.page = page;
    }
}
