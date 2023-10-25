package com.example.act_non.order.model;

import java.sql.Time;
import java.util.Date;

public interface IOrderProjection {
    Long getIdOrder();
    String getDayOrder();
    String getTimeOrder();
    Double getTotalPrice();
}
