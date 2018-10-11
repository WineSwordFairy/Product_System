package com.example.demo.Controller;

import com.example.demo.Redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.ibigsea.springboot_redis_demo.config.RedisClient;
@RestController
public class ShopIndexProductController {


    @Autowired
    private RedisClient redisClinet;


    @RequestMapping("/ShopIndexProduct")
    public String Index() {

        ///查询对应的缓存数据。
        try {




            if (true) {
                ///从缓存读取数据。

            } else {

                ///读DB。

                ///写缓存。

                ///格式化数据返回。
            }
        } catch (Exception ex) {

            return "";
        }
        ///格式化数据返回。


    }


}
