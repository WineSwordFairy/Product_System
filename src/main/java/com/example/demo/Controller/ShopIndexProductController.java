package com.example.demo.Controller;

import com.example.demo.Book.ProductBook;
import com.example.demo.DataTools.ConvertTool;
import com.example.demo.Model.ProductInfo;
import com.example.demo.Redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//import com.ibigsea.springboot_redis_demo.config.RedisClient;
@RestController
public class ShopIndexProductController {


    @Autowired
    private RedisClient redisClinet;
    @Autowired
    private ProductBook productBook;

    @RequestMapping("/ShopIndexProduct")
    public String Index() {

        try {
            if (redisClinet.CheckIsKeyExists("ShowFirstPageProduct_key")) {
                ///查询对应的缓存数据。
                String resultData = redisClinet.get("ShowFirstPageProduct_key");
                ///格式化数据返回。
                List<ProductInfo> resultList = ConvertTool.ConvertProduct(resultData);
                return "success";
            } else {
                ///读首页产品。
                List<ProductInfo> resultData = productBook.Query();
                ///写缓存。

                ///格式化数据返回。
                return "";
            }
        } catch (Exception ex) {


            return "";
        }


    }


}
