package com.workshop23.ws23.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.workshop23.ws23.model.OrderDetail;

import static com.workshop23.ws23.repo.Queries.*;

import java.util.LinkedList;
import java.util.List;

@Repository
public class NWRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<OrderDetail> getOrderDetail(Integer orderId){
        
        List<OrderDetail> orderDetail = new LinkedList<>();
        // System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        // System.out.println(orderDetail);
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_NW_GET_ORDER_DETAIL,orderId);

        while(rs.next()){
            if(null == rs.getString("order_id")){
                System.out.println("Primary key is blank");
                continue;
            }
            orderDetail.add(OrderDetail.fromSQL(rs));
            // System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCccc");
            // System.out.println(orderDetail);
        }
        // System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        // System.out.println(orderDetail);;
       
        return orderDetail;

    } 
}
