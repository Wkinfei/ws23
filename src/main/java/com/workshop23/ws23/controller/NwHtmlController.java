package com.workshop23.ws23.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.workshop23.ws23.model.OrderDetail;
import com.workshop23.ws23.repo.NWRepo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

@Controller
@RequestMapping(path="/order")
public class NwHtmlController {
    @Autowired
    NWRepo nwRepo;
    
    @GetMapping
    public String form(Model model){
        model.addAttribute("orderDetail", new OrderDetail());
        return "index";
    }

    @GetMapping("/total")
    public String result(@RequestParam(name="id") Integer orderId, Model model){
        List<OrderDetail> orderDetails = nwRepo.getOrderDetail(orderId);
        model.addAttribute("orderDetails", orderDetails);
        
        return "result";
    }
}
