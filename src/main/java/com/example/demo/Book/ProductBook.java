package com.example.demo.Book;

import com.example.demo.Model.ProductInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ProductBook {


    @Results({
            @Result(property = "title", column = "title"),
            @Result(property = "sell_point", column = "sell_point"),
//            @Result(property = "price", column = "price")
            @Result(property = "image", column = "image"),
            @Result(property = "cid", column = "cid"),
//            @Result(property = "id", column = "id")
//            @Result(property = "heatvalue", column = "heatvalue")
    })
    @Select("SELECT title,sell_point,image,cid FROM ((SELECT * FROM tb_item t1 ORDER BY heatvalue DESC limit 100) t1 left JOIN accounttable t2 ON  t1.uid = t2.id)")
    List<ProductInfo> Query();

}
