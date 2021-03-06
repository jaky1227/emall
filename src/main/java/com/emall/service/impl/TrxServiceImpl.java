package com.emall.service.impl;

import com.emall.model.Product;
import com.emall.model.User;
import com.emall.dao.ProductMapper;
import com.emall.dao.TrxMapper;
import com.emall.service.TrxService;
import com.emall.utils.ConvertPriceUnitUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TrxServiceImpl implements TrxService {

    @Resource
    private TrxMapper trxMapper;
    @Resource
    private ProductMapper productMapper;

    //获取已购买的商品列表
    @Override
    public List<Product> getBuyList(Integer userId) {
        List<Product> productList = trxMapper.getBuyList(userId);
    //将价格转换元
        for(Product product:productList){
            product.setBuyPrice(ConvertPriceUnitUtil
                    .convertF2Y(product.getBuyPrice()));
        }
        return productList;
    }

    //购买购物车里的商品,(待设置事务回滚)
    @Override
    public boolean buy(User user, List<Product> productList, Long buyTime) {
        boolean isBuy = false;

        for (Product product: productList) {
            int buyNum = product.getBuyNum();
            product = productMapper.getProduct(product.getId());

            String buyPrice = product.getPrice();
            product.setBuyNum(buyNum);
            product.setBuyPrice(buyPrice);
            isBuy = trxMapper.buy(user.getId(), product, buyTime);
            if (!isBuy) {
                throw new RuntimeException();
            }
        }

        /*for(int i = 0; i < productList.size(); i++){
            Product product = productList.get(i);
            int buyNum = product.getBuyNum();
            product = productMapper.getProduct(product.getId());

            String buyPrice = product.getPrice();
            product.setBuyNum(buyNum);
            product.setBuyPrice(buyPrice);
            System.out.println("1111111111");
            isBuy = trxMapper.buy(user.getId(), product, buyTime);
            if (!isBuy) {
                throw new RuntimeException();
            }
        }*/
        return isBuy;
    }
}
