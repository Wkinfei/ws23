package com.workshop23.ws23.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workshop23.ws23.model.OrderDetail;
import com.workshop23.ws23.repo.NWRepo;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping(path="/order/total", produces = MediaType.APPLICATION_JSON_VALUE)
public class NWController {
    @Autowired
    NWRepo nwRepo;
    
    @GetMapping(path="{orderId}")
    public ResponseEntity<String> getOrderDetail(@PathVariable Integer orderId) {
        List<OrderDetail> orderDetails = nwRepo.getOrderDetail(orderId);

        if(orderDetails.isEmpty()){
            return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body(Json.createObjectBuilder()
                        .add("message","OrderId: %s not found".formatted(orderId))
                        .build().toString());      
        }
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for (OrderDetail od : orderDetails) {
            arrBuilder.add(od.toJsonObject());
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Json.createObjectBuilder()
                .add("OrderDetail",arrBuilder)
                .build().toString());
    }
    
}
