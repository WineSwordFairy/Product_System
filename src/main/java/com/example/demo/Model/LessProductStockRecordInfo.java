package com.example.demo.Model;

import java.sql.Timestamp;

public class LessProductStockRecordInfo {
    private Integer Id;
    private Integer AccountId;
    private Integer OpreationCount;
    private String Remark;
    private Timestamp CreateDate;

    public LessProductStockRecordInfo(Integer id, Integer accountId, Integer opreationCount, String remark, Timestamp createDate) {
        Id = id;
        AccountId = accountId;
        OpreationCount = opreationCount;
        Remark = remark;
        CreateDate = createDate;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getAccountId() {
        return AccountId;
    }

    public void setAccountId(Integer accountId) {
        AccountId = accountId;
    }

    public Integer getOpreationCount() {
        return OpreationCount;
    }

    public void setOpreationCount(Integer opreationCount) {
        OpreationCount = opreationCount;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public Timestamp getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Timestamp createDate) {
        CreateDate = createDate;
    }
}
