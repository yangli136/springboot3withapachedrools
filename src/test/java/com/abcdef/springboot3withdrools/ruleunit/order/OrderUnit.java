package com.abcdef.springboot3withdrools.ruleunit.order;

import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.RuleUnitData;

import com.abcdef.springboot3withdrools.model.order.OrderDiscount;
import com.abcdef.springboot3withdrools.model.order.OrderRequest;

public class OrderUnit implements RuleUnitData {
    private final DataStore<OrderRequest> orderRequest;
    private final OrderDiscount orderDiscount = new OrderDiscount();

    public OrderUnit() {
        this(DataSource.createStore());
    }

    public OrderUnit(DataStore<OrderRequest> orderRequest) {
        this.orderRequest = orderRequest;
    }

    public DataStore<OrderRequest> getOrderRequest() {
        return orderRequest;
    }

    public OrderDiscount getOrderDiscount() {
        return orderDiscount;
    }
}
