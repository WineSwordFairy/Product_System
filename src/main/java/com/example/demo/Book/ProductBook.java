package com.example.demo.Book;

import com.example.demo.Model.ProductInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ProductBook {
    ///返回商品列表。
    @Results({
            @Result(property = "title", column = "title"),
            @Result(property = "sell_point", column = "sell_point"),
            @Result(property = "price", column = "price"),
            @Result(property = "image", column = "image"),
            @Result(property = "cid", column = "cid"),
            @Result(property = "uid", column = "id"),
            @Result(property = "heatvalue", column = "heatvalue")
    })
    @Select("SELECT title,sell_point,price,image,cid,t2.id as uid , heatvalue FROM ((SELECT * FROM tb_item t1 ORDER BY heatvalue DESC limit 100) t1 left JOIN accounttable t2 ON  t1.uid = t2.id)")
    List<ProductInfo> Query();

//    ///根据商品ID查商品。
//    @Select("SELECT * FROM tb_item WHERE id=#{id}")
//    ProductInfo QueryById(@Param("id") int pId);
//    ///根据商品ID查商品。
    @Select("SELECT title,num FROM tb_item WHERE id=#{id}")
    ProductInfo QueryById(@Param("id") int pId);
    //更新商品数量。
    @Update("UPDATE tb_item SET num=num - 1 WHERE id=#{id}")
    int UpdateCount(@Param("id") int pId);
}
