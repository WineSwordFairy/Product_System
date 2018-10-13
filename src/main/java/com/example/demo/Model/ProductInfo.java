package com.example.demo.Model;

import java.io.Serializable;
import java.math.BigInteger;

public class ProductInfo implements Serializable {


    private String title;
    private String sell_point;
    //    private int price;
    private String image;
    private Long cid;
    private long cidd;
    //    ///商品所属商家。
//    private int id;
//    ///人气
//    private int heatvalue;
    public ProductInfo(String title, String sell_point, String image, Long cid) {
        this.title = title;
        this.sell_point = sell_point;
        this.image = image;
        this.cid = cid;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSell_point() {
        return sell_point;
    }

    public void setSell_point(String sell_point) {
        this.sell_point = sell_point;
    }


}
