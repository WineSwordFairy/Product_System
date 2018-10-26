package com.example.demo.Controller;

import com.example.demo.DataTools.ConvertTool;
import com.example.demo.Model.ResponseInfo;
import com.example.demo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LessProductStockController {

    @Autowired
    private ProductService productService;

    ///扣库存。
    @RequestMapping("/LessProductStock")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String LessProductCount(int accountId, int productId, int count, String remark) {
        ///减库存。
        ResponseInfo responseInfo = productService.LessProductCount(accountId, productId, count, remark);

        if (responseInfo.getCode() == 0) {
            return ConvertTool.SerializeObject(responseInfo);
        } else {
            ///rollBack!
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ConvertTool.SerializeObject(new ResponseInfo(-1, responseInfo.getMessage()));
        }
    }
}
