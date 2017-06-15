package com.eglsc.response;



import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eglsc.json.JSONHelper;
import com.eglsc.json.JSONObject;


/**
 * Created by ${huipei.x} on 2017-5-31.
 */
public class ResponseEntity {

    public static final int RESULT_SUCCESS = 200;  //成功返回
    public static final String SUCCESS_MESSAGE = "request data success!"; // 获取数据成功
    public static final int FAILED_CODE = 201; // 获取数据失败状态码
    public static final int ERROR_CODE = 400; // 获取数据出错状态码
    public static final int PARAM_ERROR_CODE = 203; // 参数传递错误状态码
    public static final String PARAM_ERROR_MESSAGE = "请求参数传递错误!!"; // 参数传递错误

    private static SerializeConfig config;
    static {
        config = new SerializeConfig();

    }

    private static final SerializerFeature[] features = {
            SerializerFeature.WriteDateUseDateFormat,
            SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteNullListAsEmpty,
            SerializerFeature.WriteNullStringAsEmpty,
            SerializerFeature.PrettyFormat,

    };

    public static String convertResult(Object rows ,int currentPage,int pageSize ,long total)
    {
        StringBuilder jsonResult = new StringBuilder();
        JSONHelper.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
        jsonResult.append("{");
        jsonResult.append("\"status\""+":"+"200");
        jsonResult.append(",");
        jsonResult.append("\"page\""+":");
        jsonResult.append("{");
        jsonResult.append("\"currentPage\":" + currentPage+",");
        jsonResult.append("\"pageSize\":" + pageSize+",");
        jsonResult.append("}");
        jsonResult.append(",");
        jsonResult.append("\"total\":" + total);
        jsonResult.append(",");
        jsonResult.append("\"rows\"" + ":" + JSONHelper.toJSONString(rows, config, features));
        jsonResult.append("}");
        return jsonResult.toString();
    }
    public static String convertResult(Object rows ,int currentPage,int pageSize ,long total,long totalPage)
    {
        StringBuilder jsonResult = new StringBuilder();
        JSONHelper.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
        jsonResult.append("{");
        jsonResult.append("\"status\""+":"+"200");
        jsonResult.append(",");
        jsonResult.append("\"page\""+":");
        jsonResult.append("{");
        jsonResult.append("\"currentPage\":" + currentPage+",");
        jsonResult.append("\"pageSize\":" + pageSize+",");
        jsonResult.append("\"totalPage\":" + totalPage);
        jsonResult.append("}");
        jsonResult.append(",");
        jsonResult.append("\"total\":" + total);
        jsonResult.append(",");
        jsonResult.append("\"rows\"" + ":" + JSONHelper.toJSONString(rows, config,features));
        jsonResult.append("}");
        return jsonResult.toString();
    }
    public static String SuccessResult(Object rows ,long total) {
        StringBuilder jsonResult = new StringBuilder();
        JSONHelper.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
        jsonResult.append("{");
        jsonResult.append("\"status\""+":"+"200");
        jsonResult.append(",");
        jsonResult.append("\"msg\"" + ":" +JSONHelper.toJSONString(SUCCESS_MESSAGE));
        jsonResult.append(",");
        jsonResult.append("\"total\"" + ":" + total);
        jsonResult.append(",");
        jsonResult.append("\"rows\"" + ":" + JSONHelper.toJSONString(rows, config, features));
        jsonResult.append("}");
        return jsonResult.toString();
    }

    public static String SuccessResult(Object result,String msg) {
        StringBuilder jsonResult = new StringBuilder();
        JSONHelper.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
        jsonResult.append("{");
        jsonResult.append("\"status\""+":"+"200");
        jsonResult.append(",");
        jsonResult.append("\"msg\"" + ":" + JSONHelper.toJSONString(msg));
        jsonResult.append(",");
        jsonResult.append("\"rows\"" + ":" + JSONHelper.toJSONString(result, config, features));
        jsonResult.append("}");

        return jsonResult.toString();
    }

    public static String SuccessResult(Object result) {
        StringBuilder jsonResult = new StringBuilder();
        JSONHelper.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
        jsonResult.append("{");
        jsonResult.append("\"status\""+":"+"200");
        jsonResult.append(",");
        jsonResult.append("\"msg\"" + ":" + JSONHelper.toJSONString(SUCCESS_MESSAGE));
        jsonResult.append(",");
        jsonResult.append("\"rows\"" + ":" + JSONHelper.toJSONString(result, config, features));
        jsonResult.append("}");
        return jsonResult.toString();
    }

    public static String SuccessResult() {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status", RESULT_SUCCESS);
        jsonObject.put("msg", SUCCESS_MESSAGE);
        return jsonObject.toString();
    }

    public static String ErrorResult(String msg) {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status", FAILED_CODE);
        jsonObject.put("msg", msg);
        return jsonObject.toString();

    }

    public static String ErrorResult(int status,Object msg) {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status", status);
        jsonObject.put("msg", msg);
        return jsonObject.toString();
    }
}
