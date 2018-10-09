package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopIndexProductController {

    @RequestMapping("/ShopIndexProduct")
    public String Index() {

        ///查询对应的缓存数据。


        if (flag) {
            ///从缓存读取数据。

            ///格式化数据返回。

        } else {

            ///读DB。

            ///写缓存。

            ///格式化数据返回。
        }


    }


}
