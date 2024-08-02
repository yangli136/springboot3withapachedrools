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

// You should typically write only one spreadsheet of decision tables, containing all necessary RuleTable definitions, per rule package. 
// You can write separate decision table spreadsheets for separate packages, but writing multiple spreadsheets in the same package can 
// cause compilation errors from conflicting RuleSet or RuleTable attributes and is therefore not recommended.