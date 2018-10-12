package com.example.demo.Controller;

import com.example.demo.Book.ProductBook;
import com.example.demo.DataTools.ConvertTool;
import com.example.demo.Model.ProductInfo;
import com.example.demo.Model.ResponseInfo;
import com.example.demo.Redis.RedisClient;
import com.example.demo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ShopIndexProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/ShopIndexProduct")
    public String Index() {
        ResponseInfo responseInfo= productService.ProvideProductListIndex();
        return ConvertTool.ConvertResponseInfo(responseInfo);
    }


}
