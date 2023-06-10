package com.fenrir.guruguru_spring.global.common;

import com.querydsl.core.types.Order;

public enum Sort {
    CREATED_DATE_DESC("createdAt", Order.DESC),
    CREATED_DATE_ASC("createdAt", Order.ASC),
    STORE_RATING_DESC("grade", Order.DESC);

    private final String property;
    private final Order order;


    Sort(String property, Order order) {
        this.property = property;
        this.order = order;
    }

    public String getProperty() {
        return property;
    }

    public Order getOrder() {
        return order;
    }
}
