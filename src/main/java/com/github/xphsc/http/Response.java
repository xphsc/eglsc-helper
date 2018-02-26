package com.github.xphsc.http;



import com.github.xphsc.date.DateFormat;
import com.github.xphsc.json.JSONHelper;
import com.github.xphsc.json.JSONObject;
import com.github.xphsc.lang.StringConst;
import com.github.xphsc.mutable.Integers;


/**
 * Created by ${huipei.x} on 2017-7-23.
 */
public class Response extends HttpResEntity {

    private Response(){}

    private Response(int status,Object msg,Object result) {
        this.setData(getData(result));
        this.setMsg(msg);
        this.setCode(status);

    }

    private Response(int status,Object msg,Object result,long total) {
        this.setData(getData(result));
        this.setMsg(msg);
        this.setCode(status);
        this.setTotal(Integers.toInteger(total));

    }

    private Response(int status, JSONObject page,Object result,long total) {
        this.setData(getData(result));
        this.setPage(page);
        this.setCode(status);
        this.setTotal(Integers.toInteger(total));
    }


    private Response(int status,Object msg) {
        this.setCode(status);
        this.setMsg(msg);

    }



    public static Response successResult() {
        return newInstance(200,ResponseStatus.OK.getReasonPhrase());
    }
    public static Response successResult(Object result) {
        return newInstance(200, ResponseStatus.OK.getReasonPhrase(),JSONHelper.toJSON(result, DateFormat.DATE_FORMAT_DAY));
    }

    public static Response ok(Object result) {
        return newInstance(200, ResponseStatus.OK.getReasonPhrase(),JSONHelper.toJSON(result, DateFormat.DATE_FORMAT_MSEC));
    }

    public static Response successResult(Object result ,long total) {
        return newInstance(200,ResponseStatus.OK.getReasonPhrase(), JSONHelper.toJSON(result, DateFormat.DATE_FORMAT_DAY),total);
    }

    public static Response successResult(Object result ,long total,String DateFormat) {
        return newInstance(200, ResponseStatus.OK.getReasonPhrase(), JSONHelper.toJSON(result, DateFormat), total);
    }

    public static Response successResult(int status ,Object result) {
        return newInstance(status,ResponseStatus.OK.getReasonPhrase(), JSONHelper.toJSON(result, DateFormat.DATE_FORMAT_DAY));
    }

    public static Response successResult(Object result,Object msg) {
        return newInstance(200, msg, JSONHelper.toJSON(result, DateFormat.DATE_FORMAT_DAY));
    }

    public static Response successResult(Object result,String DateFormat) {
        return newInstance(200, ResponseStatus.OK.getReasonPhrase(), JSONHelper.toJSON(result, DateFormat));
    }

    public static Response convertResult(Object result ,int currentPage,int pageSize ,long total)
    {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("currentPage",currentPage);
        jsonObject.put("pageSize",pageSize);
        return newInstance(200, jsonObject, JSONHelper.toJSON(result, DateFormat.DATE_FORMAT_DAY), total);
    }

        public static Response  ok(Object result ,int currentPage,int pageSize ,long total) {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("currentPage",currentPage);
        jsonObject.put("pageSize",pageSize);
        return newInstance(200, jsonObject, JSONHelper.toJSON(result, DateFormat.DATE_FORMAT_MSEC), total);
    }

    public static Response convertResult(Object result ,int currentPage,int pageSize ,Integer totalPage)
    {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("currentPage",currentPage);
        jsonObject.put("pageSize",pageSize);
        jsonObject.put("totalPage",totalPage);
        return newInstance(200,jsonObject,JSONHelper.toJSON(result, DateFormat.DATE_FORMAT_DAY));
    }

    public static Response convertResult(Object result ,int currentPage,int pageSize ,long total,String DateFormat)
    {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("currentPage",currentPage);
        jsonObject.put("pageSize",pageSize);
        return newInstance(200,jsonObject,JSONHelper.toJSON(result, DateFormat),total);
    }

    public static Response convertResult(Object result ,int currentPage,int pageSize ,long total,int totalPage)
    {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("currentPage",currentPage);
        jsonObject.put("pageSize",pageSize);
        jsonObject.put("totalPage",totalPage);
        return newInstance(200,jsonObject, JSONHelper.toJSON(result, DateFormat.DATE_FORMAT_DAY),total);
    }

    public static Response errorResult(Object msg) {
        return newInstance(ResponseStatus.INTERNAL_SERVER_ERROR.value(),msg);

    }

    public static Response errorResult(int status,Object msg) {
        return newInstance(status,msg);
    }
    public static Response newInstance(int status,Object msg,Object result) {
        return new Response(status,msg,result);
    }

    public static Response newInstance(int status,Object msg,Object result,long total) {
        return new Response(status,msg,result,total);
    }
    public static Response newInstance(int status, JSONObject page,Object result,long total) {
        return new Response(status,page,result,total);
    }
    public static Response newInstance(int status,Object msg) {
        return new Response(status,msg);
    }

    private Object getData(Object result){
        Object data=null;
        data=JSONHelper.parse(result.toString());
        return data;
    }

}
