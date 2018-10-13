package com.example.demo.Model;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

public class ProductInfo implements Serializable {

    private Long id;
    private String title;
    private String sell_point;
    private Long price;
    private Integer num;
    private String image;
    private String barcode;
    private Long cid;
    private Integer status;
    private Timestamp created;
    private Timestamp updated;
    ///商品所属商家。
    private Integer uid;
    ///人气
    private Integer heatvalue;

    public ProductInfo(String title, String sell_point, Long price, String image, Long cid, Integer uid, Integer heatvalue) {
        this.title = title;
        this.sell_point = sell_point;
        this.price=price;
        this.image = image;
        this.cid = cid;
        this.uid = uid;
        this.heatvalue = heatvalue;
    }

    public ProductInfo(Long id, String title, String sell_point, Long price, Integer num, String image, String barcode, Long cid, Integer status, Timestamp created, Timestamp updated, Integer uid, Integer heatvalue) {
        this.id = id;
        this.title = title;
        this.sell_point = sell_point;
        this.price = price;
        this.num = num;
        this.image = image;
        this.barcode = barcode;
        this.cid = cid;
        this.status = status;
        this.created = created;
        this.updated = updated;
        this.uid = uid;
        this.heatvalue = heatvalue;
    }

    public Integer getProductCount() {
        return num;
    }

    public void setProductCount(Integer productCount) {
        this.num = productCount;
    }

    public Integer getUid() {
        return uid;
    }

    public Long getId() {
        return id;
    }

    public ProductInfo(String title, Integer num) {
        this.title = title;
        this.num = num;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Integer isStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getHeatvalue() {
        return heatvalue;
    }

    public void setHeatvalue(Integer heatvalue) {
        this.heatvalue = heatvalue;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
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
