package com.example.demo.Controller;

import com.example.demo.DataTools.ConvertTool;
import com.example.demo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountPaymentController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/CountPayment")
    public String Index(int productId, int count){

        return ConvertTool.SerializeObject(productService.Countpayment(productId,count));

    }

}
