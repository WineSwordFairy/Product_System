package com.example.demo.Service;

import com.example.demo.BaseClass.Person;
import com.example.demo.Book.ProductBook;
import com.example.demo.DataTools.ConvertTool;
import com.example.demo.Model.ProductInfo;
import com.example.demo.Model.ResponseInfo;
import com.example.demo.Redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

@Component
public class ProductService extends Person {

    @Autowired
    private RedisClient redisClinet;

    @Autowired
    private ProductBook productBook;

    ///购买商品 (性能差，不使用)。
    public ResponseInfo BuyProduct(int accountId, int productId, int buyCount) {
        //查看商品数量。
        ProductInfo pInfo = this.productBook.QueryById(productId);

        if (pInfo.getProductCount() > 0) {
            //减掉商品数量。
            this.productBook.UpdateCount(productId);
        } else {
            return this.ProvideResult(-1, "商品数量不足");
        }
        return ProvideResult(0, "Sucessfully");
    }

    ///购买商品 （使用Redis 来做 CAS）
    public ResponseInfo BuyProductByRedis(int accountId, int productId, int buyCount) {
        Jedis jedis = redisClinet.GetResource();
        try {
            ///获取Redis连接。
            ///给商品数量键添加监视(乐观锁)。
            String watchRes = jedis.watch("ProductCount");
            ///获取商品数量。
            int count = Integer.valueOf(redisClinet.get("ProductCount"));
            //检查商品数量。
            if (count > 0) {
                ///开启事务。
                Transaction tran = jedis.multi();
                ///数量--。
                tran.incrBy("ProductCount", -1);
                ///执行事务。
                List<Object> result = tran.exec();
                //扣库存成功。
                if (result.size() != 0) {
                    if (true) {
                        //抢购成功,同步 ORDER。

                        //响应。
                        return ProvideResult(0, "");

                    } else {
                        //抢购失败!

                        return ProvideResult(-1, "抢购失败!");

                    }
                } else {
                    //抢购失败。

                    return ProvideResult(-1, "抢购失败!");
                }
            }
            jedis.close();
        } catch (Exception ex) {

        } finally {
            jedis.close();
        }
        return ProvideResult(0, "");
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


}
