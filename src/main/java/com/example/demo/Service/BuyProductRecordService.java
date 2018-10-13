package com.example.demo.Service;

import com.example.demo.BaseClass.Person;
import com.example.demo.Book.IBuyProductRecordBook;
import com.example.demo.Model.BuyProductRecordInfo;
import com.example.demo.Model.ResponseInfo;
import com.example.demo.Tools.DateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BuyProductRecordService extends Person {

    @Autowired
    @Qualifier("IBuyProductRecordBook")
    private IBuyProductRecordBook myBook;

    public ResponseInfo AddBuyProductRecord(int accountId, int productId, int count, String createDate) {
        BuyProductRecordInfo info = new BuyProductRecordInfo(accountId, productId, count, DateTool.StringToDate(createDate));
        myBook.insert(info);
        return ProvideResult(0, "Sucessfully!");
    }
}
