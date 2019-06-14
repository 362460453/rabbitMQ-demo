package com.demo.goods;

import com.demo.Order;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author yanlin
 * @version v1.3
 * @date 2019-06-11 10:18 AM
 * @since v8.0
 **/
@Service
public class GoodsConsumer  {
    @RabbitListener(queues = "goods-change")
    @RabbitHandler
    public void goodsChange( Order order) {
        System.out.println("消费消息，商品名：" + order.getName());
    }

}
