package com.demo.goods.controller;

import com.demo.Order;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanlin
 * @version v1.3
 * @date 2019-06-11 10:00 AM
 * @since v8.0
 **/
@RestController
public class GoodsController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping("/test/{id}")
    public String goodsTest(@PathVariable Integer id) {
        Order order = new Order(id, "xx");
        amqpTemplate.convertAndSend("GOODS", "", order);
        System.out.println(order);
        return "成功";
    }

}
