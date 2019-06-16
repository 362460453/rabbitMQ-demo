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
 * @date 2019-06-16 2:30 PM
 * @since v8.0
 **/
@RestController
public class AEController {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping("/ae/{id}")
    public String AETest(@PathVariable Integer id) {
        Order order = new Order(id, "现任");
        amqpTemplate.convertAndSend("TestAE", "abc", order);
        order.setName("备胎");
        amqpTemplate.convertAndSend("TestAE", "a", order);
        System.out.println(order);
        return "成功";
    }
}
