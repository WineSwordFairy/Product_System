package com.example.demo.DataTools;

import com.example.demo.Model.ProductInfo;
import com.example.demo.Model.ResponseInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ConvertTool {

    ///商品集合反序列化。
    public static List<ProductInfo> ConvertProduct(String data) {
        JSONArray resultList = JSONArray.fromObject(data);
        return resultList;
    }

    ///序列化产品列表。
    public static String ConvertListProduct(List<ProductInfo> data) {
        JSONArray array = JSONArray.fromObject(data);
        return array.toString();
    }

    ///序列化结果。
    public static String SerializeObject(ResponseInfo data) {
         JSONObject json = JSONObject.fromObject(data);
         return json.toString();
    }

    ///序列反化结果。
    public static ResponseInfo DeserializeObject(String data) {
        JSONObject jsonObject = JSONObject.fromObject(data);
        ResponseInfo responseInfo = (ResponseInfo) JSONObject.toBean(jsonObject, ResponseInfo.class);
        return responseInfo;
    }

}
