package com.example.demo.Controller;


import com.example.demo.DataTools.ConvertTool;
import com.example.demo.Model.ResponseInfo;
import com.example.demo.Service.BuyProductRecordService;
import com.example.demo.Service.ProductService;
import com.example.demo.Tools.DateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BuyProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private BuyProductRecordService buyProductRecordService;

    ///性能差。
    @RequestMapping("/BuyProduct")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String BuyProduct(int accountId, int productId, int count, String createDate) {
        try {
            //查询商品数量。
            ResponseInfo result = productService.BuyProduct(accountId, productId, count);
            if (result.getCode() == 0) {
                ///商品数量足够。
                buyProductRecordService.AddBuyProductRecord(accountId, productId, count, createDate);
                return "成功!";
            } else {
                return "商品已售空!";
            }
        } catch (Exception ex) {
            ///回滚。
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ConvertTool.SerializeObject(new ResponseInfo(-1, ex.getMessage()));
        }
    }

    //秒杀系统，商品库存放在Redis。
    @RequestMapping("/BuyProductByRedis")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String BuyProductByRedis(int accountId, int productId, int count) {
        try {
            //扣库存。
            ResponseInfo responseInfo = productService.BuyProductByRedis(accountId, productId, count);
            return ConvertTool.SerializeObject(responseInfo);
        } catch (Exception ex) {
            return ConvertTool.SerializeObject(new ResponseInfo(-1, ex.getMessage()));
        }
    }


}
