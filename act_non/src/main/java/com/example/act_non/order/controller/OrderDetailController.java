package com.example.act_non.order.controller;

import com.example.act_non.order.model.OrderDetail;
import com.example.act_non.order.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderDetail")
@CrossOrigin("*")
public class OrderDetailController {
    @Autowired
    private IOrderDetailService orderDetailService;
    @PostMapping("/add")
    public ResponseEntity<?> addOrderDetail(@RequestParam("currentPrice") Double currentPrice,
                                            @RequestParam("quantity") Integer quantity,
                                            @RequestParam("idOrder") Long idOrder,
                                            @RequestParam("idProduct") Long idProduct) {
        if (idOrder != null || idProduct != null) {
            orderDetailService.addOrderDetail(currentPrice, quantity, idOrder, idProduct);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{idOrderDetail}")
    public ResponseEntity<?> getListOrderDetail(@PathVariable("idOrderDetail") Long idOrderDetail){
        if (idOrderDetail!= null){
            List<OrderDetail> orderDetails = orderDetailService.getListOrderDetail(idOrderDetail);
            return new ResponseEntity<>(orderDetails, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
