package com.demo.goods.controller;

import com.demo.Order;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yanlin
 * @version v1.3
 * @date 2019-06-16 10:03 PM
 * @since v8.0
 **/
@RestController
public class DelayController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping("/delay/{id}")
    public String delayTest(@PathVariable Integer id) {
        Order order = new Order(id, "我等了10s");
        amqpTemplate.convertAndSend("normal_exchange", "normal_key", order);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dateString = sdf.format(new Date());
        return "请求时间" + dateString;
    }
}
