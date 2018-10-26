package com.example.demo.Book;

import com.example.demo.Model.LessProductStockRecordInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ProductStockOpreationBook {

    @Insert("INSERT INTO ProductStockOpreationRecordTable(AccountId,OpreationCount,Remark,CreateDate) VALUES(#{accountId}, #{opreationCount}," +
            "#{Remark},#{CreateDate})")
    int Insert(LessProductStockRecordInfo lessProductStockRecordInfo);


}
