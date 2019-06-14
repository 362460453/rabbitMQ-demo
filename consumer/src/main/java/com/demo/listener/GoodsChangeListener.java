package com.demo.listener;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yanlin
 * @version v1.3
 * @date 2019-06-11 10:27 AM
 * @since v8.0
 **/
@Configuration
public class GoodsChangeListener {

    private static final String queue = "goods-change";


    @Bean
    public Queue goodsChangeQueue() {
        return new Queue(queue);
    }


    @Bean
    FanoutExchange goodsChangeExchange() {
        return new FanoutExchange("GOODS");// 配置广播路由器
    }

    //交换器绑定队列
    @Bean
    Binding bindingExchangeGoodsChange(Queue goodsChangeQueue, FanoutExchange goodsChangeExchange) {
        return BindingBuilder.bind(goodsChangeQueue).to(goodsChangeExchange);
    }


}
