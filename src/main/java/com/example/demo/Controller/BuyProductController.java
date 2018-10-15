package com.example.demo.Controller;


import com.example.demo.DataTools.ConvertTool;
import com.example.demo.Model.ResponseInfo;
import com.example.demo.Service.BuyProductRecordService;
import com.example.demo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuyProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private BuyProductRecordService buyProductRecordService;

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

            return ConvertTool.ConvertResponseInfo(new ResponseInfo(-1, ex.getMessage()));
        }
    }


    @RequestMapping("/BuyProductByRedis")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String BuyProductByRedis(int accountId, int productId, int count, String createDate) {
        try {
            //查询商品数量。
            ResponseInfo result = productService.BuyProductByRedis(accountId, productId, count);
            if (result.getCode() == 0) {
                ///商品数量足够。
                buyProductRecordService.AddBuyProductRecord(accountId, productId, count, createDate);
                return "成功!";
            } else {
                return "商品已售空!";
            }
        } catch (Exception ex)

        {
            return ConvertTool.ConvertResponseInfo(new ResponseInfo(-1, ex.getMessage()));
        }
    }
}
