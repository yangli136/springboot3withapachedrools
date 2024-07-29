package com.abcdef.springboot3withdrools.service.order;

import com.abcdef.springboot3withdrools.model.order.OrderDiscount;
import com.abcdef.springboot3withdrools.model.order.OrderRequest;

public interface OrderDiscountService {

    OrderDiscount getDiscount(OrderRequest orderRequest);

}
