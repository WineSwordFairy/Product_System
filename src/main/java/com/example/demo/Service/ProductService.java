package com.example.demo.Service;

import com.example.demo.BaseClass.Person;
import com.example.demo.Book.ProductBook;
import com.example.demo.Book.ProductStockOpreationBook;
import com.example.demo.DataTools.ConvertTool;
import com.example.demo.Model.LessProductStockRecordInfo;
import com.example.demo.Model.ProductInfo;
import com.example.demo.Model.ResponseInfo;
import com.example.demo.Redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Component
public class ProductService extends Person {

    //CreateOrder Url
    private static String createOrderUrl = "http://localhost:8085/CreateOrder";
//    private static String param = "sort=1";

    @Autowired
    private RedisClient redisClinet;

    @Autowired
    private ProductBook productBook;

    @Autowired
    private ProductStockOpreationBook productStockBook;

    ///购买商品 (DB方案，性能差，不使用)。
    public ResponseInfo BuyProduct(int accountId, int productId, int buyCount) {

        try {
            /// TO DO
            ProductInfo pInfo = this.productBook.QueryById(productId);

            if (pInfo.getProductCount() > 0) {
                //减掉商品数量。
                this.productBook.ProductStockLess(productId, buyCount);
                return ProvideResult(0, "Sucessfully");
            } else {
                return this.ProvideResult(-1, "商品数量不足");
            }

        } catch (Exception ex) {
            ///TODO
            throw ex;
        } finally {
            ///TODO
        }
    }

    ///购买商品 （使用Redis 来做 CAS）
    public ResponseInfo BuyProductByRedis(int accountId, int productId, int count) {
        Jedis jedis = redisClinet.GetResource();
        try {
            ///获取Redis连接。
            ///给商品数量键添加监视(乐观锁)。
            String watchRes = jedis.watch("ProductCount");
            ///获取商品数量。
            int productCount = Integer.valueOf(redisClinet.get("ProductCount"));
            //检查商品数量。
            if (productCount > 0) {
                ///开启事务。
                Transaction tran = jedis.multi();
                ///数量--。
                tran.incrBy("ProductCount", -1);
                ///执行事务。
                List<Object> result = tran.exec();
                //扣库存成功。
                if (result.size() != 0) {
                    return ProvideResult(0, "减库存成功!");
                } else {
                    //抢购失败。
                    return ProvideResult(-1, "减库存失败!");
                }
            } else {
                return ProvideResult(-1, "商品已售完!");
            }

        } catch (Exception ex) {
            return ProvideResult(-1, ex.getMessage());
        } finally {
            jedis.close();
        }
    }

    ///提供首页商品列表。
    public ResponseInfo ProvideProductListIndex() {
        try {
            //缓存不为空。
            if (redisClinet.CheckIsKeyExists("ShowFirstPageProduct_key")) {
                ///查询对应的缓存数据。
                String resultJsonStr = redisClinet.get("ShowFirstPageProduct_key");
                //反序列化。
                List<ProductInfo> productInfoList = ConvertTool.ConvertProduct(resultJsonStr);
                return ProvideResult(1, "", productInfoList);
            }
            //缓存为空。
            else {
                ///读首页产品。
                List<ProductInfo> resultData = productBook.Query();
                ///写缓存。
                String resultJsonStr = ConvertTool.ConvertListProduct(resultData);
                redisClinet.set("ShowFirstPageProduct_key", resultJsonStr);
                ///格式化数据返回。
                return ProvideResult(0, "", resultData);
            }
        } catch (Exception ex) {
            return ProvideResult(-1, ex.getMessage());
        }
    }

    ///计算订单总金额。
    public ResponseInfo Countpayment(int productId, int count) {
        try {
            /// TO DO
            ProductInfo productInfo = this.productBook.QueryById(productId);
            BigDecimal payment = new BigDecimal(productInfo.getPrice().intValue() * count);
            return ProvideResult(0, "", payment);
        } catch (Exception ex) {
            ///TODO
            return ProvideResult(-1, ex.getMessage());
        } finally {
            ///TODO
        }
    }

    ///增加商品库存操作记录。
    public ResponseInfo CreateLessProductCountRecord(int accountId, int productId, int count, String remark) {
        try {
            LessProductStockRecordInfo lessProductStockRecordInfo = new LessProductStockRecordInfo(1, accountId, count, remark, new Timestamp(new Date().getTime()));
            int lessResultInfo = this.productStockBook.Insert(lessProductStockRecordInfo);
            return ProvideResult(0, "Sucess!!");
        } catch (Exception ex) {
            return ProvideResult(-1, ex.getMessage());
        } finally {
        }
    }

    public ResponseInfo LessProductCount(int accountId, int productId, int count, String remark) {

        try {
            ///1.减商品库存。
            int lessResult = this.productBook.ProductStockLess(productId, count);

            if (lessResult == 1) {
                ///2.增加记录。
                ResponseInfo createResultInfo = this.CreateLessProductCountRecord(accountId, productId, count, remark);
                ///3. 响应
                if (createResultInfo.getCode() == 0) {//创建扣库存记录成功。
                    return ProvideResult(0, "Sucess!!");
                } else {
                    return ProvideResult(-1, createResultInfo.getMessage());
                }
            } else {//扣库存失败。
                return ProvideResult(-1, "未知异常，请重试!!");
            }
        } catch (Exception ex) {
            ///TODO
            return ProvideResult(-1, ex.getMessage());
        } finally {
            ///TODO
        }
    }
}
