package com.workshop23.ws23.model;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class OrderDetail {
    private Integer id;
    private DateTime orderDate;
    private Integer customerId;
    private Double totalPrice;
    private Double costPrice;

    public static OrderDetail fromSQL(SqlRowSet rs){
        OrderDetail od = new OrderDetail();
        od.setId(rs.getInt("order_id"));
        if (rs.getString("order_date") != null)
        od.setOrderDate(new DateTime(
            DateTimeFormat.forPattern("dd/MM/yyyy")
                            .parseDateTime(rs.getString("order_date"))));
        od.setCustomerId(rs.getInt("customer_id"));
        od.setTotalPrice(rs.getDouble("Total_Price"));
        od.setCostPrice(rs.getDouble("Cost_Price"));
        return od;
    }

    public JsonObject toJsonObject() {
        return Json.createObjectBuilder()
                .add("order_id", getId())
                .add("order_date", getOrderDate() != null ? getOrderDate().toString() : "")
                .add("customer_id", getCustomerId())
                .add("Total_Price", getTotalPrice().toString()!= null ? getTotalPrice().toString() : "")
                .add("Cost_Price", getCostPrice().toString()!= null ? getCostPrice().toString() : "")
                .build();
    }

    
    @Override
    public String toString() {
        return "OrderDetail [id=" + id + ", orderDate=" + orderDate + ", customerId=" + customerId + ", totalPrice="
                + totalPrice + ", costPrice=" + costPrice + "]";
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public DateTime getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(DateTime orderDate) {
        this.orderDate = orderDate;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Double getCostPrice() {
        return costPrice;
    }
    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }
    public Double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    
    
}
