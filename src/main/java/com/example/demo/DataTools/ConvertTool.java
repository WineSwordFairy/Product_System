package com.example.demo.DataTools;

import com.example.demo.Model.ProductInfo;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ConvertTool {

    ///结果序列化。
    public static List<ProductInfo> ConvertProduct(String data) {
        JSONObject jsonobject = JSONObject.fromObject(data);
        List<ProductInfo> list = (List<ProductInfo>) JSONObject.toBean(jsonobject, List.class);
        return list;
    }


}
