package com.demo.goods;

import com.demo.Order;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 延迟消费
 *
 * @author yanlin
 * @version v1.3
 * @date 2019-06-16 10:01 PM
 * @since v8.0
 **/
@Service
public class DelayConsumer {

    /**
     * 延迟消费方法
     *
     * @param order
     */
    @RabbitListener(queues = "delay_queue")
    @RabbitHandler
    public void delay(Order order) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println("------" + sdf.format(new Date()) + "------");
        System.out.println("请观察页面返回的时间与上面打印时间对比");

        System.out.println("这是延迟10s消费的消息：" + order.getName());
    }

}
