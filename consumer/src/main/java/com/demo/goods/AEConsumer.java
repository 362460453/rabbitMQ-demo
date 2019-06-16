package com.demo.goods;

import com.demo.Order;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * 备份交换器测试
 *
 * @author yanlin
 * @version v1.3
 * @date 2019-06-16 2:51 PM
 * @since v8.0
 **/
@Service
public class AEConsumer {

    @RabbitListener(queues = "good-queue")
    @RabbitHandler
    public void good(Order order) {
        System.out.println("成功路由到，这是：" + order.getName());
    }

    @RabbitListener(queues = "AE-queue")
    @RabbitHandler
    public void AE(Order order) {
        System.out.println("错误路由到，这是：" + order.getName());
    }

}
