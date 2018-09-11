package com.emall.service;

import com.emall.model.Product;
import com.emall.model.User;

import java.util.List;

public interface TrxService {

    List<Product> getBuyList(Integer userId);

    boolean buy(User user, List<Product> productList, Long buyTime);
}
